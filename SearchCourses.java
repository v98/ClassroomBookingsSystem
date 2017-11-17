import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class SearchCourses extends Application{
    static ListView<String> resultview;
    static Button go,addcourse;
    static TextArea t2;
    static TextField searchbar;
    static Label search;
    
    public static void main(String args[]){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene lol=new Scene(compiler(),600,700);
        primaryStage.setScene(lol);
        primaryStage.show();
    }
    public static BorderPane compiler(){
        BorderPane bp=new BorderPane();
                
        Text t=new Text("FIND COURSES");
        t.setStyle("-fx-font-weight:bold;-fx-font-size:150%");
                
        search=new Label("Search: ");
        searchbar=new TextField();
        searchbar.setMinWidth(400);
        go=new Button("Go");
        
        HBox top=new HBox(10);
        top.getChildren().addAll(search,searchbar,go);
        top.setAlignment(Pos.CENTER);
        
        resultview=new ListView();
        resultview.setMinSize(70, 100);
        t2=new TextArea();
        t2.setEditable(false);
        t2.setMinSize(200,100);
        
        HBox middle=new HBox(20);
        middle.getChildren().addAll(resultview,t2);
        middle.setAlignment(Pos.CENTER);
        
        addcourse=new Button("Add Course");
        HBox bottom=new HBox(10);
        bottom.getChildren().add(addcourse);
        bottom.setAlignment(Pos.BOTTOM_RIGHT);
        
        VBox main=new VBox(10);
        main.getChildren().addAll(t,top,middle,bottom);
        main.setAlignment(Pos.CENTER);
        main.setPadding(new Insets(10,10,10,10));
        
        bp.setCenter(main);
        bp.setAlignment(main,Pos.CENTER);
        
        go.setOnAction(e->{
            resultview.getItems().clear();
            t2.setText("");
            if(searchbar.getText().isEmpty()){
                AlertBox.display("Classroom Booking System", "Empty search string!");
            }
            else{
                
                ArrayList<String> lol=search(searchbar.getText());
                for(String s: lol){
                   resultview.getItems().add(s); 
                }
                resultview.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
                            String str=resultview.getSelectionModel().getSelectedItem();
                            //System.out.println(str);
                            t2.setText(getCDetails(str));
			}
		});
                
            }
        });
        
        addcourse.setOnAction(e->{
            String str=resultview.getSelectionModel().getSelectedItem();
            addC(str);
            resultview.getItems().remove(str);
            t2.setText("");
        });
        
        return bp;
    }
    
    
    public static ArrayList<String> search(String s){
        ArrayList<String> result=new ArrayList<String>();
        try{
            Class.forName("SearchCourses");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root","vrinda@16186");
            Statement stmt1=con.createStatement();
            
            ResultSet rs1=stmt1.executeQuery("select cid from courses where postcond like '%"+s+"%';");
            
            if(rs1.isBeforeFirst()){
                
                while(rs1.next()){
                    
                    result.add(rs1.getString(1));
                }
                
                for(String lol :result){
                    //System.out.println(lol+"wow");
                }
                result=removeExisting(result);
                
                stmt1.close();
                con.close();
            }
            else{
                //System.out.println("crap");
                stmt1.close();
                con.close();
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }    
        return result;
    }
    
    private static ArrayList<String> removeExisting(ArrayList<String> result){
        
        try{
            Class.forName("SearchCourses");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root","vrinda@16186");
            ArrayList<String> good=new ArrayList<>();
            for(String k:result){
                //System.out.println(k+"shit");
                Statement stmt1=con.createStatement();
                ResultSet rs1=stmt1.executeQuery("select courses from user where email='"+LoginPage.emailid+"';");
                rs1.next();
                String fss=rs1.getString(1);
                //System.out.println(fss);
                if(fss.contains(k)){
                    good.add(k);
                }
            
            }
            
            for(String donkey:good){
                result.remove(donkey);
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    
    private static boolean isClash(String cid1,String cid2){
        return false;
    }
    
    private static String getCDetails(String cid){
        String str=new String();
        try{
            Class.forName("SearchCourses");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root","vrinda@16186");
            Statement stmt1=con.createStatement();
            ResultSet rs1=stmt1.executeQuery("select cid,name,type,teacher,precond,postcond,credits from courses where cid='"+cid+"';");
            if(rs1.isBeforeFirst()){
                rs1.next();
                //System.out.println(rs1.getString(2));
                str=str.concat(rs1.getString(1)+"\t"+rs1.getString(2)+"\nType\t:"+rs1.getString(3)+"\nInstructor\t:"+rs1.getString(4)+"\nCredits\t:"+rs1.getString(7)+"\nPreConditions:\n"+rs1.getString(5)+"\nPostConditions:\n"+rs1.getString(6));
                //System.out.println(str);
                stmt1.close();
                con.close();
                
            }
            else{
                //System.out.println("sjit");
                stmt1.close();
                con.close();
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        //System.out.println(str);
        return str;
    }
    private static void addC(String cid){
        try{
            Class.forName("SearchCourses");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root","vrinda@16186");
            Statement stmt1=con.createStatement();
            Statement stmt2=con.createStatement();
            ResultSet rs1=stmt1.executeQuery("select courses from user where email='"+LoginPage.emailid+"';");
            rs1.next();
            String s=rs1.getString(1)+cid+",";
            stmt2.executeUpdate("update user set courses='"+s+"' where email='"+LoginPage.emailid+"';");
            stmt1.close();
            stmt2.close();
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
