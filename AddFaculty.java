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

    private static BorderPane addMid() {
        Label nm = new Label("Name");
        Label eml = new Label("Email");
        Label idd = new Label("ID");
        Label regcrs = new Label("Courses");

        nm.setMaxWidth(100);
        eml.setMaxWidth(100);
        idd.setMaxWidth(100);
        regcrs.setMaxWidth(100);

        TextField nam = new TextField();
        TextField emal = new TextField();
        TextField id = new TextField();
        TextArea ta = new TextArea();

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

        Button ok = new Button("Save Changes");
        Button rem=new Button("Delete Faculty");
        Button go=new Button("Search");
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
        return (bp);
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
