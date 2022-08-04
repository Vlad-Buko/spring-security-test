package by.app.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Vladislav Domaniewski
 */

@Controller
public class TestController {
    @GetMapping("/world")
    public String testHello() {
        return "hello";
    }

    @GetMapping("/admin")
    public String adminPAge() {
        return "admin";
    }
}
