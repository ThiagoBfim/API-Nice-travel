package com.nicetravel.nicetravel.service.travel.persist;

import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.model.CityEntity;
import com.nicetravel.nicetravel.model.ScheduleTravelEntity;
import com.nicetravel.nicetravel.model.TypeCityEntity;
import com.nicetravel.nicetravel.repository.CityRepository;
import com.nicetravel.nicetravel.repository.TypeCityRepository;
import com.nicetravel.nicetravel.service.external.GoogleMapsAPI;
import com.nicetravel.nicetravel.service.external.PlaceDTO;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Override
    protected ScheduleTravelEntity saveScheduleTravelOnDatabase(CityEntity cityEntity, int styleTravel) {
        throw new UnsupportedOperationException("HAVE TO BE IMPLEMENTED");
    }

    @Override
    protected CityEntity saveCityOnDatabase(String placeID) {
        Optional<CityEntity> cityEntityOptional = cityRepository.findByPlaceID(placeID);
        if (cityEntityOptional.isPresent()) {
            return cityEntityOptional.get();
        }
        PlaceDTO placeDTO = googleMapsAPI.getPlaceDTO(placeID);
        CityEntity cityEntity = new CityEntity();
        cityEntity.setPhotoLink(placeDTO.getImageUrl());
        cityEntity.setName(placeDTO.getName());
        cityEntity.setLatitude(placeDTO.getLat());
        cityEntity.setLongitude(placeDTO.getLng());

        updateCityTypes(placeDTO, cityEntity);
        cityRepository.save(cityEntity);
        return cityEntity;
    }

    private void updateCityTypes(PlaceDTO placeDTO, CityEntity cityEntity) {
        if (placeDTO.getTypes() != null) {
            Set<TypeCityEntity> typeCityEntityList = new HashSet<>();
            Stream.of(placeDTO.getTypes().split(","))
                    .forEach(type -> typeCityRepository.findByDescription(type).ifPresent(typeCityEntityList::add));
            cityEntity.setTypeCities(typeCityEntityList);
        }
    }

    @Override
    protected ScheduleDTO createSchedule(ScheduleTravelEntity scheduleTravelEntity) {
        throw new UnsupportedOperationException("HAVE TO BE IMPLEMENTED");
    }
}
