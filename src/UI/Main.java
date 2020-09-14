package UI;

import Logic.JobScheduling;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Arrays;

public class Main extends Application {
    private int noOfJobs;

    @Override
    public void start(Stage primaryStage) throws Exception {

        //HomePage-
        GridPane homeLayout = new GridPane();
        homeLayout.setHgap(10);
        homeLayout.setVgap(10);
        homeLayout.setPadding(new Insets(10));
        homeLayout.setAlignment(Pos.CENTER);
        homeLayout.setPrefSize(500, 400);
        //Elements-
        Label noOfJobsLabel = new Label("Enter No Of Jobs : ");
        noOfJobsLabel.setFont(new Font("Calibri", 18));
        TextField noOfJobsTextField = new TextField();
        Button proceedButton = new Button("Proceed");
        proceedButton.setPrefSize(100, 30);
        proceedButton.setFont(new Font("Calibri", 14));
        homeLayout.add(noOfJobsLabel, 0, 0);
        homeLayout.add(noOfJobsTextField, 1, 0);
        homeLayout.add(proceedButton, 1, 2);

        //InputPage-
        BorderPane inputLayout = new BorderPane();
        //InputPane section of InputPage-
        GridPane inputPane = new GridPane();
        inputPane.setHgap(20);
        inputPane.setVgap(25);
        inputPane.setPadding(new Insets(10));
        Label jobNameLabel = new Label("Job Name");
        jobNameLabel.setMaxWidth(200);
        jobNameLabel.setAlignment(Pos.CENTER);
        jobNameLabel.setFont(new Font("Calibri", 18));
        Label profitLabel = new Label("Profit");
        profitLabel.setMaxWidth(200);
        profitLabel.setAlignment(Pos.CENTER);
        profitLabel.setFont(new Font("Calibri", 18));
        Label deadlineLabel = new Label("Deadline");
        deadlineLabel.setMaxWidth(200);
        deadlineLabel.setAlignment(Pos.CENTER);
        deadlineLabel.setFont(new Font("Calibri", 18));
        inputPane.add(jobNameLabel, 0, 0);
        inputPane.add(profitLabel, 1, 0);
        inputPane.add(deadlineLabel, 2, 0);
        inputPane.setAlignment(Pos.CENTER);
        inputLayout.setCenter(inputPane);
        //BottomButtonPane
        HBox bottomButtonPane = new HBox();
        bottomButtonPane.setAlignment(Pos.CENTER);
        bottomButtonPane.setSpacing(10);
        bottomButtonPane.setPadding(new Insets(10));
        Button calculateButton = new Button("Calculate");
        calculateButton.setPrefSize(200, 30);
        calculateButton.setFont(new Font("Calibri", 16));
        bottomButtonPane.getChildren().add(calculateButton);
        inputLayout.setBottom(bottomButtonPane);

        //Making inputPage scrollable-
        ScrollPane scrollableInputLayout = new ScrollPane(inputLayout);
        scrollableInputLayout.setFitToHeight(true);
        scrollableInputLayout.setFitToWidth(true);

        //Passing homeLayout in Scene and Passing scene in Stage
        Scene homePageScene = new Scene(homeLayout);
        primaryStage.setTitle("Job Scheduling with Deadline");
        primaryStage.setScene(homePageScene);
        primaryStage.show();

        //Array Of TextFields
        TextField[] jobNameTextFields = new TextField[100];
        TextField[] jobProfitTextFields = new TextField[100];
        TextField[] jobDeadlineTextFields = new TextField[100];

        //Proceed Button Event-
        proceedButton.setOnAction(actionEvent -> {
            noOfJobs = Integer.parseInt(noOfJobsTextField.getText());

            for (int i = 1; i <= noOfJobs; i++) {
                jobNameTextFields[i] = new TextField();
                inputPane.add(jobNameTextFields[i], 0, i);

                jobProfitTextFields[i] = new TextField();
                inputPane.add(jobProfitTextFields[i], 1, i);

                jobDeadlineTextFields[i] = new TextField();
                inputPane.add(jobDeadlineTextFields[i], 2, i);
            }

            Scene inputPageScene = new Scene(scrollableInputLayout);
            primaryStage.setScene(inputPageScene);
            primaryStage.setMaximized(true);

        });

        //Calculate Button Event-
        calculateButton.setOnAction(actionEvent -> {
            JobScheduling jobScheduling = new JobScheduling(noOfJobs);
            for (int i = 1; i <= noOfJobs; i++) {
                jobScheduling.addJob(jobNameTextFields[i].getText(), Integer.parseInt(jobProfitTextFields[i].getText()), Integer.parseInt(jobDeadlineTextFields[i].getText()));
            }

            Alert resultAlert = new Alert(Alert.AlertType.INFORMATION);
            resultAlert.setTitle("Result");
            resultAlert.setHeaderText("Sequence: " + Arrays.toString(jobScheduling.getResultJobSequence()));
            resultAlert.setContentText("Profit: " + jobScheduling.getTotalProfit());
            resultAlert.showAndWait();

        });
    }

}
