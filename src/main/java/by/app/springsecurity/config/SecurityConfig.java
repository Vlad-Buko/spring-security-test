package by.app.springsecurity.config;

import by.app.springsecurity.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Vladislav Domaniewski
 *
 * Настраиваем нашу конфигурацию Spring Security
 * Реализуем класс WebSecurityConfigurerAdapter
 * Внедряем AuthenticationProvider, и записываем в нашу конфигурацию.
 */

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PeopleService peopleService;

    @Autowired
    public SecurityConfig(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    // Конфигурируем нашу страницу логина
    // Здесь будут все конфигурации
    // А так же обрабатываем нашу форму с thymeleaf, spring security
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // теперь вклюяили csrf защиту
        http.authorizeRequests()
                // здесь можно не писать "ROLE" , Spring итак понимает
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/auth/login", "/auth/registration",  "/error").permitAll()
                .anyRequest().hasAnyRole("USER", "ADMIN")// На эти URL можем заходить
                .and()
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/world", true)
                .failureUrl("/auth/login?error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login");
    }

    // Здесь конфигурируем пароль для приложения
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(peopleService).passwordEncoder(getPasswordEncoder());

    }

    // создаем новый обьект bcrypt
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
