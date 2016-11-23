package rest.client.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public final class ResultsView {

    private final List<Object> objects;

    public ResultsView(final Object... objects) {
        this.objects = new ArrayList<>();
        for (Object object : objects) {
            if (object != null) {
                this.objects.add(object);
            }
        }
    }

    public final String print(final boolean success) {
        String view = "";
        OutputStream outputStream = new ByteArrayOutputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        for (Object object : this.objects) {
            try {
                objectMapper.writeValue(outputStream, object);
                view += outputStream.toString();
                view += "\n\n";
            } catch (IOException ex) {
                view = "Error, page could not be printed";
                return view;
            }
        }
        if (!view.isEmpty()) {
            if (success) {
                view = "SUCCESS\n\n" + view;
            } else {
                view = "FAILURE\n\n" + view;
            }
        }
        return view;
    }

}
