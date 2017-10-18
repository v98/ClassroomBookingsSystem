
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;

public class HomeScreen extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Scene sc=new Scene(addBPane(addVBox()));
		Stage s=new Stage();
		s.setScene(sc);
		s.show();	
	}
	
	private static VBox addVBox() {
		Text t1=new Text("Username");
		Button b1=new Button("Time Table");
		Button b2=new Button("Search Courses");
		Button b3=new Button("Room Availability");
		Button b4=new Button("My Requests");
		Button b5=new Button("My Profile");
		
		VBox v=new VBox();
		t1.maxWidth(Double.MAX_VALUE);
		b1.setMaxWidth(Double.MAX_VALUE);
		b2.setMaxWidth(Double.MAX_VALUE);
		b3.setMaxWidth(Double.MAX_VALUE);
		b4.setMaxWidth(Double.MAX_VALUE);
		b5.setMaxWidth(Double.MAX_VALUE);
		v.getChildren().addAll(t1,b1,b2,b3,b4,b5);
		v.setPadding(new Insets(0, 20, 0, 0));
		v.setAlignment(Pos.CENTER);
		return v;
	}
	
	private static BorderPane addBPane(VBox v) {
		BorderPane bp=new BorderPane();
		Button lo=new Button("Log Out");
		Text txt=new Text("Welcome to ClassRoom Booking System :)");
		bp.setLeft(v);
		bp.setRight(lo);
		bp.setCenter(txt);
		bp.setAlignment(lo, Pos.TOP_LEFT);
		bp.setAlignment(txt,Pos.CENTER);
		bp.setPadding(new Insets(10,10,10,10));
		return(bp);
	}

	public static void main(String ar[]) {
		launch(ar);
	}
}