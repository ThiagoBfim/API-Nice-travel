package com.nicetravel.nicetravel.config;

import com.nicetravel.nicetravel.service.activity.persist.AbstractActivityService;
import com.nicetravel.nicetravel.service.activity.persist.ActivityImplService;
import com.nicetravel.nicetravel.service.activity.persist.MockActivityService;
import com.nicetravel.nicetravel.service.activity.retrieve.AbstractFindActivityService;
import com.nicetravel.nicetravel.service.activity.retrieve.FindActivityImplService;
import com.nicetravel.nicetravel.service.activity.retrieve.MockFindActivityService;
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

    @Bean
    public AbstractFindActivityService findActivityService(){
        if(propertiesUtil.isDevelopMode()){
            return new MockFindActivityService();
        }
        return new FindActivityImplService();
    }

    @Bean
    public AbstractActivityService activityService(){
        if(propertiesUtil.isDevelopMode()){
            return new MockActivityService();
        }
        return new ActivityImplService();
    }

}
