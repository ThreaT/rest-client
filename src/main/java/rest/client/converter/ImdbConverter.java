package rest.client.converter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import rest.client.dto.ImdbMovieDto;
import rest.client.dto.ImdbMovieDtoSearch;
import rest.client.model.Movie;

@Component
public class ImdbConverter {

    public List<Movie> convert(ImdbMovieDto imdbDataDto) {
        List<Movie> movies = new ArrayList<>();
        for (ImdbMovieDtoSearch imdbDataDtoSearch : imdbDataDto.getSearch()) {
            if ((imdbDataDtoSearch.getTitle() == null) || (imdbDataDtoSearch.getTitle().isEmpty()) || (imdbDataDtoSearch.getYear() == null) || (imdbDataDtoSearch.getYear().isEmpty())) {
                continue;
            }
            Movie movie = new Movie();
            movie.setName(imdbDataDtoSearch.getTitle());
            movie.setYear(imdbDataDtoSearch.getYear());
            movies.add(movie);
        }
        return movies;
    }
}
