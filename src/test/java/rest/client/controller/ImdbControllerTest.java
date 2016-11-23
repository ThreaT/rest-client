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
import rest.client.service.ImdbService;

@RunWith(MockitoJUnitRunner.class)
public class ImdbControllerTest {

    @InjectMocks
    private ImdbController imdbController;

    @Mock
    private ImdbService imdbService;

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
            this.imdbController.search(this.defaultSearchTerm);
            verify(this.imdbService, times(1)).get(this.defaultSearchTerm);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Unhappy Paths
     */
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerWhenEmpty() {
        this.imdbController.search("");
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerWhenNull() {
        this.imdbController.search(null);
    }

}
