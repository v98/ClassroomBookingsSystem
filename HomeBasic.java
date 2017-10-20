
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class HomeScreen extends Application {
    static Stage window;
    static Scene signin,home;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window=primaryStage;
        Scene sc=new Scene(addBPane(addVBox(0,window),addTop(),addMain()));
        window.setScene(sc);
        window.show();
    }
    public static Scene getScene(int t,Stage s,Scene back){
        signin=back;
        window=s;
        home=new Scene(addBPane(addVBox(t,s),addTop(),addMain()),500,300);
        return home;
    }
    private static VBox addVBox(int t,Stage s) {
        //Text t1=new Text("Vrinda Mittal");
        VBox v=new VBox();
        if (t==0){
            Button[] panel=new Button[5];
            panel[0]=new Button("TimeTable");
            panel[1]=new Button("Search Courses");
            panel[2]=new Button("Room Availability");
            panel[3]=new Button("Pending Requests");
            panel[4]=new Button("My Profile");
            for(int i=0;i<5;i++){
                panel[i].setMaxWidth(Double.MAX_VALUE);
                v.getChildren().add(panel[i]);
                v.setPadding(new Insets(0, 20, 0, 0));
                v.setAlignment(Pos.CENTER);
            }
            panel[2].setOnAction(e ->{
                BorderPane bp=RoomAvailability.compiler();
                s.setScene(new Scene(addBPane(addVBox(0,s),addTop(),bp),500,300));
            });
            panel[3].setOnAction(e ->{
                BorderPane bp=MyRequests.compiler();
                s.setScene(new Scene(addBPane(addVBox(0,s),addTop(),bp),500,300));
            });
            panel[4].setOnAction(e ->{
                BorderPane bp=Profile.addCenter();
                s.setScene(new Scene(addBPane(addVBox(0,s),addTop(),bp),500,300));
            });
        }
        else if(t==1) {
            Button[] panel =new  Button[3];
            panel[0] = new Button("TimeTable");
            panel[1] = new Button("Room Availability");
            panel[2] = new Button("My Profile");
            for(int i=0;i<3;i++){
                panel[i].setMaxWidth(Double.MAX_VALUE);
                v.getChildren().add(panel[i]);
                v.setPadding(new Insets(0, 20, 0, 0));
                v.setAlignment(Pos.CENTER);
            }

        }

        else{
            Button[] panel=new Button[7];
            panel[0]=new Button("TimeTable");// branch and year specific
            panel[1]=new Button("Courses"); // add or delete course
            panel[2]=new Button("Students");//edit or remove student
            panel[3]=new Button("Faculty");//edit  or remove faculty
            panel[4]=new Button("Room Availability");
            panel[5]=new Button("Room Requests");
            panel[6]=new Button("MyProfile");
            for(int i=0;i<7;i++){
                panel[i].setMaxWidth(Double.MAX_VALUE);
                v.getChildren().add(panel[i]);
                v.setPadding(new Insets(0, 20, 0, 0));
                v.setAlignment(Pos.CENTER);
            }

        }

        return v;
    }
    private static BorderPane addTop(){
        BorderPane bp=new BorderPane();
        Button lo=new Button("Log Out");
        Button un=new Button("Username");
        lo.setOnAction(e -> window.setScene(signin));
        un.setOnAction(e ->window.setScene(home));
        un.setMaxWidth(Double.MAX_VALUE);
        bp.setLeft(un);
        bp.setRight(lo);
        bp.setAlignment(lo, Pos.TOP_LEFT);
        bp.setAlignment(un, Pos.TOP_RIGHT);
        bp.setPadding(new Insets(0,10,10,10));
        return bp;
    }
    private static BorderPane addBPane(VBox v, BorderPane b,BorderPane main) {
        BorderPane bp=new BorderPane();
        //Button lo=new Button("Log Out");
        //Text txt=new Text("Welcome to ClassRoom Booking System :)");
        bp.setLeft(v);
        bp.setTop(b);
//        bp.setCenter(txt);
        bp.setCenter(main);
        //bp.setAlignment(lo, Pos.TOP_LEFT);
        //bp.setAlignment(txt,Pos.CENTER);
        bp.setPadding(new Insets(10,10,10,10));
        return(bp);
    }

    private static BorderPane addMain(){
        BorderPane bp=new BorderPane();
        Text txt=new Text("Welcome to ClassRoom Booking System :)");
        bp.setCenter(txt);
        bp.setAlignment(txt,Pos.CENTER);
        bp.setPadding(new Insets(10,10,10,10));
        return bp;

    }

    public static void main(String ar[]) {
        launch(ar);
    }
}
