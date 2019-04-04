package com.nicetravel.nicetravel.util;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static com.nicetravel.nicetravel.util.Constants.DEVELOP_MODE;
import static com.nicetravel.nicetravel.util.Constants.GOOGLE_ENABLED;

@Component
public class PropertiesUtil {

    @Autowired
    private Environment environment;

    public boolean isDevelopMode() {
        return BooleanUtils.isTrue(environment.getProperty(DEVELOP_MODE, Boolean.class));
    }

    public boolean isGoogleDisabled() {
        return BooleanUtils.isFalse(environment.getProperty(GOOGLE_ENABLED, Boolean.class));
    }
}
