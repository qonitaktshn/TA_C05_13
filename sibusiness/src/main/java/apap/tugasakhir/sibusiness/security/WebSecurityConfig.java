package apap.tugasakhir.sibusiness.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/user/**").hasAuthority("Manager Business")
                .antMatchers("/coupon/add").hasAnyAuthority("Staff_Product", "Staff_Marketing")
                .antMatchers("/coupon/update/**").hasAuthority("Staff_Marketing")
                .antMatchers("/coupon/list-coupon").permitAll()
                .antMatchers("/item-factory/view-all").hasAuthority("Manager Business")
                .antMatchers(HttpMethod.GET,"/api/v1/coupon/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/list-item").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/detail-item/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/list-mesin/**").hasAnyAuthority("Manager Business", "Staff_Product")
                .antMatchers(HttpMethod.POST, "/api/v1/list-item-factory").permitAll()
                .antMatchers(HttpMethod.POST, "/item/").hasAuthority("Manager Business")
                .antMatchers(HttpMethod.POST, "/cabang/").hasAuthority("Manager Business")
                .antMatchers("/coupon/delete/**").hasAuthority("Staff_Marketing")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(encoder())
//                .withUser("pebisnispemula").password(encoder().encode("sibusiness"))
//                .roles("Manager Business");
//    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }
}