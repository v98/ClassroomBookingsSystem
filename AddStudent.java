import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class AddStudent extends Application{
	@Override
	public void start(Stage arg0) {
		Scene sc=new Scene(compiler(addTop(),addGPane(),addMid()));
		Stage s=new Stage();
		s.setScene(sc);
		s.setTitle("Edit Student");
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
        Button b5=new Button("Student");
        Button b6=new Button("Faculty");
        
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
	private static BorderPane addMid() {
		Label nm=new Label("Name");
		Label eml=new Label("Email");
		Label prg=new Label("Program");
		Label idd=new Label("ID");
		Label typ=new Label("Branch");
		Label regcrs=new Label("Courses");
			
		nm.setMaxWidth(50);
		eml.setMaxWidth(50);
		prg.setMaxWidth(50);
		idd.setMaxWidth(50);
		typ.setMaxWidth(50);
		regcrs.setMaxWidth(50);
		
		TextField nam=new TextField();
		TextField emal=new TextField();
		TextField id=new TextField();
		TextArea ta=new TextArea();
		
		nam.setMaxWidth(250);
		emal.setMaxWidth(250);
		id.setMaxWidth(250);
		ta.setMaxWidth(250);
		
		ComboBox<String> prog=new ComboBox<String>();
		prog.getItems().addAll("BTech","MTech","PhD");
		ComboBox<String> tpe=new ComboBox<String>();
		tpe.getItems().addAll("CSE","CSAM","ECE");
//		switch(prog.getSelectionModel().getSelectedItem().charAt(0)) {
//		case 'B':
//			tpe.getItems().addAll("CSE","CSAM","ECE");
//			break;
//		case 'M':
//			tpe.getItems().addAll("CSE","ECE");
//			break;
//		case 'P':
//			tpe.getItems().addAll("CSE","ECE");
//			break;
//		}
		GridPane tmp1=new GridPane();
		tmp1.add(prg, 1, 1);
		tmp1.add(prog, 2, 1);
		tmp1.setHgap(5);
		tmp1.setMaxWidth(Double.MAX_VALUE);
		GridPane tmp2=new GridPane();
		tmp2.add(typ, 3, 1);
		tmp2.add(tpe, 4, 1);
		tmp2.setHgap(5);
		tmp2.setMaxWidth(Double.MAX_VALUE);
		
		GridPane gp=new GridPane();
		gp.add(nm, 1, 1);
		gp.add(nam, 2, 1);
		gp.add(idd, 1, 2);
		gp.add(id,2,2);
		gp.add(eml,1,3);
		gp.add(emal, 2, 3);
		gp.add(tmp1, 1, 4);
		gp.add(tmp2, 2, 4);
		gp.add(regcrs,1,5);
		gp.add(ta, 2, 5);
//		gp.setHgap(10);
		gp.setVgap(10);
		gp.setPadding(new Insets(10,10,10,10));
		
		Button ok = new Button("Save Changes");
		Button rem=new Button("Delete Student");
		Button go=new Button("Search");
		GridPane gp1=new GridPane();
		gp1.add(go, 1, 1);
		gp1.add(ok, 2, 1);
		gp1.add(rem, 3, 1);
		gp1.setHgap(10);
		gp1.setVgap(10);
		
		BorderPane bp=new BorderPane();
//		bp.setTop(top);
//		bp.setAlignment(top,Pos.CENTER);
		bp.setCenter(gp);
		bp.setAlignment(gp, Pos.CENTER);
		bp.setBottom(gp1);
		bp.setAlignment(gp1,Pos.CENTER);
		return(bp);
	}
	
	private static BorderPane compiler(BorderPane top,GridPane left,BorderPane mid) {
		Text tops=new Text("STUDENT");
		tops.setStyle("-fx-font-weight:bold;-fx-font-size:150%");
		BorderPane b1=new BorderPane();
		BorderPane bp=new BorderPane();
		b1.setTop(tops);
		b1.setAlignment(tops, Pos.CENTER);
		b1.setLeft(left);
		b1.setAlignment(left, Pos.CENTER);
		b1.setCenter(mid);
		b1.setAlignment(mid, Pos.CENTER);
		b1.setPadding(new Insets(10,10,10,10));
		bp.setTop(top);
		bp.setAlignment(top,Pos.CENTER);
		bp.setCenter(b1);
		bp.setAlignment(b1,Pos.CENTER);
		bp.setPadding(new Insets(10,0,0,0));
		return(bp);
	}

	public static void main(String ar[]) {
		launch(ar);
	}
}
