package com.nicetravel.nicetravel.service.travel.persist;

import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.model.CityEntity;
import com.nicetravel.nicetravel.model.ScheduleTravelEntity;
import com.nicetravel.nicetravel.model.TypeCityEntity;
import com.nicetravel.nicetravel.repository.CityRepository;
import com.nicetravel.nicetravel.repository.ScheduleTravelRepository;
import com.nicetravel.nicetravel.repository.TypeCityRepository;
import com.nicetravel.nicetravel.service.external.GoogleMapsAPI;
import com.nicetravel.nicetravel.service.external.PlaceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
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

    @Override
    protected ScheduleTravelEntity saveScheduleTravelOnDatabase(CityEntity cityEntity, int numberDays) {
        ScheduleTravelEntity scheduleTravelEntity = new ScheduleTravelEntity();
        scheduleTravelEntity.setPublicAccess(Boolean.FALSE);
        scheduleTravelEntity.setCityEntity(cityEntity);
        scheduleTravelEntity.setNumberDays(numberDays);
        return scheduleTravelRepository.save(scheduleTravelEntity);
    }


    @Override
    @Transactional
    protected CityEntity saveCityOnDatabase(String placeID) {
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

        updateCityTypes(placeDTO, cityEntity);
        cityRepository.save(cityEntity);
        return cityEntity;
    }

    @Transactional(readOnly = true)
    void updateCityTypes(PlaceDTO placeDTO, CityEntity cityEntity) {
        if (placeDTO.getTypes() != null) {
            Set<TypeCityEntity> typeCityEntityList = new HashSet<>();
            Stream.of(placeDTO.getTypes().split(","))
                    .forEach(type -> typeCityRepository.findByDescription(type).ifPresent(typeCityEntityList::add));
            cityEntity.setTypeCities(typeCityEntityList);
        }
    }

    @Override
    protected ScheduleDTO createScheduleDTO(ScheduleTravelEntity scheduleTravelEntity) {
        return new ScheduleDTO()
                .setQtdDays(scheduleTravelEntity.getNumberDays())
                .setImageUrl(scheduleTravelEntity.getCityEntity().getPhotoLink())
                .setNameCity(scheduleTravelEntity.getCityEntity().getName())
                .setScheduleCod(scheduleTravelEntity.getCod());
    }

    @Override
    public boolean publishTravelSchedule(Long travelId) {
        Optional<ScheduleTravelEntity> scheduleTravelOptional = scheduleTravelRepository.findById(travelId);
        if (scheduleTravelOptional.isPresent()) {
            ScheduleTravelEntity scheduleTravel = scheduleTravelOptional.get();
            scheduleTravel.setPublicAccess(Boolean.TRUE);
            scheduleTravelRepository.save(scheduleTravel);
            return true;
        }
        return false;
    }

}
