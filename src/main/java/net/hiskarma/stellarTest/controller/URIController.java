package net.hiskarma.stellarTest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class URIController {
    @RequestMapping("/loginSuccess")
    public String index() {
        return "LoginSuccess(Google): Hello Spring Boot!";
    }
}
