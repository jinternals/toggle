package com.jinternals.toggle.autoconfig;


import com.jinternals.toggle.core.repository.ToggleRepository;
import com.jinternals.toggle.api.decider.ToggleDecider;
import com.jinternals.toggle.api.definition.ToggleDefinitionProvider;
import org.junit.After;
import org.junit.Test;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

public class ToggleDefinitionAutoConfigurationTest {

    private AnnotationConfigApplicationContext context;

    @After
    public void tearDown() {
        if (this.context != null) {
            this.context.close();
        }
    }

    @Test
    public void shouldLoadToggleRepositoryBean() {
        load(EmptyConfiguration.class, "toggle.abc.r1.enabled=true", "toggle.xyz.r2.enabled=false");
        ToggleRepository toggleRepository = this.context.getBean(ToggleRepository.class);
        assertThat(toggleRepository).isNotNull();
        assertThat(toggleRepository.isToggleOn("abc.r1")).isTrue();
        assertThat(toggleRepository.isToggleOn("xyz.r2")).isFalse();
        assertThat(this.context.getBeansOfType(ToggleRepository.class)).size().isEqualTo(1);
    }

    @Test
    public void shouldLoadToggleDeciderBean() {
        load(EmptyConfiguration.class, "toggle.abc.r1.enabled=true", "toggle.xyz.r2.enabled=false");
        ToggleDecider toggleDecider = this.context.getBean(ToggleDecider.class);
        assertThat(toggleDecider).isNotNull();
        assertThat(toggleDecider.isToggleOn("abc.r1")).isTrue();
        assertThat(toggleDecider.isToggleOff("xyz.r2")).isTrue();
        assertThat(this.context.getBeansOfType(ToggleDecider.class)).size().isEqualTo(1);
    }

    @Test
    public void shouldLoadToggleDefinitionProviderBean() {
        load(EmptyConfiguration.class);
        ToggleDefinitionProvider toggleDefinitionProvider = this.context.getBean(ToggleDefinitionProvider.class);
        assertThat(toggleDefinitionProvider.getToggleDefinitions().size()).isEqualTo(2);
    }

    @Configuration
    static class EmptyConfiguration {
    }


    private void load(Class<?> config, String... environment) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        TestPropertyValues.of(environment).applyTo(applicationContext);
        applicationContext.register(config);
        applicationContext.register(ToggleAutoConfiguration.class);
        applicationContext.refresh();
        this.context = applicationContext;
    }
}