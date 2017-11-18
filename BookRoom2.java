import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import static javafx.scene.text.Font.font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vrinda
 */
public class BookRoom2{
    static Stage window;
    static Label roomid,roomcap,tfrom,tto,date,purpose;
    static Button book;
    static TextField rid,rcap,stime,etime,ddate;
    static TextArea t2;
    
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        window=primaryStage;
//        
//        Scene sc=new Scene(compiler(),300,300);
//        window.setScene(sc);
//        window.show();
//    }
    public static void compiler(){
        Stage window=new Stage();
       
        BorderPane bp=new BorderPane();
        
        Text title = new Text("Booking Details");
	title.setStyle("-fx-font-weight:bold;-fx-font-size:150%");
//        title.setFont(Font.font("Helvetica",30));
//        title.setFill(Color.BLACK);
        
        roomid=new Label("Room ID :");
        roomcap=new Label("Room Capacity :");
        tfrom=new Label("Time From :");
        tto=new Label("Time To :");
        date= new Label("Date :");
        purpose=new Label("Purpose :");
        //TextFlow textFlow = new TextFlow();
        
        rid=new TextField(RoomAvailability.myrid);
        rid.setEditable(false);
//        rid.setFont(Font.font("Helvetica"));
//        rid.setFill(Color.BROWN);
        
        rcap=new TextField(RoomAvailability.myrcap);
        rcap.setEditable(false);
//        rcap.setFont(Font.font("Helvetica"));
//        rcap.setFill(Color.BROWN);
        
        stime=new TextField(RoomAvailability.mystime);
        stime.setEditable(false);
//        stime.setFont(Font.font("Helvetica"));
//        stime.setFill(Color.BROWN);
        
        etime=new TextField(RoomAvailability.myetime);
        stime.setEditable(false);
//        etime.setFont(Font.font("Helvetica"));
//        etime.setFill(Color.BROWN);
        
        ddate=new TextField(RoomAvailability.mydate);
        ddate.setEditable(false);
//        ddate.setFont(Font.font("Helvetica"));
//        ddate.setFill(Color.BROWN);
        t2=new TextArea();
        t2.setMaxWidth(150);
        t2.setMinHeight(70);
        
        GridPane gp=new GridPane();
        gp.add(roomid, 1, 1);
        gp.add(roomcap, 1, 2);
        gp.add(tfrom, 1, 3);
        gp.add(tto, 1, 4);
        gp.add(date, 1, 5);
        gp.add(purpose, 1, 6);
        gp.add(rid, 2, 1);
        gp.add(rcap, 2, 2);
        gp.add(stime, 2, 3);
        gp.add(etime, 2, 4);
        gp.add(ddate, 2, 5);
        gp.add(t2,2,6);
        
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(10);
        gp.setPadding(new Insets(10,10,10,10));
        
        book=new Button("Send Request");
        bp.setTop(title);
        bp.setAlignment(title,Pos.CENTER);
        bp.setCenter(gp);
        bp.setAlignment(gp,Pos.CENTER);
        bp.setBottom(book);
        bp.setAlignment(book,Pos.CENTER);
        bp.setPadding(new Insets(10,10,10,10));
        
        book.setOnAction(e->{
            boolean val=ConfirmBox.display("Classroom Booking System","Are you sure you want to request this room?");
            if(val==true){
                //System.out.println("Awesome.");
                try{
                    Class.forName("BookRoom2");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root", "vrinda@16186");
                    Statement stmt = con.createStatement();
                    //System.out.println("insert into bookings(rid,email,capacity,status,stime,etime,purpose) values('"+RoomAvailability.myrid+"','"+LoginPage.emailid+"',"+Integer.parseInt(RoomAvailability.myrcap)+",'confirmed','"+RoomAvailability.mydate+" "+RoomAvailability.mystime+"','"+RoomAvailability.mydate+" "+RoomAvailability.myetime+"';)");
                    stmt.executeUpdate("insert into bookings(rid,email,capacity,status,stime,etime,purpose) values('"+RoomAvailability.myrid+"','"+LoginPage.emailid+"',"+Integer.parseInt(RoomAvailability.myrcap)+",'pending','"+RoomAvailability.mydate+" "+RoomAvailability.mystime+"','"+RoomAvailability.mydate+" "+RoomAvailability.myetime+"','"+t2.getText()+"');");
        
                    
                }
                catch(Exception t){
                    t.printStackTrace();
                }
                window.close();
                
            }
            else{
                //System.out.println("Ok Bye.");
                window.close();
            }
        });
        
        Scene sc=new Scene(bp,500,300);
        window.setScene(sc);
        window.show();
        //return bp;
    }
    
    
//    public static void main(String[] args){
//        launch(args);
//    }
    
}