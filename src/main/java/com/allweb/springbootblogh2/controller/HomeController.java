package com.allweb.springbootblogh2.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
@CrossOrigin
public class HomeController {

  @GetMapping
  public ModelAndView redirect() {
    return new ModelAndView("redirect:swagger-ui.html");
  }

}
