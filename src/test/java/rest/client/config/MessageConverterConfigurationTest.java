package rest.client.config;

import org.springframework.http.MediaType;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class MessageConverterConfigurationTest {

    @Test
    public void testEmptyConstructor() {
        MessageConverterConfiguration messageConverterConfiguration = new MessageConverterConfiguration();
        assertNotNull(messageConverterConfiguration);
    }

    @Test
    public void testObjectMapperConstructor() {
        MessageConverterConfiguration messageConverterConfiguration = new MessageConverterConfiguration();
        assertNotNull(messageConverterConfiguration);
        assertTrue(messageConverterConfiguration.getSupportedMediaTypes().contains(new MediaType("text", "javascript")));
    }

}
