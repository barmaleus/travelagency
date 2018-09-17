package by.rekuts.travelagency.view;

import by.rekuts.travelagency.domain.Country;
import by.rekuts.travelagency.repository.CountrySpecification;
import by.rekuts.travelagency.service.CountryService;
import by.rekuts.travelagency.view.controller.MainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ViewTestConfig.class})
@WebAppConfiguration
@ComponentScan(basePackages = {"by.rekuts.**"})
public class ControllerTest {
    @Autowired
    MainController mainController;

    private MockMvc mvc;

    @Test
    public void testHomePage() throws Exception {
        mvc = standaloneSetup(mainController).build();
        mvc.perform(get("/"))
                .andExpect(view().name("list/tours"));
    }

    @Test
    public void testGetCountries() throws Exception {
        ArrayList<Country> list = new ArrayList<>(10);
        CountryService mockService = mock(CountryService.class);
        when(mockService.getList(new CountrySpecification())).thenReturn(list);
        mvc = standaloneSetup(mainController).setSingleView(new InternalResourceView("/WEB-INF/views/list/countries.ftl")).build();
        mvc.perform(get("list/countries"));
    }
}
