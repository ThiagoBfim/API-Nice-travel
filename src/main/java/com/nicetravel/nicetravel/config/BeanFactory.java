package com.nicetravel.nicetravel.config;

import com.nicetravel.nicetravel.service.persist.AbstractTravelScheduleService;
import com.nicetravel.nicetravel.service.persist.MockTravelScheduleService;
import com.nicetravel.nicetravel.service.persist.TravelScheduleImplService;
import com.nicetravel.nicetravel.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanFactory {

    @Autowired
    private PropertiesUtil propertiesUtil;

    @Bean
    public AbstractTravelScheduleService taskService() {
        if (propertiesUtil.isDevelopMode()) {
            return new MockTravelScheduleService();
        }
        return new TravelScheduleImplService();
    }

}
