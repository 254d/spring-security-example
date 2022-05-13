package com.example.model;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomerDetails implements UserDetails {

  private Customer customer;

  public CustomerDetails(Customer customer) {
    this.customer = customer;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return AuthorityUtils.createAuthorityList(customer.getRole().split(","));
  }

  public String getId() {
    return this.customer.getId();
  }

  @Override
  public String getPassword() {
    return this.customer.getPassword();
  }

  @Override
  public String getUsername() {
    return this.customer.getName();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public String toString() {
    return String.format(
        "CustomerDetails(id=%s, username=%s, password=%s, isAccountNonExpired=%b,"
            + " isAccountNonLocked=%b, isCredentialsNonExpired=%b, isEnabled=%b)",
        getId(), getUsername(), getPassword(), isAccountNonExpired(), isAccountNonLocked(),
        isCredentialsNonExpired(), isEnabled());
  }
}
