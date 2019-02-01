package com.gao.controller;

import com.gao.model.Order;
import com.gao.model.SSong;
import com.gao.service.MService;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


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
    private JFXTreeTableView<SSong> songTable;

    @FXML
    private JFXTextField songFilter;

    @FXML
    private TextField search;

    private ObservableList<Order> l = FXCollections.observableArrayList();

    private ObservableList<SSong> sl = FXCollections.observableArrayList();


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
            sl.add(new SSong("title" + i, "album" + i, i, "songer" + i));
        }
        songTable.setRoot(new RecursiveTreeItem<>(sl, RecursiveTreeObject::getChildren));
        JFXTreeTableColumn<SSong, String> titleColumn = new JFXTreeTableColumn<>("标题");
        titleColumn.setCellValueFactory(param -> {
            if (titleColumn.validateValue(param) && param.getValue().getValue() != null) {
                return param.getValue().getValue().titleProperty();
            } else {
                return titleColumn.getComputedValue(param);
            }
        });



        JFXTreeTableColumn<SSong, String> songerColumn = new JFXTreeTableColumn<>("歌手");
        songerColumn.setCellValueFactory(param -> {
            if (songerColumn.validateValue(param) && param.getValue().getValue() != null) {
                return param.getValue().getValue().songerProperty();
            } else {
                return songerColumn.getComputedValue(param);
            }
        });


        JFXTreeTableColumn<SSong, String> albumColumn = new JFXTreeTableColumn<>("专辑");
        albumColumn.setCellValueFactory(param -> {
            if (albumColumn.validateValue(param) && param.getValue().getValue() != null) {
                return param.getValue().getValue().albumProperty();
            } else {
                return albumColumn.getComputedValue(param);
            }
        });


        JFXTreeTableColumn<SSong, String> ltimeColumn = new JFXTreeTableColumn<>("时长");
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
            // ObservableList<SSong> s2 = FXCollections.observableArrayList();
            // s2.add(new SSong("c", "", 1, ""));
            // sl.clear();
            // sl.addAll(s2);


            songTable.setPredicate(songTreeItem -> {
                final SSong s = songTreeItem.getValue();
                return s.getTitle().toLowerCase().contains(newValue.toLowerCase()) ||
                        s.getSonger().toLowerCase().contains(newValue.toLowerCase())||
                        s.getAlbum().toLowerCase().contains(newValue.toLowerCase());
            });
        });
    }


    private void initSearch(){
        search.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
                System.out.println(search.getText());
                new MService().serach(search.getText(), sl);
            }
        });
    }


    private class rc extends JFXTreeTableRow<SSong> {
        rc() {
            super();
            setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && this.getIndex() < songTable.getCurrentItemsCount()){
                    System.out.println(songTable.getSelectionModel().getSelectedItem().getValue().getTitle());
                }
            });
        }
    }

}
