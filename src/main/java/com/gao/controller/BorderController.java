package com.gao.controller;

import com.gao.model.Order;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSlider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class BorderController {


    @FXML
    private FlowPane top;
    @FXML
    private HBox bottom;
    @FXML
    private FlowPane center;
    @FXML
    private VBox left;

    @FXML
    private ImageView pre;

    @FXML
    private JFXSlider jd;

    @FXML
    private JFXListView<Order> orderList;
    // @FXML
    // FlowPane right;
    private ObservableList<Order> l = FXCollections.observableArrayList();


    public void cl() {
        System.out.println(233);
        l.add(new Order(System.currentTimeMillis() + ""));
    }

    @FXML
    private void initialize() {



        for (int i = 0; i < 20; i++){
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

    }

    @FXML
    public void nextBtn(){
        System.out.println("next");
    }

    @FXML
    public void preBtn(){
        System.out.println("pre");
    }

    @FXML
    public void playBtn(){
        System.out.println("play");
    }

}
