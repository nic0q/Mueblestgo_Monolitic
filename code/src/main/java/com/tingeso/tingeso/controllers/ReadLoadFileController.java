package com.tingeso.tingeso.controllers;

import com.tingeso.tingeso.services.ReadilyService;
import com.tingeso.tingeso.services.UploadService;
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
  
  @GetMapping("/load-txt")
  public String load_txt() {
    return "loadfile";
  }
  @PostMapping("/save-txt")
  public String save_txt( @RequestParam("archivos") MultipartFile file, RedirectAttributes ms) {
    upload.save(file);
    ms.addFlashAttribute("mensaje", "Archivo guardado correctamente!!");
    return "redirect:/load-txt";
  }
  @GetMapping("/read-data")
  public String readData() throws FileNotFoundException, ParseException {
    readfile.readFile();
    return "redirect:/";
  }
}
