import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.shape.HLineTo;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class EditCourses {
	// @Override
	// public void start(Stage arg0) {
	//
	// }

	static TextField Id, nam, typ, cre, ins;
	static TextArea t1, t2;

	private static GridPane addMid() {
		Label id = new Label("Course ID");
		Label name = new Label("Course Name");
		Label type = new Label("Type");
		Label instructor = new Label("Instructor");
		Label credits = new Label("Credits");
		Label prcon = new Label("Preconditions");
		Label pocon = new Label("PostConditions");

		Id = new TextField();
		Id.setEditable(false);
		nam = new TextField();
		nam.setEditable(false);
		typ = new TextField();
		typ.setEditable(false);
		ins = new TextField();
		cre = new TextField();

		t1 = new TextArea();
		t1.setPrefHeight(150);
		t1.setText("Details will be displayed here");
		t1.setPrefWidth(300);

		t2 = new TextArea();
		t2.setPrefHeight(150);
		t2.setText("Details will be displayed here");
		t2.setPrefWidth(300);

		GridPane tmp1 = new GridPane();
		tmp1.add(id, 1, 1);
		tmp1.add(Id, 2, 1);
		tmp1.setHgap(10);
		GridPane tmp2 = new GridPane();
		tmp2.add(name, 1, 1);
		tmp2.add(nam, 2, 1);
		tmp2.setHgap(10);

		GridPane tmp3 = new GridPane();
		tmp3.add(type, 1, 1);
		tmp3.add(typ, 2, 1);
		tmp3.setHgap(10);

		GridPane tmp4 = new GridPane();
		tmp4.add(credits, 1, 1);
		tmp4.add(cre, 2, 1);
		tmp4.setHgap(10);

		GridPane tmp5 = new GridPane();
		tmp5.add(instructor, 1, 1);
		tmp5.add(ins, 2, 1);
		tmp5.setHgap(10);

		GridPane tmp6 = new GridPane();
		tmp6.add(prcon, 1, 1);
		tmp6.add(t1, 1, 2);
		tmp6.setVgap(10);

		GridPane tmp7 = new GridPane();
		tmp7.add(pocon, 1, 1);
		tmp7.add(t2, 1, 2);
		tmp7.setVgap(10);

		// Label sel = new Label("Select Course");
		// Label edt = new Label("Edit Details:");
		// ListView<String> list = new ListView<String>();
		// ObservableList<String> items = FXCollections.observableArrayList("Course 1",
		// "Course 2", "Course 3",
		// "Course 4");
		// list.setItems(items);
		// list.setPrefWidth(100);
		// list.setPrefHeight(70);
		// ineTo hl=new HLineTo(100);

		GridPane gpu = new GridPane();
		gpu.add(tmp1, 1, 1);
		gpu.add(tmp2, 2, 1);
		// gp.add(sel, 1, 2);
		// gpu.add(edt, 1, 2);
		// gp.add(list, 1, 3);
		// gp.add(t2, 2, 3);
		//Button disp = new Button("Display Course");
		//gpu.add(disp, 3, 1);
		gpu.add(tmp3, 1, 3);
		gpu.add(tmp4, 2, 3);
		gpu.add(tmp5, 3, 3);
		gpu.setHgap(10);
		gpu.setVgap(10);

		GridPane gpl = new GridPane();
		gpl.add(tmp6, 1, 1);
		gpl.add(tmp7, 2, 1);
		gpl.setHgap(40);
		gpl.setVgap(10);
		gpl.setAlignment(Pos.CENTER);
		GridPane gp = new GridPane();
		gp.add(gpu, 1, 1);
		gp.add(gpl, 1, 2);

		gp.setVgap(10);
		gp.setAlignment(Pos.CENTER);
		return (gp);
	}

	public static void compiler() {
		BorderPane bp = new BorderPane();
		GridPane mid = addMid();
		Text head = new Text("EDIT COURSES");
		head.setStyle("-fx-font-weight:bold;-fx-font-size:125%;");
		bp.setTop(head);
		bp.setAlignment(head, Pos.TOP_CENTER);
		bp.setCenter(mid);
		bp.setAlignment(mid, Pos.CENTER);
		// bp.setLeft(left);
		// bp.setAlignment(left,Pos.CENTER);
		Button fin = new Button("Commit Changes");
		bp.setBottom(fin);
		bp.setAlignment(fin, Pos.CENTER);
		bp.setPadding(new Insets(30, 10, 30, 10));
		BorderPane bp1 = new BorderPane();
		// bp1.setTop(top);
		bp1.setAlignment(bp1, Pos.CENTER);
		bp1.setCenter(bp);
		bp1.setAlignment(bp, Pos.CENTER);
		bp1.setPadding(new Insets(10, 10, 10, 10));
		Scene sc = new Scene(bp1);
		Stage s = new Stage();
		s.setScene(sc);
		s.setTitle("Edit Courses");
		s.show();
		
		ArrayList<String> str=AdminCourses.hm.get(AdminCourses.list.getSelectionModel().getSelectedItem());
		Id.setText(str.get(0));
		//str=str.substring(str.indexOf(' ')+1);
		nam.setText(str.get(1));
		//str=str.substring(str.indexOf("Type")+6);
		typ.setText(str.get(2));
		//str=str.substring(str.indexOf("Instructor")+12);
		ins.setText(str.get(3));
		//str=str.substring(str.indexOf("Credits: ")+8);
		cre.setText(str.get(4));
		//str=str.substring(str.indexOf("Pre-Cond")+16);
		t1.setText(str.get(5));
		t2.setText(str.get(6));
		
		
		
		fin.setOnAction(e -> {
			try {
				Class.forName("AdminCourses");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root",
						"vrinda@16186");
				Statement stmt = con.createStatement();
				String scid = Id.getText();
				String sins = ins.getText();
				String scre = cre.getText();
				String st1 = t1.getText();
				String st2 = t2.getText();
//				ArrayList<String> lol=new ArrayList<String>();
//				lol.add(Id.getText());
//				lol.add(nam.getText());
//				lol.add(typ.getText());
//				lol.add(ins.getText());
//				lol.add(cre.getText());
//				lol.add(t1.getText());
//				lol.add(t2.getText());
//				AdminCourses.hm.put(nam.getText(),lol);
				stmt.executeUpdate("update courses set teacher='" + sins + "',credits=" + scre + ",precond='" + st1
						+ "',postcond='" + st2 + "' where cid='" + scid + "';");
				AdminCourses.connect();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			s.close();
		});
	}
	// public static void main(String ar[]) {
	// launch(ar);
	// }
}
