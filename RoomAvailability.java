import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import javafx.event.EventHandler;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class RoomAvailability {
    static TableView<TableItem> table;
    static ObservableList<TableItem> oblist;
    static Spinner<Integer> h1,h2,m1,m2;
    static LocalDate dt;
    static DatePicker dp;
    static String sh,sm,eh,em;
    static String myrid,mystime,myetime,myrcap,mydate;
    
    public static class TableItem{
        String roomid,status;
        Integer capacity;
    
    public TableItem(String rid,int cap,String stat){
        roomid=rid;
        capacity=cap;
        status=stat;
    }   
    public String getRoomid(){
        return roomid;
    }
    
    public String getStatus(){
        return status;
    }
    
    public Integer getCapacity(){
        return capacity;
    }
    }
    
    private static HBox addDate() {
        dp=new DatePicker();
        dp.setValue(LocalDate.now());
        dp.setMaxWidth(118);
        Text date=new Text("Date");
        
        HBox gp=new HBox(5);
        
        gp.getChildren().addAll(date,dp);

        return(gp);
    }

    private static GridPane addTime() {
        h1=new Spinner<Integer>();
        h2=new Spinner<Integer>();
        m1=new Spinner<Integer>();
        m2=new Spinner<Integer>();
        Text frm = new Text("From");
        Text to=new Text("To");

        SpinnerValueFactory<Integer> hs1=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23,00);
        SpinnerValueFactory<Integer> hs2=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23,00);
        SpinnerValueFactory<Integer> ms1=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,00);
        SpinnerValueFactory<Integer> ms2=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,00);

        h1.setValueFactory(hs1);
        h2.setValueFactory(hs2);
        m1.setValueFactory(ms1);
        m2.setValueFactory(ms2);

        h1.setMaxWidth(50);
        h2.setMaxWidth(50);
        m1.setMaxWidth(50);
        m2.setMaxWidth(50);
        
 
        GridPane tmp1=new GridPane();
        GridPane tmp2=new GridPane();
        tmp1.add(h1, 1, 1);
        tmp1.add(m1, 2, 1);
        tmp1.setHgap(10);
        tmp2.add(h2, 1, 1);
        tmp2.add(m2, 2, 1);
        tmp2.setHgap(10);

        GridPane gp=new GridPane();
        gp.add(frm, 1, 1);
        gp.add(tmp1, 2, 1);
        gp.add(to, 3, 1);
        gp.add(tmp2, 4, 1);
        gp.setHgap(10);
        return(gp);
    }

    @SuppressWarnings("rawtypes")
    private static TableView addTable() {
        TableColumn room=new TableColumn("RoomID");
        TableColumn cap=new TableColumn("Capacity");
        TableColumn avbl=new TableColumn("Status");
        TableView table=new TableView();
        table.getColumns().addAll(room,cap,avbl);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setEditable(false);
        table.setMaxWidth(322);
        table.setMaxHeight(322);
        return(table);
    }

    private static BorderPane addMid() {
        GridPane gp=new GridPane();
        gp.add(addDate(), 1, 2);
        gp.add(addTime(), 1, 1);
        //Button go=new Button("Go!");
        //gp.add(go,2,2);
        gp.setVgap(10);
        gp.setPadding(new Insets(10,10,10,10));
        BorderPane bp=new BorderPane();
        bp.setTop(gp);
        bp.setAlignment(gp, Pos.CENTER);
        bp.setCenter(addTable());
        bp.setAlignment(addTable(),Pos.CENTER);
        bp.setPadding(new Insets(0,10,15,10));
        return(bp);
    }

    public static BorderPane compiler() {
        BorderPane bp=new BorderPane();
        
        //table setup
        table=new TableView<>();
        
        TableColumn<TableItem,String> room=new TableColumn<>("RoomID");
        TableColumn<TableItem,Integer> cap=new TableColumn<>("Capacity");
        TableColumn<TableItem,String> al=new TableColumn<>("Status");
        
        room.setCellValueFactory(new PropertyValueFactory<>("roomid"));
        cap.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        al.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        table.getColumns().addAll(room,cap,al);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setEditable(false);
        table.setMaxWidth(322);
        table.setMaxHeight(322);
        
        
        VBox filler=new VBox(10);
        filler.getChildren().add(addTime());
        Button go=new Button("GO");
        
        go.setOnAction(e->{
            //int type=LoginPage.type;
            sh=h1.getEditor().getText();
            if(sh.length()==1){
                sh="0".concat(sh);
            }
            sm=m1.getEditor().getText();
            if(sm.length()==1){
                sm="0".concat(sm);
            }
            
            mystime=sh.concat(":"+sm);
            
            em=m2.getEditor().getText();
            if(em.length()==1){
                em="0".concat(em);
            }
            eh=h2.getEditor().getText();
            if(eh.length()==1){
                eh="0".concat(eh);
            }
            myetime=eh.concat(":"+em);
            
            dt=dp.getValue();
            mydate=dt.toString();
            oblist=getObsList(sh,sm,eh,em,dt);
            for(TableItem i:oblist){
                System.out.println(i.getCapacity());
            }
            table.setItems(oblist);
            if (true){
                
            }
            else{
                table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
                                TableItem titem=table.getSelectionModel().getSelectedItem();
                                myrid=titem.getRoomid();
                                myrcap=titem.getCapacity().toString();
                                
			}
		});
                
            }
            
            
            
        });
        
        HBox temp=new HBox(10);
        temp.getChildren().addAll(addDate(),go);
        temp.setAlignment(Pos.CENTER);

        filler.getChildren().addAll(temp,table);
        filler.setAlignment(Pos.CENTER);
        bp.setCenter(filler);

        bp.setPadding(new Insets(10,10,10,10));
        return(bp);
    }
    
    private static ObservableList<TableItem> getObsList(String starthour,String startminute,String endhour,String endminute,LocalDate date){
        oblist=FXCollections.observableArrayList();
        String startstring=date.toString().concat(" "+starthour+":"+startminute);
        String endstring=date.toString().concat(" "+endhour+":"+endminute);
        
        SimpleDateFormat format=new SimpleDateFormat("E");
        String day=format.format(Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())).toLowerCase();
        
        try{
            Class.forName("RoomAvailability");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root", "vrinda@16186");
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("select rid from rooms");
            ArrayList<String> check1=new ArrayList<>();
            while(rs.next()){
                check1.add(rs.getString(1));
            }
            stmt.close();
            
            Statement stmt2=con.createStatement();
            ResultSet rs1=stmt2.executeQuery("select rid,"+day+" from rooms");
            
            //check regular timetable
            while(rs1.next()){
                int val=0;
                String[] ops=rs1.getString(2).split(",");
                for(int i=0;i<ops.length;i++){
                    if(ops[i].equals("")){
                        continue;
                    }
                    System.out.println(ops[i]+" "+starthour+":"+startminute+"-"+endhour+":"+endminute);
                    boolean check=isClash(ops[i],starthour+":"+startminute+"-"+endhour+":"+endminute);
                    System.out.println(check);
                    if (check==true){
                        val=1;
                    }
                    if(val>0){
                        break;
                    }
                }
                if(val!=0){
                    System.out.println(rs1.getString(1));
                    check1.remove(rs1.getString(1));
                }
            }
            stmt2.close();
            //check bookings list
            Statement stmt3=con.createStatement();
            ResultSet rs3=stmt3.executeQuery("select rid,capacity,status,time(stime),time(etime),date(etime) from bookings where date(etime)='"+date.toString()+"';");
            rs3.next();
            if(!rs3.isBeforeFirst()){
                rs3.beforeFirst();
                while(rs3.next()){
                    boolean check=isClash(rs3.getString(4).substring(0,5)+"-"+rs3.getString(4).substring(0,5),starthour+":"+startminute+"-"+endhour+":"+endminute);
                    if(check==true){
                        check1.remove(rs3.getString(1));
                    }
                }
                
                for(String k:check1){
                    Statement stmttemp=con.createStatement();
                    ResultSet rstemp=stmttemp.executeQuery("select capacity from rooms where rid='"+k+"';");
                    rstemp.next();
                    //System.out.println(k);
                    oblist.add(new TableItem(k,Integer.parseInt(rstemp.getString(1)),"Available"));
                    stmttemp.close();
                }
                
            }
            else{
                for(String k:check1){
                    Statement stmttemp=con.createStatement();
                    ResultSet rstemp=stmttemp.executeQuery("select capacity from rooms where rid='"+k+"';");
                    rstemp.next();
                    oblist.add(new TableItem(k,Integer.parseInt(rstemp.getString(1)),"Available"));
                    stmttemp.close();
                }
            }
            stmt3.close();
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return oblist;
    }
    
    public static int isafter(String t1,String t2){
        int n1=Integer.parseInt(t1.substring(0,2)+t1.substring(3));
        int n2=Integer.parseInt(t2.substring(0,2)+t2.substring(3));
        if (n2>n1){
            return 2;
        }
        else if(n1>n2){
            return 1;
        }
        else{
            return 0;
        }
            
    }
    
    public static boolean isClash(String r1,String r2){
        String t1=r1.substring(0,5);
        String t2=r1.substring(6);
        String t11=r2.substring(0,5);
        String t22=r1.substring(6);
        
        if(isafter(t1,t11)==1){
            if(isafter(t22,t1)==2||isafter(t22,t1)==0){
                return true;
            }
            else{
                return false;
            }
        }
        else if(isafter(t1,t11)==2){
            if(isafter(t11,t2)==1){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return true;
        }
        
    }
    

    
}

//public static class TableItem{
//    String roomid,status;
//    int capacity;
//    
//    public TableItem(String rid,int cap,String stat){
//        roomid=rid;
//        capacity=cap;
//        status=stat;
//    } 
//}
