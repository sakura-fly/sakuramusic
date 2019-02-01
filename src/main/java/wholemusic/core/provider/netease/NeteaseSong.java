package wholemusic.core.provider.netease;

import com.alibaba.fastjson.annotation.JSONField;

import wholemusic.core.api.MusicProvider;
import wholemusic.core.model.*;
import wholemusic.core.util.SongUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haohua on 2018/2/9.
 */
@SuppressWarnings("SpellCheckingInspection")
class NeteaseSong extends BaseBean implements Song {

    @JSONField(name = "name")
    public String name;

    @JSONField(name = "id")
    public long songId;

    @JSONField(name = "ar", alternateNames = {"artists",})
    public ArrayList<NeteaseArtist> artists;

    @JSONField(name = "al", alternateNames = {"album",})
    public NeteaseAlbum album;

    private MusicLink musicLink;

    private NeteaseLyric lyric;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSongId() {
        return String.valueOf(songId);
    }

    @Override
    public List<? extends Artist> getArtists() {
        return artists;
    }

    @Override
    public String getFormattedArtistsString() {
        return SongUtils.getArtistsString(this);
    }

    @Override
    public Album getAlbum() {
        return album;
    }

    @Override
    public MusicProvider getMusicProvider() {
        return MusicProvider.Netease;
    }

    @Override
    public MusicLink getMusicLink() {
        return musicLink;
    }

    @Override
    public String getPicUrl() {
        if (album != null) {
            return album.picUrl;
        }
        return null;
    }

    public void setMusicLink(MusicLink musicLink) {
        this.musicLink = musicLink;
    }

    public void setLyric(NeteaseLyric lyric) {
        this.lyric = lyric;
    }

    public NeteaseLyric getLyric() {
        return lyric;
    }
}
