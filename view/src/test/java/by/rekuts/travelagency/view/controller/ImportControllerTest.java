package by.rekuts.travelagency.view.controller;

import by.rekuts.travelagency.view.confuguration.TestSecurityConfiguration;
import by.rekuts.travelagency.view.confuguration.ViewTestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ViewTestConfig.class, TestSecurityConfiguration.class})
@WebAppConfiguration
@ComponentScan(basePackages = {"by.rekuts.**"})
public class ImportControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .alwaysDo(print())
                .build();
    }

    @Test
    public void singleFileUploadTest_post() throws Exception {
        var file = new MockMultipartFile("file", "filename.csv", null, "bar".getBytes());
        mvc.perform(fileUpload("/import-post")
                .file(file)
                .with(user("user").roles("ADMIN")))
                .andExpect(status().is(302))
                .andExpect(flash().attribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'"))
                .andExpect(view().name("redirect:/importStatus"));
    }

    @Test
    public void singleFileUploadTestFalse_post_emptyFile() throws Exception {
        var file = new MockMultipartFile("file", "filename.csv", null, "".getBytes());
        mvc.perform(fileUpload("/import-post")
                .file(file)
                .with(user("user").roles("ADMIN")))
                .andExpect(status().is(302))
                .andExpect(flash().attribute("message", "Please select a file to import"))
                .andExpect(view().name("redirect:/importStatus"));
    }

    @Test
    public void singleFileUploadTestFalse_post_wrongFileType() throws Exception {
        var file = new MockMultipartFile("file", "filename.json", null, "bar".getBytes());
        mvc.perform(fileUpload("/import-post")
                .file(file)
                .with(user("user").roles("ADMIN")))
                .andExpect(status().is(302))
                .andExpect(flash().attribute("message", "Please select .csv file to import"))
                .andExpect(view().name("redirect:/importStatus"));
    }

    @Test
    public void singleFileUploadTestFalse_post_noRights() throws Exception {
        var file = new MockMultipartFile("file", "filename.csv", null, "bar".getBytes());
        mvc.perform(fileUpload("/import-post")
                .file(file)
                .with(user("user")))
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/error"));
    }

}
