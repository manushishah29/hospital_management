package com.softvan.hospitalManagement.security;


import com.softvan.hospitalManagement.jwt.JwtTokenFilterConfigurer;
import com.softvan.hospitalManagement.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurity {

    private final JwtTokenProvider jwtTokenProvider;

//    @Primary
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return web -> web.ignoring().antMatchers(this.getPublicUrls());
//    }

    @Primary
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        // No session will be created or used by spring security.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // set Expression Handler
        http.authorizeRequests()
                .expressionHandler(webSecurityExpressionHandler());
        // Entry points
        http.authorizeRequests()
                .antMatchers(this.getPublicUrls())
                .permitAll()
                // Disallow everything else..
                .anyRequest()
                .authenticated();
        // Apply JWT
        http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

//        http.addFilterBefore(new JwtTokenFilter(jwtTokenProvider), JwtTokenFilter.class);

        // Optional, if you want to test the API from a browser
        http.httpBasic().disable();
        return http.build();
    }

//    @Bean
//    public RoleHierarchyImpl roleHierarchy() {
//        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
////        roleHierarchy.setHierarchy("ROLE_SUPERADMIN > ROLE_ADMIN > ROLE_USER");
//        roleHierarchy.setHierarchy(Role.getRoleHierarchy());
//        return roleHierarchy;
//    }

    private SecurityExpressionHandler<FilterInvocation> webSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
        return defaultWebSecurityExpressionHandler;
    }

    public String[] getPublicUrls() {
        return new String[]{
                "/authenticate/**",
                "/register/**",
                "/**/swagger-ui/**",
                "/api/auth/**",
                "/v3/api-docs/**",
                "/configuration/ui",
                "/swagger-resources",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "/swagger-resources/configuration/ui",
                "/swagger-resources/configuration/security",
                "/**/actuator/**"};
    }

}
