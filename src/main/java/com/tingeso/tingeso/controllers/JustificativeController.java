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
  static String justificativeView = "justificativeForm";
  @Autowired
  JustificativeService justificativeService;
  
  @Autowired
  EmployeeService employeeService;
  
  @GetMapping("/justificative-form")
  public String justificativeForm(Model model) {
    model.addAttribute(justificativeView, new JustificativeEntity());
    return justificativeView;
  }
  @PostMapping("/justificative-form")
  public String justificativeFormSubmit(@Valid @ModelAttribute("justificativeForm") JustificativeEntity justificativeForm, BindingResult result, Model model) {
    if(result.hasErrors()){
      model.addAttribute(justificativeView,  justificativeForm);
      return justificativeView;
    }
    if(employeeService.exists_employee(justificativeForm.getRut_employee())){
      justificativeService.save_justificative(justificativeForm);
      model.addAttribute("success",  "Justificativo ingresado");
      return justificativeView;
    }
    model.addAttribute("wrong_user",  "El Usuario no existe");
    return justificativeView;
  }
}