package br.com.fiap.livros.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.fiap.livros.security.JwtAuthenticationEntryPoint;
import br.com.fiap.livros.security.JwtRequestFilter;
import br.com.fiap.livros.security.JwtUserService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtRequestFilter jwtRequestFilter;
    private final JwtUserService jwtUserService;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                            JwtRequestFilter jwtRequestFilter,
                            JwtUserService jwtUserService,
                            PasswordEncoder passwordEncoder) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtRequestFilter = jwtRequestFilter;
        this.jwtUserService = jwtUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // @formatter: off

        auth.userDetailsService(this.jwtUserService)
            .passwordEncoder(this.passwordEncoder);

            // auth.inMemoryAuthentication()
        //     .withUser("admin")
        //     .password("{noop}admin")
        //     .roles("ADMIN","USER")
        //     .and()
        //     .withUser("user")
        //     .password("{noop}user")
        //     .roles("USER");

        // @formatter: on
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter: off

        http
            .authorizeRequests()
            .antMatchers("/users").permitAll()
            .anyRequest().authenticated()
            .and()
                .exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and() 
                .csrf().disable()
                .formLogin().disable();

        http.addFilterBefore(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        // http.httpBasic()
        //     .and()
        //         .authorizeRequests()
        //         .antMatchers(HttpMethod.POST, "/books").hasRole("ADMIN")
        //     .and()
        //         .csrf().disable()
        //         .formLogin().disable();

        // @formatter: on
    }
}