package dev.jigar.runnerz.user;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

// 🔴 THIS FILE REPLACES THE BOILER PLATE NEEDED IN `UserRestClient.java`

public interface UserHttpClient {
    
    @GetExchange("/users")
    List<User> findAll();

    @GetExchange("/users/{id}")
    User findById(@PathVariable Integer id);

}
