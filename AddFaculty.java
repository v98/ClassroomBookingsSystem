import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class AddFaculty {
    static TextField nam,emal,id;
    static TextArea ta;
    static Button ok,rem,go;
    private static BorderPane addMid() {
        Label nm = new Label("Name");
        Label eml = new Label("Email");
        Label idd = new Label("ID");
        Label regcrs = new Label("Courses");

        nm.setMaxWidth(100);
        eml.setMaxWidth(100);
        idd.setMaxWidth(100);
        regcrs.setMaxWidth(100);

        nam = new TextField();
        emal = new TextField();
        id = new TextField();
        ta = new TextArea();
        
        emal.setDisable(true);
        ta.setDisable(true);

        nam.setMaxWidth(250);
        emal.setMaxWidth(250);
        id.setMaxWidth(250);
        ta.setMaxWidth(250);

        GridPane gp = new GridPane();
        gp.add(nm, 1, 1);
        gp.add(nam, 2, 1);
        gp.add(idd, 1, 2);
        gp.add(id, 2, 2);
        gp.add(eml, 1, 3);
        gp.add(emal, 2, 3);
        gp.add(regcrs, 1, 4);
        gp.add(ta, 2, 4);
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setPadding(new Insets(10, 10, 10, 10));

        ok = new Button("Save Changes");
        rem=new Button("Delete Faculty");
        go=new Button("Search");
        
        ok.setDisable(true);
        rem.setDisable(true);
        
        GridPane gp1=new GridPane();
        gp1.add(go, 1, 1);
        gp1.add(ok, 2, 1);
        gp1.add(rem, 3, 1);
        gp1.setHgap(10);
        gp1.setVgap(10);

        BorderPane bp = new BorderPane();
        // bp.setTop(top);
        // bp.setAlignment(top,Pos.CENTER);
        bp.setCenter(gp);
        bp.setAlignment(gp, Pos.CENTER);
        bp.setBottom(gp1);
        bp.setAlignment(gp1, Pos.CENTER);
        
        go.setOnAction(e->{
            
            if(nam.getText().isEmpty()||id.getText().isEmpty()){
                AlertBox.display("Classroom Booking System", "Empty fields!");
            }
            else{
                String sname=nam.getText();
                String sid=id.getText();
                try{
                    Class.forName("AddFaculty");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root","vrinda@16186");
                    Statement stmnt = con.createStatement();
                    ResultSet rs = stmnt.executeQuery("select * from user where id='" + sid +"'&& name='"+sname+"' && type="+1+";");
                    rs.next();
                    if(!rs.isBeforeFirst()){
                        
                    id.setEditable(false);
                    
                    ok.setDisable(false);
                    rem.setDisable(false);
                    emal.setDisable(false);                        
                    ta.setDisable(false);
                    emal.setText(rs.getString(3));
                    ta.setText(rs.getString(8));
                    stmnt.close();
                    con.close();
                        
                    }
                    else{
                        stmnt.close();
                        con.close();
                    }
                  
                }
                catch(Exception t){
                    t.printStackTrace();
                }
            }
        });
        
        ok.setOnAction(e->{
            try{
                Class.forName("AddFaculty");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root","vrinda@16186");
                Statement stmnt = con.createStatement();
                
                stmnt.executeUpdate("update user set name='"+nam.getText()+"',email='" + emal.getText()+"',courses='" + ta.getText()+ "'where id='" + id.getText() + "';");
                //ok.setDisable(true);
                clear();
            }
            catch(Exception t){
                t.printStackTrace();
            }
        });
        
        rem.setOnAction(e->{
            try{
                Class.forName("AddFaculty");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root","vrinda@16186");
                Statement stmnt = con.createStatement();
                
                stmnt.executeUpdate("delete from user where id='"+id.getText()+"';");
                
                clear();
            }
            catch(Exception t){
                t.printStackTrace();
            }
            
        });
        return (bp);
    }
    public static void clear(){
        System.out.println(nam==null);
        nam.setText(null);
        emal.setText(null);
        id.setText(null);
        ta.setText(null);
        ok.setDisable(true);
        rem.setDisable(true);
        emal.setDisable(true);
        ta.setDisable(true);
    }

    public static BorderPane compiler() {
        BorderPane mid=addMid();
        Text tops = new Text("FACULTY");
        tops.setStyle("-fx-font-weight:bold;-fx-font-size:150%");
        BorderPane b1 = new BorderPane();
        BorderPane bp = new BorderPane();
        b1.setTop(tops);
        b1.setAlignment(tops, Pos.CENTER);
//        b1.setLeft(left);
//        b1.setAlignment(left, Pos.CENTER);
        b1.setCenter(mid);
        b1.setAlignment(mid, Pos.CENTER);
        b1.setPadding(new Insets(10, 10, 10, 10));
//        bp.setTop(top);
//        bp.setAlignment(top, Pos.CENTER);
        bp.setCenter(b1);
        bp.setAlignment(b1, Pos.CENTER);
        bp.setPadding(new Insets(10, 0, 0, 0));
        return (bp);
    }


}
