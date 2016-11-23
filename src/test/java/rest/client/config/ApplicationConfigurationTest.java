package rest.client.config;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class ApplicationConfigurationTest {

    @Test
    public void testEmptyConstructor() {
        ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();
        assertNotNull(applicationConfiguration);
    }
}
