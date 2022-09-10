package com.tingeso.tingeso.controllers;

import com.tingeso.tingeso.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class LoadFileController {
  @Autowired
  private UploadService upload;

  @PostMapping("/load-txt")
  public String carga( @RequestParam("archivos") MultipartFile file, RedirectAttributes ms) {
    upload.save(file);
    ms.addFlashAttribute("mensaje", "Archivo guardado correctamente!!");
    return "redirect:/";
  }
}
