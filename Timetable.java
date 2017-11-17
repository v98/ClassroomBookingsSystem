import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

import javafx.geometry.Pos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class ViewTimetable {
//	static List ls;
	static TextField labs = new TextField();
	static TextField tuts = new TextField();
	
	static ComboBox<String> streams;
	static ComboBox<String> year;
	static ComboBox<String> course;
	static ObservableList<String> items;
	static ObservableList<String> items1;
	static ObservableList<String> items2;
	static ObservableList<String> items3;
	static TableView<populate> tab;
	static Button gen;
	static Button b1;
	private static Connection con;
	private static TableColumn<populate, String> c1, c2, c3, c4, c5, c6;
	static ObservableList<populate> data;
	private static String lb, tt;

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
		tab = new TableView<populate>();
		c1 = new TableColumn<populate, String>("Monday");
		c2 = new TableColumn<populate, String>("Tuesday");
		c3 = new TableColumn<populate, String>("Wednesday");
		c4 = new TableColumn<populate, String>("Thursday");
		c5 = new TableColumn<populate, String>("Friday");
		c6 = new TableColumn<populate, String>("Saturday");
		

		try {
			Class.forName("ViewTimetable");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root", "ashutosh");
			Statement stmt = con.createStatement();
			ResultSet rs1 = stmt.executeQuery("select distinct branch from courses;");
			ArrayList<String> brnch = new ArrayList<String>();
			while (rs1.next()) {
				brnch.add(rs1.getString(1));
			}

			items1 = FXCollections.observableArrayList(brnch);
			streams.setItems(items1);

			streams.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// TODO Auto-generated method stub
					Statement stmnt1;
					try {
						stmnt1 = con.createStatement();
						ResultSet rs2 = stmnt1.executeQuery("select distinct year from courses;");
						ArrayList<String> Yr = new ArrayList<String>();
						while (rs2.next()) {
							Yr.add(rs2.getString(1));
						}
						items2 = FXCollections.observableArrayList(Yr);
						year.setItems(items2);

						gen.setOnMouseClicked(new EventHandler<MouseEvent>() {

							@Override
							public void handle(MouseEvent event) {
								// TODO Auto-generated method stub
								if (streams.getSelectionModel().getSelectedItem() == null
										|| year.getSelectionModel().getSelectedItem() == null) {
									AlertBox.display("Classroom Booking System",
											"Please select stream and year first.");
								} else {
									String s1 = streams.getSelectionModel().getSelectedItem();
									String s2 = year.getSelectionModel().getSelectedItem();
									try {
										Statement stmnt = con.createStatement();
										ResultSet rs3 = stmnt
												.executeQuery("select distinct name from courses where branch='" + s1
														+ "' and year=" + s2 + ";");
										System.out.println("select distinct name from courses where branch='" + s1
												+ "' and year=" + s2 + ";");
										ArrayList<String> names = new ArrayList<String>();
										while (rs3.next()) {
											names.add(rs3.getString(1));
										}
										items3 = FXCollections.observableArrayList(names);
										course.getItems().clear();
										course.setItems(items3);
										b1.setOnAction(e1 -> {
											if (course.getSelectionModel().getSelectedItem() == null) {
												AlertBox.display("Classroom Booking System",
														"Please select the course first.");
											} else {
												try {
													Statement stmt2 = con.createStatement();
													ResultSet rs4 = stmt2.executeQuery(
															"select mon,tue,wed,thu,fri,sat,lab,tut from courses where branch='"
																	+ s1 + "' and year=" + s2 + " and name='"
																	+ course.getSelectionModel().getSelectedItem()
																	+ "';");
													System.out.println(
															"select mon,tue,wed,thu,fri,sat,lab,tut from courses where branch='"
																	+ s1 + "' and year=" + s2 + " and name='"
																	+ course.getSelectionModel().getSelectedItem()
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

														data = FXCollections.observableArrayList(
																new populate(d1, d2, d3, d4, d5, d6),
																new populate(r1, r2, r3, r4, r5, r6));
														c1.setCellValueFactory(
																new PropertyValueFactory<populate, String>("d1"));
														c2.setCellValueFactory(
																new PropertyValueFactory<populate, String>("d2"));
														c3.setCellValueFactory(
																new PropertyValueFactory<populate, String>("d3"));
														c4.setCellValueFactory(
																new PropertyValueFactory<populate, String>("d4"));
														c5.setCellValueFactory(
																new PropertyValueFactory<populate, String>("d5"));
														c6.setCellValueFactory(
																new PropertyValueFactory<populate, String>("d6"));
														tab.getColumns().addAll(c1, c2, c3, c4, c5, c6);
														tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
														tab.setItems(data);
														System.out.println(tt+" "+lb);
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
								}
							};
						});
					} catch (Exception e) {
						e.printStackTrace();
					}
				};
			});
		} catch (Exception e32) {
			e32.printStackTrace();
		}
		// tab.setFixedCellSize(25);
		// tab.prefHeightProperty().bind(Bindings.size(tab.getItems()).multiply(tab.getFixedCellSize()).add(30));
		return tab;
	}

	public static BorderPane compiler2() {
		BorderPane tmp1 = new BorderPane();
		ListView<String> list = new ListView<String>();
		items = FXCollections.observableArrayList("Day", "Timing", "Venue");
		list.setItems(items);
		list.setPrefWidth(150);
		list.setPrefHeight(75);
		list.setMinHeight(75);
		
		Label crs = new Label("Stream");
		streams = new ComboBox<String>();
		streams.getItems().addAll("CSE", "CSAM", "ECE");

		Label yr = new Label("Year");
		year = new ComboBox<String>();
		HBox left = new HBox(5);
		left.getChildren().addAll(crs, streams);
		left.setAlignment(Pos.CENTER);

		HBox right = new HBox(5);
		right.getChildren().addAll(yr, year);
		right.setAlignment(Pos.CENTER);

		gen = new Button("Open Timetable");
		HBox top = new HBox(20);
		top.getChildren().addAll(left, right, gen);
		top.setAlignment(Pos.CENTER);

		HBox mid1 = search();
		TableView tabs = addMid();

		HBox middle = new HBox(5);
		middle.getChildren().addAll(list, tabs);
		middle.setAlignment(Pos.CENTER);

		VBox main = new VBox(20);
		main.getChildren().addAll(top, mid1, middle);
		main.setAlignment(Pos.CENTER);

		Text t = new Text("TIMETABLE");
		t.setStyle("-fx-font-weight:bold;-fx-font-size:150%");
		BorderPane bp = new BorderPane();
		bp.setTop(t);
		bp.setCenter(main);
		// bp.setLeft(left);
		
		bp.setAlignment(t, Pos.CENTER);
		bp.setAlignment(main, Pos.CENTER);
		
		VBox bot = new VBox();
		Label tut = new Label("Tutorial");
		Label lab = new Label("Lab");
		labs.setEditable(false);
		tuts.setEditable(false);
		bot.setPadding(new Insets(10,0,10,5));
		bot.getChildren().addAll(tut,tuts,lab,labs);
		bp.setRight(bot);

		// bp.setAlignment(left, Pos.CENTER);
		bp.setPadding(new Insets(10, 10, 10, 10));
		return (bp);
	}

	public static BorderPane compiler() {
		BorderPane tmp1 = new BorderPane();
		ListView<String> list = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList("Day", "Timing", "Venue");
		list.setItems(items);
		list.setPrefWidth(100);
		list.setPrefHeight(50);

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
//		bp.setAlignment(bot,Pos.CENTER_RIGHT);
//		
//		// bp.setLeft(left);
//
//		bp.setAlignment(t, Pos.CENTER);
//		bp.setAlignment(main, Pos.CENTER);
		
		// bp.setAlignment(left, Pos.CENTER);
		bp.setPadding(new Insets(10, 10, 10, 10));

		return (bp);
	}

	public static class populate {
		private final SimpleStringProperty d1, d2, d3, d4, d5, d6;

		private populate(String d1, String d2, String d3, String d4, String d5, String d6) {
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
