package com.in28minutes.restful_web_services_springboot.app01.jpa;

import com.in28minutes.restful_web_services_springboot.app01.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPARepository extends JpaRepository<User, Integer> {
}
