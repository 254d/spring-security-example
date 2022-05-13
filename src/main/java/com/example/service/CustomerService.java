package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.model.Customer;
import com.example.model.CustomerDetails;
import com.example.repository.CustomerRepository;

@Service
public class CustomerService implements UserDetailsService {
  @Autowired
  CustomerRepository repo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Customer customer = repo.findById(username);
    if (null == customer) {
      throw new UsernameNotFoundException(username + "is not found.");
    }
    return new CustomerDetails(customer);
  }
}
