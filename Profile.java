import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class Profile  {


    public static BorderPane addCenter(){
        BorderPane bp=new BorderPane();
        GridPane gp=new GridPane();
        VBox sp=new VBox(10);
        Text head=new Text("PROFILE");
        head.setStyle("-fx-font-weight:bold;-fx-font-size:125%;");
        sp.getChildren().add(head);
        //sp.setAlignment(head, Pos.TOP_CENTER);
        Text name=new Text("Name");
        Text id=new Text("ID");
        Text email=new Text("Email ID");

        TextField t1=new TextField();
        TextField t2=new TextField();
        TextField t3=new TextField();
        t1.setEditable(false);
        t2.setEditable(false);
        t3.setEditable(false);

        gp.add(name, 1, 1);
        gp.add(id, 1, 2);
        gp.add(email, 1, 3);

        gp.add(t1, 2, 1);
        gp.add(t2, 2, 2);
        gp.add(t3, 2, 3);

        gp.setHgap(10);
        gp.setVgap(5);

        sp.getChildren().add(gp);
        //sp.setAlignment(gp,Pos.CENTER);

        Button vc=new Button("View Courses");
        vc.setOnAction(e ->{
            ViewCourses.addCenter();
        });
        sp.getChildren().add(vc);
        //sp.setAlignment(vc, Pos.BOTTOM_CENTER);
        sp.setPadding(new Insets(10,20,10,10));
        sp.setAlignment(Pos.CENTER);
        bp.setCenter(sp);
        return(bp);
    }


}

