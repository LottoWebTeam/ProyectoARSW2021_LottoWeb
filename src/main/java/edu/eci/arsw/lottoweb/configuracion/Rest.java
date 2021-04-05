package edu.eci.arsw.lottoweb.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Rest {

    @Bean
    public RestOperations restOperations (){
        return new RestTemplate();
    }


}
