
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
public class ManageRequests{
    static Stage window;
    static Label roomid,roomcap,tfrom,tto,date,purpose,email;
    static Text rid,rcap,stime,etime,ddate,ppurpose,bemail;
    static Button rcancel,raccept,bcancel;
    public static int val; 
    public static void studentcompiler(){
        
        try{
            Class.forName("MyRequests");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root", "vrinda@16186");
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("select rid,email,capacity,status,time(stime),time(etime),date(stime),purpose from bookings where bid="+MyRequests.selectedbid+";");
            rs.next();
            window=new Stage();
       
            BorderPane bp=new BorderPane();
        
            Text title = new Text("Booking Details");
            title.setStyle("-fx-font-weight:bold;-fx-font-size:150%");
//        title.setFont(Font.font("Helvetica",30));
//        title.setFill(Color.BLACK);
        
            roomid=new Label("Room ID :");
            email= new Label("Email ID :");
            roomcap=new Label("Room Capacity :");
            tfrom=new Label("Time From :");
            tto=new Label("Time To :");
            date= new Label("Date :");
            purpose= new Label("Purpose :");
            
        
        //TextFlow textFlow = new TextFlow();
        
        rid=new Text(rs.getString(1));
        //rid.setEditable(false);
        rid.setFont(Font.font("Helvetica"));
        rid.setFill(Color.BROWN);
        
        bemail=new Text(rs.getString(2));
        //rid.setEditable(false);
        bemail.setFont(Font.font("Helvetica"));
        bemail.setFill(Color.BROWN);
        
        
        
        rcap=new Text(rs.getString(3));
        //rcap.setEditable(false);
        rcap.setFont(Font.font("Helvetica"));
        rcap.setFill(Color.BROWN);
        
        stime=new Text(rs.getString(5));
        //stime.setEditable(false);
        stime.setFont(Font.font("Helvetica"));
        stime.setFill(Color.BROWN);
        
        etime=new Text(rs.getString(6));
        //stime.setEditable(false);
        etime.setFont(Font.font("Helvetica"));
        etime.setFill(Color.BROWN);
        
        ddate=new Text(rs.getString(7));
        //ddate.setEditable(false);
        ddate.setFont(Font.font("Helvetica"));
        ddate.setFill(Color.BROWN);
        
        ppurpose=new Text(rs.getString(8));
        //ddate.setEditable(false);
        ppurpose.setFont(Font.font("Helvetica"));
        ppurpose.setFill(Color.BROWN);
        
        
        GridPane gp=new GridPane();
        gp.add(roomid, 1, 1);
        gp.add(email, 1, 2);
        gp.add(roomcap, 1, 3);
        gp.add(tfrom, 1, 4);
        gp.add(tto, 1, 5);
        gp.add(date, 1, 6);
        gp.add(purpose, 1, 7);
        
        gp.add(rid, 2, 1);
        gp.add(bemail, 2, 2);
        gp.add(rcap, 2, 3);
        gp.add(stime, 2, 4);
        gp.add(etime, 2, 5);
        gp.add(ddate, 2, 6);
        gp.add(ppurpose, 2, 7);
        
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(10);
        gp.setPadding(new Insets(10,10,10,10));
        
        raccept=new Button("Accept Request");
        rcancel=new Button("Reject Request");
        
        
        HBox hbox=new HBox(20);
        hbox.getChildren().addAll(raccept,rcancel);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10,10,10,10));
        
        bp.setTop(title);
        
        bp.setAlignment(title,Pos.CENTER);
        bp.setCenter(gp);
        bp.setAlignment(gp,Pos.CENTER);
        bp.setBottom(hbox);
        bp.setAlignment(hbox,Pos.CENTER);
        bp.setPadding(new Insets(10,10,10,10));
        
        raccept.setOnAction(e->{
            try{
                Class.forName("ManageRequests");
                Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root", "vrinda@16186");
                Statement stmt2=conn.createStatement();
                stmt2.executeUpdate("update bookings set status='confirmed' where bid="+MyRequests.selectedbid+";");
                
                stmt2.close();
                conn.close();
            }
            catch(Exception t){
                t.printStackTrace();
            }
            window.close();
        });
        
