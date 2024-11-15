package com.example.chatapp.config;

import java.util.Arrays;
import java.util.List;

public class WhiteListUrls {
  public static final List<String> WHITELIST_PATHS = Arrays.asList(
      "/api/auth/login",
      "/api/auth/register",
      "/",
      "/h2-console/**",
      "/test",
      "/docs/**",
      "/swagger-ui/**",
      "/swagger-ui.html",
      "/v3/api-docs/**"
  );
}
