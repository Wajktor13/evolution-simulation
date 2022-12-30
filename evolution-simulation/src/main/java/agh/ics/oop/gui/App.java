package agh.ics.oop.gui;

import agh.ics.oop.classes.*;
import agh.ics.oop.enums.MapVariant;
import agh.ics.oop.enums.PlantsGrowVariant;
import agh.ics.oop.interfaces.IPlantsSpawner;
import agh.ics.oop.interfaces.IWorldMap;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.FileNotFoundException;


public class App extends Application {
    private static final int settingsSceneWidth = 475;
    private static final int settingsSceneHeight = 725;
    private static final int simulationSceneWidth = 725;
    private static final int simulationSceneHeight = 725;
    private Scene settingsScene;

    @Override
    public void start(Stage settingsStage){
        createSettingsScene();
        settingsStage.setScene(settingsScene);
        settingsStage.setTitle("Evolution Simulation Settings");
        settingsStage.getIcons().add(new Image("file:src/main/resources/settings.png"));
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

        GridPane settingsGrid = new GridPane();
        settingsGrid.setAlignment(Pos.CENTER);
        settingsGrid.setHgap(10);
        settingsGrid.setVgap(10);
        settingsGrid.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        settingsGrid.add(new Label("PREDEFINED SETTINGS"),0,0);
        settingsGrid.add(loadPredefinedSettingsInput,1,0);

        settingsGrid.add(new Label("MAP WIDTH"),0,1);
        settingsGrid.add(mapWidthInput,1,1);

        settingsGrid.add(new Label("MAP HEIGHT"),0,2);
        settingsGrid.add(mapHeightInput,1,2);

        settingsGrid.add(new Label("MAP VARIANT"), 0,3);
        settingsGrid.add(mapVariantInput,1,3);

        settingsGrid.add(new Label("INITIAL PLANTS"), 0,4);
        settingsGrid.add(initialPlantsInput,1,4);

        settingsGrid.add(new Label("PLANTS ENERGY"), 0,5);
        settingsGrid.add(plantsEnergyInput,1,5);

        settingsGrid.add(new Label("DAILY PLANTS GROW"), 0,6);
        settingsGrid.add(dailyPlantsGrowInput,1,6);

        settingsGrid.add(new Label("PLANTS GROW VARIANT"), 0,7);
        settingsGrid.add(plantsGrowVariantInput,1,7);

        settingsGrid.add(new Label("INITIAL ANIMALS"), 0,8);
        settingsGrid.add(initialAnimalsInput,1,8);

        settingsGrid.add(new Label("INITIAL ANIMALS ENERGY"), 0,9);
        settingsGrid.add(initialAnimalsEnergyInput,1,9);

        settingsGrid.add(new Label("ENERGY REQUIRED TO REPRODUCE"), 0,10);
        settingsGrid.add(energyRequiredToReproduceInput,1,10);

        settingsGrid.add(new Label("REPRODUCTION ENERGY LOSS"), 0,11);
        settingsGrid.add(reproductionEnergyLossInput,1,11);

        settingsGrid.add(new Label("ANIMALS MOVE VARIANT"), 0,12);
        settingsGrid.add(animalsMoveVariantInput,1,12);

        settingsGrid.add(new Label("MIN MUTATIONS"), 0,13);
        settingsGrid.add(minMutationsInput,1,13);

        settingsGrid.add(new Label("MAX MUTATIONS"), 0,14);
        settingsGrid.add(maxMutationsInput,1,14);

        settingsGrid.add(new Label("GENE LENGTH"), 0,15);
        settingsGrid.add(geneLengthInput,1,15);

        settingsGrid.add(new Label("MUTATIONS VARIANT"), 0,16);
        settingsGrid.add(mutationsVariantInput,1,16);

        settingsGrid.add(new Label("DAY DURATION (ms)"), 0,17);
        settingsGrid.add(dayDurationInput,1,17);

        settingsGrid.add(startButton,0,18,2,2);
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

        settingsScene = new Scene(settingsGrid, App.settingsSceneWidth, App.settingsSceneHeight);
    }

