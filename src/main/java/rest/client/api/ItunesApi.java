package rest.client.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import rest.client.dto.ItunesAlbumDto;

@Component
public class ItunesApi {

    @Autowired
    private RestTemplate restTemplate;

    public ItunesAlbumDto get(String term) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://itunes.apple.com/search")
                .queryParam("media", "music")
                .queryParam("term", term);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<ItunesAlbumDto> response = this.restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, ItunesAlbumDto.class);
        return response.getBody();
    }
}
