package com.employee;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeControllerBulkHead {


    @Bulkhead(name="employee-bulkhead", fallbackMethod ="fallBackEmployeeService" )
    @GetMapping("/employee")
    public String getEmpWithAddress(){
        String employee="Sandeep Kumar Patel";
        System.out.println("Sandeep kumar patel");
        return "Employee: "+employee;

    }

    public String fallBackEmployeeService(Exception exception){
        return "No of concurrent calls capacity reached No more calls allowed try after some time";
    }
}
