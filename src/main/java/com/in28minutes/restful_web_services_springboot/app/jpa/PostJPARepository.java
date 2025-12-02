package com.in28minutes.restful_web_services_springboot.app.jpa;

import com.in28minutes.restful_web_services_springboot.app.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJPARepository extends JpaRepository<Post, Integer> {
}
