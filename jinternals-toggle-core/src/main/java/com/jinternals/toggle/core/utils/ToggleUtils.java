package com.jinternals.toggle.core.utils;

import com.jinternals.toggle.core.constants.ToggleConstants;

import static java.lang.String.format;

public class ToggleUtils {

    public static String toggleName(String toggle) {
        return String.format("%s.%s.%s", ToggleConstants.PREFIX, toggle, ToggleConstants.SUFFIX);
    }

}
