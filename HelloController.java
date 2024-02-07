package com.example.doga;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML
    private Pane pnJatek;
    @FXML
    private Label lbUp;
    @FXML
    private Label lbRight;
    @FXML
    private Label lbDown;
    @FXML
    private Label lbLeft;


    private Label[][] lbtomb = new Label[8][10];
    private char[][] chtomb = new char[8][10];
    public char[] f = {'U', 'R', 'D', 'L'};


    public void initialize() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 10; y++) {
                lbtomb[x][y] = new Label();
                int random = (int) (Math.random() * 4);
                lbtomb[x][y].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icon" + f[random] + ".png"))));
                chtomb[x][y] = f[random];
                lbtomb[x][y].setTranslateX(y * 72);
                lbtomb[x][y].setTranslateY(x * 72);
                int xx = x;
                int yy = y;
                lbtomb[x][y].setOnMouseEntered(e -> lbtomb[xx][yy].setStyle("-fx-background-color:lightblue;"));
                lbtomb[x][y].setOnMouseExited(e -> lbtomb[xx][yy].setStyle("-fx-background-color:white;"));
                lbtomb[x][y].setOnMouseClicked(e -> katt(xx, yy));
                pnJatek.getChildren().add(lbtomb[x][y]);
            }
        }
        count();
    }

    public void katt(int xx, int yy) {
        int rand = (int) (Math.random() * 4);
        if (xx > 0 && chtomb[xx][yy] == 'U') {
            lbtomb[xx - 1][yy].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icon" + f[rand] + ".png"))));
            chtomb[xx - 1][yy] = f[rand];
        } else if (yy < 9 && chtomb[xx][yy] == 'R') {
            lbtomb[xx][yy + 1].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icon" + f[rand] + ".png"))));
            chtomb[xx][yy + 1] = f[rand];
        } else if (xx < 7 && chtomb[xx][yy] == 'D') {
            lbtomb[xx + 1][yy].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icon" + f[rand] + ".png"))));
            chtomb[xx + 1][yy] = f[rand];
        } else if (yy > 0 && chtomb[xx][yy] == 'L') {
            lbtomb[xx][yy - 1].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icon" + f[rand] + ".png"))));
            chtomb[xx][yy - 1] = f[rand];
        }

        count();
        win();
    }

    public void reset() {
        pnJatek.getChildren().clear();
        initialize();
    }

    int up = 0;
    int right = 0;
    int down = 0;
    int left = 0;

    public void count() {
        up = 0;
        right = 0;
        down = 0;
        left = 0;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 10; y++) {
                if (chtomb[x][y] == 'U') {
                    up++;
                    lbUp.setText(up + " db");
                } else if (chtomb[x][y] == 'R') {
                    right++;
                    lbRight.setText(right + " db");
                } else if (chtomb[x][y] == 'D') {
                    down++;
                    lbDown.setText(down + " db");
                } else if (chtomb[x][y] == 'L') {
                    left++;
                    lbLeft.setText(left + " db");
                }
            }
        }
    }

    public void win() {
        if (up == down && left == right && left == up) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Mindegyikből azonos számú van!");
            alert.setTitle("Gömbök");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    public void hatter() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 10; y++) {
                if(chtomb[x][y]=='U'){
                    lbtomb[x][y].setStyle("-fx-background-color:orange;");
                }

            }
        }

    }
    public void vissza(){
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 10; y++) {
                if(chtomb[x][y]=='U'){
                    lbtomb[x][y].setStyle("-fx-background-color:white;");
                }

            }
        }
    }
}
