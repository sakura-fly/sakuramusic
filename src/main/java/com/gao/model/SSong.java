package com.gao.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;

public class SSong extends RecursiveTreeObject<SSong> {
    private StringProperty title;
    private StringProperty album;
    private DoubleProperty ltime;
    private StringProperty songer;

    public SSong(String title, String album, double ltime, String songer) {
        this.title = new SimpleStringProperty(title);
        this.album = new SimpleStringProperty(album);
        this.ltime = new SimpleDoubleProperty(ltime);
        this.songer = new SimpleStringProperty(songer);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getAlbum() {
        return album.get();
    }

    public StringProperty albumProperty() {
        return album;
    }

    public void setAlbum(String album) {
        this.album.set(album);
    }

    public double getLtime() {
        return ltime.get();
    }

    public DoubleProperty ltimeProperty() {
        return ltime;
    }

    public void setLtime(double ltime) {
        this.ltime.set(ltime);
    }

    public String getSonger() {
        return songer.get();
    }

    public StringProperty songerProperty() {
        return songer;
    }

    public void setSonger(String songer) {
        this.songer.set(songer);
    }
}
