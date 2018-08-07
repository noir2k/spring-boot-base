package io.colligence.module.common.controller;

import io.colligence.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CrossOrigin
@RestController
public class UserController {
    @RequestMapping("/my_oauth_info")
    public Principal user(Principal principal) {
        return principal;
    }
}
