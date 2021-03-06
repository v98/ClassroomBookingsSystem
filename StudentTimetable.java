import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

import javafx.geometry.Pos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//import java.util.List;

//import javafx.application.Application;
//import javafx.beans.binding.Bindings;
//import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
//import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
//import javafx.stage.Stage;
//import javafx.util.Callback;
import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.GridPane;

public class StudentTimetable {
	// static List ls;
	static TextArea labs = new TextArea();
	static TextArea tuts = new TextArea();
	static ComboBox<String> course;
	static ObservableList<String> items;
	static ObservableList<String> items3;
	static TableView<pops> tab;
	static Button gen;
	static Button b1;
	private static Connection con;
	private static TableColumn<pops, String> c1, c2, c3, c4, c5, c6;
	static ObservableList<pops> data;
	private static String lb, tt, s1, s2;;

	private static HBox search() {
		Label cname = new Label("Course");
		course = new ComboBox<String>();
		course.getItems().addAll("Course1", "Course2", "Course3", "Course4", "Course5");
		b1 = new Button("GO");
		HBox search = new HBox(10);
		search.getChildren().addAll(cname, course);
		search.setAlignment(Pos.CENTER);
		HBox top = new HBox(70);
		top.getChildren().addAll(search, b1);
		top.setAlignment(Pos.CENTER);
		return top;
	}

