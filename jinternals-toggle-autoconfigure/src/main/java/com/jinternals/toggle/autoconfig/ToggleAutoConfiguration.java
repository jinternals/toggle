package com.jinternals.toggle.autoconfig;

import com.jinternals.toggle.autoconfig.properties.ToggleConfigurationProperties;
import com.jinternals.toggle.core.definition.ToggleDefinitionParser;
import com.jinternals.toggle.core.definition.ToggleDefinitionProvider;
import com.jinternals.toggle.core.definition.impl.YamlToggleDefinitionParser;
import com.jinternals.toggle.core.repository.DefaultToggleRepository;
import com.jinternals.toggle.core.repository.ToggleRepository;
import com.jinternals.toggle.core.services.DefaultService;
import com.jinternals.toggle.core.services.ToggleService;
import com.jinternals.toggle.core.state.EnvironmentToggleStateProvider;
import com.jinternals.toggle.core.state.ToggleStateProvider;
import com.jinternals.toggle.defination.ClasspathToggleDefinitionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import static com.jinternals.toggle.Constants.TOGGLE_CONFIG_PREFIX;


/**
 * Created by mradul on 31/01/17.
 */
@Configuration
@EnableConfigurationProperties(ToggleConfigurationProperties.class)
@ConditionalOnClass({ToggleService.class, ToggleRepository.class, ToggleDefinitionParser.class})
@ConditionalOnProperty(prefix = TOGGLE_CONFIG_PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class ToggleAutoConfiguration {


    @Autowired
    private ToggleConfigurationProperties toggleConfigurationProperties;


    @Bean
    @ConditionalOnMissingBean
    public ToggleDefinitionParser toggleDefinitionParser() {
        return new YamlToggleDefinitionParser();
    }

    @Bean
    @ConditionalOnMissingBean
    public ToggleDefinitionProvider toggleDefinitionProvider(ToggleDefinitionParser toggleDefinitionParser) {
        return new ClasspathToggleDefinitionProvider(toggleConfigurationProperties.getDefinitionFile(), toggleDefinitionParser);
    }

    @Bean
    @ConditionalOnMissingBean
    public ToggleStateProvider toggleStateProvider(Environment environment) {
        return new EnvironmentToggleStateProvider(environment);
    }

    @Bean
    @ConditionalOnMissingBean
    public ToggleRepository toggleRepository(ToggleDefinitionProvider definitionProvider, ToggleStateProvider stateProvider) {
        return new DefaultToggleRepository(definitionProvider, stateProvider);
    }

    @Bean
    @ConditionalOnMissingBean
    public ToggleService toggleDecider(ToggleRepository toggleRepository) {
        return new DefaultService(toggleRepository);
    }

}

