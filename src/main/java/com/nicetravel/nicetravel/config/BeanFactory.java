package com.nicetravel.nicetravel.config;

import com.nicetravel.nicetravel.service.travel.persist.AbstractTravelScheduleService;
import com.nicetravel.nicetravel.service.travel.persist.MockTravelScheduleService;
import com.nicetravel.nicetravel.service.travel.persist.TravelScheduleImplService;
import com.nicetravel.nicetravel.service.travel.retrieve.AbstractFindTravelScheduleService;
import com.nicetravel.nicetravel.service.travel.retrieve.FindTravelScheduleImplService;
import com.nicetravel.nicetravel.service.travel.retrieve.MockFindTravelScheduleService;
import com.nicetravel.nicetravel.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanFactory {

    @Autowired
    private PropertiesUtil propertiesUtil;

    @Bean
    public AbstractTravelScheduleService travelSchedule() {
        if (propertiesUtil.isDevelopMode()) {
            return new MockTravelScheduleService();
        }
        return new TravelScheduleImplService();
    }

    @Bean
    public AbstractFindTravelScheduleService findTravelSchedule() {
        if (propertiesUtil.isDevelopMode()) {
            return new MockFindTravelScheduleService();
        }
        return new FindTravelScheduleImplService();
    }

}
