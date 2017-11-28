package com.example;


import java.net.InetAddress;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;


import lombok.Data;
@Data
@ConfigurationProperties(prefix="foo")
public class FooProperties {
 private boolean enabled;
 private InetAddress remoteAddress;
 private List array;
 private final Security security;
 
 @Data
 public static class Security {
 private String username;
 private String password;

 }
}
