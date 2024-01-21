package com.employee;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class EmployeeControllerRateLimiter {
    @RateLimiter(name="employee-rate-limiter", fallbackMethod ="fallBackEmployeeService" )
   // @GetMapping("/employee")
    public String getEmpWithAddress(){
        String employee="Sandeep Kumar Patel";
               return "Employee: "+employee;

    }

    public String fallBackEmployeeService(Exception exception){
        return "Capacity reached No more calls allowed try after some time";
    }
}
