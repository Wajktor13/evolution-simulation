package agh.ics.oop.gui;


import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class GuiElementBox {
    private final VBox box = new VBox();

    public GuiElementBox(int cellSize, IMapGuiElement element){
        try {
            int imageSize = (int) Math.floor(cellSize * 0.75);

            Image image = new Image(new FileInputStream(element.getImageUrl()));
            ImageView view = new ImageView(image);
            view.setFitHeight(imageSize);
            view.setFitWidth(imageSize);

            this.box.getChildren().add(view);
            this.box.setAlignment(Pos.CENTER);

        } catch (FileNotFoundException err) {
            System.out.printf(err + "'%s'%n", element.getImageUrl());
        }
    }

    public VBox getBox(){
        return this.box;
    }
}