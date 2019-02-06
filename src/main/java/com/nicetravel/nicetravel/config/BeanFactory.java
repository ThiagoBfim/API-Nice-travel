package com.nicetravel.nicetravel.config;

import com.nicetravel.nicetravel.service.ITravelScheduleService;
import com.nicetravel.nicetravel.service.MockTravelScheduleService;
import com.nicetravel.nicetravel.service.TravelScheduleImplService;
import com.nicetravel.nicetravel.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanFactory {

    @Autowired
    private PropertiesUtil propertiesUtil;

    @Bean
    public ITravelScheduleService taskService() {
        if (propertiesUtil.isDevelopMode()) {
            return new MockTravelScheduleService();
        }
        return new TravelScheduleImplService();
    }

}
