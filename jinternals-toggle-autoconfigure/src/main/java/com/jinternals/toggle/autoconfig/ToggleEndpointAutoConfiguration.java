package com.jinternals.toggle.autoconfig;

import com.jinternals.toggle.endpoint.ToggleEndpoint;
import com.jinternals.toggle.core.repository.ToggleRepository;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnAvailableEndpoint;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mradul on 31/01/17.
 */
@Configuration
@AutoConfigureAfter(ToggleAutoConfiguration.class)
@ConditionalOnProperty(prefix = "jinternals.toggles", name="enabled", havingValue = "true", matchIfMissing = true)
public class ToggleEndpointAutoConfiguration {

    @Bean
    @ConditionalOnClass({ToggleRepository.class})
    @ConditionalOnMissingBean
    @ConditionalOnAvailableEndpoint
    public ToggleEndpoint toggleEndpoint() {
        return new ToggleEndpoint();
    }

}
