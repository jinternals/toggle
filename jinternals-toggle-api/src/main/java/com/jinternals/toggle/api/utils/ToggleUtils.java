package com.jinternals.toggle.api.utils;

import static com.jinternals.toggle.api.constants.ToggleConstants.PREFIX;
import static com.jinternals.toggle.api.constants.ToggleConstants.SUFFIX;
import static java.lang.String.format;

public class ToggleUtils {

    public static String toggleName(String toggle) {
        return format("%s.%s.%s", PREFIX, toggle, SUFFIX);
    }

}
