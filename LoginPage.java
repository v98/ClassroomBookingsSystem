import javafx.geometry.Insets;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;

//this is basic layout I'm still working on making it look presentable

public class LoginPage extends Application {
    static Stage window;
    static Scene main;
    @Override
    public void start(Stage primaryStage) throws Exception {
        window=primaryStage;
        Text uname = new Text("Username");
        Text pass = new Text("Password");

        Button login = new Button("Sign In!");
        TextField user = new TextField();
        PasswordField psswd = new PasswordField();

        login.setOnAction(e ->{
            //authenticate and return value for t
            int t=0;//0=student,1=faculty,2=admin
            Scene lol=HomeScreen.getScene(t,window,main);
            window.setScene(lol);
            //if yes displaynextpage

        });

        GridPane in=new GridPane();
        GridPane g1 = new GridPane();
        g1.setHgap(10);
        g1.setVgap(10);
        g1.setPadding(new Insets(0, 10, 0, 10));

        g1.add(uname,1,5);
        g1.add(user,2,5);
        g1.add(pass,1,6);
        g1.add(psswd,2,6);
        g1.add(login,2,7);
        in.add(g1,1,1);

        Text name = new Text("Name");
        Text email = new Text("Email");
        Text id=new Text("ID");
        Text ps=new Text("Password");
        Text cps=new Text("Confirm Password");

        TextField nam = new TextField();
        TextField eml = new TextField();
        TextField idn=new TextField();
        PasswordField pswd=new PasswordField();
        PasswordField cpswd=new PasswordField();

        Button sup = new Button("Sign Up!");
        sup.setOnAction(e ->{
            boolean val=ConfirmBox.display("Classroom Booking System","Are you sure you want to submit?");
            if(val==true){
                AlertBox.display("Classroom Booking System","Thank you for signing up!\nPlease proceed to Sign In.");
            }
            //update database
            //show information/alert box -"please login"
            //reload page
        });

        GridPane g2 = new GridPane();
        g2.setHgap(10);
        g2.setVgap(10);
        g2.setPadding(new Insets(0, 10, 0, 50));

        g2.add(name,1,1);
        g2.add(nam,2,1);
        g2.add(id,1,2);
        g2.add(idn,2,2);
        g2.add(email,1,3);
        g2.add(eml,2,3);
        g2.add(ps,1,4);
        g2.add(pswd,2,4);
        g2.add(cps,1,5);
        g2.add(cpswd,2,5);
        g2.add(sup,2,6);

        in.setPadding(new Insets(0, 0, 10, 0));
        in.add(g2,2,1);
        main = new Scene(in);


        window.setTitle("Classroom Booking System");
        window.setScene(main);
        window.show();
    }

    public static void main(String ar[]) {
        launch(ar);
    }
}