    private void createAndRunNewSimulation(SimulationParameters newSimulationParameters){
        IWorldMap newMap;
        IPlantsSpawner newPlantsSpawner;
        GridPane newGrid;
        Stage newSimulationStage = new Stage();

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

        newGrid = this.createNewSimulationWindow(newMap, newSimulationStage);
        newGrid.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        SimulationEngine newSimulationEngine = new SimulationEngine(newMap, newSimulationParameters.dayDuration,
                newSimulationParameters.initialAnimals, newSimulationParameters.initialAnimalEnergy,
                newSimulationParameters.geneLength, newSimulationParameters.minMutations,
                newSimulationParameters.maxMutations, newGrid);

        Thread newEngineThread = new Thread(newSimulationEngine);
        newSimulationStage.setOnHiding( event ->  {newEngineThread.stop();});

        newEngineThread.start();
    }

    private GridPane createNewSimulationWindow(IWorldMap newMap, Stage newSimulationStage){
        Vector2d lowerLeft = newMap.getLowerLeft();
        Vector2d upperRight = newMap.getUpperRight();
        int rows = upperRight.getY() - lowerLeft.getY() + 1;
        int cols = upperRight.getX() - lowerLeft.getX() + 1;
        int cellSize = (int) Math.floor(Math.min(App.calculateCellSize(App.simulationSceneWidth, cols),
                calculateCellSize(App.simulationSceneHeight, rows)));

        newSimulationStage.getIcons().add(new Image("file:src/main/resources/animal.png"));
        newSimulationStage.setTitle("Evolution Simulation");


        GridPane newGrid = new GridPane();
        for (int i = 0; i < cols; i++) newGrid.getColumnConstraints().add(new ColumnConstraints(cellSize));
        for (int i = 0; i < rows; i++) newGrid.getRowConstraints().add(new RowConstraints(cellSize));
        renderGrid(newGrid, newMap);

        Scene newScene = new Scene(newGrid, App.simulationSceneWidth, App.simulationSceneHeight);
        newSimulationStage.setScene(newScene);
        newSimulationStage.show();

        return newGrid;
    }

    public static void renderGrid(GridPane grid, IWorldMap map){
        Vector2d lowerLeft = map.getLowerLeft();
        Vector2d upperRight = map.getUpperRight();
        int rows = upperRight.getY() - lowerLeft.getY() + 1;
        int cols = upperRight.getX() - lowerLeft.getX() + 1;
        int cellSize = (int) Math.floor(Math.min(App.calculateCellSize(App.simulationSceneWidth, cols),
                calculateCellSize(App.simulationSceneHeight, rows)));

        grid.getChildren().clear();

        App.addMapObjectsToGrid(grid, map, rows, lowerLeft.getY(), cols, lowerLeft.getX(), cellSize);
    }

    private static void addMapObjectsToGrid(GridPane grid, IWorldMap map, int rows, int rowsStart, int cols,
                                            int colsStart, int cellSize){
        GuiElementBox elementCreator;
        try {
            elementCreator = new GuiElementBox();
            for (int x = 0; x < cols; x++) {
                for (int y = 0; y < rows; y++) {
                    Object element = map.objectAt(new Vector2d(colsStart + x, rows + rowsStart - y - 1));
                    if (element != null) {
                        VBox sq = elementCreator.getBox(cellSize, (IMapGuiElement) element);
                        grid.add(sq, x, y);
                        GridPane.setHalignment(sq, HPos.CENTER);
                    }
                }
            }
        }
        catch (FileNotFoundException exception){
            System.out.println("Couldn't load files");
        }
    }

    private static double calculateCellSize(int availableLength, int numberOfCells){
        return (availableLength) / numberOfCells;
    }
}
