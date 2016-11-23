package rest.client.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import org.mockito.runners.MockitoJUnitRunner;
import rest.client.api.ImdbApi;
import rest.client.converter.ImdbConverter;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ImdbServiceTest {

    @InjectMocks
    private ImdbService imdbService;

    @Mock
    private ImdbApi imdbApi;

    @Mock
    private ImdbConverter imdbConverter;

    private String defaultSearchTerm;

    @Before
    public void init() {
        this.defaultSearchTerm = "Godzilla";
    }

    /**
     * Happy Path
     */
    @Test
    public void testGet() {
        try {
            this.imdbService.get(this.defaultSearchTerm);
            verify(this.imdbApi, times(1)).get(this.defaultSearchTerm);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Unhappy Paths
     */
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerWhenEmpty() {
        this.imdbService.get("");
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerWhenNull() {
        this.imdbService.get(null);
    }

}
