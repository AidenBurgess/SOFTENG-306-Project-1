package main.java.visualisation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.dotio.TaskGraph;
import main.java.scheduler.InformationHolder;

import java.io.IOException;


/**
 *
 * @todo update methods
 */
public class VisualisationDriver extends Application {
    private static InformationHolder _informationHolder = null;
    private static TaskGraph _taskGraph = null;
    private static int _numProcessors = 0;

    public static void main(InformationHolder informationHolder, TaskGraph taskGraph, int numProcessors) {
        _informationHolder = informationHolder;
        _taskGraph = taskGraph;
        _numProcessors = numProcessors;
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VisualisationDashboard.fxml"));
        Parent root = loader.load();
        VisualisationController controller = loader.getController();
        controller.makeStageDraggable();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        // Make window a fixed size
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        stage.setOnHidden(e -> controller.shutdown());
        stage.show();
    }

    public static InformationHolder getInformationHolder() {
        return _informationHolder;
    }

    public static TaskGraph getTaskGraph() {
        return _taskGraph;
    }

    public static int getNumProcessors() {
        return _numProcessors;
    }
}
