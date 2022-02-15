package com.jinternals.toggle.endpoint;


import com.jinternals.toggle.core.repository.ToggleRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {
    @Bean
    public ToggleEndpoint toggleEndpoint() {
        return new ToggleEndpoint();
    }

    @Bean
    public ToggleRepository toggleRepository() {
        return Mockito.mock(ToggleRepository.class);

    }

}
