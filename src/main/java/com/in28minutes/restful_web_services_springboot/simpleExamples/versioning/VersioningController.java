package com.in28minutes.restful_web_services_springboot.simpleExamples.versioning;

import com.in28minutes.restful_web_services_springboot.simpleExamples.versioning.v1.PersonV1;
import com.in28minutes.restful_web_services_springboot.simpleExamples.versioning.v2.Name;
import com.in28minutes.restful_web_services_springboot.simpleExamples.versioning.v2.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {
    // URI
    @GetMapping("/v1/person")
    public PersonV1 getPersonV1URI() {
        return new PersonV1("John Doe");
    }

    @GetMapping("/v2/person")
    public PersonV2 getPersonV2URI() {
        return new PersonV2(new Name("John", "Doe"));
    }

    // Params
    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getPersonV1Params() {
        return new PersonV1("John Doe");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getPersonV2Params() {
        return new PersonV2(new Name("John", "Doe"));
    }

    // Headers
    @GetMapping(path = "/person", headers = "X-API-VERSION=1")
    public PersonV1 getPersonV1Headers() {
        return new PersonV1("John Doe");
    }

    @GetMapping(path = "/person", headers = "X-API-VERSION=2")
    public PersonV2 getPersonV2Headers() {
        return new PersonV2(new Name("John", "Doe"));
    }

    // Media type
    @GetMapping(path = "/person", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getPersonV1MediaType() {
        return new PersonV1("John Doe");
    }

    @GetMapping(path = "/person", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getPersonV2MediaType() {
        return new PersonV2(new Name("John", "Doe"));
    }
}
