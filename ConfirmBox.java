import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {
    static Boolean answer;
    public static boolean display(String title,String msg){
        Stage window2=new Stage();
        window2.initModality(Modality.APPLICATION_MODAL);
        window2.setTitle(title);
        window2.setMinWidth(300);

        Label label=new Label(msg);

        Button yes=new Button("Yes");
        Button no=new Button("No");

        yes.setOnAction(e -> {
            answer=true;
            window2.close();
        });

        no.setOnAction(e -> {
            answer=false;
            window2.close();
        });


        HBox layout =new HBox(20);
        layout.setPadding(new Insets(10,10,10,10));
        layout.getChildren().addAll(label,yes,no);
        layout.setAlignment(Pos.CENTER);


        window2.setScene(new Scene(layout));
        window2.showAndWait();
        return answer;

    }
}
