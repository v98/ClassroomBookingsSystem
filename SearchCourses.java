import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class SearchCourses {


    private static HBox search() {
        Label l1=new Label("Enter Keywords");
        TextField t1=new TextField();
        l1.setMaxWidth(100);
        t1.setMaxWidth(300);
        Button b1=new Button("GO");
        HBox gp=new HBox(10);
        gp.getChildren().addAll(l1,t1,b1);
        gp.setAlignment(Pos.CENTER);
        return gp;
    }
    private static TableView addMid() {
        TableView tab=new TableView();
        TableColumn c1=new TableColumn("Search Results");
        tab.getColumns().addAll(c1);
        tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        return tab;
    }
    public static BorderPane compiler() {
        HBox top=search();

        TableView mid=addMid();
        VBox main =new VBox(20);
        main.getChildren().addAll(top,mid);
        main.setAlignment(Pos.CENTER);

        Text t=new Text("FIND COURSES");
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
