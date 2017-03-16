
import Models.NotificationSender;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		NotificationSender ns = new NotificationSender("English");
		ns.sendNotification("DrinkWater", "water.png");
		
		
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		launch(args);
	}


	
}
