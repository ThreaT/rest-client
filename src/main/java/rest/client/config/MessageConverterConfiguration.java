package rest.client.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

public class MessageConverterConfiguration extends AbstractJackson2HttpMessageConverter {

    public MessageConverterConfiguration() {
        this(Jackson2ObjectMapperBuilder.json().build());
    }

    public MessageConverterConfiguration(ObjectMapper objectMapper) {
        super(objectMapper, MediaType.APPLICATION_JSON, new MediaType("text", "javascript"));
    }
}
