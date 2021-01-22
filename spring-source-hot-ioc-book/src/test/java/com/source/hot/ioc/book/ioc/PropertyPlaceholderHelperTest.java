package com.source.hot.ioc.book.ioc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import org.springframework.util.PropertyPlaceholderHelper;

public class PropertyPlaceholderHelperTest {
    @Test
    void test() throws Exception {
        String a = "{a}{b}{c}";
        String b = "{a{b}{c}}";
        PropertyPlaceholderHelper propertyPlaceholderHelper = new PropertyPlaceholderHelper("{", "}");
        Properties properties = new Properties();
        properties.put("a", "1");
        properties.put("b", "2");
        properties.put("c", "3");
        properties.put("a23", "abc");
        System.out.println("替换前:" + a);

        String s = propertyPlaceholderHelper.replacePlaceholders(a, properties);

        String aReplace = propertyPlaceholderHelper.replacePlaceholders(a, getPlaceholderResolver(properties));
        System.out.println("替换后:" + aReplace);
        String s1 = propertyPlaceholderHelper.replacePlaceholders(b, getPlaceholderResolver(properties));
        System.out.println(s1);
    }

    @NotNull
    private PropertyPlaceholderHelper.PlaceholderResolver getPlaceholderResolver(Properties properties) {
        return new PropertyPlaceholderHelper.PlaceholderResolver() {
            @Override
            public String resolvePlaceholder(String placeholderName) {
                String value = properties.getProperty(placeholderName);
                return value;
            }
        };
    }
}
