package com.jinternals.toggle.autoconfig;

import com.jinternals.toggle.endpoint.ToggleEndpoint;
import org.junit.After;
import org.junit.Test;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

public class ToggleEndpointAutoToggleDefinitionConfigurationTest {

    private AnnotationConfigApplicationContext context;

    @After
    public void tearDown() {
        if (this.context != null) {
            this.context.close();
        }
    }

    @Test
    public void shouldLoadFeatureToggleRepositoryBean() {
        load(EmptyConfiguration.class);
        ToggleEndpoint toggleEndpoint = this.context.getBean(ToggleEndpoint.class);
        assertThat(toggleEndpoint).isNotNull();
        assertThat(this.context.getBeansOfType(ToggleEndpoint.class)).size().isEqualTo(1);
    }

    @Test
    public void shouldLoadCustomFeatureToggleRepositoryBean() {
        load(ToggleEndpointAutoConfiguration.class);
        ToggleEndpoint toggleEndpoint = this.context.getBean(ToggleEndpoint.class);
        assertThat(toggleEndpoint).isNotNull();
        assertThat(this.context.getBeansOfType(ToggleEndpoint.class)).size().isEqualTo(1);
    }

    @Test
    public void shouldLoadFeatureToggleRepositoryBeanIfEnabled() {
        load(EmptyConfiguration.class, "management.endpoint.toggle.enabled=false");
        assertThat(this.context.getBeansOfType(ToggleEndpoint.class)).size().isEqualTo(0);
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