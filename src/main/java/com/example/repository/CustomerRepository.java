package com.example.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.example.model.Customer;

@Mapper
public interface CustomerRepository {
  @Select("SELECT * FROM customer WHERE id=#{id}")
  Customer findById(String id);
}
