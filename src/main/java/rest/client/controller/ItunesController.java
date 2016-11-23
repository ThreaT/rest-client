package rest.client.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import rest.client.model.Album;
import rest.client.service.ItunesService;
import rest.client.view.ResultsView;

@Controller
public class ItunesController {

    @Autowired
    private ItunesService itunesService;

    public ResultsView search(String term) {
        if ((term == null) || (term.isEmpty())) {
            throw new NullPointerException();
        }
        List<Album> albums = itunesService.get(term);
        return new ResultsView(albums);
    }

}
