package com.nicetravel.nicetravel;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.nicetravel.nicetravel.util.Constants.GOOGLE_MAPS_KEY;

public class GooglePlaceAPITest extends NicetravelApplicationTests {


    //DOC -> https://developers.google.com/places/web-service/search#Fields

    @Autowired
    private Environment environment;

    private static final String urlGoogle = "https://maps.googleapis.com/maps/api/place/details/json?";
    private static final String urlImageGoogle = "https://maps.googleapis.com/maps/api/place/photo?";

    @Test
    public void testGetTypeLocation() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlGoogle)
                .queryParam("placeid", "ChIJIb5p4kuSqgcRUiZizPUS4TQ")
                .queryParam("fields", "name,rating,geometry,photos,types")
                .queryParam("key", environment.getProperty(GOOGLE_MAPS_KEY));

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = new RestTemplate().exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        JSONObject bodyObject = new JSONObject(response.getBody());
        JSONObject result = bodyObject.getJSONObject("result");
        String imagePhotoReference = (String) ((JSONObject) (result.getJSONArray("photos").get(0))).get("photo_reference");
        Integer imageWidth = (Integer) ((JSONObject) (result.getJSONArray("photos").get(0))).get("width");

        UriComponentsBuilder builderImageUrl = UriComponentsBuilder.fromHttpUrl(urlImageGoogle)
                .queryParam("maxwidth", imageWidth)
                .queryParam("photoreference", imagePhotoReference)
                .queryParam("key", environment.getProperty(GOOGLE_MAPS_KEY));

        JSONObject locationJson = result.getJSONObject("geometry").getJSONObject("location");
        String name = (String) result.get("name");

        Double lng = (Double)locationJson.get("lng");
        Double lat = (Double) locationJson.get("lat");


        String imageUrl = builderImageUrl.toUriString(); //O PhotoReference é um hash gerado, não da para utilizar no teste unitario.
        String types = result.getJSONArray("types").join(",").replaceAll("\"", "");
        Assert.assertEquals("natural_feature,establishment", types);
        Assert.assertEquals(new Double(-8.4844765), lat);
        Assert.assertEquals(new Double(-34.9996923), lng);
        Assert.assertEquals("Porto de Galinhas", name);
    }
}
