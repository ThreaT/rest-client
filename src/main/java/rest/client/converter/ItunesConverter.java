package rest.client.converter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import rest.client.dto.ItunesAlbumDto;
import rest.client.dto.ItunesAlbumDtoResult;
import rest.client.model.Album;

@Component
public class ItunesConverter {

    public List<Album> convert(ItunesAlbumDto itunesAlbumDto) {
        List<Album> albums = new ArrayList<>();
        for (ItunesAlbumDtoResult itunesAlbumDtoResult : itunesAlbumDto.getResults()) {
            if ((itunesAlbumDtoResult.getCollectionName() == null) || (itunesAlbumDtoResult.getCollectionName().isEmpty()) || (itunesAlbumDtoResult.getReleaseDate() == null) || (itunesAlbumDtoResult.getReleaseDate().isEmpty())) {
                continue;
            }
            Album album = new Album();
            album.setName(itunesAlbumDtoResult.getCollectionName());
            album.setYear(itunesAlbumDtoResult.getReleaseDate());
            albums.add(album);
        }
        return albums;
    }
}
