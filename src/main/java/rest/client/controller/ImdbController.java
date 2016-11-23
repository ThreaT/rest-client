package rest.client.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import rest.client.model.Movie;
import rest.client.service.ImdbService;
import rest.client.view.ResultsView;

@Controller
public class ImdbController {

    @Autowired
    private ImdbService imdbService;

    public ResultsView search(String term) {
        if ((term == null) || (term.isEmpty())) {
            throw new NullPointerException();
        }
        List<Movie> movies = imdbService.get(term);
        return new ResultsView(movies);
    }

}
