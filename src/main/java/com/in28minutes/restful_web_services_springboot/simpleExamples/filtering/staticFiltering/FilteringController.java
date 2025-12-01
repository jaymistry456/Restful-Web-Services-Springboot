package com.in28minutes.restful_web_services_springboot.simpleExamples.filtering.staticFiltering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
    @GetMapping("/filtering")
    public Filtering getFilteringData() {
        return new Filtering("val1", "val2", "val3");
    }
}
