package com.example.flip;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;
import java.util.Scanner;

public class HelloController {

    @FXML private Pane pnJatek;
    @FXML private Label lbSzam;
    @FXML private Label lbSzaz;
    @FXML private Label level;

    private int[]tomb = {1,2,3,4,5};
    private int cur = 0;


    private Label[][] lbTomb = new Label[8][12];
    private char[][] cTomb = new char[8][12];

    public void initialize(){

        for (int i = 0; i < 8; i++){ for (int j = 0; j < 12; j++) {
                lbTomb[i][j] = new Label();
                lbTomb[i][j].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icons/white.png"))));
                lbTomb[i][j].setTranslateY(i*64);
                lbTomb[i][j].setTranslateX(j*64);
                int ii = i; int jj = j;
                lbTomb[i][j].setStyle("-fx-background-color: white;");
                lbTomb[i][j].setOnMouseEntered(e -> lbTomb[ii][jj].setStyle("-fx-background-color: lightgrey;"));
                lbTomb[i][j].setOnMouseExited(e -> lbTomb[ii][jj].setStyle("-fx-background-color: white;"));
                lbTomb[i][j].setOnMouseClicked(e -> katt(ii, jj));
                cTomb[i][j] = 'W';
                pnJatek.getChildren().add(lbTomb[i][j]);
            }
        }
        load("level"+tomb[cur]+".txt");
        level.setText(tomb[cur]+"");
        szamol();
    }

    private void katt(int ii, int jj){
        for (int i = 0; i < 8; i++) {
            if (cTomb[i][jj] == 'W') {
                cTomb[i][jj] = 'R';
                lbTomb[i][jj].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icons/red.png"))));
            }
            else {
                cTomb[i][jj] = 'W';
                lbTomb[i][jj].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icons/white.png"))));
            }
        }
        for (int j = 0; j < 12; j++) {
            if (cTomb[ii][j] == 'W') {
                cTomb[ii][j] = 'R';
                lbTomb[ii][j].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icons/red.png"))));
            }
            else {
                cTomb[ii][j] = 'W';
                lbTomb[ii][j].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icons/white.png"))));
            }
        }
        if (cTomb[ii][jj] == 'W') {
            cTomb[ii][jj] = 'R';
            lbTomb[ii][jj].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icons/red.png"))));
        }
        else {
            cTomb[ii][jj] = 'W';
            lbTomb[ii][jj].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icons/white.png"))));
        }
        szamol();
    }


    private void load(String s){
        pnJatek.getChildren().clear();
        Scanner in = null;
        try{
            in = new Scanner(new File("levels/"+s), "utf-8");
            int x = 0;
            while (in.hasNext()){
                String[] t = in.nextLine().split("");
                for (int j = 0; j < 12; j++){
                    cTomb[x][j] = t[j].charAt(0);
                }
                x++;
            }
            for (int i = 0; i < 8; i++){ for (int j = 0; j < 12; j++) {
                if (cTomb[i][j] == 'W') lbTomb[i][j].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icons/white.png"))));
                else lbTomb[i][j].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icons/red.png"))));
                pnJatek.getChildren().add(lbTomb[i][j]);
            }
            }
        }catch (Exception e){
            System.out.println(e);
        }finally {
            if (in != null) in.close();
        }
    }



    private void szamol(){
        int k = 0;
        for (int i = 0; i < 8; i++){ for (int j = 0; j < 12; j++) {
                if (cTomb[i][j] == 'W') k++;
            }
        }
        lbSzam.setText(k+" db");
        lbSzaz.setText(((k*100)/96)+" %");
        if (((k*100)/96) == 100){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setContentText("Pálya teljesítve!");
            a.setTitle("Flip");
            a.showAndWait();
        }
    }

    @FXML private void onReloadClick(){
        load("level"+tomb[cur]+".txt");
        level.setText(tomb[cur]+"");
        szamol();
    }

    @FXML private void onPrevClick(){
        if (cur > 0) cur--;
        load("level"+tomb[cur]+".txt");
        level.setText(tomb[cur]+"");
        szamol();
    }

    @FXML private void onNextClick(){
        if (cur < tomb.length-1) cur++;
        load("level"+tomb[cur]+".txt");
        level.setText(tomb[cur]+"");
        szamol();
    }
}