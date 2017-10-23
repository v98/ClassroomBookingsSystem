import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class EditCourse extends Application {
	@Override
	public void start(Stage arg0) {
		Scene sc=new Scene(compiler(addTop(),addGPane(),addMid()));
		Stage s=new Stage();
		s.setScene(sc);
		s.setTitle("Edit Courses");
		s.show();
	}

	private static BorderPane addTop() {
		BorderPane bp = new BorderPane();
		Button lo = new Button("Log Out");
		Text txt = new Text("UNAME");
		txt.maxWidth(Double.MAX_VALUE);
		bp.setLeft(txt);
		bp.setRight(lo);
		bp.setAlignment(lo, Pos.TOP_LEFT);
		bp.setPadding(new Insets(0, 10, 0, 10));
		return bp;
	}

	private static GridPane addGPane() {
		Button b1 = new Button("Time Table");
		Button b2 = new Button("Courses");
		Button b3 = new Button("Room Availability");
		Button b4 = new Button("View Pending Requests");
		Button b5 = new Button("Add Student");
		Button b6 = new Button("Add Faculty");

		GridPane v = new GridPane();
		b1.setMaxWidth(Double.MAX_VALUE);
		b2.setMaxWidth(Double.MAX_VALUE);
		b3.setMaxWidth(Double.MAX_VALUE);
		b4.setMaxWidth(Double.MAX_VALUE);
		b5.setMaxWidth(Double.MAX_VALUE);
		b6.setMaxWidth(Double.MAX_VALUE);
		v.add(b1, 1, 1);
		v.add(b2, 1, 2);
		v.add(b3, 1, 3);
		v.add(b4, 1, 4);
		v.add(b5, 1, 5);
		v.add(b6, 1, 6);
		v.setPadding(new Insets(10, 20, 10, 10));
		v.setAlignment(Pos.CENTER);
		v.setVgap(10);
		return v;
	}

	private static GridPane addMid() {
		Label id = new Label("Course ID");
		Label name = new Label("Course Name");
		TextField Id = new TextField();
		TextField nam = new TextField();
		GridPane tmp1 = new GridPane();
		tmp1.add(id, 1, 1);
		tmp1.add(Id, 2, 1);
		tmp1.setHgap(10);
		GridPane tmp2 = new GridPane();
		tmp2.add(name, 1, 1);
		tmp2.add(nam, 2, 1);
		tmp2.setHgap(10);
		Label sel = new Label("Select Course");
		Label edt = new Label("Edit Details");
		ListView<String> list = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList("Course 1", "Course 2", "Course 3",
				"Course 4");
		list.setItems(items);
		list.setPrefWidth(100);
		list.setPrefHeight(70);
		TextArea t2 = new TextArea();
		t2.setPrefHeight(150);
		t2.setText("Details will be displayed here");
		t2.setPrefWidth(280);
		GridPane gp = new GridPane();
		gp.add(tmp1, 1, 1);
		gp.add(tmp2, 2, 1);
		gp.add(sel, 1, 2);
		gp.add(edt, 2, 2);
		gp.add(list, 1, 3);
		gp.add(t2, 2, 3);
		Button disp=new Button("Display Courses");
		gp.add(disp, 3, 1);
		gp.setHgap(10);
		gp.setVgap(10);
		return(gp);
	}
	private static BorderPane compiler(BorderPane top,GridPane left,GridPane mid) {
		BorderPane bp=new BorderPane();
		Text head=new Text("EDIT COURSES");
		head.setStyle("-fx-font-weight:bold;-fx-font-size:125%;");
		bp.setTop(head);
		bp.setAlignment(head, Pos.TOP_CENTER);
		bp.setCenter(mid);
		bp.setAlignment(mid,Pos.CENTER);
		bp.setLeft(left);
		bp.setAlignment(left,Pos.CENTER);
		Button fin=new Button("Commit Changes");
		bp.setBottom(fin);
		bp.setAlignment(fin,Pos.CENTER);
		bp.setPadding(new Insets(10,10,10,10));
		BorderPane bp1=new BorderPane();
		bp1.setTop(top);
		bp1.setAlignment(bp1,Pos.CENTER);
		bp1.setCenter(bp);
		bp1.setAlignment(bp,Pos.CENTER);
		bp1.setPadding(new Insets(10,0,0,0));
		return(bp1);
	}
	public static void main(String ar[]) {
		launch(ar);
	}
}
