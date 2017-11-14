import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginPage extends Application {
	static Stage window;
	static Scene main;
	static TextField user, nam, eml, idn;
	static PasswordField psswd, pswd, cpswd;
	static String username,Id,emailid;
	static RadioButton stu, fac;

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		Text uname = new Text("Username");
		Text pass = new Text("Password");
		Text typ = new Text("Signup as :");

		Button login = new Button("Sign In!");
		user = new TextField();
		psswd = new PasswordField();
		stu = new RadioButton();
		fac = new RadioButton();

		HBox rdbuttons = new HBox(20);
		final ToggleGroup group = new ToggleGroup();

		stu.setText("Student");
		fac.setText("Faculty");

		stu.setToggleGroup(group);
		fac.setToggleGroup(group);

		GridPane in = new GridPane();
		GridPane g1 = new GridPane();

		g1.setHgap(10);
		g1.setVgap(10);
		g1.setPadding(new Insets(0, 10, 0, 10));

		g1.add(uname, 1, 5);
		g1.add(user, 2, 5);
		g1.add(pass, 1, 6);
		g1.add(psswd, 2, 6);
		g1.add(login, 2, 8);

		in.add(g1, 1, 1);
		group.selectedToggleProperty();

		Text name = new Text("Name");
		Text email = new Text("Email");
		Text id = new Text("ID");
		Text ps = new Text("Password");
		Text cps = new Text("Confirm Password");

		nam = new TextField();
		eml = new TextField();
		idn = new TextField();
		pswd = new PasswordField();
		cpswd = new PasswordField();

		Button sup = new Button("Sign Up!");

		GridPane g2 = new GridPane();
		g2.setHgap(10);
		g2.setVgap(10);
		g2.setPadding(new Insets(0, 10, 0, 50));

		g2.add(name, 1, 1);
		g2.add(nam, 2, 1);
		g2.add(id, 1, 2);
		g2.add(idn, 2, 2);
		g2.add(email, 1, 3);
		g2.add(eml, 2, 3);
		g2.add(ps, 1, 4);
		g2.add(pswd, 2, 4);
		g2.add(cps, 1, 5);
		g2.add(cpswd, 2, 5);
		g2.add(typ, 1, 6);
		g2.add(sup, 2, 7);

		rdbuttons.getChildren().add(stu);
		rdbuttons.getChildren().add(fac);
		g2.add(rdbuttons, 2, 6);
 
		in.setPadding(new Insets(0, 0, 10, 0));
		in.add(g2, 2, 1);
		main = new Scene(in);

		eml.appendText("@iiitd.ac.in");
		eml.setTooltip(new Tooltip("Do not include @iiitd.ac.in!"));
		window.setTitle("Classroom Booking System");
		window.setScene(main);
		window.show();

		sup.setOnAction(e -> {
			boolean val = ConfirmBox.display("Classroom Booking System", "Are you sure you want to submit?");
			if (val == true && checkData() == true) {
				int a = update();// check and update database
				if (a == 0) {
					AlertBox.display("Classroom Booking System",
							"You are already a registered member.\nPlease use existing credentials.");
					clear(); // reload page-not needed
				} else {
					AlertBox.display("Classroom Booking System",
							"Thank you for signing up!\nPlease proceed to Sign In.");
					clear(); // reload page-not needed
				}
			}
		});

		login.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (!(user.getText() == null || psswd.getText() == null)) {
					int t = validate();
					if (t == 1 || t == 2 || t == 0) {
						Scene lol = HomeScreen.getScene(t, window, main);
						window.setScene(lol);
					}
				} else {
					AlertBox.display("Classroom Booking System", "Please fill all the fields.");
				}
			}
		});

	}

	private static int validate() {
		String n1 = user.getText();
		String n2 = psswd.getText();
		try {
			Class.forName("LoginPage");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root",
					"ashutosh");
			Statement stmnt = con.createStatement();
			ResultSet rs = stmnt
					.executeQuery("select * from user where name='" + n1 + "' and password = '" + n2 + "';");
			if (!rs.isBeforeFirst()) {
				AlertBox.display("Classroom Booking System",
						"Please check username or password.\nIf you haven't yet signed up, sign up now.");
				clear();
				stmnt.close();
				con.close();
				return (3);
			} else {
				rs.next();
				int t = Integer.parseInt(rs.getString(5));
				AlertBox.display("Classroom Booking System", "Welcome " + rs.getString(2));
				username = rs.getString(2);
				emailid=rs.getString(3);
				Id=rs.getString(1);
				clear();
				stmnt.close();
				con.close();
				return (t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (3);
	}

	private static int update() {
		String name = nam.getText();
		String email = eml.getText();
		String id = idn.getText();
		String n1 = pswd.getText();
		String n2 = cpswd.getText();
		int c = 0;
		try {
			Class.forName("LoginPage");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root",
					"ashutosh");
			Statement stmnt = con.createStatement();
			ResultSet rs = stmnt.executeQuery("select * from user where id='" + id + "';");
			c = (stu.isSelected() == true) ? 0 : 1;
			if (!rs.isBeforeFirst()) {
				Statement stmt = con.createStatement();
				stmt.executeUpdate("insert into user(id,name,email,password,type) values('" + id + "','" + name + "','"
						+ email + "','" + n2 + "','" + c + "');");
				
				stmnt.close();
				con.close();
				return (1);
			} else {
				stmnt.close();
				con.close();
				return (0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	private static boolean checkData() {
		String str = nam.getText();
		if (str.isEmpty() == false) {
			for (int i = 0; i < str.length(); i++) {
				if (Character.isLetter(str.charAt(i)) == false && str.charAt(i) != ' ') {
					AlertBox.display("Classroom Booking System", "Invalid Name!");
					return (false);
				}
			}
		} else {
			AlertBox.display("Classroom Booking System", "Empty Name!");
			return (false);
		}
		str = idn.getText();
		if (str.isEmpty() == true) {
			AlertBox.display("Classroom Booking System", "Empty Id!");
			return false;
		}
		String str1 = pswd.getText();
		str = cpswd.getText();
		if (str1.isEmpty() || str.isEmpty()) {
			AlertBox.display("Classroom Booking System", "Empty Password Field(s)!");
			return false;
		} else {
			if (str.equals(str1) == false) {
				AlertBox.display("Classroom Booking System", "Passwords do not match!");
				return (false);
			}
		}
		str = eml.getText();
		str = str.replace("@iiitd.ac.in", "");
		if (str.isEmpty() == true) {
			 AlertBox.display("Classroom Booking System", "Invalid Email!");
			return (false);
		}
		if(stu.isSelected()==false && fac.isSelected()==false) {
			AlertBox.display("Classroom Booking System", "Please select \"Signup As\"");
			return(false);
		}
		return (true);
	}

	public static void clear() {
		nam.setText("");
		eml.setText("@iiitd.ac.in");
		idn.setText("");
		pswd.setText("");
		cpswd.setText("");
		user.setText("");
		psswd.setText("");
		stu.setSelected(false);
		fac.setSelected(false);
	}

	public static void main(String ar[]) {
		launch(ar);
	}
}