package com.tingeso.tingeso.controllers;

import com.tingeso.tingeso.models.ExtraHoursEntity;

// import org.springframework.web.bind.annotation.RequestParam;

import com.tingeso.tingeso.services.EmployeeService;
import com.tingeso.tingeso.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @Autowired
  private UploadService upload;

  @GetMapping("/employees")
  public String getEmployees(Model model) {
    model.addAttribute("employees", employeeService.getEmployees());
    return "greeting";
  }

  @GetMapping("/")
  public String home() {
    return "home";
  }

  @GetMapping("/insertTest")
  public String insertTest() {
    employeeService.insertEmployee();
    return "greeting";
  }

  @PostMapping("/cargar")
  public String carga(
    @RequestParam("archivos") MultipartFile file,
    RedirectAttributes ms
  ) {
    upload.save(file);
    ms.addFlashAttribute("mensaje", "Archivo guardado correctamente!!");
    return "redirect:/";
  }
  @PostMapping("/extra_hours_form")
  public String submitForm(@ModelAttribute("extra_hours") ExtraHoursEntity extra_hours) {
    System.out.println(extra_hours);
    return "register_success";
}
}
