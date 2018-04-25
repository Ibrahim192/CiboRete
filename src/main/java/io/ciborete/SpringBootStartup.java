package io.ciborete;

import io.ciborete.enums.UserType;
import io.ciborete.helper.CurrentLoggedInUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.DelegatingFilterProxy;

@SpringBootApplication
public class SpringBootStartup {

    public static void main(String[] args){

        SpringApplication.run(SpringBootStartup.class,args);
        CurrentLoggedInUser.setLoggedInUser("a29f3cd5-0c97-4f81-8f70-02c3e83d6762", UserType.FAN_USER);
    }

   /* @Bean
    public FilterRegistrationBean securityFilterChainRegistration() {
        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
        delegatingFilterProxy.setTargetBeanName(AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME);
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(delegatingFilterProxy);
        registrationBean.setName(AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }*/

}
