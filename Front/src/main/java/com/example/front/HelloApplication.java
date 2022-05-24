package com.example.front;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.applet.AudioClip;
import java.io.IOException;
import java.util.LinkedList;

public class HelloApplication extends Application {
    private Scene lastscene(Stage stage){
        BorderPane gridborder = new BorderPane();
        GridPane gridPane = new GridPane();
        HBox hboxdesc = new HBox();
        StackPane stack = new StackPane();
        Text description = new Text("CONGRATULATIONS");
        description.setTextAlignment(TextAlignment.CENTER);
        hboxdesc.setMargin(description, new Insets(20, 0, 0, 200));
        hboxdesc.getChildren().add(description);
        gridborder.setTop(hboxdesc);

        Button buttonhome = new Button("HOME");
        buttonhome.setPrefSize(200, 40);
        Button buttonretry = new Button("RETRY");
        buttonretry.setPrefSize(200, 40);
        Button buttonexit = new Button("EXIT");
        buttonexit.setPrefSize(200, 40);

        GridPane.setMargin(buttonhome, new Insets(380, 0, 0, 80));
        GridPane.setMargin(buttonretry, new Insets(380, 0, 0, 80));
        GridPane.setMargin(buttonexit, new Insets(380, 0, 0, 80));

        gridPane.add(buttonhome, 1, 1);
        gridPane.add(buttonretry, 2,1);
        gridPane.add(buttonexit, 3, 1);

        gridborder.setCenter(gridPane);
        Scene scene = new Scene(gridborder, 960, 540);
        return scene;
    }


