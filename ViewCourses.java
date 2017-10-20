import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class ViewCourses{

//    @Override
//    public void start(Stage arg0) {
//        Scene sc=new Scene(compiler(addTop(),addVBox(),addCenter()));
//        Stage s=new Stage();
//        s.setScene(sc);
//        s.setTitle("My Courses");
//        s.show();
//    }

//    private static BorderPane addTop(){
//        BorderPane bp=new BorderPane();
//        Button lo=new Button("Log Out");
//        Text txt=new Text("MOCCHI");
//        txt.maxWidth(Double.MAX_VALUE);
//        bp.setLeft(txt);
//        bp.setRight(lo);
//        bp.setAlignment(lo, Pos.TOP_LEFT);
//        bp.setPadding(new Insets(0,10,0,10));
//        return bp;
//    }
//    private static VBox addVBox() {
//        Button b1=new Button("Time Table");
//        Button b2=new Button("Search Courses");
//        Button b3=new Button("Room Availability");
//        Button b4=new Button("My Requests");
//        Button b5=new Button("My Profile");
//
//        VBox v=new VBox();
//        b1.setMaxWidth(Double.MAX_VALUE);
//        b2.setMaxWidth(Double.MAX_VALUE);
//        b3.setMaxWidth(Double.MAX_VALUE);
//        b4.setMaxWidth(Double.MAX_VALUE);
//        b5.setMaxWidth(Double.MAX_VALUE);
//        v.getChildren().addAll(b1,b2,b3,b4,b5);
//        v.setPadding(new Insets(10, 20, 10, 10));
//        v.setAlignment(Pos.CENTER);
//
//        return v;
//    }

    public static void addCenter() {


        GridPane gp=new GridPane();
        VBox bp=new VBox(10);
        Text head=new Text("MY COURSES");
        head.setStyle("-fx-font-weight:bold;-fx-font-size:125%;");
        bp.getChildren().add(head);

        Button rem=new Button("Remove Course");
        Button done=new Button("Done");
        //GridPane bp1=new GridPane();
        Text crs=new Text("Courses");
        Text det=new Text("Course Details");
        ListView<String> list = new ListView<String>();
        ObservableList<String> items =FXCollections.observableArrayList ("Course 1", "Course 2", "Course 3", "Course 4");
        list.setItems(items);
        list.setPrefWidth(100);
        list.setPrefHeight(70);
        TextArea t2=new TextArea();
        t2.setEditable(false);
        t2.setPrefHeight(150);
        t2.setPrefWidth(280);

        gp.setHgap(5);
        gp.setVgap(5);
        gp.add(crs, 1, 1);
        gp.add(det, 2, 1);
        gp.add(list, 1, 2);
        gp.add(t2, 2, 2);
        gp.add(rem, 2, 3);

        bp.getChildren().add(gp);

        HBox lower=new HBox(10);
        lower.getChildren().addAll(rem,done);
        lower.setAlignment(Pos.CENTER);
        bp.getChildren().add(lower);
        bp.setAlignment(Pos.CENTER);
        bp.setPadding(new Insets(10,10,10,10));


        Scene scene1=new Scene(bp,300,300);
        Stage courses=new Stage();
        courses.setTitle("Classroom Booking System: My Courses");

        courses.setScene(scene1);
        done.setOnAction(e ->courses.close());
        courses.show();

    }


}
