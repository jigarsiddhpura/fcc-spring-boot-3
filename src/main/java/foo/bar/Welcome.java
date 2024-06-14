package foo.bar;

import org.springframework.stereotype.Component;

@Component
public class Welcome {
    public String getWelcomeMsg() {
        return "Hello World";
    }
}
