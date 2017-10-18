import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
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

public class RoomAvailability extends Application{
	
	@Override
	public void start(Stage arg0) {
		Scene sc=new Scene(compiler(addTop(),addVBox(),addMid()));
		Stage s=new Stage();
		s.setScene(sc);
		s.setTitle("Room Availibility");
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
	private static GridPane addVBox() {
        Button b1=new Button("Time Table");
        Button b2=new Button("Search Courses");
        Button b3=new Button("Room Availability");
        Button b4=new Button("My Requests");
        Button b5=new Button("My Profile");
        
        GridPane v=new GridPane();
        b1.setMaxWidth(Double.MAX_VALUE);
        b2.setMaxWidth(Double.MAX_VALUE);
        b3.setMaxWidth(Double.MAX_VALUE);
        b4.setMaxWidth(Double.MAX_VALUE);
        b5.setMaxWidth(Double.MAX_VALUE);
        v.add(b1,1,1);
        v.add(b2, 1, 2);
        v.add(b3, 1, 3);
        v.add(b4, 1, 4);
        v.add(b5, 1, 5);
        v.setVgap(10);
        v.setPadding(new Insets(0, 0, 10, 0));
        v.setAlignment(Pos.CENTER);    
        return v;
    }
	
	private static GridPane addDate() {
		DatePicker dp=new DatePicker();
		dp.setValue(LocalDate.now());
		LocalDate dt=dp.getValue();
		Text date=new Text("Date");
		Text day=new Text("Day");
		ObservableList<String> options = FXCollections.observableArrayList("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday");
		ComboBox dy = new ComboBox(options);//Isme event listener se day set karna h
		GridPane gp=new GridPane();
		dp.setMaxWidth(118);
		dy.setMaxWidth(118);
		gp.add(date, 1, 1);
		gp.add(dp, 2, 1);
		gp.add(day, 3, 1);
		gp.add(dy, 4, 1);
		gp.setHgap(10);
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
		gp.add(addDate(), 1, 1);
		gp.add(addTime(), 1, 2);
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
	
	public static BorderPane compiler(BorderPane top, GridPane left,BorderPane mid) {
		BorderPane bp=new BorderPane();
		bp.setLeft(left);
		bp.setAlignment(left,Pos.CENTER);
		bp.setTop(top);
		bp.setAlignment(top,Pos.CENTER);
		bp.setCenter(mid);
		bp.setAlignment(mid,Pos.CENTER);
		bp.setPadding(new Insets(10,0,0,10));
		return(bp);
	}
	
	public static void main(String arp[]) {
		launch(arp);
	}
}
