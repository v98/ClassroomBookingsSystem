import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
//import javafx.application.Application;
//import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
//import javafx.scene.Scene;
import javafx.scene.text.Text;
//import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
//import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.ResultSet;

public class AdminCourses {
	static List ls;
	static ListView<String> list = new ListView<String>();
	static HashMap<String, ArrayList<String>> hm;
	private static TextArea t2;
	private static Button edit;
	static ObservableList<String> items;

	@SuppressWarnings("static-access")
	private static BorderPane addCenter() {
		GridPane gp = new GridPane();
		BorderPane bp = new BorderPane();
		Text head = new Text("COURSES");
		head.setStyle("-fx-font-weight:bold;-fx-font-size:125%;");
		bp.setTop(head);
		bp.setAlignment(head, Pos.TOP_CENTER);

		Text crs = new Text("Courses");
		Text det = new Text("Course Details");
		connect();
		t2 = new TextArea();
		list.setPrefWidth(100);
		list.setPrefHeight(70);
		t2.setEditable(false);
		t2.setPrefHeight(150);
		t2.setPrefWidth(300);

		gp.setHgap(5);
		gp.setVgap(5);
		gp.add(crs, 1, 1);
		gp.add(det, 2, 1);
		gp.add(list, 1, 2);
		gp.add(t2, 2, 2);
		Button rem = new Button("Remove Course");
		edit = new Button("Edit Course");
		Button view = new Button("View Timetable");
		//Button ref = new Button("Refresh");

		GridPane tmp = new GridPane();
		tmp.add(rem, 1, 1);
		tmp.add(edit, 2, 1);
		tmp.add(view, 3, 1);
		//tmp.add(ref, 2, 2);
		tmp.setHgap(10);
		tmp.setVgap(10);
		gp.add(tmp, 2, 3);

		bp.setCenter(gp);
		bp.setAlignment(gp, Pos.CENTER);
		bp.setPadding(new Insets(0, 10, 10, 0));

//		ref.setOnAction(e -> {
//			connect();
//		});
		list.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				ArrayList<String> lol = hm.get(list.getSelectionModel().getSelectedItem());
				String s = lol.get(0) + " " + lol.get(1) + "\nType: " + lol.get(2) + "\nInstructor: " + lol.get(3)
						+ "\nCredits: " + lol.get(4) + "\nPre-Conditions: " + lol.get(5) + "\nPost-Conditions"
						+ lol.get(6);

				t2.setText(s);
			}
		});
		rem.setOnAction(e -> {
			boolean val = ConfirmBox.display("Classroom Booking System",
					"Are you sure you want to remove this course?");
			if (val == true) {
				remove();
			}
		});
		edit.setOnAction(e -> {
			EditCourses.compiler();
			connect();
			// Edit();
		});
		return bp;
	}

	public static BorderPane compiler() {
		BorderPane b2 = addCenter();
		System.out.println(b2 == null);
		BorderPane b = new BorderPane();
		b.setCenter(b2);
		b.setPadding(new Insets(10, 0, 0, 0));
		return (b);
	}

	static void connect() {
		try {
			Class.forName("AdminCourses");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root",
					"vrinda@16186");
			Statement stmt = con.createStatement();
			Statement stmt1 = con.createStatement();
			ResultSet rs = stmt.executeQuery("select name from courses;");
			ResultSet rs1 = stmt1.executeQuery("select cid,type,teacher,credits,precond,postcond from courses;");
			hm = new HashMap<String, ArrayList<String>>();
			// t2 = new TextArea();
			// list=new ListView<String>();
			int i = 0;
			ls = new ArrayList<String>();
			while (rs.next()) {
				if (i == 0) {
					rs1.next();
				}
				ArrayList<String> s = new ArrayList<String>();
				String s1 = rs.getString(1);
				// System.out.println(s1);
				// s = rs1.getString(1) + " " + s1 + " \nType: " + rs1.getString(2) + "
				// \nInstructor: "
				// + rs1.getString(3) + " \nCredits: " + rs1.getString(4) + " \nPre-Conditions:
				// \n" + rs1.getString(5)
				// + " \nPost-Conditions: \n" + rs1.getString(6);
				// System.out.println(s==null);
				s.add(rs1.getString(1));
				s.add(s1);
				s.add(rs1.getString(2));
				s.add(rs1.getString(3));
				s.add(rs1.getString(4));
				String strs = rs1.getString(5);
				String strss = rs1.getString(6);
				s.add(strs);
				s.add(strss);
				System.out.println(strs);
				System.out.println(strss);
				hm.put(s1, s);
				// System.out.println(s1==null);
				ls.add(s1);
			}
			// list=new ListView<String>();
			items = FXCollections.observableArrayList(ls);
			list.setItems(items);
			ArrayList<String> lol = hm.get(list.getSelectionModel().getSelectedItem());
			
			String s = lol.get(0) + " " + lol.get(1) + "\nType: " + lol.get(2) + "\nInstructor: " + lol.get(3)
					+ "\nCredits: " + lol.get(4) + "\nPre-Conditions: " + lol.get(5) + "\nPost-Conditions"
					+ lol.get(6);
			
			System.out.println(lol.get(4));
			t2.setText(s);
			stmt.close();
			stmt1.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void remove() {
		try {
			Class.forName("AdminCourses");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root",
					"vrinda@16186");
			// t2 = new TextArea();
			// list=new ListView<String>();
			Statement stmt = con.createStatement();
			String s = list.getSelectionModel().getSelectedItem();
			stmt.executeUpdate("delete from courses where name='" + s + "';");
			// connect();
			list.setItems(items);
			t2.setText("");
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	private static void Edit() {
//		t2.setEditable(true);
//		edit.setText("Save Changes");
//		// t2 = new TextArea();
//		// list=new ListView<String>();
//		boolean val = ConfirmBox.display("Classroom Booking System", "Are you sure you want to edit this course?");
//		if (val == true) {
//			try {
//				Class.forName("AdminCourses");
//				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root",
//						"vrinda@16186");
//				Statement stmt = con.createStatement();
//				String s = list.getSelectionModel().getSelectedItem();
//				stmt.executeUpdate("delete from courses where name='" + s + "';");
//				connect();
//				list.setItems(items);
//				t2.setText("");
//				stmt.close();
//				con.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
}
