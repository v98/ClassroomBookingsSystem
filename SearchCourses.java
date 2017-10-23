	import javafx.geometry.Insets;
	import javafx.geometry.Pos;
	import javafx.application.Application;
	import javafx.scene.Scene;
	import javafx.scene.text.Text;
	import javafx.stage.Stage;
	import javafx.scene.control.Button;
	import javafx.scene.control.ComboBox;
	import javafx.scene.control.Label;
	import javafx.scene.control.TableColumn;
	import javafx.scene.control.TableView;
	import javafx.scene.control.TextField;
	import javafx.scene.control.TextArea;
	import javafx.scene.layout.BorderPane;
	import javafx.scene.layout.GridPane;
	
	public class SearchCourses extends Application{
		@Override 
		public void start(Stage arg0) {
			Scene sc=new Scene(compiler(addTop(),addGPane(),search(),addMid()));
			Stage s=new Stage();
			s.setScene(sc);
			s.setTitle("Timetable");
			s.show();
		}
		private static BorderPane addTop(){
	        BorderPane bp=new BorderPane();
	        Button lo=new Button("Log Out");
	        Text txt=new Text("MOCCHI");
	        txt.maxWidth(Double.MAX_VALUE);
	        bp.setLeft(txt);
	        bp.setRight(lo);
	        bp.setAlignment(lo, Pos.TOP_LEFT);
	        bp.setPadding(new Insets(0,10,0,10));
	        return bp;
	    }
		private static GridPane addGPane() {
	        Button b1=new Button("Time Table");
	        Button b2=new Button("Courses");
	        Button b3=new Button("Room Availability");
	        Button b4=new Button("View Pending Requests");
	        Button b5=new Button("Student");
	        Button b6=new Button("Faculty");
	        
	        GridPane v=new GridPane();
	        b1.setMaxWidth(Double.MAX_VALUE);
	        b2.setMaxWidth(Double.MAX_VALUE);
	        b3.setMaxWidth(Double.MAX_VALUE);
	        b4.setMaxWidth(Double.MAX_VALUE);
	        b5.setMaxWidth(Double.MAX_VALUE);
	        b6.setMaxWidth(Double.MAX_VALUE);
	        v.add(b1,1,1);
	        v.add(b2,1,2);
	        v.add(b3,1,3);
	        v.add(b4,1,4);
	        v.add(b5,1,5);
	        v.add(b6,1,6);
	        v.setPadding(new Insets(10, 20, 10, 10));
	        v.setAlignment(Pos.CENTER);
	        v.setVgap(10);
	        return v;
	    }
		private static GridPane search() {
			Label l1=new Label("Course Name");
			TextField t1=new TextField();
			Label l2=new Label("Course Code");
			TextField t2=new TextField();
			Button b1=new Button("GO");
			GridPane gp=new GridPane();
			gp.add(l1, 1, 1);
			gp.add(t1, 2, 1);
			gp.add(l2, 3, 1);
			gp.add(t2, 4, 1);
			gp.add(b1, 4, 2);
			gp.setHgap(10);
			gp.setVgap(10);
			return gp;
		}
		private static TableView addMid() {
			TableView tab=new TableView();
			TableColumn c1=new TableColumn("Course Code");
			TableColumn c2=new TableColumn("Course Name");
			tab.getColumns().addAll(c1,c2);
			tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			return tab;
		}
		private static BorderPane compiler(BorderPane top,GridPane left,GridPane mid1,TableView mid2) {
			BorderPane tmp1=new BorderPane();
			tmp1.setTop(mid1);
			tmp1.setAlignment(mid1,Pos.CENTER);
			tmp1.setCenter(mid2);
			tmp1.setAlignment(mid2, Pos.CENTER);
			tmp1.setPadding(new Insets(10));
			
			Text t=new Text("TIMETABLE");
			t.setStyle("-fx-font-weight:bold;-fx-font-size:150%");
			BorderPane bp=new BorderPane();
			bp.setTop(t);
			bp.setCenter(tmp1);
			bp.setLeft(left);
			bp.setAlignment(t, Pos.CENTER);
			bp.setAlignment(tmp1, Pos.CENTER);
			bp.setAlignment(left, Pos.CENTER);
			bp.setPadding(new Insets(10));
			return(bp);
		}
		public static void main(String ar[]) {
			launch (ar);
		}
	
	}
