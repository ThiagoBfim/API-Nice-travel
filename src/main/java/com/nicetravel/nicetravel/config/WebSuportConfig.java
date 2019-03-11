package com.nicetravel.nicetravel.config;

import com.nicetravel.nicetravel.model.enuns.StyleTravel;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebSuportConfig extends WebMvcConfigurationSupport {

    @Override
    public FormattingConversionService mvcConversionService() {
        FormattingConversionService formattingConversionService = super.mvcConversionService();
        formattingConversionService.addConverter(new StyleTravelConveter());
        return formattingConversionService;
    }

    //TODO mover para outro pacote
    private static class StyleTravelConveter implements Converter<String, StyleTravel> {
        @Override
        public StyleTravel convert(String source) {
            try {
                return StyleTravel.valueOf(source);
            } catch(Exception e) {
                return null;
            }
        }
    }
}
