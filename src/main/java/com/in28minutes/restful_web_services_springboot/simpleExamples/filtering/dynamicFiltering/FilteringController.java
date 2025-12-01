package com.in28minutes.restful_web_services_springboot.simpleExamples.filtering.dynamicFiltering;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {
    // field1 and field3 only
    @GetMapping("/filtering")
    @JsonView(Views.Filter1.class)
    public Filtering getFilteringData() {
        return new Filtering("val1", "val2", "val3");
    }

    // field2 and field3 only
    @GetMapping("/filtering-list")
    @JsonView(Views.Filter2.class)
    public List<Filtering> getFilteringListData() {
        return Arrays.asList(
                new Filtering("val1", "val2", "val3"),
                new Filtering("val4", "val5", "val6")
        );
    }
}
