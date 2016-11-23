package rest.client.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import org.mockito.runners.MockitoJUnitRunner;
import rest.client.api.ItunesApi;
import rest.client.converter.ItunesConverter;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ItunesServiceTest {

    @InjectMocks
    private ItunesService itunesService;

    @Mock
    private ItunesApi itunesApi;

    @Mock
    private ItunesConverter itunesConverter;

    private String defaultSearchTerm;

    @Before
    public void init() {
        this.defaultSearchTerm = "Mozart";
    }

    /**
     * Happy Path
     */
    @Test
    public void testGet() {
        try {
            this.itunesService.get(this.defaultSearchTerm);
            verify(this.itunesApi, times(1)).get(this.defaultSearchTerm);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Unhappy Paths
     */
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerWhenEmpty() {
        this.itunesService.get("");
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerWhenNull() {
        this.itunesService.get(null);
    }

}
