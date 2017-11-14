import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class AdminCourses {


    private static BorderPane addCenter() {
        GridPane gp=new GridPane();
        BorderPane bp=new BorderPane();
        Text head=new Text("COURSES");
        head.setStyle("-fx-font-weight:bold;-fx-font-size:125%;");
        bp.setTop(head);
        bp.setAlignment(head, Pos.TOP_CENTER);

        GridPane bp1=new GridPane();
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
        Button rem=new Button("Remove Course");
        Button edit=new Button("Edit Course");
        Button view=new Button("View Timetable");
        GridPane tmp=new GridPane();
        tmp.add(rem, 1, 1);
        tmp.add(edit, 2, 1);
        tmp.add(view, 3, 1);
        tmp.setHgap(10);
        gp.add(tmp, 2, 3);

        bp.setCenter(gp);
        bp.setAlignment(gp,Pos.CENTER);
        bp.setPadding(new Insets(0,10,10,0));
        edit.setOnAction(e->{
            EditCourse.compiler();
        });
        return bp;
    }

    public static BorderPane compiler() {
        BorderPane b2=addCenter();
        BorderPane b=new BorderPane();
//        b.setTop(b1);
//        b.setLeft(v1);
        b.setCenter(b2);
        b.setPadding(new Insets(10,0,0,0));
        return(b);
    }


}
