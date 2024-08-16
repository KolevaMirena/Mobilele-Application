package org.softuin.mobilele.config;


import org.softuin.mobilele.model.enums.UserRoleEnum;
import org.softuin.mobilele.repository.UserRepository;
import org.softuin.mobilele.service.impl.MobileleUserDetailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    private final String rememberMeKey;

    public SecurityConfiguration(@Value("mobilele.remember.me.key") String rememberMeKey) {
        this.rememberMeKey = rememberMeKey;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(
                //defines which urls are visible by which users
                authorizeRequest ->{
                    //allow static recourses are visible to anyone - images, js, css
                    authorizeRequest.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                            //allow login and registration url to anyone
                            .requestMatchers("/", "/users/login", "/users/register", "/users/login-error").permitAll()
                            .requestMatchers("/offers/all").permitAll()
                            .requestMatchers("/brands").hasRole(UserRoleEnum.ADMIN.name())
                            //all other requests are authenticated
                            .anyRequest().authenticated();

                }
        ).formLogin(
                formLogin ->{
                    //redirect here when we try to access smth that is not allowed
                    formLogin.loginPage("/users/login")
                            //the names of the input fields
                            .usernameParameter("email")
                            .passwordParameter("password")
                            .defaultSuccessUrl("/")
                            .failureForwardUrl("/users/login-error");


                }
        ).logout(
                logout ->{
                    //the url that we POST to perform the logout
                    logout.logoutUrl("/users/logout")
                            .logoutSuccessUrl("/")
                            .invalidateHttpSession(true);


                }

        ).rememberMe(

                rememberMe ->{

                    rememberMe.key(rememberMeKey)
                            //should be the same as name of the checkbox in the template
                            .rememberMeParameter("rememeberme")
                            //the cookie name that will be set to the client
                            .rememberMeCookieName("rememeberme");

                }

        );
        //TODO remember me

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        //this service translates our application user and roles to Spring representation which spring security understands
        return new MobileleUserDetailService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

}
