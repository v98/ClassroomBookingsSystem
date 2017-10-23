import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class ViewTimetable {



    private static HBox search() {
        Label cname=new Label("Course");
        ComboBox<String> course=new ComboBox<String>();
        course.getItems().addAll("Course1","Course2","Course3","Course4","Course5");
        Button b1=new Button("GO");
        HBox search=new HBox(10);
        search.getChildren().addAll(cname,course);
        search.setAlignment(Pos.CENTER);
        HBox top=new HBox(70);
        top.getChildren().addAll(search,b1);
        top.setAlignment(Pos.CENTER);
        return top;
    }
    private static TableView addMid() {
        TableView tab=new TableView();


        TableColumn c1=new TableColumn("Monday");
        TableColumn c2=new TableColumn("Tuesday");
        TableColumn c3=new TableColumn("Wednesday");
        TableColumn c4=new TableColumn("Thursday");
        TableColumn c5=new TableColumn("Friday");
        TableColumn c6=new TableColumn("Saturday");

        tab.getColumns().addAll(c1,c2,c3,c4,c5,c6);
        return tab;
    }
    public static BorderPane compiler2() {
        BorderPane tmp1=new BorderPane();
        ListView<String> list = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList ("Day","Timing","Venue");
        list.setItems(items);
        list.setPrefWidth(100);
        list.setPrefHeight(40);

        Label course=new Label("Stream");
        ComboBox<String> streams=new ComboBox<String>();
        streams.getItems().addAll("CSE","CSAM","ECE");

        Label year=new Label("Year");
        ComboBox<String> batch=new ComboBox<String>();
        batch.getItems().addAll("1st year","2nd year","3rd year","4th year");
        HBox left=new HBox(5);
        left.getChildren().addAll(course,streams);
        left.setAlignment(Pos.CENTER);

        HBox right=new HBox(5);
        right.getChildren().addAll(year,batch);
        right.setAlignment(Pos.CENTER);

        Button gen=new Button("Open Timetable");
        HBox top=new HBox(20);
        top.getChildren().addAll(left,right,gen);
        top.setAlignment(Pos.CENTER);


        HBox mid1=search();
        TableView tab=addMid();

        HBox middle=new HBox(5);
        middle.getChildren().addAll(list,tab);
        middle.setAlignment(Pos.CENTER);

        VBox main=new VBox(20);
        main.getChildren().addAll(top,mid1,middle);
        main.setAlignment(Pos.CENTER);

        Text t=new Text("TIMETABLE");
        t.setStyle("-fx-font-weight:bold;-fx-font-size:150%");
        BorderPane bp=new BorderPane();
        bp.setTop(t);
        bp.setCenter(main);
        //bp.setLeft(left);
        bp.setAlignment(t, Pos.CENTER);
        bp.setAlignment(main, Pos.CENTER);
        //bp.setAlignment(left, Pos.CENTER);
        bp.setPadding(new Insets(10,10,10,10));
        return(bp);
    }

    public static BorderPane compiler() {
        BorderPane tmp1=new BorderPane();
        ListView<String> list = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList ("Day","Timing","Venue");
        list.setItems(items);
        list.setPrefWidth(100);
        list.setPrefHeight(40);



        HBox mid1=search();
        TableView tab=addMid();

        HBox middle=new HBox(5);
        middle.getChildren().addAll(list,tab);
        middle.setAlignment(Pos.CENTER);

        VBox main=new VBox(20);
        main.getChildren().addAll(mid1,middle);
        main.setAlignment(Pos.CENTER);

        Text t=new Text("TIMETABLE");
        t.setStyle("-fx-font-weight:bold;-fx-font-size:150%");
        BorderPane bp=new BorderPane();
        bp.setTop(t);
        bp.setCenter(main);
        //bp.setLeft(left);
        bp.setAlignment(t, Pos.CENTER);
        bp.setAlignment(main, Pos.CENTER);
        //bp.setAlignment(left, Pos.CENTER);
        bp.setPadding(new Insets(10,10,10,10));
        return(bp);
    }

}
