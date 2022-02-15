package com.jinternals.toggle.autoconfig;

import com.jinternals.toggle.interceptor.ToggleInterceptor;
import com.jinternals.toggle.core.repository.ToggleRepository;
import com.jinternals.toggle.core.decider.ToggleDecider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;

@Configuration
@ConditionalOnWebApplication(type = SERVLET)
@ConditionalOnBean({ToggleRepository.class})
@AutoConfigureAfter(ToggleAutoConfiguration.class)
@ConditionalOnProperty(prefix = "jinternals.toggles", name="enabled", havingValue = "true", matchIfMissing = true)
public class ToggleInterceptorAutoConfiguration implements WebMvcConfigurer {

    @Autowired
    private ToggleDecider toggleDecider;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ToggleInterceptor(toggleDecider));
    }

}
