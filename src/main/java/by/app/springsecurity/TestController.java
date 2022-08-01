package by.app.springsecurity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Vladislav Domaniewski
 */

@Controller
public class TestController {
    @GetMapping("/world")
    public String testHello() {
        return "hello";
    }
}
