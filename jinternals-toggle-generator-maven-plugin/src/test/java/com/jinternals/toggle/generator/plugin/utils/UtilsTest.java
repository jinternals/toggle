package com.jinternals.toggle.generator.plugin.utils;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UtilsTest {

    @Test
    public void shouldNormalizeStringWithDot() {
        String normalized = Utils.normalizeName("demo.basic");
        assertThat(normalized).isEqualTo("DEMO_BASIC");
    }

}
