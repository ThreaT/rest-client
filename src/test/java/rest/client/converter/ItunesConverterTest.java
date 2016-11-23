package rest.client.converter;

import static java.util.Arrays.asList;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import rest.client.dto.ItunesAlbumDto;
import rest.client.dto.ItunesAlbumDtoResult;
import rest.client.model.Album;

public class ItunesConverterTest {

    private ItunesConverter itunesConverter;

    private ItunesAlbumDto itunesAlbumDto;

    @Before
    public void init() {
        this.itunesConverter = new ItunesConverter();
        this.itunesAlbumDto = new ItunesAlbumDto();
        ItunesAlbumDtoResult itunesAlbumDtoResult = new ItunesAlbumDtoResult();
        itunesAlbumDtoResult.setCollectionName("1st Movement");
        itunesAlbumDtoResult.setReleaseDate("1788");
        this.itunesAlbumDto.setResultCount(1);
        this.itunesAlbumDto.setResults(
                asList(
                        itunesAlbumDtoResult
                )
        );
    }

    /**
     * Happy Path
     */
    @Test
    public void testConvert() {
        try {
            List<Album> albums = this.itunesConverter.convert(this.itunesAlbumDto);
            assertFalse(albums.isEmpty());
            assertNotNull(albums.get(0).getName());
            assertNotNull(albums.get(0).getYear());
            assertFalse(albums.get(0).getName().isEmpty());
            assertFalse(albums.get(0).getYear().isEmpty());
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Unhappy Paths
     */
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerWhenNull() {
        this.itunesConverter.convert(null);
    }

    @Test
    public void shouldSkipResultsWithNullOrEmptyNameOrYear() {
        ItunesAlbumDtoResult itunesAlbumDtoResultEmptyName = new ItunesAlbumDtoResult();
        itunesAlbumDtoResultEmptyName.setCollectionName("");
        itunesAlbumDtoResultEmptyName.setReleaseDate("1788");

        ItunesAlbumDtoResult itunesAlbumDtoResultNullName = new ItunesAlbumDtoResult();
        itunesAlbumDtoResultNullName.setCollectionName(null);
        itunesAlbumDtoResultNullName.setReleaseDate("1788");

        ItunesAlbumDtoResult itunesAlbumDtoResultEmptyReleaseDate = new ItunesAlbumDtoResult();
        itunesAlbumDtoResultEmptyReleaseDate.setCollectionName("Mozart");
        itunesAlbumDtoResultEmptyReleaseDate.setReleaseDate("");

        ItunesAlbumDtoResult itunesAlbumDtoResultNullReleaseDate = new ItunesAlbumDtoResult();
        itunesAlbumDtoResultNullReleaseDate.setCollectionName("Mozart");
        itunesAlbumDtoResultNullReleaseDate.setReleaseDate(null);

        this.itunesAlbumDto.setResultCount(4);
        this.itunesAlbumDto.setResults(
                asList(
                        itunesAlbumDtoResultEmptyName,
                        itunesAlbumDtoResultNullName,
                        itunesAlbumDtoResultEmptyReleaseDate,
                        itunesAlbumDtoResultNullReleaseDate
                )
        );

        List<Album> albums = this.itunesConverter.convert(this.itunesAlbumDto);
        assertTrue(albums.isEmpty());
    }

}
