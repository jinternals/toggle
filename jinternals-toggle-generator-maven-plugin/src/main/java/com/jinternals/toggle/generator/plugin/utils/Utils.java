package com.jinternals.toggle.generator.plugin.utils;

import java.io.File;

public class Utils {

    public static String normalizeName(String name) {
        return name.replaceAll("\\.", "_").toUpperCase();
    }

}
