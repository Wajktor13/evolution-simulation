package agh.ics.oop.gui;

import agh.ics.oop.classes.*;
import agh.ics.oop.enums.MapVariant;
import agh.ics.oop.enums.PlantsGrowVariant;
import agh.ics.oop.interfaces.IPlantsSpawner;
import agh.ics.oop.interfaces.IWorldMap;
import javafx.application.Application;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;


public class App extends Application {
    private final int settingsSceneWidth = 450;
    private final int settingsSceneHeight = 700;
    private final int simulationSceneWidth = 1000;
    private final int simulationSceneHeight = 600;
    private Scene settingsScene;

    @Override
    public void start(Stage settingsStage){
        createSettingsScene();
        settingsStage.setScene(settingsScene);
        settingsStage.setTitle("Evolution Simulation Settings");
        settingsStage.show();
    }

    private void createSettingsScene() {

        ChoiceBox<String> loadPredefinedSettingsInput = new ChoiceBox<>();
        loadPredefinedSettingsInput.getItems().addAll("none", "Predefined Settings 1",
                "Predefined Settings 2");
        loadPredefinedSettingsInput.getSelectionModel().selectFirst();

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

        Button startButton = new Button("START SIMULATION");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("PREDEFINED SETTINGS"),0,0);
        grid.add(loadPredefinedSettingsInput,1,0);

        grid.add(new Label("MAP WIDTH"),0,1);
        grid.add(mapWidthInput,1,1);

        grid.add(new Label("MAP HEIGHT"),0,2);
        grid.add(mapHeightInput,1,2);

        grid.add(new Label("MAP VARIANT"), 0,3);
        grid.add(mapVariantInput,1,3);

        grid.add(new Label("INITIAL PLANTS"), 0,4);
        grid.add(initialPlantsInput,1,4);

        grid.add(new Label("PLANTS ENERGY"), 0,5);
        grid.add(plantsEnergyInput,1,5);

        grid.add(new Label("DAILY PLANTS GROW"), 0,6);
        grid.add(dailyPlantsGrowInput,1,6);

        grid.add(new Label("PLANTS GROW VARIANT"), 0,7);
        grid.add(plantsGrowVariantInput,1,7);

        grid.add(new Label("INITIAL ANIMALS"), 0,8);
        grid.add(initialAnimalsInput,1,8);

        grid.add(new Label("INITIAL ANIMALS ENERGY"), 0,9);
        grid.add(initialAnimalsEnergyInput,1,9);

        grid.add(new Label("ENERGY REQUIRED TO REPRODUCE"), 0,10);
        grid.add(energyRequiredToReproduceInput,1,10);

        grid.add(new Label("REPRODUCTION ENERGY LOSS"), 0,11);
        grid.add(reproductionEnergyLossInput,1,11);

        grid.add(new Label("ANIMALS MOVE VARIANT"), 0,12);
        grid.add(animalsMoveVariantInput,1,12);

        grid.add(new Label("MIN MUTATIONS"), 0,13);
        grid.add(minMutationsInput,1,13);

        grid.add(new Label("MAX MUTATIONS"), 0,14);
        grid.add(maxMutationsInput,1,14);

        grid.add(new Label("GENE LENGTH"), 0,15);
        grid.add(geneLengthInput,1,15);

        grid.add(new Label("MUTATIONS VARIANT"), 0,16);
        grid.add(mutationsVariantInput,1,16);

        grid.add(new Label("DAY DURATION (ms)"), 0,17);
        grid.add(dayDurationInput,1,17);

        grid.add(startButton,0,18,2,2);
        GridPane.setHalignment(startButton, HPos.CENTER);
        GridPane.setValignment(startButton, VPos.CENTER);

        loadPredefinedSettingsInput.setOnAction(event ->
        {
            SimulationParameters predefinedSimulationParameters;
            String predefinedSettingsOption = loadPredefinedSettingsInput.getValue();

            if(predefinedSettingsOption.equals("Predefined Settings 1")){
                predefinedSimulationParameters = SimulationParameters.getPredefinedParameters1();
            } else if (predefinedSettingsOption.equals("Predefined Settings 2")) {
                predefinedSimulationParameters = SimulationParameters.getPredefinedParameters2();
            } else {
                return;
            }

            mapWidthInput.setText(String.valueOf(predefinedSimulationParameters.mapWidth));
            mapHeightInput.setText(String.valueOf(predefinedSimulationParameters.mapHeight));
            mapVariantInput.setValue(String.valueOf(predefinedSimulationParameters.mapVariant));
            initialPlantsInput.setText(String.valueOf(predefinedSimulationParameters.initialPlants));
            plantsEnergyInput.setText(String.valueOf(predefinedSimulationParameters.plantEnergy));
            dailyPlantsGrowInput.setText(String.valueOf(predefinedSimulationParameters.dailyPlantsGrow));
            plantsGrowVariantInput.setValue(String.valueOf(predefinedSimulationParameters.plantsGrowVariant));
            initialAnimalsInput.setText(String.valueOf(predefinedSimulationParameters.initialAnimals));
            initialAnimalsEnergyInput.setText(String.valueOf(predefinedSimulationParameters.initialAnimalEnergy));
            energyRequiredToReproduceInput.setText(String.valueOf(predefinedSimulationParameters.energyRequiredToReproduce));
            reproductionEnergyLossInput.setText(String.valueOf(predefinedSimulationParameters.reproductionEnergyLoss));
            animalsMoveVariantInput.setValue(String.valueOf(predefinedSimulationParameters.animalMoveVariant));
            minMutationsInput.setText(String.valueOf(predefinedSimulationParameters.minMutations));
            maxMutationsInput.setText(String.valueOf(predefinedSimulationParameters.maxMutations));
            geneLengthInput.setText(String.valueOf(predefinedSimulationParameters.geneLength));
            mutationsVariantInput.setValue(String.valueOf(predefinedSimulationParameters.mutationVariant));
            dayDurationInput.setText(String.valueOf(predefinedSimulationParameters.dayDuration));
        });