        rcancel.setOnAction(e->{
            try{
                Class.forName("ManageRequests");
                Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root", "vrinda@16186");
                Statement stmt2=conn.createStatement();
                stmt2.executeUpdate("update bookings set status='cancelled' where bid="+MyRequests.selectedbid+";");
                
                stmt2.close();
                conn.close();
            }
            catch(Exception t){
                t.printStackTrace();
            }
            window.close();    
            });
        
        Scene sc=new Scene(bp,300,300);
        window.setScene(sc);
        window.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void otherscompiler(){
        
        try{
            Class.forName("ManageRequests");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root", "vrinda@16186");
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("select rid,email,capacity,status,time(stime),time(etime),date(stime),purpose from bookings where bid="+MyRequests.selectedbid+";");
            rs.next();
            window=new Stage();
       
            BorderPane bp=new BorderPane();
        
            Text title = new Text("Booking Details");
            title.setStyle("-fx-font-weight:bold;-fx-font-size:150%");
//        title.setFont(Font.font("Helvetica",30));
//        title.setFill(Color.BLACK);
        
            roomid=new Label("Room ID :");
            email= new Label("Email ID :");
            roomcap=new Label("Room Capacity :");
            tfrom=new Label("Time From :");
            tto=new Label("Time To :");
            date= new Label("Date :");
            //purpose= new Label("Purpose :");
            
        
        //TextFlow textFlow = new TextFlow();
        
        rid=new Text(rs.getString(1));
        //rid.setEditable(false);
        rid.setFont(Font.font("Helvetica"));
        rid.setFill(Color.BROWN);
        
        bemail=new Text(rs.getString(2));
        //rid.setEditable(false);
        bemail.setFont(Font.font("Helvetica"));
        bemail.setFill(Color.BROWN);
        
        
        
        rcap=new Text(rs.getString(3));
        //rcap.setEditable(false);
        rcap.setFont(Font.font("Helvetica"));
        rcap.setFill(Color.BROWN);
        
        stime=new Text(rs.getString(5));
        //stime.setEditable(false);
        stime.setFont(Font.font("Helvetica"));
        stime.setFill(Color.BROWN);
        
        etime=new Text(rs.getString(6));
        //stime.setEditable(false);
        etime.setFont(Font.font("Helvetica"));
        etime.setFill(Color.BROWN);
        
        ddate=new Text(rs.getString(7));
        //ddate.setEditable(false);
        ddate.setFont(Font.font("Helvetica"));
        ddate.setFill(Color.BROWN);
        
//        ppurpose=new Text(rs.getString(8));
//        //ddate.setEditable(false);
//        ppurpose.setFont(Font.font("Helvetica"));
//        ppurpose.setFill(Color.BROWN);
//        
        
        GridPane gp=new GridPane();
        gp.add(roomid, 1, 1);
        gp.add(email, 1, 2);
        gp.add(roomcap, 1, 3);
        gp.add(tfrom, 1, 4);
        gp.add(tto, 1, 5);
        gp.add(date, 1, 6);
        //gp.add(purpose, 1, 7);
        
        gp.add(rid, 2, 1);
        gp.add(bemail, 2, 2);
        gp.add(rcap, 2, 3);
        gp.add(stime, 2, 4);
        gp.add(etime, 2, 5);
        gp.add(ddate, 2, 6);
        //gp.add(ppurpose, 2, 7);
        
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(10);
        gp.setPadding(new Insets(10,10,10,10));
        
        //baccept=new Button("Accept Request");
        bcancel=new Button("Cancel Booking");
        
        
        HBox hbox=new HBox(20);
        hbox.getChildren().add(bcancel);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10,10,10,10));
        
        bp.setTop(title);
        
        bp.setAlignment(title,Pos.CENTER);
        bp.setCenter(gp);
        bp.setAlignment(gp,Pos.CENTER);
        bp.setBottom(hbox);
        bp.setAlignment(hbox,Pos.CENTER);
        bp.setPadding(new Insets(10,10,10,10));
        
        bcancel.setOnAction(e->{
            try{
                Class.forName("ManageRequests");
                Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root", "vrinda@16186");
                Statement stmt2=conn.createStatement();
                stmt2.executeUpdate("update bookings set status='cancelled' where bid="+MyRequests.selectedbid+";");
                
                stmt2.close();
                conn.close();
            }
            catch(Exception t){
                t.printStackTrace();
            }
            window.close();
        });
        
        
        Scene sc=new Scene(bp,300,300);
        window.setScene(sc);
        window.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
