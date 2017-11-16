import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class ViewCourses{
    static List ls;
    static ListView<String> list = new ListView<String>();
    static HashMap<String, ArrayList<String>> hm;
    static ObservableList<String> items;
    private static TextArea t2;

//    @Override
//    public void start(Stage arg0) {
//        Scene sc=new Scene(compiler(addTop(),addVBox(),addCenter()));
//        Stage s=new Stage();
//        s.setScene(sc);
//        s.setTitle("My Courses");
//        s.show();
//    }

//    private static BorderPane addTop(){
//        BorderPane bp=new BorderPane();
//        Button lo=new Button("Log Out");
//        Text txt=new Text("MOCCHI");
//        txt.maxWidth(Double.MAX_VALUE);
//        bp.setLeft(txt);
//        bp.setRight(lo);
//        bp.setAlignment(lo, Pos.TOP_LEFT);
//        bp.setPadding(new Insets(0,10,0,10));
//        return bp;
//    }
//    private static VBox addVBox() {
//        Button b1=new Button("Time Table");
//        Button b2=new Button("Search Courses");
//        Button b3=new Button("Room Availability");
//        Button b4=new Button("My Requests");
//        Button b5=new Button("My Profile");
//
//        VBox v=new VBox();
//        b1.setMaxWidth(Double.MAX_VALUE);
//        b2.setMaxWidth(Double.MAX_VALUE);
//        b3.setMaxWidth(Double.MAX_VALUE);
//        b4.setMaxWidth(Double.MAX_VALUE);
//        b5.setMaxWidth(Double.MAX_VALUE);
//        v.getChildren().addAll(b1,b2,b3,b4,b5);
//        v.setPadding(new Insets(10, 20, 10, 10));
//        v.setAlignment(Pos.CENTER);
//
//        return v;
//    }

    public static void addCenter() {


        GridPane gp=new GridPane();
        VBox bp=new VBox(10);
        Text head=new Text("MY COURSES");
        head.setStyle("-fx-font-weight:bold;-fx-font-size:125%;");
        bp.getChildren().add(head);

        Button rem=new Button("Remove Course");
        Button done=new Button("Done");
        //GridPane bp1=new GridPane();
        Text crs=new Text("Courses");
        Text det=new Text("Course Details");
       
        //ListView<String> list = new ListView<String>();
        t2=new TextArea();
        
        t2.setEditable(false);
        t2.setPrefHeight(150);
        t2.setPrefWidth(280);
        list.setPrefWidth(100);
        list.setPrefHeight(70);
        
        //ObservableList<String> items =FXCollections.observableArrayList ("Course 1", "Course 2", "Course 3", "Course 4");
        //list.setItems(items);\
        connect();
        
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
                                //System.out.println(list.getSelectionModel().getSelectedItem());
				ArrayList<String> result=hm.get(list.getSelectionModel().getSelectedItem());
                                //System.out.println(result.get(0).isEmpty());
                                String s = result.get(0) + " " + result.get(1) + "\nType: " + result.get(2) + "\nInstructor: " + result.get(3)+ "\nCredits: " + result.get(4) + "\nPre-Conditions: " + result.get(5) + "\nPost-Conditions: "+ result.get(6);
                                
                                t2.setText(s);
			}
		});
        
        rem.setOnAction(e-> {
            remove();
            t2.setText("");
        });

        gp.setHgap(5);
        gp.setVgap(5);
        gp.add(crs, 1, 1);
        gp.add(det, 2, 1);
        gp.add(list, 1, 2);
        gp.add(t2, 2, 2);
        gp.add(rem, 2, 3);

        bp.getChildren().add(gp);

        HBox lower=new HBox(10);
        lower.getChildren().addAll(rem,done);
        lower.setAlignment(Pos.CENTER);
        bp.getChildren().add(lower);
        bp.setAlignment(Pos.CENTER);
        bp.setPadding(new Insets(10,10,10,10));


        Scene scene1=new Scene(bp,300,300);
        Stage courses=new Stage();
        courses.setTitle("Classroom Booking System: My Courses");
        
        courses.setScene(scene1);
        done.setOnAction(e ->courses.close());
        courses.show();

    }
    
    public static void connect() {
	try {
            Class.forName("ViewCourses");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root","vrinda@16186");
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery("select courses from user where email='"+LoginPage.emailid+"';");
            rs.next();
            String[] courseslol=rs.getString(1).split(",");
            int n=courseslol.length;
            hm = new HashMap<String, ArrayList<String>>();
            ls=new ArrayList<String>();
            for(int i=0;i<n;i++){
                Statement stmt1 = con.createStatement();
                //System.out.println(courseslol[i]);
                ResultSet rs1 = stmt1.executeQuery("select cid,name,type,teacher,credits,precond,postcond from courses where cid='"+courseslol[i]+"';");
                //System.out.println("select cid,name,type,teacher,credits,precond,postcond from courses where cid='"+courseslol[i]+"';");
                while(rs1.next()){
                    ArrayList<String> temp=new ArrayList<String>();
                    temp.add(rs1.getString(1));//cid
                    temp.add(rs1.getString(2));//name
                    temp.add(rs1.getString(3));//type
                    temp.add(rs1.getString(4));//teacher
                    temp.add(rs1.getString(5));//credits
                    temp.add(rs1.getString(6));//precond
                    temp.add(rs1.getString(7));//postcond
                    ls.add(rs1.getString(2));
                    hm.put(rs1.getString(2),temp);
                    
                }
                stmt1.close();   
            }
            items=FXCollections.observableArrayList(ls);
            
            list.setItems(items);
            stmt.close();
            
            con.close();
            
            
            
            } catch (Exception e) {
			e.printStackTrace();
            }
	}
    
    private static void remove(){
        try{
            Class.forName("ViewCourses");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root","vrinda@16186");
            Statement stmt1 = con.createStatement();
            String q=list.getSelectionModel().getSelectedItem();
            ResultSet rs1=stmt1.executeQuery("select courses from user where email='"+LoginPage.emailid+"';");
            rs1.next();
            
            Statement stmt2 = con.createStatement();
            ResultSet rs2=stmt2.executeQuery("select cid from courses where name='"+q+"';");
            rs2.next();
            
            String t=rs1.getString(1).replace(rs2.getString(1)+",","");
            
            Statement stmt3=con.createStatement();
            
            stmt3.executeUpdate("update user set courses='"+t+"' where email='"+LoginPage.emailid+"';");
            connect();
            stmt1.close();
            stmt2.close();
            stmt3.close();
            con.close();
            
            
        }
        catch(Exception t){
            t.printStackTrace();
            
        }
    }


}
