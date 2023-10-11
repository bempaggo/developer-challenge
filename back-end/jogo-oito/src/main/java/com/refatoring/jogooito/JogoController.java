package com.refatoring.jogooito;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JogoController {
  
  @GetMapping("/")
  public String allChanceController () {
    return "all Chance from game";
  }
}
