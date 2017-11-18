
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.scene.Scene;
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

public class PendingRequest extends Application{
    @Override
    public void start(Stage arg0) {
        Scene sc=new Scene(compiler(addTop(),addGPane(),addMid()));
        Stage s=new Stage();
        s.setScene(sc);
        s.setTitle("Pending Requests");
        s.show();
    }
    private static BorderPane addTop(){
        BorderPane bp=new BorderPane();
        Button lo=new Button("Log Out");
        Text txt=new Text("MOCCHI");
        txt.maxWidth(Double.MAX_VALUE);
        bp.setLeft(txt);
        bp.setRight(lo);
        bp.setAlignment(lo, Pos.TOP_LEFT);
        bp.setPadding(new Insets(0,10,0,10));
        return bp;
    }
    private static GridPane addGPane() {
        Button b1=new Button("Time Table");
        Button b2=new Button("Courses");
        Button b3=new Button("Room Availability");
        Button b4=new Button("View Pending Requests");
        Button b5=new Button("Add Student");
        Button b6=new Button("Add Faculty");

        GridPane v=new GridPane();
        b1.setMaxWidth(Double.MAX_VALUE);
        b2.setMaxWidth(Double.MAX_VALUE);
        b3.setMaxWidth(Double.MAX_VALUE);
        b4.setMaxWidth(Double.MAX_VALUE);
        b5.setMaxWidth(Double.MAX_VALUE);
        b6.setMaxWidth(Double.MAX_VALUE);
        v.add(b1,1,1);
        v.add(b2,1,2);
        v.add(b3,1,3);
        v.add(b4,1,4);
        v.add(b5,1,5);
        v.add(b6,1,6);
        v.setPadding(new Insets(10, 20, 10, 10));
        v.setAlignment(Pos.CENTER);
        v.setVgap(10);
        return v;
    }
    private static TableView addMid() {
        TableView tb=new TableView();
        TableColumn id=new TableColumn("ID");
        TableColumn name=new TableColumn("Room");
        TableColumn rsn=new TableColumn("Reason");
        tb.getColumns().addAll(id,name,rsn);
        tb.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tb.setEditable(false);
        tb.setMaxHeight(300);
        tb.setMaxWidth(300);
        return(tb);
    }
    private static BorderPane compiler(BorderPane top,GridPane left,TableView mid) {
        Text tp = new Text("Room Bookings");
        tp.setStyle("-fx-font-weight:bold;-fx-font-size:150%");
        BorderPane bp=new BorderPane();

        bp.setTop(tp);
        bp.setAlignment(tp, Pos.CENTER);
        bp.setLeft(left);
        bp.setAlignment(left, Pos.CENTER);
        bp.setCenter(mid);
        bp.setAlignment(mid, Pos.CENTER);
        bp.setPadding(new Insets(10,10,10,10));
        BorderPane bp1=new BorderPane();
        bp1.setTop(top);
        bp1.setAlignment(top, Pos.CENTER);
        bp1.setCenter(bp);
        bp1.setAlignment(bp, Pos.CENTER);
        bp1.setPadding(new Insets(10,10,10,10));
        GridPane tmp=new GridPane();
//        Button ok=new Button("Accept Request");
//        Button cancel=new Button("Reject Request");
//        tmp.add(ok, 1, 1);
//        tmp.setHgap(10);
//        tmp.add(cancel,2,1);
//        bp1.setBottom(tmp);
        bp1.setAlignment(tmp, Pos.CENTER);

        return(bp1);
    }
    public static void main(String ar[]) {
        launch(ar);
    }
}
