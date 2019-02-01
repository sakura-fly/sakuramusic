package wholemusic.core.provider.baidu;

import com.alibaba.fastjson.annotation.JSONField;
import wholemusic.core.api.MusicProvider;
import wholemusic.core.model.Album;
import wholemusic.core.model.Artist;
import wholemusic.core.model.BaseBean;
import wholemusic.core.model.Song;
import wholemusic.core.util.SongUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haohua on 2018/2/11.
 */
@SuppressWarnings("SpellCheckingInspection")
class BaiduAlbum extends BaseBean implements Album {

    @JSONField(name = "title")
    public String name;

    @JSONField(name = "album_id")
    public String id;

    @JSONField(name = "author")
    public String author;

    @JSONField(name = "artist_id")
    public String artistId;

    public List<BaiduSong> songs;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAlbumId() {
        return id;
    }

    @Override
    public List<? extends Song> getSongs() {
        return songs;
    }

    public void setSongs(List<BaiduSong> songs) {
        this.songs = songs;
    }

    @Override
    public List<? extends Artist> getArtists() {
        ArrayList<BaiduArtist> artists = new ArrayList<>();
        BaiduArtist artist = new BaiduArtist();
        artist.id = this.artistId;
        artist.name = this.author;
        artists.add(artist);
        return artists;
    }

    @Override
    public String getFormattedArtistsString() {
        return SongUtils.getArtistsString(getArtists());
    }

    @Override
    public MusicProvider getMusicProvider() {
        return MusicProvider.Baidu;
    }
}
