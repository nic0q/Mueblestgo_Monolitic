package com.tingeso.tingeso.controllers;
  
import com.tingeso.tingeso.services.ExtraHoursService;
import com.tingeso.tingeso.services.JustificativeService;
import com.tingeso.tingeso.services.ReadilyService;
import com.tingeso.tingeso.services.UploadService;
import com.tingeso.tingeso.services.WorkedDaysService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;
import java.text.ParseException;

@Controller
@RequestMapping
public class ReadLoadFileController {
  @Autowired
  private UploadService upload;
  @Autowired
  private ReadilyService readfile;
  @Autowired
  private ExtraHoursService extraHoursService;
  @Autowired
  private WorkedDaysService workedDaysService;
  @Autowired
  private JustificativeService justificativeService;

  @GetMapping("/load-txt")
  public String loadTxt() {
    return "loadfile";
  }
  @PostMapping("/save-txt")
  public String saveTxt( @RequestParam("archivos") MultipartFile file, RedirectAttributes ms) throws FileNotFoundException, ParseException {
    upload.save(file);
    if(!readfile.nombre_correcto(file.getOriginalFilename())){
      ms.addFlashAttribute("mensaje", "El archivo es del tipo DATA.txt o data.txt");
      return "redirect:/load-txt";
    }
    ms.addFlashAttribute("mensaje", "Archivo leído correctamente");
    justificativeService.deleteAll();
    extraHoursService.deleteAll();
    workedDaysService.deleteAll();
    readfile.readFile();
    return "redirect:/load-txt";
  }
}
