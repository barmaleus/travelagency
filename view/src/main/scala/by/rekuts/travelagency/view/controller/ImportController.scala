package by.rekuts.travelagency.view.controller

import java.io.IOException

import by.rekuts.travelagency.service.TourService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{PostMapping, RequestParam}
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class ImportController @Autowired() (tourService: TourService) {
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping(Array("/import-post"))
  def singleFileUpload(@RequestParam("file") file: MultipartFile, redirectAttributes: RedirectAttributes): String = {
    if (file.isEmpty) {
      redirectAttributes.addFlashAttribute("message", "Please select a file to import")
      return "redirect:/importStatus"
    }
    if (!file.getOriginalFilename.endsWith(".csv")) {
      redirectAttributes.addFlashAttribute("message", "Please select .csv file to import")
      return "redirect:/importStatus"
    }
    try {
      val bytes = new String(file.getBytes)
      tourService.importCsvTours(bytes)
      redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename + "'")
    } catch {
      case e: IOException =>
        e.printStackTrace()
    }
    "redirect:/importStatus"
  }
}
