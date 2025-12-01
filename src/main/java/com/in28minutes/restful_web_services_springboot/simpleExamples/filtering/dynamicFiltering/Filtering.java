package com.in28minutes.restful_web_services_springboot.simpleExamples.filtering.dynamicFiltering;

import com.fasterxml.jackson.annotation.JsonView;

public class Filtering {
    @JsonView(Views.Filter1.class)
    private String field1;
    @JsonView(Views.Filter2.class)
    private String field2;
    @JsonView({ Views.Filter1.class, Views.Filter2.class })
    private String field3;

    public Filtering(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }
}
