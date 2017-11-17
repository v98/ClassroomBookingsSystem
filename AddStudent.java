import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class AddStudent {
	static TextField emal, nam, id;
	static TextArea ta;
	static Button go, ok, rem;
	static ComboBox<String> prog, tpe;

	private static BorderPane addMid() {
		Label nm = new Label("Name");
		Label eml = new Label("Email");
		Label prg = new Label("Branch");
		Label idd = new Label("ID");
		Label typ = new Label("Year");
		Label regcrs = new Label("Courses");

		nm.setMaxWidth(50);
		eml.setMaxWidth(50);
		prg.setMaxWidth(100);
		idd.setMaxWidth(50);
		typ.setMinWidth(50);

		regcrs.setMaxWidth(50);

		nam = new TextField();
		emal = new TextField();
		id = new TextField();
		ta = new TextArea();

		// emal.setDisable(true);
		// ta.setDisable(true);

		nam.setMaxWidth(250);
		emal.setMaxWidth(250);
		id.setMaxWidth(250);
		ta.setMaxWidth(250);

		prog = new ComboBox<String>();
		prog.getItems().addAll("BTech", "MTech", "PhD");
		// prog.setDisable(true);
		tpe = new ComboBox<String>();
		tpe.getItems().addAll("1", "2", "3", "4");
		HBox rone = new HBox(10);
		rone.getChildren().addAll(nm, nam);
		rone.setAlignment(Pos.CENTER);
		HBox rtwo = new HBox(10);
		rtwo.setAlignment(Pos.CENTER);
		rtwo.getChildren().addAll(idd, id);
		HBox rthree = new HBox(10);
		rthree.getChildren().addAll(eml, emal);
		rthree.setAlignment(Pos.CENTER);
		HBox rfour1 = new HBox(10);
		rfour1.getChildren().addAll(prg, prog);
		rfour1.setAlignment(Pos.CENTER);
		HBox rfour2 = new HBox(10);
		rfour2.getChildren().addAll(typ, tpe);
		rfour2.setAlignment(Pos.CENTER);

		HBox rfour = new HBox(10);
		rfour.getChildren().addAll(rfour1, rfour2);
		rfour.setAlignment(Pos.CENTER);
		HBox rfive = new HBox(10);
		rfive.getChildren().addAll(regcrs, ta);
		rfive.setAlignment(Pos.CENTER);

		ok = new Button("Save Changes");
		rem = new Button("Delete Student");
		go = new Button("Search");

		clear();
		go.setOnAction(e -> {
			emal.setDisable(false);
			prog.setDisable(false);
			tpe.setDisable(false);
			ta.setDisable(false);
			ok.setDisable(false);
			rem.setDisable(false);
			if (nam.getText().isEmpty() || id.getText().isEmpty()) {
				AlertBox.display("Classroom Booking System", "Empty fields!");
				clear();
			} else {
				String sname = nam.getText();
				String sid = id.getText();
				try {
					Class.forName("AddStudent");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false",
							"root", "ashutosh");
					Statement stmnt = con.createStatement();
					ResultSet rs = stmnt.executeQuery(
							"select * from user where id='" + sid + "' && name='" + sname + "' && type=0;");
					System.out.println("select * from user where id='" + sid + "' && name='" + sname + "' && type=0;");
					try {
						rs.next();
						if (!rs.isBeforeFirst()) {
							tpe.setValue(rs.getString(6));
							emal.setText(rs.getString(3));
							ta.setText(rs.getString(8));
							prog.setValue(rs.getString(9));
							stmnt.close();
							con.close();
						} else {
							stmnt.close();
							con.close();
						}
					} catch (Exception e1) {
						AlertBox.display("AddStudent", "Student Not Found!");
						clear();
					}
				} catch (Exception t) {
					t.printStackTrace();
					clear();
				}
			}
		});

		ok.setOnAction(e -> {
			try {
				Class.forName("AddStudent");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root",
						"ashutosh");
				Statement stmnt = con.createStatement();
				System.out.println(
						"update user set email='" + emal.getText() + "',year='" + tpe.getValue() + "',courses='"
								+ ta.getText() + "', prog='" + prog.getValue() + "' where id='" + id.getText() + "';");

				stmnt.executeUpdate(
						"update user set email='" + emal.getText() + "',year='" + tpe.getValue() + "',courses='"
								+ ta.getText() + "', prog='" + prog.getValue() + "' where id='" + id.getText() + "';");
				clear();
			} catch (Exception t) {
				t.printStackTrace();
			}
		});

		rem.setOnAction(e -> {
			try {
				Class.forName("AddStudent");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root",
						"ashutosh");
				Statement stmnt = con.createStatement();
				stmnt.executeUpdate("delete from user where id='" + id.getText() + "';");
				clear();
			} catch (Exception t) {
				t.printStackTrace();
			}

		});
		HBox last = new HBox(10);
		last.getChildren().addAll(go, ok, rem);

		VBox lol = new VBox(20);
		lol.setAlignment(Pos.CENTER);
		lol.getChildren().addAll(rone, rtwo, rthree, rfour, rfive, last);
		BorderPane bp = new BorderPane();

		bp.setCenter(lol);
		bp.setAlignment(lol, Pos.CENTER);
		bp.setPadding(new Insets(10, 10, 10, 10));
		return (bp);
	}

	public static void clear() {
		nam.setText(null);
		emal.setText(null);
		id.setText(null);
		prog.setValue(null);
		tpe.setValue(null);
		ta.setText(null);
		emal.setDisable(true);
		prog.setDisable(true);
		tpe.setDisable(true);
		ta.setDisable(true);
		ok.setDisable(true);
		rem.setDisable(true);
	}

	public static BorderPane compiler() {
		BorderPane mid = addMid();
		Text tops = new Text("STUDENT");
		tops.setStyle("-fx-font-weight:bold;-fx-font-size:150%");
		BorderPane b1 = new BorderPane();
		BorderPane bp = new BorderPane();
		b1.setTop(tops);
		b1.setAlignment(tops, Pos.CENTER);
		b1.setCenter(mid);
		b1.setAlignment(mid, Pos.CENTER);
		b1.setPadding(new Insets(10, 10, 10, 10));
		bp.setCenter(b1);
		bp.setAlignment(b1, Pos.CENTER);
		bp.setPadding(new Insets(10, 0, 0, 0));
		return (bp);
	}

}
