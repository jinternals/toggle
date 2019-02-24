package com.jinternals.toggle.autoconfig;

import com.jinternals.toggle.endpoint.ToggleEndpoint;
import com.jinternals.toggle.core.repository.ToggleRepository;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mradul on 31/01/17.
 */
@Configuration
@AutoConfigureAfter(ToggleAutoConfiguration.class)
public class ToggleEndpointAutoConfiguration {

    @Bean
    @ConditionalOnClass({ToggleRepository.class})
    @ConditionalOnMissingBean
    @ConditionalOnEnabledEndpoint
    public ToggleEndpoint toggleEndpoint() {
        return new ToggleEndpoint();
    }

}
