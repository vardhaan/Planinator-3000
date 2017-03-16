package Models;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.*;
import javax.swing.JOptionPane;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class NotificationSender{
	
	private ResourceBundle bundle;
	private ArrayList<MessageIconObject> messageQueue;
	
	public static final String BUNDLE_URL = "resources/";
	public static final String DEFAULT_ICON = "clocked!.png";

	public NotificationSender(String language) {
		bundle = ResourceBundle.getBundle(BUNDLE_URL+language);
		messageQueue = new ArrayList<MessageIconObject>();
	}
	
	public class MessageIconObject {
		private String message;
		private Image icon;
		
		public MessageIconObject(String messageToSet, Image imageToSet) {
			message = messageToSet;
			icon = imageToSet;
		}
		
		public String getMessage() {
			return message;
		}
		
		public Image getIcon() {
			return icon;
		}
	}
	
	private void executeMessages() {
		while(!messageQueue.isEmpty()) {
			try {
				MessageIconObject holder = messageQueue.remove(0);
				String convertedMessage = bundle.getString(holder.getMessage());
				Runtime.getRuntime().exec(new String[] { "osascript", "-e", "display notification \"" + convertedMessage + "\" with title \"Clocked!\""});
				Alert al = new Alert(AlertType.INFORMATION);
				al.setTitle("Clocked! Reminder");
				al.setHeaderText("Drink Water Now!");
				Image iconImage = holder.getIcon();
				ImageView iconIV = new ImageView(iconImage);
				
				al.setGraphic(iconIV);
				al.setContentText("Drink 1 cup of water to maintain healthy hydration");
				al.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Creates a new notification to be displayed on the screen with the provided message and icon.
	 * @param message Message to be displayed in the alert box and notification
	 * @param iconPath Icon to be set as the image in the alert box
	 */
	public synchronized void sendNotification(String message, String iconPath) {
		if (iconPath == null) {
			iconPath = DEFAULT_ICON;
		}
		Image icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPath));
		MessageIconObject infoHolder = new MessageIconObject(message, icon);
		messageQueue.add(infoHolder);
		executeMessages();

	}
	
	

	
}
