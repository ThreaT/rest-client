package rest.client.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.client.api.ItunesApi;
import rest.client.converter.ItunesConverter;
import rest.client.model.Album;

@Service
public class ItunesService {

    @Autowired
    private ItunesApi itunesApi;

    @Autowired
    private ItunesConverter itunesConverter;

    public List<Album> get(String term) {
        if ((term == null) || (term.isEmpty())) {
            throw new NullPointerException("Please specify a search term");
        }
        return this.itunesConverter.convert(this.itunesApi.get(term));
    }

}
