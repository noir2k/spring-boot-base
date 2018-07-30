package net.hiskarma.stellarTest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        // For resources and...
//        web.ignoring().antMatchers("/css/**", "/script/**", "/");
        // 임시.
        web.ignoring().antMatchers("/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO: csrf 임시 disable => POST 작동 안됨
        // TODO: csrf enable 하고, client에서 ajax POST 호출 시, header에 csrf token 첨부하여 요청하게 할 것.
        http.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/my_oauth_info").authenticated()
//                .antMatchers("/api/users**").permitAll()
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/loginSuccess")
                .failureUrl("/loginFailure")
                .and()
                .logout()
                .logoutSuccessUrl("/").permitAll();
    }
}
