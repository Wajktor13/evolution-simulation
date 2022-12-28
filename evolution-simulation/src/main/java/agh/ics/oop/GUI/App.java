package agh.ics.oop.GUI;

import agh.ics.oop.SimulationParameters;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;


public class App extends Application {
    private final int settingsSceneWidth = 1400;
    private final int settingsSceneHeight = 800;
    private final int simulationSceneWidth = 1000;
    private final int simulationSceneHeight = 600;
    private Scene settingsScene;

    @Override
    public void start(Stage settingsStage){
        createSettingsScene();
        settingsStage.setScene(settingsScene);
        settingsStage.show();
    }

    private void createSettingsScene() {
        TextField mapWidthInput = new TextField();

        TextField mapHeightInput = new TextField();

        ChoiceBox<String> mapVariantInput = new ChoiceBox<>();
        mapVariantInput.getItems().addAll("Spherical Earth");
        mapVariantInput.getSelectionModel().selectFirst();

        TextField initialPlantsInput = new TextField();

        TextField plantsEnergyInput = new TextField();

        TextField dailyPlantsGrowInput = new TextField();

        ChoiceBox<String> plantsGrowVariantInput = new ChoiceBox<>();
        plantsGrowVariantInput.getItems().addAll("Prefer Equator");
        plantsGrowVariantInput.getSelectionModel().selectFirst();

        TextField initialAnimalsInput = new TextField();

        TextField initialAnimalsEnergyInput = new TextField();

        TextField energyRequiredToReproduceInput = new TextField();

        TextField reproductionEnergyLossInput = new TextField();

        ChoiceBox<String> animalsMoveVariantInput = new ChoiceBox<>();
        animalsMoveVariantInput.getItems().addAll("Full Predestination");
        animalsMoveVariantInput.getSelectionModel().selectFirst();

        TextField minMutationsInput = new TextField();

        TextField maxMutationsInput = new TextField();

        TextField geneLengthInput = new TextField();

        ChoiceBox<String> mutationsVariantInput = new ChoiceBox<>();
        mutationsVariantInput.getItems().addAll("Fully Randomized");
        mutationsVariantInput.getSelectionModel().selectFirst();

        TextField dayDurationInput = new TextField();

        Button startButton = new Button("START");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("MAP WIDTH: "),0,0);
        grid.add(mapWidthInput,1,0);

        grid.add(new Label("MAP HEIGHT: "),0,1);
        grid.add(mapHeightInput,1,1);

        grid.add(new Label("MAP VARIANT"), 0,2);
        grid.add(mapVariantInput,1,2);

        grid.add(new Label("INITIAL PLANTS"), 0,3);
        grid.add(initialPlantsInput,1,3);

        grid.add(new Label("PLANTS ENERGY"), 0,4);
        grid.add(plantsEnergyInput,1,4);

        grid.add(new Label("DAILY PLANTS GROW"), 0,5);
        grid.add(dailyPlantsGrowInput,1,5);

        grid.add(new Label("PLANTS GROW VARIANT"), 0,6);
        grid.add(plantsGrowVariantInput,1,6);

        grid.add(new Label("INITIAL ANIMALS"), 0,7);
        grid.add(initialAnimalsInput,1,7);

        grid.add(new Label("INITIAL ANIMALS ENERGY"), 0,8);
        grid.add(initialAnimalsEnergyInput,1,8);

        grid.add(new Label("ENERGY REQUIRED TO REPRODUCE"), 0,9);
        grid.add(energyRequiredToReproduceInput,1,9);

        grid.add(new Label("REPRODUCTION ENERGY LOSS"), 0,10);
        grid.add(reproductionEnergyLossInput,1,10);

        grid.add(new Label("ANIMALS MOVE VARIANT"), 0,11);
        grid.add(animalsMoveVariantInput,1,11);

        grid.add(new Label("MIN MUTATIONS"), 0,12);
        grid.add(minMutationsInput,1,12);

        grid.add(new Label("MAX MUTATIONS"), 0,13);
        grid.add(maxMutationsInput,1,13);

        grid.add(new Label("GENE LENGTH"), 0,14);
        grid.add(geneLengthInput,1,14);

        grid.add(new Label("MUTATIONS VARIANT"), 0,15);
        grid.add(mutationsVariantInput,1,15);

        grid.add(new Label("DAY DURATION"), 0,16);
        grid.add(dayDurationInput,1,16);

        grid.add(startButton,0,17,2,2);
        GridPane.setHalignment(startButton, HPos.CENTER);
        GridPane.setValignment(startButton, VPos.CENTER);

        startButton.setOnAction(event ->
        {
            SimulationParameters simulationParameters = new SimulationParameters(mapHeightInput.getText(),
                    mapWidthInput.getText(), mapVariantInput.getValue(), initialPlantsInput.getText(),
                    plantsEnergyInput.getText(), dailyPlantsGrowInput.getText(), plantsGrowVariantInput.getValue(),
                    initialAnimalsInput.getText(), initialAnimalsEnergyInput.getText(),
                    energyRequiredToReproduceInput.getText(), reproductionEnergyLossInput.getText(),
                    animalsMoveVariantInput.getValue(), minMutationsInput.getText(), maxMutationsInput.getText(),
                    mutationsVariantInput.getValue(), geneLengthInput.getText(), dayDurationInput.getText());
        });

        settingsScene = new Scene(grid, this.settingsSceneWidth, this.settingsSceneHeight);
    }

}
