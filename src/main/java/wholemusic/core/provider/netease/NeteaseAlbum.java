package wholemusic.core.provider.netease;

import com.alibaba.fastjson.annotation.JSONField;
import wholemusic.core.api.MusicProvider;
import wholemusic.core.model.Album;
import wholemusic.core.model.Artist;
import wholemusic.core.model.BaseBean;
import wholemusic.core.model.Song;
import wholemusic.core.util.SongUtils;

import java.util.List;

/**
 * Created by haohua on 2018/2/11.
 */
@SuppressWarnings("SpellCheckingInspection")
class NeteaseAlbum extends BaseBean implements Album {

    @JSONField(name = "name")
    public String name;

    @JSONField(name = "id")
    public long id;

    @JSONField(name = "artists")
    public List<NeteaseArtist> artists;

    public List<NeteaseSong> songs;

    @JSONField(name = "picUrl")
    public String picUrl;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAlbumId() {
        return String.valueOf(id);
    }

    @Override
    public List<? extends Song> getSongs() {
        return songs;
    }

    public void setSongs(List<NeteaseSong> songs) {
        this.songs = songs;
    }

    @Override
    public List<? extends Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<NeteaseArtist> artists) {
        this.artists = artists;
    }

    @Override
    public String getFormattedArtistsString() {
        return SongUtils.getArtistsString(getArtists());
    }

    @Override
    public MusicProvider getMusicProvider() {
        return MusicProvider.Netease;
    }
}
