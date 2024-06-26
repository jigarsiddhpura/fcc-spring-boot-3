package dev.jigar.runnerz.run;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RunController {
    
    @GetMapping("/home")
    public String home() {
        return "Hello guyzz!";
    }
}
