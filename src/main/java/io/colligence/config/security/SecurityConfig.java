package io.colligence.config.security;

import io.colligence.config.SocialConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO: csrf 임시 disable => POST 작동 안됨
        // TODO: csrf enable 하고, client에서 ajax POST 호출 시, header에 csrf token 첨부하여 요청하게 할 것.
        http.cors().and()
                .csrf().disable()
                .oauth2Login()
                    .defaultSuccessUrl("/loginSuccess")
                    .failureUrl("/loginFailure")
                    .and()
                .logout()
                    .logoutSuccessUrl("/").permitAll()
                    .and()
                .authorizeRequests()
                .antMatchers("/api/users**").permitAll()
                .antMatchers("/my_oauth_info").authenticated()
                .antMatchers("/login").anonymous()
                .anyRequest().permitAll();
    }

    private SocialConfig getSocialConfig() {
        SocialConfig config = new SocialConfig();
        return config;
    }
}
