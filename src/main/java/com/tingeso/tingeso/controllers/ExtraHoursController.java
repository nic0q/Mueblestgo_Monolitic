package com.tingeso.tingeso.controllers;

import com.tingeso.tingeso.entities.ExtraHoursForm;
import com.tingeso.tingeso.services.ExtraHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    model.addAttribute("extra", new ExtraHoursForm());
    return "extraHoursForm";
  }

  @PostMapping("/extra-hours-form")
  public String extraHoursSubmit(
    @ModelAttribute ExtraHoursForm extra_form,
    Model model
  ) {
    model.addAttribute("extra", extra_form);
    System.out.println(extra_form.toString());
    extraHoursService.verify_form(extra_form);
    return "extraHoursForm";
  }
}
