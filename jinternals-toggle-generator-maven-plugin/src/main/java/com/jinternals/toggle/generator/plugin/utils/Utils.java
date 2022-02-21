package com.jinternals.toggle.generator.plugin.utils;

public class Utils {

    public static String normalizeName(String name) {
        return name.replaceAll("\\.", "_").toUpperCase();
    }

}
