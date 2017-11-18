
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import static javafx.scene.text.Font.font;
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
public class BookRoom extends Application {
    Stage window;
    static Label roomid,roomcap,tfrom,tto,date;
    static Button book;
    static Text rid,rcap,stime,etime,ddate;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window=primaryStage;
        
        Scene sc=new Scene(compiler(),300,300);
        window.setScene(sc);
        window.show();
    }
    public static BorderPane compiler(){
        BorderPane bp=new BorderPane();
        
        Text title=new Text("Booking Details");
        title.setFont(Font.font("Helvetica",30));
        title.setFill(Color.BLACK);
        
        roomid=new Label("Room ID :");
        roomcap=new Label("Room Capacity :");
        tfrom=new Label("Time From :");
        tto=new Label("Time To :");
        date= new Label("Date :");
        
        rid=new Text(RoomAvailability.myrid);
        rid.setFont(Font.font("Helvetica"));
        rid.setFill(Color.BROWN);
        
        rcap=new Text(RoomAvailability.myrcap);
        rcap.setFont(Font.font("Helvetica"));
        rcap.setFill(Color.BROWN);
        
        stime=new Text(RoomAvailability.mystime);
        stime.setFont(Font.font("Helvetica"));
        stime.setFill(Color.BROWN);
        
        etime=new Text(RoomAvailability.myetime);
        etime.setFont(Font.font("Helvetica"));
        etime.setFill(Color.BROWN);
        
        ddate=new Text(RoomAvailability.mydate);
        ddate.setFont(Font.font("Helvetica"));
        ddate.setFill(Color.BROWN);
        
        GridPane gp=new GridPane();
        gp.add(roomid, 1, 1);
        gp.add(roomcap, 1, 2);
        gp.add(tfrom, 1, 3);
        gp.add(tto, 1, 4);
        gp.add(date, 1, 5);
        
        gp.add(rid, 2, 1);
        gp.add(rcap, 2, 2);
        gp.add(stime, 2, 3);
        gp.add(etime, 2, 4);
        gp.add(ddate, 2, 5);
        
        gp.setAlignment(Pos.CENTER);
        
        gp.setPadding(new Insets(10,10,10,10));
        
        book=new Button("Book Room");
        
        bp.setCenter(gp);
        bp.setBottom(book);
        
        return bp;
    }
    
    
    public static void main(String[] args){
        launch(args);
    }
    
}
