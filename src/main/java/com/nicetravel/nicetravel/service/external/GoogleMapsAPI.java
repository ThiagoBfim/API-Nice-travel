package com.nicetravel.nicetravel.service.external;

import com.nicetravel.nicetravel.exceptions.GooglePlaceNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.nicetravel.nicetravel.util.Constants.GOOGLE_MAPS_KEY;

/**
 * API para o google MAPS/Place
 * <a href='https://developers.google.com/places/web-service/search#Fields'>Google Place API</a>
 */
@Service
public class GoogleMapsAPI {

    @Autowired
    private Environment environment;

    private static final String INVALID_REQUEST = "INVALID_REQUEST";
    private static final String urlGoogle = "https://maps.googleapis.com/maps/api/place/details/json?";
    private static final String urlImageGoogle = "https://maps.googleapis.com/maps/api/place/photo?";


    public PlaceDTO getPlaceDTO(String placeId) {
        ResponseEntity<String> responsePlaceInformation = getPlaceInformation(placeId);

        JSONObject bodyObject = new JSONObject(responsePlaceInformation.getBody());
        String status = new JSONObject(responsePlaceInformation.getBody())
                .optString("status");
        if (StringUtils.isEmpty(status) || INVALID_REQUEST.equals(status)) {
            throw new GooglePlaceNotFoundException(String.format("NOT FOUND A CITY WITH PLACE ID %s", placeId));
        }
        if(StringUtils.isBlank(bodyObject.optString("result"))){
            throw new GooglePlaceNotFoundException(bodyObject.getString("error_message"));
        }
        JSONObject result = bodyObject.getJSONObject("result");

        String imageUrl;
        if (StringUtils.isNotBlank(result.optString("photos"))) {
            JSONObject photo = (JSONObject) (result.getJSONArray("photos").get(0));
            imageUrl = createImageURL(photo);
        } else {
            imageUrl = "https://i.pinimg.com/736x/07/0e/67/070e67abfd0cb1c8fb9336ab5c44ec8a.jpg";
        }

        String formattedAddress = result.optString("formatted_address");
        JSONObject locationJson = result.getJSONObject("geometry").getJSONObject("location");
        String name = (String) result.get("name");

        Double lng = (Double) locationJson.get("lng");
        Double lat = (Double) locationJson.get("lat");

        String types = result.getJSONArray("types").join(",").replaceAll("\"", "");
        return new PlaceDTO()
                .setLng(lng)
                .setLat(lat)
                .setName(name)
                .setImageUrl(imageUrl)
                .setTypes(types)
                .setFormattedAddress(formattedAddress);

    }

    private String createImageURL(JSONObject photos) {
        String imagePhotoReference = (String) photos.get("photo_reference");
        Integer imageWidth = (Integer) photos.get("width");

        UriComponentsBuilder builderImageUrl = UriComponentsBuilder.fromHttpUrl(urlImageGoogle)
                .queryParam("maxwidth", imageWidth)
                .queryParam("photoreference", imagePhotoReference)
                .queryParam("key", environment.getProperty(GOOGLE_MAPS_KEY));

        return builderImageUrl.toUriString();
    }

    private ResponseEntity<String> getPlaceInformation(String placeId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlGoogle)
                .queryParam("placeid", placeId)
                .queryParam("fields", "formatted_address,name,rating,geometry,photos,types")
                .queryParam("key", environment.getProperty(GOOGLE_MAPS_KEY));

        HttpEntity<?> entity = new HttpEntity<>(headers);

        return new RestTemplate().exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);
    }
}
