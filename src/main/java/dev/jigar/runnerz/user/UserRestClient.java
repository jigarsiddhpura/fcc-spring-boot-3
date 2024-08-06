package dev.jigar.runnerz.user;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import java.util.*;

@Component
public class UserRestClient {
    
    private final RestClient restClient;



    public UserRestClient(RestClient.Builder builder) {
        JdkClientHttpRequestFactory jdkHttpClientResourceFactory = new JdkClientHttpRequestFactory();
        jdkHttpClientResourceFactory.setReadTimeout(5000);

        this.restClient = builder
                    .baseUrl("https://jsonplaceholder.typicode.com")
                    .requestFactory(jdkHttpClientResourceFactory) // optional - changes the http lib used under the hood - customizing underlying http request factory
                    .defaultHeader("User-Agent", "Spring-5-webclient") // optional
                    .build();
    }

    public List<User> findAll() {
        return restClient.get()
                    .uri("/users")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});
    }

    public User findById(Integer _id) {
        return restClient.get()
                    .uri("/users/{id}", _id)
                    .retrieve()
                    .body(User.class);
    }

}
