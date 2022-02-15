package com.jinternals.toggle.autoconfig;

import com.jinternals.toggle.endpoint.ToggleEndpoint;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

public class ToggleEndpointAutoToggleDefinitionConfigurationTest {

    private AnnotationConfigApplicationContext context;

    @AfterEach
    public void tearDown() {
        if (this.context != null) {
            this.context.close();
        }
    }

    @Test
    public void shouldLoadToggleRepositoryBean() {
        load(EmptyConfiguration.class);
        ToggleEndpoint toggleEndpoint = this.context.getBean(ToggleEndpoint.class);
        Assertions.assertThat(toggleEndpoint).isNotNull();
        Assertions.assertThat(this.context.getBeansOfType(ToggleEndpoint.class)).size().isEqualTo(1);
    }

    @Test
    public void shouldLoadCustomToggleRepositoryBean() {
        load(ToggleEndpointAutoConfiguration.class);
        ToggleEndpoint toggleEndpoint = this.context.getBean(ToggleEndpoint.class);
        Assertions.assertThat(toggleEndpoint).isNotNull();
        Assertions.assertThat(this.context.getBeansOfType(ToggleEndpoint.class)).size().isEqualTo(1);
    }

    @Test
    public void shouldLoadToggleRepositoryBeanIfEnabled() {
        load(EmptyConfiguration.class, "management.endpoint.toggle.enabled=false");
        Assertions.assertThat(this.context.getBeansOfType(ToggleEndpoint.class)).size().isEqualTo(0);
    }


    @Configuration
    static class EmptyConfiguration {
    }


    private void load(Class<?> config, String... environment) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        TestPropertyValues.of(environment).applyTo(applicationContext);
        applicationContext.register(config);
        applicationContext.register(ToggleAutoConfiguration.class);
        applicationContext.register(ToggleEndpointAutoConfiguration.class);
        applicationContext.refresh();
        this.context = applicationContext;
    }

}
