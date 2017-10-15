import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    // We can use this one to display warnings like "loginID/password do not match etc"

    public static void display(String title,String msg){

        //title-->warning box ka title, msg-->warning jo display hogi

        Stage window2=new Stage();
        window2.initModality(Modality.APPLICATION_MODAL);
        window2.setTitle(title);
        window2.setMinWidth(300);

        Label label=new Label(msg);

        Button close=new Button("Ok");
        close.setOnAction(e->window2.close());

        VBox layout =new VBox(20);
        layout.getChildren().addAll(label,close);
        layout.setAlignment(Pos.CENTER);

        window2.setScene(new Scene(layout));
        window2.showAndWait();

    }
}
