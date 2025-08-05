package com.carservice.cardemo.controller;

import com.carservice.cardemo.dto.appUser.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;


@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    Environment environment;

    @PostMapping("/generateJWT")
    public String generateJWT(@RequestBody UserCredentials userCredentials) throws UnknownHostException {
        String url = "http://" + InetAddress.getLoopbackAddress().getHostAddress() + ":" + environment.getProperty("local.server.port") + "/auth";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<UserCredentials> request = new HttpEntity<>(userCredentials);
        ResponseEntity<UserCredentials> response = restTemplate.exchange(url, HttpMethod.POST, request, UserCredentials.class);
        return response.getHeaders().get("Authorization").toString();
    }
}