    private Scene thirdscene(Stage stage){
        //Crear aqui segunda escena
        StackPane stack = new StackPane();
        Text title = new Text("Music Tune Education");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setTextAlignment(TextAlignment.CENTER);
        HBox hbox = new HBox();
        hbox.setMargin(title, new Insets(0, 0, 0, 350));
        hbox.getChildren().add(title);


        Button buttonsing = new Button("SING");
        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Scene scene = lastscene(stage);
                stage.setTitle("Tutorial JavaFX");
                stage.setScene(scene);
                stage.show();
            }
        };
        buttonsing.setOnAction(event1);

        /*AudioClip buzzer = new AudioClip(getClass().getResource("/descargas/LAsound.mp3").toExternalForm()) {
            @Override
            public void play() {

            }
            @Override
            public void loop() {

            }
            @Override
            public void stop() {

            }
        };
        buttonsing.setOnAction(event ->{
            buzzer.play();
        });*/

        buttonsing.setPrefSize(200, 40);
        GridPane gridPane = new GridPane();
        gridPane.setMargin(buttonsing, new Insets(180, 0, 0, 150));
        gridPane.add(buttonsing, 3, 1);


        BorderPane border = new BorderPane();
        border.setCenter(gridPane);
        Scene scene = new Scene(border, 960, 540);
        return scene;
    }

    private Scene secondscene(Stage stage){
        //Crear aqui segunda escena
        BorderPane border = new BorderPane();
        Button buttonchange = new Button("TEST");
        Mixer.Info[] infos = AudioSystem.getMixerInfo(); //tenemos instancias de los dispositivos de audio instalados en el pc.
        LinkedList<Mixer.Info> infos_2 = new LinkedList<>();
        for(Mixer.Info info: infos) {
            if (info.getName().startsWith("Port") == false){
                infos_2.add(info);
            }
        }

        ChoiceBox deviceSelection = new ChoiceBox();
        deviceSelection.setPrefSize(200, 40);
        deviceSelection.getItems().addAll(infos_2);
        deviceSelection.setValue(infos_2.getFirst()); //by default the first MIDI device.
        border.setCenter(deviceSelection);

        deviceSelection.setOnAction((event) -> {
            int selectedIndex = deviceSelection.getSelectionModel().getSelectedIndex();
            Object selectedItem = deviceSelection.getSelectionModel().getSelectedItem();

            System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
            System.out.println("   ChoiceBox.getValue(): " + deviceSelection.getValue());

            try {
                TargetDataLine mic = AudioSystem.getTargetDataLine(new
                        AudioFormat(44100, 16, 1, true, true), infos_2.get(selectedIndex));
                System.out.println("Device works correctly!!!!");
            }catch(Exception e){
                System.out.println(e);
            }
        });



        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Scene scene = thirdscene(stage);
                stage.setTitle("Tutorial JavaFX");
                stage.setScene(scene);
                stage.show();
            }
        };

        buttonchange.setOnAction(event);
        buttonchange.setPrefSize(200, 40);
        border.setTop(buttonchange);
        Scene scene = new Scene(border, 960, 540);
        return scene;
    }


    private  HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        StackPane stack = new StackPane();
        Text title = new Text("Music Tune Education");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setTextAlignment(TextAlignment.CENTER);
        hbox.setMargin(title, new Insets(0, 0, 0, 350));
        hbox.getChildren().add(title);

        Rectangle helpIcon = new Rectangle(30.0, 25.0);
        helpIcon.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
                new Stop[]{
                        new Stop(0, Color.web("#4977A3")),
                        new Stop(0.5, Color.web("#B0C6DA")),
                        new Stop(1,Color.web("#9CB6CF")),}));
        helpIcon.setStroke(Color.web("#D0E6FA"));
        helpIcon.setArcHeight(3.5);
        helpIcon.setArcWidth(3.5);

        Text helpText = new Text("?");
        helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        helpText.setFill(Color.WHITE);
        helpText.setStroke(Color.web("#7080A0"));

        stack.getChildren().addAll(helpIcon, helpText);
        stack.setAlignment(Pos.CENTER_RIGHT);     // Right-justify nodes in stack
        StackPane.setMargin(helpText, new Insets(0, 10, 0, 0));

        hbox.getChildren().add(stack);            // Add to HBox from Example 1-2
        HBox.setHgrow(stack, Priority.ALWAYS);    // Give stack any extra space

        return hbox;
    }

    @Override
    public void start(Stage stage) throws IOException {
        BorderPane border = new BorderPane();
        HBox hbox = addHBox();
        border.setTop(hbox);

        BorderPane gridborder = new BorderPane();
        border.setCenter(gridborder);
        GridPane gridPane = new GridPane();
        HBox hboxdesc = new HBox();
        StackPane stack = new StackPane();
        Text description = new Text("Description");
        description.setTextAlignment(TextAlignment.CENTER);
        hboxdesc.setMargin(description, new Insets(20, 0, 0, 200));
        hboxdesc.getChildren().add(description);
        gridborder.setTop(hboxdesc);

        ChoiceBox levelselect = new ChoiceBox();
        levelselect.setPrefSize(200, 40);
        levelselect.getItems().addAll("Level 1", "Level 2", "Level 3");
        levelselect.setValue("Level 1");
        ChoiceBox rangeselect = new ChoiceBox();
        rangeselect.setPrefSize(200, 40);
        rangeselect.getItems().addAll("Bajo", "Contralto", "Tenor", "Bajo");
        rangeselect.setValue("Bajo");
        Button buttonstart = new Button("START");

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Scene scene = secondscene(stage);
                stage.setTitle("Tutorial JavaFX");
                stage.setScene(scene);
                stage.show();
            }
        };

        buttonstart.setOnAction(event);
        buttonstart.setPrefSize(200, 40);

        GridPane.setMargin(levelselect, new Insets(180, 0, 0, 80));
        GridPane.setMargin(rangeselect, new Insets(180, 0, 0, 40));
        GridPane.setMargin(buttonstart, new Insets(180, 0, 0, 150));

        gridPane.add(levelselect, 1, 1);
        gridPane.add(rangeselect, 2,1);
        gridPane.add(buttonstart, 3, 1);

        gridborder.setCenter(gridPane);

        Scene scene = new Scene(border, 960, 540);
        stage.setTitle("Music Tune education!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}