	private static TableView addMid() {
		tab = new TableView<pops>();
		c1 = new TableColumn<pops, String>("Monday");
		c2 = new TableColumn<pops, String>("Tuesday");
		c3 = new TableColumn<pops, String>("Wednesday");
		c4 = new TableColumn<pops, String>("Thursday");
		c5 = new TableColumn<pops, String>("Friday");
		c6 = new TableColumn<pops, String>("Saturday");

		try {
			Class.forName("StudentTimetable");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root", "ashutosh");
			Statement stmt = con.createStatement();
			ResultSet rs1 = stmt.executeQuery("select distinct branch from courses;");
			ArrayList<String> brnch = new ArrayList<String>();
			while (rs1.next()) {
				brnch.add(rs1.getString(1));
			}
			try {
				Statement stmt1 = con.createStatement();
				ResultSet rs = stmt.executeQuery("select year,branch from user where id='" + LoginPage.Id + "';");
				rs.next();
				s2 = rs.getString(1);
				s1 = rs.getString(2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Statement stmnt = con.createStatement();
				System.out.println("select distinct name from courses where branch='" + s1 + "' and year=" + s2 + ";");
				ResultSet rs3 = stmnt.executeQuery(
						"select distinct name from courses where branch='" + s1 + "' and year=" + s2 + ";");
				ArrayList<String> names = new ArrayList<String>();
				while (rs3.next()) {
					names.add(rs3.getString(1));
				}
				items3 = FXCollections.observableArrayList(names);
				course.getItems().clear();
				course.setItems(items3);
				b1.setOnAction(e1 -> {
					if (course.getSelectionModel().getSelectedItem() == null) {
						AlertBox.display("Classroom Booking System", "Please select the course first.");
					} else {
						try {
							Statement stmt2 = con.createStatement();
							ResultSet rs4 = stmt2
									.executeQuery("select mon,tue,wed,thu,fri,sat,lab,tut from courses where branch='"
											+ s1 + "' and year=" + s2 + " and name='"
											+ course.getSelectionModel().getSelectedItem() + "';");
							System.out.println("select mon,tue,wed,thu,fri,sat,lab,tut from courses where branch='" + s1
									+ "' and year=" + s2 + " and name='" + course.getSelectionModel().getSelectedItem()
									+ "';");
							tab.getColumns().clear();
							while (rs4.next()) {
								String d1, d2, d3, d4, d5, d6;
								d1 = rs4.getString(1);
								d2 = rs4.getString(2);
								d3 = rs4.getString(3);
								d4 = rs4.getString(4);
								d5 = rs4.getString(5);
								d6 = rs4.getString(6);
								lb = rs4.getString(7);
								tt = rs4.getString(8);

								String r1, r2, r3, r4, r5, r6;
								r1 = d1.substring(0, d1.indexOf(' '));
								d1 = d1.substring(d1.indexOf(' ') + 1);
								r2 = d2.substring(0, d2.indexOf(' '));
								d2 = d2.substring(d2.indexOf(' ') + 1);
								r3 = d3.substring(0, d3.indexOf(' '));
								d3 = d3.substring(d3.indexOf(' ') + 1);
								r4 = d4.substring(0, d4.indexOf(' '));
								d4 = d4.substring(d4.indexOf(' ') + 1);
								r5 = d5.substring(0, d5.indexOf(' '));
								d5 = d5.substring(d5.indexOf(' ') + 1);
								r6 = d6.substring(0, d6.indexOf(' '));
								d6 = d6.substring(d6.indexOf(' ') + 1);

								data = FXCollections.observableArrayList(new pops(d1, d2, d3, d4, d5, d6),
										new pops(r1, r2, r3, r4, r5, r6));
								c1.setCellValueFactory(new PropertyValueFactory<pops, String>("d1"));
								c2.setCellValueFactory(new PropertyValueFactory<pops, String>("d2"));
								c3.setCellValueFactory(new PropertyValueFactory<pops, String>("d3"));
								c4.setCellValueFactory(new PropertyValueFactory<pops, String>("d4"));
								c5.setCellValueFactory(new PropertyValueFactory<pops, String>("d5"));
								c6.setCellValueFactory(new PropertyValueFactory<pops, String>("d6"));
								tab.getColumns().addAll(c1, c2, c3, c4, c5, c6);
								tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
								tab.setItems(data);
								tab.setMinWidth(tab.getPrefWidth());
								System.out.println(tt + " " + lb);
								labs.setText(lb);
								tuts.setText(tt);

								tab.setPrefHeight(75);
								tab.setMinHeight(75);

							}
						} catch (Exception e3) {
							e3.printStackTrace();
						}
					}
				});
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// tab.prefHeightProperty().bind(Bindings.size(tab.getItems()).multiply(tab.getFixedCellSize()).add(30));
		return tab;
	}

	public static BorderPane compiler() {
		BorderPane tmp1 = new BorderPane();
		ListView<String> list = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList("Day", "Timing", "Venue");
		list.setItems(items);
		list.setPrefWidth(60);
		list.setPrefHeight(50);
		list.setMinSize(60, 50);

		HBox mid1 = search();
		TableView tabs = addMid();

		HBox middle = new HBox(5);
		middle.getChildren().addAll(list, tabs);
		
		middle.setAlignment(Pos.CENTER);

		VBox main = new VBox(20);
		main.getChildren().addAll(mid1, middle);
		main.setAlignment(Pos.CENTER);

		Text t = new Text("TIMETABLE");
		t.setStyle("-fx-font-weight:bold;-fx-font-size:150%");

		BorderPane bp = new BorderPane();
		bp.setTop(t);
		bp.setCenter(main);
		VBox bot = new VBox();
		Label tut = new Label("Tutorial");
		Label lab = new Label("Lab");
		labs.setEditable(false);
		labs.setPrefHeight(15);
		labs.setPrefWidth(150);
		tuts.setPrefWidth(150);
		tuts.setPrefHeight(15);
		tuts.setEditable(false);
		bot.setPadding(new Insets(10,0,10,10));
		bot.getChildren().addAll(tut,tuts,lab,labs);
		bp.setRight(bot);

		bp.setAlignment(t, Pos.CENTER);
		bp.setAlignment(main, Pos.CENTER);

		bp.setPadding(new Insets(10, 10, 10, 10));

		return (bp);
	}

	public static class pops {
		private final SimpleStringProperty d1, d2, d3, d4, d5, d6;

		private pops(String d1, String d2, String d3, String d4, String d5, String d6) {
			this.d1 = new SimpleStringProperty(d1);
			this.d6 = new SimpleStringProperty(d6);
			this.d5 = new SimpleStringProperty(d5);
			this.d4 = new SimpleStringProperty(d4);
			this.d3 = new SimpleStringProperty(d3);
			this.d2 = new SimpleStringProperty(d2);
		}

		public String getD1() {
			return d1.get();
		}

		public String getD2() {
			return d2.get();
		}

		public String getD3() {
			return d3.get();
		}

		public String getD4() {
			return d4.get();
		}

		public String getD5() {
			return d5.get();
		}

		public String getD6() {
			return d6.get();
		}
	}

}
