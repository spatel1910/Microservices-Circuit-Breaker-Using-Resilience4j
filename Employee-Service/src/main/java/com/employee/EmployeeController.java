package com.employee;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class EmployeeController {
    @Autowired
    private WebClient webClient;
    @CircuitBreaker(name="xyz",fallbackMethod = "fallBackGetAddress")
   // @GetMapping("/employee") This is disable since any other Getmapping is enable i.e. RetryController
    public String getEmpWithAddress(){
        String employee="Sandeep Kumar Patel";
        Mono<String> address= (webClient.get().uri("/address").retrieve().bodyToMono(String.class));
        return "Employee: "+employee+" Address: "+address.block();

    }

    public String fallBackGetAddress(Exception exception){
        return "Address Service is Down sending default response Rewa,MP";
    }
}
