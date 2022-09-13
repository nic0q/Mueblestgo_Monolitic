package com.tingeso.tingeso.controllers;

import com.tingeso.tingeso.entities.ExtraHoursForm;
import com.tingeso.tingeso.services.ExtraHoursService;

import javax.validation.Valid;

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
public class ExtraHoursController {
  @Autowired
  ExtraHoursService extraHoursService;
  @GetMapping("/extra-hours-form")
  public String extraHoursForm(Model model) {
    model.addAttribute("extra_form", new ExtraHoursForm());
    return "extraHoursForm";
  }
  @PostMapping("/extra-hours-form")
  public String extraHoursSubmit(@Valid @ModelAttribute("extra_form") ExtraHoursForm extra_form, BindingResult result, Model model) {
    if(result.hasErrors()){
      model.addAttribute("extra_form",  extra_form);
      return "extraHoursForm";
    }
    if(extraHoursService.exists_employee(extra_form)){
      extraHoursService.save_extra_hours(extra_form);
      model.addAttribute("success",  "Horas extra ingresadas");
      return "extraHoursForm";
    }
    model.addAttribute("wrong_user",  "El Usuario no existe");
    return "extraHoursForm";
  }
}