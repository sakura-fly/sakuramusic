package com.gao.service;

import com.gao.adapter.SongAdapter;
import com.gao.model.SSong;
import javafx.collections.ObservableList;
import wholemusic.core.api.MusicApi;
import wholemusic.core.model.Song;
import wholemusic.core.provider.netease.NeteaseMusicApi;

import java.io.IOException;
import java.util.List;

public class MService {


    public void serach(String q, ObservableList<SSong> sl) {
        MusicApi api = new NeteaseMusicApi();
        new Thread(() -> {
            List<? extends Song> r;
            try {
                r = api.searchMusicSync(q, 0, true);
                // System.out.println(JSONObject.toJSON());
                sl.clear();
                sl.addAll(new SongAdapter().adaptiveSong(r));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();


    }

}
