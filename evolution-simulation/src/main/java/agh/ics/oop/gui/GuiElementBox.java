package agh.ics.oop.gui;


import agh.ics.oop.classes.Animal;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class GuiElementBox {
    Image imageAnimalFull = null;
    Image imageAnimalHalf = null;
    Image imageAnimalLow = null;
    Image imagePlant = null;


    public GuiElementBox() throws FileNotFoundException {

        try {
            this.imageAnimalFull = new Image(new FileInputStream("src/main/resources/animal_full_hp.png"));
            this.imageAnimalHalf = new Image(new FileInputStream("src/main/resources/animal_half_hp.png"));
            this.imageAnimalLow = new Image(new FileInputStream("src/main/resources/animal_low_hp.png"));
            this.imagePlant = new Image(new FileInputStream("src/main/resources/plant.png"));
        } catch (Exception e) {
            System.out.println("Error while loading files: " + e);
        }
    }

    public VBox getBox(int cellSize, IMapGuiElement element) {
        ImageView elementView;

        int imageSize = (int) Math.floor(cellSize * 0.75);

        if (element instanceof Animal) {
            if (((Animal) element).getEnergy() < ((Animal) element).maxEnergy * 0.25)
                elementView = new ImageView(imageAnimalLow);
            else if (((Animal) element).getEnergy() < ((Animal) element).maxEnergy * 0.75)
                elementView = new ImageView(imageAnimalHalf);
            else
                elementView = new ImageView(imageAnimalFull);
        } else {
            elementView = new ImageView(imagePlant);
        }
        elementView.setFitHeight(imageSize);
        elementView.setFitWidth(imageSize);

        VBox box = new VBox();

        box.getChildren().addAll(elementView);
        box.setAlignment(Pos.CENTER);
        return box;
    }
}