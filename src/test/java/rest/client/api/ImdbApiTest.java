package rest.client.api;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import rest.client.dto.ItunesAlbumDto;

@RunWith(MockitoJUnitRunner.class)
public class ImdbApiTest {

    @InjectMocks
    private ImdbApi imdbApi;

    @Mock
    private RestTemplate restTemplate;

    /**
     * Happy Path
     */
    @Test
    public void testGet() {
        ResponseEntity<List<ItunesAlbumDto>> myEntity = new ResponseEntity<>(HttpStatus.ACCEPTED);
        Mockito.when(restTemplate.exchange(
                Matchers.eq("http://www.omdbapi.com"),
                Matchers.eq(HttpMethod.GET),
                Matchers.<HttpEntity<List<ItunesAlbumDto>>>any(),
                Matchers.<ParameterizedTypeReference<List<ItunesAlbumDto>>>any())
        ).thenReturn(myEntity);
    }

    /**
     * Unhappy Paths
     */
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerWhenEmpty() {
        this.imdbApi.get("");
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerWhenNull() {
        this.imdbApi.get(null);
    }

}
