package com.in28minutes.restful_web_services_springboot.simpleExamples;

public class HelloWorldBean {
    private String message;

    public HelloWorldBean(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return "HelloWorldBean{" +
                "message='" + message + '\'' +
                '}';
    }
}
