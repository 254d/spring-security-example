package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.model.Customer;
import com.example.model.CustomerDetails;
import com.example.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SampleController {
  @Autowired
  CustomerRepository repo;

  @Autowired
  PasswordEncoder passwordEncoder;

  @GetMapping("/")
  public String index(@AuthenticationPrincipal CustomerDetails customerDetails, Model model) {
    log.info(customerDetails.toString());
    return "index";
  }

  @GetMapping("/admin")
  public String admin(Model model) {
    Customer customer = new Customer();
    model.addAttribute("customer", customer);
    return "admin";
  }

  @PostMapping("/admin/user-add")
  public String userAdd(@ModelAttribute Customer customer, Model model) {
    if (null == repo.findById(customer.getId())) {
      customer.setPassword(passwordEncoder.encode(customer.getPassword()));
      customer.setRole("ROLE_ADMIN");
      repo.insert(customer);
      model.addAttribute("resultMessage", customer.getId() + "を登録しました。");
    } else {
      model.addAttribute("resultMessage", customer.getId() + "はすでに存在します。");
    }

    return "admin";
  }
}
