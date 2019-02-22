package com.jinternals.toggle.autoconfig;

import com.jinternals.toggle.core.decider.DefaultToggleDecider;
import com.jinternals.toggle.defination.ClasspathToggleDefinitionProvider;
import com.jinternals.toggle.core.defination.parser.ToggleDefinitionParser;
import com.jinternals.toggle.core.defination.parser.impl.YamlToggleDefinitionParser;
import com.jinternals.toggle.core.repository.ToggleRepository;
import com.jinternals.toggle.core.repository.DefaultToggleRepository;
import com.jinternals.toggle.api.decider.ToggleDecider;
import com.jinternals.toggle.api.definition.ToggleDefinitionProvider;
import com.jinternals.toggle.api.state.ToggleStateProvider;
import com.jinternals.toggle.core.state.EnvironmentFeatureToggleStateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


/**
 * Created by mradul on 31/01/17.
 */
@Configuration
@EnableConfigurationProperties(ToggleConfiguration.class)
@ConditionalOnClass({ToggleDecider.class, ToggleRepository.class, ToggleDefinitionParser.class})
public class ToggleAutoConfiguration {


    @Autowired
    private ToggleConfiguration toggleConfiguration;


    @Bean
    @ConditionalOnMissingBean
    public ToggleDefinitionParser featureToggleDefinitionParser() {
        return new YamlToggleDefinitionParser();
    }

    @Bean
    @ConditionalOnMissingBean
    public ToggleDefinitionProvider featureToggleDefinitionProvider(ToggleDefinitionParser toggleDefinitionParser) {
        return new ClasspathToggleDefinitionProvider(toggleConfiguration.getDefinitionFile(), toggleDefinitionParser);
    }

    @Bean
    @ConditionalOnMissingBean
    public ToggleStateProvider getEnvironmentFeatureToggleStateProvider(Environment environment) {
        return new EnvironmentFeatureToggleStateProvider(environment);
    }

    @Bean
    @ConditionalOnMissingBean
    public ToggleRepository getFeatureToggleInfoProvider(ToggleDefinitionProvider definitionProvider, ToggleStateProvider stateProvider) {
        return new DefaultToggleRepository(definitionProvider, stateProvider);
    }

    @Bean
    @ConditionalOnMissingBean
    public ToggleDecider getFeatureToggleDecider(ToggleRepository featureToggleInfoProvider) {
        return new DefaultToggleDecider(featureToggleInfoProvider);
    }

}

