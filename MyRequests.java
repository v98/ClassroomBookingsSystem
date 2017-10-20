
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.scene.Scene;
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

public class MyRequests{

    private static TableView addMid() {
        TableView tb=new TableView();
        TableColumn id=new TableColumn("Sno.");
        TableColumn name=new TableColumn("Room");
        TableColumn rsn=new TableColumn("Reason");
        tb.getColumns().addAll(id,name,rsn);
        tb.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tb.setEditable(false);
        tb.setMaxHeight(300);
        tb.setMaxWidth(300);
        return(tb);
    }
    public static BorderPane compiler() {
        TableView mid=addMid();
        Text tp = new Text("Pending Requests");
        tp.setStyle("-fx-font-weight:bold;-fx-font-size:150%");
        //BorderPane bp=new BorderPane();
        VBox bp=new VBox(10);
        bp.getChildren().add(tp);

//        bp.setLeft(left);
//        bp.setAlignment(left, Pos.CENTER);
        bp.getChildren().add(mid);
        //mid.setAlignment(Pos.CENTER);
        bp.setPadding(new Insets(10,10,10,10));
        BorderPane bp1=new BorderPane();
//        bp1.setTop(top);
//        bp1.setAlignment(top, Pos.CENTER);


        Button cancel=new Button("Cancel Request");

        bp.getChildren().add(cancel);
        bp.setAlignment(Pos.CENTER);
        bp1.setCenter(bp);
        bp1.setAlignment(bp, Pos.CENTER);
        bp1.setPadding(new Insets(10,10,10,10));
        cancel.setAlignment(Pos.CENTER);

        return(bp1);
    }

}