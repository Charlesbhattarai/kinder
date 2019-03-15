package com.seva.kinder.Configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if (auth.getAuthority().equals("admin")) {
                httpServletResponse.sendRedirect("/signinsuccess");
            System.out.println("Client-manager");

        }
          else  if (auth.getAuthority().equals("user")) {
                httpServletResponse.sendRedirect("/signinsuccess");
                System.out.println("Client-manager");
            }

//        if ("Client-user".equals(auth.getAuthority())) {
//            response.sendRedirect("/admin/dashboard");
//            System.out.println("Client-user");
//        }
//        if ("System-admin".equals(auth.getAuthority())) {
//            response.sendRedirect("/admin/dashboard");
//            System.out.println("System-admin");
//        }
//        if ("Client-admin".equals(auth.getAuthority())) {
//            response.sendRedirect("/admin/dashboard");
//            System.out.println("Client-admin");
//        }
//        if ("USER".equals(auth.getAuthority())){
//            response.sendRedirect("/dashboard");
//            System.out.println("User");
//    }
        }
    }
}