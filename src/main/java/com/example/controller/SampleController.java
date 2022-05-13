package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.model.CustomerDetails;
import com.example.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SampleController {
  @Autowired
  CustomerRepository repo;

  @GetMapping("/")
  public String index(@AuthenticationPrincipal CustomerDetails customerDetails, Model model) {
    log.info(customerDetails.toString());
    return "index";
  }

  @GetMapping("/admin")
  public String admin(Model model) {
    return "admin";
  }
}
