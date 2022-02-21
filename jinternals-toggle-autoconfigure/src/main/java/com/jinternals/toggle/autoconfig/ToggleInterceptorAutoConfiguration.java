package com.jinternals.toggle.autoconfig;

import com.jinternals.toggle.core.repository.ToggleRepository;
import com.jinternals.toggle.core.services.ToggleService;
import com.jinternals.toggle.interceptor.ToggleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.jinternals.toggle.Constants.TOGGLE_CONFIG_PREFIX;
import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;

@Configuration
@ConditionalOnWebApplication(type = SERVLET)
@ConditionalOnBean({ToggleRepository.class})
@AutoConfigureAfter(ToggleAutoConfiguration.class)
@ConditionalOnProperty(prefix = TOGGLE_CONFIG_PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class ToggleInterceptorAutoConfiguration implements WebMvcConfigurer {

    @Autowired
    private ToggleService toggleService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ToggleInterceptor(toggleService));
    }

}
