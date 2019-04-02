package by.rekuts.travelagency.view.controller

import by.rekuts.travelagency.view.configuration.{TestSecurityConfiguration, ViewTestConfig}
import org.junit.{Before, Test}
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.mock.web.MockMultipartFile
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.{anonymous, user}
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.{fileUpload, post}
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.{flash, forwardedUrl, status, view}
import org.springframework.test.web.servlet.setup.{DefaultMockMvcBuilder, MockMvcBuilders}
import org.springframework.web.context.WebApplicationContext

@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(classes = Array(classOf[ViewTestConfig], classOf[TestSecurityConfiguration]))
@WebAppConfiguration
@ComponentScan(basePackages = Array("by.rekuts.**"))
class ImportControllerTest {
  @Autowired
  private var context: WebApplicationContext = _

  private var mvc: MockMvc = _

  @Before
  def setup(): Unit = {
    mvc = MockMvcBuilders
      .webAppContextSetup(context)
      .apply[DefaultMockMvcBuilder](springSecurity)
      .alwaysDo[DefaultMockMvcBuilder](print())
      .build
  }

  @Test
  @throws[Exception]
  def signUpTest(): Unit = {
    mvc.perform(post("/reg-user").`with`(anonymous))
      .andExpect(status.isFound)
      .andExpect(view.name("redirect:/tours"))
  }

  @Test
  @throws[Exception]
  def signUpTestFalse(): Unit = {
    mvc.perform(post("/reg-user").`with`(user("user")))
      .andExpect(status.isForbidden)
      .andExpect(forwardedUrl("/error"))
  }

  @Test
  @throws[Exception]
  def singleFileUploadTest_post(): Unit = {
    val file = new MockMultipartFile("file", "filename.csv", null, "bar".getBytes)
    mvc.perform(fileUpload("/import-post").file(file).`with`(user("user").roles("ADMIN"))).andExpect(status.is(302)).andExpect(flash.attribute("message", "You successfully uploaded '" + file.getOriginalFilename + "'")).andExpect(view.name("redirect:/importStatus"))
  }

  @Test
  @throws[Exception]
  def singleFileUploadTestFalse_post_emptyFile(): Unit = {
    val file = new MockMultipartFile("file", "filename.csv", null, "".getBytes)
    mvc.perform(fileUpload("/import-post").file(file).`with`(user("user").roles("ADMIN"))).andExpect(status.is(302)).andExpect(flash.attribute("message", "Please select a file to import")).andExpect(view.name("redirect:/importStatus"))
  }

  @Test
  @throws[Exception]
  def singleFileUploadTestFalse_post_wrongFileType(): Unit = {
    val file = new MockMultipartFile("file", "filename.json", null, "bar".getBytes)
    mvc.perform(fileUpload("/import-post").file(file).`with`(user("user").roles("ADMIN"))).andExpect(status.is(302)).andExpect(flash.attribute("message", "Please select .csv file to import")).andExpect(view.name("redirect:/importStatus"))
  }

  @Test
  @throws[Exception]
  def singleFileUploadTestFalse_post_noRights(): Unit = {
    val file = new MockMultipartFile("file", "filename.csv", null, "bar".getBytes)
    mvc.perform(fileUpload("/import-post").file(file).`with`(user("user"))).andExpect(status.isForbidden).andExpect(forwardedUrl("/error"))
  }
}
