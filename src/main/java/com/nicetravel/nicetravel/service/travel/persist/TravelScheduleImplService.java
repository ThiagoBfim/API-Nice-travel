package com.nicetravel.nicetravel.service.travel.persist;

import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.model.*;
import com.nicetravel.nicetravel.repository.*;
import com.nicetravel.nicetravel.service.external.GoogleMapsAPI;
import com.nicetravel.nicetravel.service.external.PlaceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Stream;

public class TravelScheduleImplService extends AbstractTravelScheduleService {


    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private TypeCityRepository typeCityRepository;

    @Autowired
    private GoogleMapsAPI googleMapsAPI;

    @Autowired
    private ScheduleTravelRepository scheduleTravelRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VoteScheduleRepository voteScheduleRepository;

    @Override
    protected UserEntity createOrGetUser(String userUID, String userEmail, String userName) {
        UserEntity userEntity = userRepository.findByUid(userUID).orElse(new UserEntity());
        userEntity.setUid(userUID);
        userEntity.setEmail(userEmail);
        userEntity.setName(userName);
        return userRepository.save(userEntity);
    }

    @Override
    protected ScheduleTravelEntity saveScheduleTravel(CityEntity cityEntity, int numberDays, UserEntity userOwner) {
        ScheduleTravelEntity scheduleTravelEntity = new ScheduleTravelEntity();
        scheduleTravelEntity.setPublicAccess(Boolean.FALSE);
        scheduleTravelEntity.setCityEntity(cityEntity);
        scheduleTravelEntity.setUserOwner(userOwner);

        List<ScheduleDayEntity> scheduleDays = new ArrayList<>();
        for (int i = 1; i <= numberDays; i++) {
            ScheduleDayEntity scheduleDayEntity = new ScheduleDayEntity();
            scheduleDayEntity.setDay(i);
            scheduleDayEntity.setScheduleTravelEntity(scheduleTravelEntity);
            scheduleDays.add(scheduleDayEntity);
        }
        scheduleTravelEntity.setScheduleDayEntities(scheduleDays);
        return scheduleTravelRepository.save(scheduleTravelEntity);
    }


    @Override
    @Transactional
    protected CityEntity saveCity(String placeID) {
        Optional<CityEntity> cityEntityOptional = cityRepository.findByPlaceID(placeID);
        if (cityEntityOptional.isPresent()) {
            return cityEntityOptional.get();
        }
        PlaceDTO placeDTO = googleMapsAPI.getPlaceDTO(placeID);
        CityEntity cityEntity = new CityEntity();
        cityEntity.setPlaceID(placeID);
        cityEntity.setPhotoLink(placeDTO.getImageUrl());
        cityEntity.setName(placeDTO.getName());
        cityEntity.setLatitude(placeDTO.getLat());
        cityEntity.setLongitude(placeDTO.getLng());
        cityEntity.setFormattedAddress(placeDTO.getFormattedAddress());

        updateCityTypes(placeDTO, cityEntity);
        cityRepository.save(cityEntity);
        return cityEntity;
    }

    @Transactional(readOnly = true)
    void updateCityTypes(PlaceDTO placeDTO, CityEntity cityEntity) {
        if (placeDTO.getTypes() != null) {
            Set<TypeCityEntity> typeCityEntityList = new HashSet<>();
            Stream.of(placeDTO.getTypes().split(","))
                    .forEach(type -> typeCityRepository.findByDescription(type.trim()).ifPresent(typeCityEntityList::add));
            cityEntity.setTypeCities(typeCityEntityList);
        }
    }

    @Override
    protected ScheduleDTO createScheduleDTO(ScheduleTravelEntity scheduleTravelEntity) {
        return new ScheduleDTO(scheduleTravelEntity);
    }

    @Override
    public boolean publishTravelSchedule(Long scheduleId) {
        return updateScheduleTravel(scheduleId, scheduleTravel -> scheduleTravel.setPublicAccess(Boolean.TRUE));
    }

    @Override
    public boolean voteTravelSchedule(Long scheduleId, String userUID, Boolean positiveVote) {
        if(voteScheduleRepository.countAllByUserVoteUid(userUID) > 0){
            return false;
        }
        return updateScheduleTravel(scheduleId, scheduleTravel -> {
            int vote = positiveVote ? 1 : -1;
            scheduleTravel.setNumberStar(scheduleTravel.getNumberStar() + vote);
        });
    }

    @Override
    public void delete(Long scheduleId) {
        scheduleTravelRepository.deleteById(scheduleId);
    }

    private boolean updateScheduleTravel(Long scheduleId, UpdateScheduleTravelConsumer consumer) {
        Optional<ScheduleTravelEntity> scheduleTravelOptional = scheduleTravelRepository.findById(scheduleId);
        if (scheduleTravelOptional.isPresent()) {
            ScheduleTravelEntity scheduleTravel = scheduleTravelOptional.get();
            consumer.accept(scheduleTravel);
            scheduleTravelRepository.save(scheduleTravel);
            return true;
        }
        return false;
    }

}
