package wholemusic.core.api;

import wholemusic.core.model.Album;
import wholemusic.core.model.Song;
import wholemusic.core.model.MusicLink;

import java.io.IOException;
import java.util.List;

/**
 * Created by haohua on 2018/2/8.
 */
public interface MusicApi {

    /**
     * 使用关键词查询歌曲
     *
     * @param keyword
     * @param page
     */
    List<? extends Song> searchMusicSync(String keyword, int page, boolean needLink) throws IOException;

    /**
     * 获取某歌曲id对应的音乐链接
     *
     * @param musicId
     * @return
     * @throws Exception
     */
    MusicLink getMusicLinkByIdSync(String musicId) throws IOException;

    /**
     * 获取某歌曲ids对应的歌曲详细信息
     *
     * @param needLyric
     * @param musicIds
     * @return
     * @throws IOException
     */
    List<? extends Song> getSongDetailInfoByIdsSync(boolean needLyric, String... musicIds) throws IOException;

    /**
     * 获取专辑id对应的专辑信息
     *
     * @param albumId
     * @param needLink
     */
    Album getAlbumInfoByIdSync(String albumId, boolean needLink) throws IOException;
}
