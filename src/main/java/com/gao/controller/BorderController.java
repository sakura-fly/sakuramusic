package com.gao.controller;

import com.gao.model.Order;
import com.gao.model.Song;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jdk.nashorn.internal.objects.NativeJava;


public class BorderController {


    @FXML
    private HBox top;
    @FXML
    private HBox bottom;
    @FXML
    private VBox center;
    @FXML
    private VBox left;

    @FXML
    private ImageView pre;

    @FXML
    private JFXSlider jd;

    @FXML
    private JFXListView<Order> orderList;

    @FXML
    private JFXTreeTableView<Song> songTable;

    @FXML
    private JFXTextField songFilter;

    @FXML
    private TextField search;

    private ObservableList<Order> l = FXCollections.observableArrayList();

    private ObservableList<Song> sl = FXCollections.observableArrayList();


    @FXML
    private void initialize() {





        for (int i = 0; i < 5; i++) {
            l.add(new Order("item" + i));
        }


        orderList.setCellFactory(param -> new JFXListCell<Order>() {
            @Override
            protected void updateItem(Order item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item.getName());
                }
            }
        });
        orderList.setItems(l);

        orderList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> System.out.println(newValue.getName()));


        jd.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
        });

        initSongTable();

        initSongFilter();

        initSearch();

    }

    @FXML
    public void nextBtn() {
        System.out.println("next");
    }

    @FXML
    public void preBtn() {
        System.out.println("pre");
    }

    @FXML
    public void playBtn() {
        System.out.println("play");
    }


    private void initSongTable() {


        for (int i = 0; i < 6; i++) {
            sl.add(new Song("title" + i, "album" + i, i, "songer" + i));
        }
        songTable.setRoot(new RecursiveTreeItem<>(sl, RecursiveTreeObject::getChildren));
        JFXTreeTableColumn<Song, String> titleColumn = new JFXTreeTableColumn<>("标题");
        titleColumn.setCellValueFactory(param -> {
            if (titleColumn.validateValue(param) && param.getValue().getValue() != null) {
                return param.getValue().getValue().titleProperty();
            } else {
                return titleColumn.getComputedValue(param);
            }
        });



        JFXTreeTableColumn<Song, String> songerColumn = new JFXTreeTableColumn<>("歌手");
        songerColumn.setCellValueFactory(param -> {
            if (songerColumn.validateValue(param) && param.getValue().getValue() != null) {
                return param.getValue().getValue().songerProperty();
            } else {
                return songerColumn.getComputedValue(param);
            }
        });


        JFXTreeTableColumn<Song, String> albumColumn = new JFXTreeTableColumn<>("专辑");
        albumColumn.setCellValueFactory(param -> {
            if (albumColumn.validateValue(param) && param.getValue().getValue() != null) {
                return param.getValue().getValue().albumProperty();
            } else {
                return albumColumn.getComputedValue(param);
            }
        });


        JFXTreeTableColumn<Song, String> ltimeColumn = new JFXTreeTableColumn<>("时长");
        ltimeColumn.setCellValueFactory(param -> {
            if (ltimeColumn.validateValue(param) && param.getValue().getValue() != null) {
                return new SimpleStringProperty(String.valueOf(param.getValue().getValue().getLtime()));
            } else {
                return ltimeColumn.getComputedValue(param);
            }
        });


        songTable.getColumns().addAll(titleColumn, songerColumn, albumColumn, ltimeColumn);

        songTable.setRowFactory(param -> new rc());



    }

    private void initSongFilter() {
        songFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            // ObservableList<Song> s2 = FXCollections.observableArrayList();
            // s2.add(new Song("c", "", 1, ""));
            // sl.clear();
            // sl.addAll(s2);


            songTable.setPredicate(songTreeItem -> {
                final Song s = songTreeItem.getValue();
                return s.getTitle().contains(newValue) ||
                        s.getSonger().contains(newValue)||
                        s.getAlbum().contains(newValue);
            });
        });
    }


    private void initSearch(){
        search.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
                System.out.println(search.getText());
            }
        });
    }


    private class rc extends JFXTreeTableRow<Song> {
        rc() {
            super();
            setOnMouseClicked(event -> {
                System.out.println(songTable.getCurrentItemsCount());
                if (this.getIndex() < songTable.getCurrentItemsCount()){

                    System.out.println(songTable.getSelectionModel().getSelectedItem().getValue());
                }
                System.out.println(this.getIndex());
            });
        }
    }

}
