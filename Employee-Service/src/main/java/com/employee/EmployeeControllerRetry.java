package com.employee;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class EmployeeControllerRetry {
    @Autowired
    private WebClient webClient;
    @Retry(name="xyz-retry",fallbackMethod = "fallBackGetAddress")
   // @GetMapping("/employee")
    public String getEmpWithAddress(){
        String employee="Sandeep Kumar Patel";
        System.out.println("Retrying");
        Mono<String> address= (webClient.get().uri("/address").retrieve().bodyToMono(String.class));
        return "Employee: "+employee+" Address: "+address.block();

    }

    public String fallBackGetAddress(Exception exception){
        return "Address Service is Down sending default response Rewa,MP";
    }
}