        startButton.setOnAction(event ->
        {
            SimulationParameters newSimulationParameters = new SimulationParameters(mapHeightInput.getText(),
                    mapWidthInput.getText(), mapVariantInput.getValue(), initialPlantsInput.getText(),
                    plantsEnergyInput.getText(), dailyPlantsGrowInput.getText(), plantsGrowVariantInput.getValue(),
                    initialAnimalsInput.getText(), initialAnimalsEnergyInput.getText(),
                    energyRequiredToReproduceInput.getText(), reproductionEnergyLossInput.getText(),
                    animalsMoveVariantInput.getValue(), minMutationsInput.getText(), maxMutationsInput.getText(),
                    mutationsVariantInput.getValue(), geneLengthInput.getText(), dayDurationInput.getText());

            this.createAndRunNewSimulation(newSimulationParameters);
        });

        settingsScene = new Scene(grid, this.settingsSceneWidth, this.settingsSceneHeight);
    }

    private void createAndRunNewSimulation(SimulationParameters newSimulationParameters){
        IWorldMap newMap;
        IPlantsSpawner newPlantsSpawner;
        GridPane newGrid;

        if (newSimulationParameters.plantsGrowVariant == PlantsGrowVariant.PREFER_EQUATOR){
            newPlantsSpawner = new EquatorPlantsSpawner(newSimulationParameters.mapHeight,
                    newSimulationParameters.equatorHeight);
        } else {
            /*
            another plants spawner variant
             */

            newPlantsSpawner = new EquatorPlantsSpawner(newSimulationParameters.mapHeight,
                    newSimulationParameters.equatorHeight);
        }

        if (newSimulationParameters.mapVariant == MapVariant.SPHERICAL_EARTH){
            newMap = new SphericalWorldMap(newSimulationParameters.mapWidth, newSimulationParameters.mapHeight,
                    newPlantsSpawner, newSimulationParameters.initialPlants, newSimulationParameters.dailyPlantsGrow,
                    newSimulationParameters.plantEnergy, newSimulationParameters.energyRequiredToReproduce,
                    newSimulationParameters.reproductionEnergyLoss);
        } else {
            /*
            another map variant
             */

            newMap = new SphericalWorldMap(newSimulationParameters.mapWidth, newSimulationParameters.mapHeight,
                    newPlantsSpawner, newSimulationParameters.initialPlants, newSimulationParameters.dailyPlantsGrow,
                    newSimulationParameters.plantEnergy, newSimulationParameters.energyRequiredToReproduce,
                    newSimulationParameters.reproductionEnergyLoss);
        }

        newGrid = this.createNewSimulationWindow(newMap);

        SimulationEngine newSimulationEngine = new SimulationEngine(newMap, newSimulationParameters.dayDuration,
                newSimulationParameters.initialAnimals, newSimulationParameters.initialAnimalEnergy,
                newSimulationParameters.geneLength, newSimulationParameters.minMutations,
                newSimulationParameters.maxMutations, newGrid);

        Thread newEngineThread = new Thread(newSimulationEngine);
        newEngineThread.start();
    }

    private GridPane createNewSimulationWindow(IWorldMap map){
        Stage newSimulationStage = new Stage();
        newSimulationStage.setTitle("Evolution Simulation");
        GridPane newGrid = new GridPane();
        renderGrid(newGrid, map);
        Scene newScene = new Scene(newGrid, this.simulationSceneWidth, this.simulationSceneHeight);
        newSimulationStage.setScene(newScene);
        newSimulationStage.show();

        return newGrid;
    }

    public static void renderGrid(GridPane grid, IWorldMap map){
        grid.getChildren().clear();

        Vector2d lowerLeft = map.getLowerLeft();
        Vector2d upperRight = map.getUpperRight();
        int rows = upperRight.getY() - lowerLeft.getY() + 1;
        int cols = upperRight.getX() - lowerLeft.getX() + 1;
        int cellSize = 50;


        for (int i = 0; i < cols; i++) grid.getColumnConstraints().add(new ColumnConstraints(cellSize));
        for (int i = 0; i < rows; i++) grid.getRowConstraints().add(new RowConstraints(cellSize));

        addMapObjectsToGrid(grid, map, rows, lowerLeft.getY(), cols, lowerLeft.getX(), cellSize);
    }

    private static void addMapObjectsToGrid(GridPane grid, IWorldMap map, int rows, int rowsStart, int cols, int colsStart,
                                     int cellSize){
        VBox box;
        for (int x = 0; x < cols; x++){
            for (int y = 0; y < rows; y++){
                Object element = map.objectAt(new Vector2d(colsStart + x, rows + rowsStart - y - 1));
                if (element != null){
                    box = new GuiElementBox(cellSize, (IMapGuiElement) element).getBox();
                    GridPane.setHalignment(box, HPos.CENTER);
                    grid.add(box, x, y, 1, 1);
                }
            }
        }
    }

}
