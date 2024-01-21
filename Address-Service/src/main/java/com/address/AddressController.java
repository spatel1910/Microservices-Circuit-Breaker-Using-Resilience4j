package com.address;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

    @GetMapping("/address")
    public String getAddress()                               {
        return "86/k Village Tikuri 32 Rewa MP";
    }
}


