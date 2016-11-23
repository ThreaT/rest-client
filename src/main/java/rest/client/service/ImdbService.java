package rest.client.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.client.api.ImdbApi;
import rest.client.converter.ImdbConverter;
import rest.client.model.Movie;

@Service
public class ImdbService {

    @Autowired
    private ImdbApi imdbApi;

    @Autowired
    private ImdbConverter imdbConverter;

    public List<Movie> get(String term) {
        if ((term == null) || (term.isEmpty())) {
            throw new NullPointerException("Please specify a search term");
        }
        return imdbConverter.convert(imdbApi.get(term));
    }

}
