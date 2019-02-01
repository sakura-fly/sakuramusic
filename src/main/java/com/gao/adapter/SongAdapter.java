package com.gao.adapter;

import com.gao.model.SSong;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter {

    public SSong adaptiveSong(wholemusic.core.model.Song song) {

        return new SSong(song.getName(), song.getAlbum().getName(), 0, song.getArtists().toString());

    }


    public List<SSong> adaptiveSong(List<? extends wholemusic.core.model.Song> songs) {
        List<SSong> r = new ArrayList<>();
        for (wholemusic.core.model.Song song : songs) {
            StringBuilder songers = new StringBuilder(song.getArtists().get(0).getName());
            for (int i = 1; i < song.getArtists().size(); i++){
                songers.append("/").append(song.getArtists().get(i).getName());

            }
            r.add(new SSong(song.getName(), song.getAlbum().getName(), 0, songers.toString()));
        }
        return r;

    }


}
