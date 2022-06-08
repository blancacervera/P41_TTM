package com.example.front;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.*;
import javax.swing.*;


import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

enum LevelSelector {
    LEVEL1,
    LEVEL2,
    LEVEL3
}

enum RangeSelector {
    BAJO,
    CONTRALTO,
    TENOR
}

public class HelloApplication extends Application {
    LevelSelector ls;
    RangeSelector rs;
    private boolean testOk = false;

    private void lastscene(Stage stage){
        BorderPane gridborder = new BorderPane();
        GridPane gridPane = new GridPane();
        HBox hboxdesc = new HBox();
        StackPane stack = new StackPane();
        Text description = new Text("CONGRATULATIONS");
        description.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        description.setTextAlignment(TextAlignment.CENTER);
        hboxdesc.setMargin(description, new Insets(50, 0, 0, 230));
        hboxdesc.getChildren().add(description);
        gridborder.setTop(hboxdesc);

        Label notalbl = new Label("X / 10");
        notalbl.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        Label namelbl = new Label("Name: ");
        TextField nametxt = new TextField();
        nametxt.setPrefSize(400, 40);
        Button buttonsave = new Button("SAVE");
        buttonsave.setPrefSize(200, 40);

        Button buttonhome = new Button("HOME");
        buttonhome.setPrefSize(200, 40);
        EventHandler<ActionEvent> homeevent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                firstScene(stage);
            }
        };
        buttonhome.setOnAction(homeevent);

        Button buttonretry = new Button("RETRY");
        buttonretry.setPrefSize(200, 40);
        EventHandler<ActionEvent> retryevent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                thirdscene(stage);
            }
        };
        buttonretry.setOnAction(retryevent);

        Button buttonexit = new Button("EXIT");
        buttonexit.setPrefSize(200, 40);
        EventHandler<ActionEvent> exitevent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                stage.close();
            }
        };
        buttonexit.setOnAction(exitevent);

        GridPane.setMargin(notalbl, new Insets(20, 0, 0, 150));
        GridPane.setMargin(namelbl, new Insets(150, 0, 0, 80));
        GridPane.setMargin(nametxt, new Insets(150, 0, 0, -100));
        GridPane.setMargin(buttonsave, new Insets(150, 0, 0, 80));
        GridPane.setMargin(buttonhome, new Insets(70, 0, 0, 110));
        GridPane.setMargin(buttonretry, new Insets(70, 0, 0, 80));
        GridPane.setMargin(buttonexit, new Insets(70, 0, 0, 60));

        gridPane.add(notalbl, 2, 1);
        gridPane.add(namelbl, 1, 2);
        gridPane.add(nametxt, 2, 2);
        gridPane.add(buttonsave, 3, 2);
        gridPane.add(buttonhome, 1, 3);
        gridPane.add(buttonretry, 2,3);
        gridPane.add(buttonexit, 3, 3);

        gridborder.setCenter(gridPane);
        Scene scene = new Scene(gridborder, 960, 540);
        stage.setScene(scene);
        stage.show();
    }


    private void thirdscene(Stage stage){
        //Crear aqui segunda escena
        StackPane stack = new StackPane();
        Text title = new Text("Music Tune Education");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setTextAlignment(TextAlignment.CENTER);
        HBox hbox = addHBox();

        //Imagen
        String nombreFichero = "imagenes\\\\fotos_intervals\\\\mezzo_soprano\\\\5-1.png";
        String rutaAbsoluta = new File(nombreFichero).getAbsolutePath();
        System.out.println(rutaAbsoluta);
        ImageView imageView = new ImageView(rutaAbsoluta);

        Button buttonsing = new Button("SING");
        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                lastscene(stage);
            }
        };
        buttonsing.setOnAction(event1);
        Media buzzer = new Media(getClass().getResource("/LAmetronome.wav").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(buzzer);
        buttonsing.setOnAction(event -> {
            if(mediaPlayer.getStatus() != MediaPlayer.Status.PLAYING){
                mediaPlayer.play();
            }

        });


        buttonsing.setPrefSize(200, 40);
        GridPane gridPane = new GridPane();
        gridPane.setMargin(imageView, new Insets(150, 0, 0, 150));
        gridPane.add(imageView, 1, 1);
        gridPane.setMargin(buttonsing, new Insets(80, 0, 0, 100));
        gridPane.add(buttonsing, 2, 1);

        /*class JavaSoundRecorder {
            // record duration, in milliseconds
            static final long RECORD_TIME = 16000;  // 16 seconds

            // path of the wav file
            File wavFile = new File("E:/Test/RecordAudio.wav");

            // format of audio file
            AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;

            // the line from which audio data is captured
            TargetDataLine line;

            // Defines an audio format
            AudioFormat getAudioFormat() {
                float sampleRate = 16000;
                int sampleSizeInBits = 8;
                int channels = 2;
                boolean signed = true;
                boolean bigEndian = true;
                AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
                        channels, signed, bigEndian);
                return format;
            }

            //Captures the sound and record into a WAV file
            void start() {
                try {
                    AudioFormat format = getAudioFormat();
                    DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

                    // checks if system supports the data line
                    if (!AudioSystem.isLineSupported(info)) {
                        System.out.println("Line not supported");
                        System.exit(0);
                    }
                    line = (TargetDataLine) AudioSystem.getLine(info);
                    line.open(format);
                    line.start();   // start capturing

                    System.out.println("Start capturing...");

                    AudioInputStream ais = new AudioInputStream(line);

                    System.out.println("Start recording...");

                    // start recording
                    AudioSystem.write(ais, fileType, wavFile);

                } catch (LineUnavailableException ex) {
                    ex.printStackTrace();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }

            // Closes the target data line to finish capturing and recording
            void finish() {
                //line.stop();
                //line.close();
                System.out.println("Finished");
            }

            //Entry to run the program

            public static void main(String[] args) {
                final JavaSoundRecorder recorder = new JavaSoundRecorder();

                // creates a new thread that waits for a specified
                // of time before stopping
                Thread stopper = new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(RECORD_TIME);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        recorder.finish();
                    }
                });

                stopper.start();

                // start recording
                recorder.start();
            }
        }
        */

        BorderPane border = new BorderPane();
        border.setTop(hbox);
        border.setCenter(gridPane);
        Scene scene = new Scene(border, 960, 540);
        stage.setScene(scene);
        stage.show();
    }

    private void secondscene(Stage stage) throws IOException {
        //Crear aqui segunda escena
        BorderPane border = new BorderPane();
        Button testbutton = new Button("TEST");
        Circle testcircle = new Circle(50, Color.WHITE);
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
        //testcircle.setCenterX(674);
        //testcircle.setCenterY(267);
        testbutton.setLayoutX(416);
        testbutton.setLayoutY(300);
        border.setBottom(testbutton);
        border.setRight(testcircle);

        deviceSelection.setOnAction((event) -> {
            int selectedIndex = deviceSelection.getSelectionModel().getSelectedIndex();
            Object selectedItem = deviceSelection.getSelectionModel().getSelectedItem();

            System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
            System.out.println("   ChoiceBox.getValue(): " + deviceSelection.getValue());

            try {
                TargetDataLine mic = AudioSystem.getTargetDataLine(new
                        AudioFormat(44100, 16, 1, true, true), infos_2.get(selectedIndex));
                System.out.println("Device works correctly!!!!");
                testcircle.setFill(Color.rgb(85, 255 , 0));
                this.testOk = true;
            }catch(Exception e){
                testcircle.setFill(Color.RED);
                this.testOk = false;
                System.out.println(e);
            }
        });

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                if (testOk){
                    thirdscene(stage);
                }
            }
        };

        testbutton.setOnAction(event);
        testbutton.setPrefSize(200, 40);
        Scene scene = new Scene(border, 960, 540);
        stage.setScene(scene);
        stage.show();
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

        EventHandler<ActionEvent> helpevent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Alert helpalert = new Alert(Alert.AlertType.INFORMATION);
                helpalert.setTitle("INFORMATION");
                helpalert.setHeaderText("\t\tSOFTWARE DOCUMENTATION");
                helpalert.setContentText("1.First Steps");
                helpalert.showAndWait();
            }
        };
        Button helpButton = new Button("?");
        helpButton.setPrefSize(29, 23);
        helpButton.setOnAction(helpevent);

        stack.getChildren().addAll(helpIcon, helpButton);
        stack.setAlignment(Pos.CENTER_RIGHT);     // Right-justify nodes in stack
        StackPane.setMargin(helpButton, new Insets(1, 1, 0, 0));

        hbox.getChildren().add(stack);            // Add to HBox from Example 1-2
        HBox.setHgrow(stack, Priority.ALWAYS);    // Give stack any extra space

        return hbox;
    }

    private void firstScene(Stage stage){
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
        levelselect.getItems().addAll(LevelSelector.values());
        levelselect.setValue(LevelSelector.values()[0]);
        ChoiceBox rangeselect = new ChoiceBox();
        rangeselect.setPrefSize(200, 40);
        rangeselect.getItems().addAll(RangeSelector.values());
        rangeselect.setValue(RangeSelector.values()[0]);
        Button buttonstart = new Button("START");

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                try {
                    secondscene(stage);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                rs = (RangeSelector) rangeselect.getValue();
                ls = (LevelSelector) levelselect.getValue();
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

    @Override
    public void start(Stage stage) throws IOException {
        firstScene(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}