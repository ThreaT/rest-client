package rest.client.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import rest.client.dto.ImdbMovieDto;

@Component
public class ImdbApi {

    @Autowired
    private RestTemplate restTemplate;

    public ImdbMovieDto get(String term) {
        if ((term == null) || (term.isEmpty())) {
            throw new NullPointerException("Please specify a search term");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://www.omdbapi.com")
                .queryParam("s", term);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<ImdbMovieDto> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, ImdbMovieDto.class);
        return response.getBody();
    }

}
