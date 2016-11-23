package rest.client.controller;

import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;
import rest.client.service.ItunesService;

@RunWith(MockitoJUnitRunner.class)
public class ItunesControllerTest {

    @InjectMocks
    private ItunesController itunesController;

    @Mock
    private ItunesService itunesService;

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
            this.itunesController.search(this.defaultSearchTerm);
            verify(this.itunesService, times(1)).get(this.defaultSearchTerm);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Unhappy Paths
     */
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerWhenEmpty() {
        this.itunesController.search("");
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerWhenNull() {
        this.itunesController.search(null);
    }

}
