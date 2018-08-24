package by.rekuts.travelagency.view.controller;


import by.rekuts.travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class ImportController {

    @Autowired
    private TourService tourService;

    @GetMapping("/import")
    public String index() {
        return "import";
    }

    @PostMapping("/import")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to import");
            return "redirect:importStatus";
        }

        if (! file.getOriginalFilename().endsWith(".csv")) {
            redirectAttributes.addFlashAttribute("message", "Please select .csv file to import");
            return "redirect:importStatus";
        }

        try {
            String bytes = new String(file.getBytes());
            tourService.importCsvTours(bytes);


            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/importStatus";
    }

    @GetMapping("/importStatus")
    public String uploadStatus() {
        return "importStatus";
    }

}
