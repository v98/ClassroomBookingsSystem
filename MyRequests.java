
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class MyRequests{
    public static TableView<RBookings> tb;
    public static ObservableList<RBookings> stu;
    public static ObservableList<RBookings> ad;
    public static String selectedbid,selectedstatus;
    
    public static class RBookings{
        String roomid,status,timing,date;
        int bid;
        public RBookings(int bid,String rid,String stat,String time,String date){
            roomid=rid;
            status=stat;
            timing=time;
            this.date=date;
            this.bid=bid;
            
        }
        
        public String getRoomid(){
            return roomid;
            
        }
        
        public String getStatus(){
            return status;
        }
        
        public String getTiming(){
            return timing;
        }
        
        public String getDate(){
            return date;
        }
        
        public int getBid(){
            return bid;
        }
    }

    public static BorderPane compiler() {
        
        tb=new TableView<>();
        
        TableColumn<RBookings,String> id=new TableColumn<>("Room ID");
        TableColumn<RBookings,String> name=new TableColumn<>("Status");
        TableColumn<RBookings,String> rsn=new TableColumn<>("Timing");
        TableColumn<RBookings,String> rsn2=new TableColumn<>("Date");
        
        id.setCellValueFactory(new PropertyValueFactory<>("roomid"));
        name.setCellValueFactory(new PropertyValueFactory<>("status"));
        rsn.setCellValueFactory(new PropertyValueFactory<>("timing"));
        rsn2.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        tb.getColumns().addAll(id,name,rsn,rsn2);
        tb.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tb.setEditable(false);
        tb.setMaxHeight(300);
        tb.setMaxWidth(300);
         
        stu=getOBLstudent();
        
        tb.setItems(stu);
        
        Text tp = new Text("Pending Requests");
        tp.setStyle("-fx-font-weight:bold;-fx-font-size:150%");

        VBox bp=new VBox(10);
        bp.getChildren().add(tp);

        bp.getChildren().add(tb);

        bp.setPadding(new Insets(10,10,10,10));
        BorderPane bp1=new BorderPane();

        bp.setAlignment(Pos.CENTER);
        bp1.setCenter(bp);
        bp1.setAlignment(bp, Pos.CENTER);
        bp1.setPadding(new Insets(10,10,10,10));
        
        
        

        return(bp1);
    }
    public static BorderPane compiler2() {
        tb=new TableView<>();
        
        TableColumn<RBookings,Integer> sno=new TableColumn<>("Booking ID");
        TableColumn<RBookings,String> id=new TableColumn<>("Room ID");
        TableColumn<RBookings,String> name=new TableColumn<>("Status");
        TableColumn<RBookings,String> rsn=new TableColumn<>("Timing");
        TableColumn<RBookings,String> rsn2=new TableColumn<>("Date");
        
        sno.setCellValueFactory(new PropertyValueFactory<>("bid"));
        id.setCellValueFactory(new PropertyValueFactory<>("roomid"));
        name.setCellValueFactory(new PropertyValueFactory<>("status"));
        rsn.setCellValueFactory(new PropertyValueFactory<>("timing"));
        rsn2.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        tb.getColumns().addAll(id,name,rsn,rsn2);
        tb.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tb.setEditable(false);
        tb.setMaxHeight(300);
        tb.setMaxWidth(300);
        
        ad=getOBLadmin();
        tb.setItems(ad);
        tb.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
                                RBookings titem=tb.getSelectionModel().getSelectedItem();
                                selectedbid=titem.getBid()+"";
                                selectedstatus=titem.getStatus();
                                if(selectedstatus.equals("pending")){
                                    ManageRequests.studentcompiler();
                                }
                                else if(selectedstatus.equals("confirmed")){
                                    ManageRequests.otherscompiler();
                                }
                                tb.setItems(getOBLadmin());
			}
		});
        Text tp = new Text("Room Bookings");
        tp.setStyle("-fx-font-weight:bold;-fx-font-size:150%");
        
        VBox bp=new VBox(10);
        bp.getChildren().add(tp);


        bp.getChildren().add(tb);
        
        bp.setPadding(new Insets(10,10,10,10));
        BorderPane bp1=new BorderPane();

        bp.setAlignment(Pos.CENTER);
        bp1.setCenter(bp);
        bp1.setAlignment(bp, Pos.CENTER);
        bp1.setPadding(new Insets(10,10,10,10));
        

        return(bp1);
    }
    
    public static ObservableList<RBookings> getOBLstudent(){
        ObservableList<RBookings> oblist=FXCollections.observableArrayList();
        try{
            Class.forName("MyRequests");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root", "vrinda@16186");
            Statement stmt = con.createStatement();
            Statement stmt2=con.createStatement();
            stmt2.executeUpdate("update bookings set status='cancelled' where status='pending' and datediff(bookingdate,sysdate()) > 5;");
            stmt2.close();
            ResultSet rs=stmt.executeQuery("select rid,status,Time(stime),Time(etime),Date(stime),datediff(bookingdate,sysdate()),bid from bookings where email='"+LoginPage.emailid+"';");
//            rs.next();
            if(rs.isBeforeFirst()){
                while(rs.next()){
                        //System.out.println(rs.getString(2));
                        RBookings temp=new RBookings(Integer.parseInt(rs.getString(7)),rs.getString(1),rs.getString(2),rs.getString(3)+"-"+rs.getString(4),rs.getString(5));
                        oblist.add(temp);
                    
                }
                stmt.close();
                con.close();
            }
            else{
                stmt.close();
                con.close();
            }
            
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return oblist;
    }
    
    public static ObservableList<RBookings> getOBLadmin(){
        ObservableList<RBookings> oblist=FXCollections.observableArrayList();
        try{
            Class.forName("MyRequests");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root", "vrinda@16186");
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("select rid,status,Time(stime),Time(etime),Date(stime),datediff(bookingdate,sysdate()),bid from bookings where status <> 'cancelled';");
            if(rs.isBeforeFirst()){
                while(rs.next()){
                    RBookings temp=new RBookings(Integer.parseInt(rs.getString(7)),rs.getString(1),rs.getString(2),rs.getString(3)+"-"+rs.getString(4),rs.getString(5));
                    oblist.add(temp);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return oblist;
    }

}
