package com.tingeso.tingeso.controllers;
import javax.validation.Valid;

import com.tingeso.tingeso.entities.JustificativeEntity;
import com.tingeso.tingeso.services.EmployeeService;
import com.tingeso.tingeso.services.JustificativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class JustificativeController {
  @Autowired
  JustificativeService justificativeService;
  
  @Autowired
  EmployeeService employeeService;
  
  @GetMapping("/justificative-form")
  public String justificativeForm(Model model) {
    model.addAttribute("justificative_form", new JustificativeEntity());
    return "justificativeForm";
  }
  @PostMapping("/justificative-form")
  public String justificativeFormSubmit(@Valid @ModelAttribute("justificative_form") JustificativeEntity justificative_form, BindingResult result, Model model) {
    if(result.hasErrors()){
      model.addAttribute("justificative_form",  justificative_form);
      return "justificativeForm";
    }
    if(employeeService.exists_employee(justificative_form.getRut_employee())){
      justificativeService.save_justificative(justificative_form);
      model.addAttribute("success",  "Justificativo ingresado");
      return "justificativeForm";
    }
    model.addAttribute("wrong_user",  "El Usuario no existe");
    return "justificativeForm";
  }
}