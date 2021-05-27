package nl.abnamro.com.recipesweb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SecurityConfig is configuration class to config basic authentication and authorization.
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] AUTH_LIST = {
            "/v3/api-docs",
            "/v2/api-docs",
            "/swagger-ui.html",
            "/swagger-ui-custom.html"
    };


    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(AUTH_LIST).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        http.headers().frameOptions().disable();
    }

    /**
     * Global Authentication configuration.
     * @param authenticationManagerBuilder
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception
    {
        authenticationManagerBuilder.inMemoryAuthentication()
                                .withUser("user")
                                .password(passwordEncoder().encode("user"))
                                .roles("USER")
                .and()
                                .withUser("admin")
                                .password(passwordEncoder().encode("admin"))
                                .roles("USER");
    }

    /**
     * passwordEncoder is a bean to encode the password.
     * @return BCrypt password.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
