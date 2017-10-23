import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class AddStudent {

    private static BorderPane addMid() {
        Label nm=new Label("Name");
        Label eml=new Label("Email");
        Label prg=new Label("Program");
        Label idd=new Label("ID");
        Label typ=new Label("Branch");
        Label regcrs=new Label("Courses");

        nm.setMaxWidth(50);
        eml.setMaxWidth(50);
        prg.setMaxWidth(100);
        idd.setMaxWidth(50);
        typ.setMinWidth(50);

        regcrs.setMaxWidth(50);

        TextField nam=new TextField();
        TextField emal=new TextField();
        TextField id=new TextField();
        TextArea ta=new TextArea();

        nam.setMaxWidth(250);
        emal.setMaxWidth(250);
        id.setMaxWidth(250);
        ta.setMaxWidth(250);

        ComboBox<String> prog=new ComboBox<String>();
        prog.getItems().addAll("BTech","MTech","PhD");
        ComboBox<String> tpe=new ComboBox<String>();
        tpe.getItems().addAll("CSE","CSAM","ECE");
//		switch(prog.getSelectionModel().getSelectedItem().charAt(0)) {
//		case 'B':
//			tpe.getItems().addAll("CSE","CSAM","ECE");
//			break;
//		case 'M':
//			tpe.getItems().addAll("CSE","ECE");
//			break;
//		case 'P':
//			tpe.getItems().addAll("CSE","ECE");
//			break;
//		}
        HBox rone=new HBox(10);
        rone.getChildren().addAll(nm,nam);
        rone.setAlignment(Pos.CENTER);
        HBox rtwo=new HBox(10);
        rtwo.setAlignment(Pos.CENTER);
        rtwo.getChildren().addAll(idd,id);
        HBox rthree=new HBox(10);
        rthree.getChildren().addAll(eml,emal);
        rthree.setAlignment(Pos.CENTER);
        HBox rfour1=new HBox(10);
        rfour1.getChildren().addAll(prg,prog);
        rfour1.setAlignment(Pos.CENTER);
        HBox rfour2=new HBox(10);
        rfour2.getChildren().addAll(typ,tpe);
        rfour2.setAlignment(Pos.CENTER);

        HBox rfour=new HBox(10);
        rfour.getChildren().addAll(rfour1,rfour2);
        rfour.setAlignment(Pos.CENTER);
        HBox rfive=new HBox(10);
        rfive.getChildren().addAll(regcrs,ta);
        rfive.setAlignment(Pos.CENTER);



        Button ok = new Button("Save Changes");
        Button rem=new Button("Delete Student");
        Button go=new Button("Search");
        HBox last=new HBox(10);
        last.getChildren().addAll(go,ok,rem);

        VBox lol=new VBox(20);
        lol.setAlignment(Pos.CENTER);
        lol.getChildren().addAll(rone,rtwo,rthree,rfour,rfive,last);
        BorderPane bp=new BorderPane();

        bp.setCenter(lol);
        bp.setAlignment(lol, Pos.CENTER);
//        bp.setBottom(last);
//        bp.setAlignment(last,Pos.CENTER);
        bp.setPadding(new Insets(10,10,10,10));
        return(bp);
    }

    public static BorderPane compiler() {
        BorderPane mid=addMid();
        Text tops=new Text("STUDENT");
        tops.setStyle("-fx-font-weight:bold;-fx-font-size:150%");
        BorderPane b1=new BorderPane();
        BorderPane bp=new BorderPane();
        b1.setTop(tops);
        b1.setAlignment(tops, Pos.CENTER);
        //b1.setLeft(left);
        //b1.setAlignment(left, Pos.CENTER);
        b1.setCenter(mid);
        b1.setAlignment(mid, Pos.CENTER);
        b1.setPadding(new Insets(10,10,10,10));
        //bp.setTop(top);
        //bp.setAlignment(top,Pos.CENTER);
        bp.setCenter(b1);
        bp.setAlignment(b1,Pos.CENTER);
        bp.setPadding(new Insets(10,0,0,0));
        return(bp);
    }


}
