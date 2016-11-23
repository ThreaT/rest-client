package rest.client.converter;

import static java.util.Arrays.asList;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import rest.client.dto.ImdbMovieDto;
import rest.client.dto.ImdbMovieDtoSearch;
import rest.client.model.Movie;

public class ImdbConverterTest {

    private ImdbConverter imdbConverter;

    private ImdbMovieDto imdbMovieDto;

    @Before
    public void init() {
        this.imdbConverter = new ImdbConverter();
        this.imdbMovieDto = new ImdbMovieDto();
        ImdbMovieDtoSearch imdbMovieDtoSearch = new ImdbMovieDtoSearch();
        imdbMovieDtoSearch.setTitle("Godzilla");
        imdbMovieDtoSearch.setYear("1992");
        this.imdbMovieDto.setSearch(
                asList(
                        imdbMovieDtoSearch
                )
        );
    }

    /**
     * Happy Path
     */
    @Test
    public void testConvert() {
        try {
            List<Movie> movies = this.imdbConverter.convert(this.imdbMovieDto);
            assertFalse(movies.isEmpty());
            assertNotNull(movies.get(0).getName());
            assertNotNull(movies.get(0).getYear());
            assertFalse(movies.get(0).getName().isEmpty());
            assertFalse(movies.get(0).getYear().isEmpty());
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Unhappy Paths
     */
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerWhenNull() {
        this.imdbConverter.convert(null);
    }

    @Test
    public void shouldSkipResultsWithNullOrEmptyNameOrYear() {
        ImdbMovieDtoSearch imdbMovieDtoSearchEmptyTitle = new ImdbMovieDtoSearch();
        imdbMovieDtoSearchEmptyTitle.setTitle("");
        imdbMovieDtoSearchEmptyTitle.setYear("1992");

        ImdbMovieDtoSearch imdbMovieDtoSearchNullTitle = new ImdbMovieDtoSearch();
        imdbMovieDtoSearchNullTitle.setTitle(null);
        imdbMovieDtoSearchNullTitle.setYear("1992");

        ImdbMovieDtoSearch imdbMovieDtoSearchEmptyYear = new ImdbMovieDtoSearch();
        imdbMovieDtoSearchEmptyYear.setTitle("Godzilla");
        imdbMovieDtoSearchEmptyYear.setYear("");

        ImdbMovieDtoSearch imdbMovieDtoSearchNullYear = new ImdbMovieDtoSearch();
        imdbMovieDtoSearchNullYear.setTitle("Godzilla");
        imdbMovieDtoSearchNullYear.setYear(null);

        this.imdbMovieDto.setSearch(
                asList(
                        imdbMovieDtoSearchEmptyTitle,
                        imdbMovieDtoSearchNullTitle,
                        imdbMovieDtoSearchEmptyYear,
                        imdbMovieDtoSearchNullYear
                )
        );

        List<Movie> movies = this.imdbConverter.convert(this.imdbMovieDto);
        assertTrue(movies.isEmpty());
    }
}
