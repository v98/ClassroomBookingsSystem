import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.time.LocalDate;

public class RoomAvailability {




    private static HBox addDate() {
        DatePicker dp=new DatePicker();
        dp.setValue(LocalDate.now());
        LocalDate dt=dp.getValue();
        Text date=new Text("Date");
        //Text day=new Text("Day");
        //ObservableList<String> options = FXCollections.observableArrayList("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday");
        //ComboBox dy = new ComboBox(options);//Isme event listener se day set karna h
        HBox gp=new HBox(5);
        dp.setMaxWidth(118);
        //dy.setMaxWidth(118);
        gp.getChildren().addAll(date,dp);

        //gp.add(day, 3, 1);
        //gp.add(dy, 4, 1);
        return(gp);
    }

    private static GridPane addTime() {
        Spinner<Integer> h1=new Spinner<Integer>();
        Spinner<Integer> h2=new Spinner<Integer>();
        Spinner<Integer> m1=new Spinner<Integer>();
        Spinner<Integer> m2=new Spinner<Integer>();
        Text frm = new Text("From");
        Text to=new Text("To");

        SpinnerValueFactory<Integer> hs1=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23,00);
        SpinnerValueFactory<Integer> hs2=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23,00);
        SpinnerValueFactory<Integer> ms1=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,00);
        SpinnerValueFactory<Integer> ms2=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,00);

        h1.setValueFactory(hs1);
        h2.setValueFactory(hs2);
        m1.setValueFactory(ms1);
        m2.setValueFactory(ms2);

        h1.setMaxWidth(50);
        h2.setMaxWidth(50);
        m1.setMaxWidth(50);
        m2.setMaxWidth(50);

        GridPane tmp1=new GridPane();
        GridPane tmp2=new GridPane();
        tmp1.add(h1, 1, 1);
        tmp1.add(m1, 2, 1);
        tmp1.setHgap(10);
        tmp2.add(h2, 1, 1);
        tmp2.add(m2, 2, 1);
        tmp2.setHgap(10);

        GridPane gp=new GridPane();
        gp.add(frm, 1, 1);
        gp.add(tmp1, 2, 1);
        gp.add(to, 3, 1);
        gp.add(tmp2, 4, 1);
        gp.setHgap(10);
        return(gp);
    }

    @SuppressWarnings("rawtypes")
    private static TableView addTable() {
        TableColumn room=new TableColumn("Room");
        TableColumn cap=new TableColumn("Capacity");
        TableColumn avbl=new TableColumn("Available");
        TableView table=new TableView();
        table.getColumns().addAll(room,cap,avbl);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setEditable(false);
        table.setMaxWidth(322);
        table.setMaxHeight(322);
        return(table);
    }

    private static BorderPane addMid() {
        GridPane gp=new GridPane();
        gp.add(addDate(), 1, 2);
        gp.add(addTime(), 1, 1);
        //Button go=new Button("Go!");
        //gp.add(go,2,2);
        gp.setVgap(10);
        gp.setPadding(new Insets(10,10,10,10));
        BorderPane bp=new BorderPane();
        bp.setTop(gp);
        bp.setAlignment(gp, Pos.CENTER);
        bp.setCenter(addTable());
        bp.setAlignment(addTable(),Pos.CENTER);
        bp.setPadding(new Insets(0,10,15,10));
        return(bp);
    }

    public static BorderPane compiler() {
        BorderPane bp=new BorderPane();

        //bp.setLeft(left);
        //bp.setAlignment(left,Pos.CENTER);
        //bp.setTop(top);
        //bp.setAlignment(top,Pos.CENTER);
        VBox filler=new VBox(10);
        filler.getChildren().add(addTime());
        Button go=new Button("GO");
        HBox temp=new HBox(10);
        temp.getChildren().addAll(addDate(),go);
        temp.setAlignment(Pos.CENTER);

        filler.getChildren().addAll(temp,addTable());
        filler.setAlignment(Pos.CENTER);
        bp.setCenter(filler);

        bp.setPadding(new Insets(10,10,10,10));
        return(bp);
    }

//    public static void main(String arp[]) {
//        launch(arp);
//    }
}
