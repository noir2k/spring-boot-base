package net.hiskarma.stellarTest.controller;

import net.hiskarma.stellarTest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserController {
    @Autowired
    private UserRepository userRepository;
}
