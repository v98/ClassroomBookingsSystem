import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.application.Application;
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
            if(searchbar.getText().isEmpty()){
                AlertBox.display("Classroom Booking System", "Empty search string!");
            }
            else{
//                for(String s:search(searchbar.getText())){
//                   resultview.getItems().add(s); 
//                }
                resultview.getItems().addAll("lol","hello","fuckol"); 
                t2.setText(getCDetails("lol"));
            }
        });
        
        return bp;
    }
    
    
    public static ArrayList<String> search(String s){
        ArrayList<String> result=new ArrayList<String>();
        
        return result;
    }
    
    private static boolean isClash(String cid1,String cid2){
        return false;
    }
    
    private static String getCDetails(String cid){
        return cid;
    }
}
