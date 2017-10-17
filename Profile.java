import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class Profile extends Application {
	@Override
	public void start(Stage arg0) throws Exception {
		Scene sc=new Scene(compiler(addTop(),addVBox(),addCenter()));
		Stage s=new Stage();
		s.setScene(sc);
		s.setTitle("My Profile");
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
	private static VBox addVBox() {
        Button b1=new Button("Time Table");
        Button b2=new Button("Search Courses");
        Button b3=new Button("Room Availability");
        Button b4=new Button("My Requests");
        Button b5=new Button("My Profile");
        
        VBox v=new VBox();
        b1.setMaxWidth(Double.MAX_VALUE);
        b2.setMaxWidth(Double.MAX_VALUE);
        b3.setMaxWidth(Double.MAX_VALUE);
        b4.setMaxWidth(Double.MAX_VALUE);
        b5.setMaxWidth(Double.MAX_VALUE);
        v.getChildren().addAll(b1,b2,b3,b4,b5);
        v.setPadding(new Insets(10, 20, 10, 10));
        v.setAlignment(Pos.CENTER);
        return v;
    }
	
	private static BorderPane addCenter(){
		GridPane gp=new GridPane();
		BorderPane bp=new BorderPane();
		Text head=new Text("PROFILE");
		head.setStyle("-fx-font-weight:bold;-fx-font-size:125%;");
		bp.setTop(head);
		bp.setAlignment(head, Pos.TOP_CENTER);
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
		gp.setVgap(7);
		
		bp.setCenter(gp);
		bp.setAlignment(gp,Pos.CENTER);
		
		Button vc=new Button("View Courses");
		bp.setBottom(vc);
		bp.setAlignment(vc, Pos.CENTER);
		bp.setPadding(new Insets(10,20,10,10));
		return(bp);
	}
	
	private static BorderPane compiler(BorderPane b1,VBox b2,BorderPane b3) {
		BorderPane fin=new BorderPane();
		b1.setPadding(new Insets(10,10,10,10));
		fin.setTop(b1);
		fin.setLeft(b2);
		fin.setCenter(b3);
		return(fin);
	}
	
	public static void main(String ar[]) {
		launch(ar);
	}
}
