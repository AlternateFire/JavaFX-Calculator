import java.security.Principal;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*This method handles displaying the GUI for all the interest calculations.
 *As well as the buttons, sliders, and text boxes to select the calculations 
 *the user wants, and to input data for those calculations.
 */
public class InterestTableGUI extends Application {
	@Override 
	public void start(Stage primaryStage) {
		//Window resolution 
		int sceneWidth = 300, sceneHeight = 200;

		//Button, Label, and TextField objects 
		Button buttonSimpleInterest = new Button("SimpleInterest");
		Button buttonBothInterest = new Button("BothInterest");
		Label principalLabel = new Label("Principal: ");
		TextField principalText = new TextField();
		Label rateLabel = new Label("Rate (Percentage): ");
		TextField rateText = new TextField();

		//Slider
		Label sliderLabel = new Label("Number of Years: ");
		Slider slider = new Slider(1, 25, 1);
		slider.setMajorTickUnit(4.0); 
		slider.setSnapToTicks(true);	
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);

		//Text object and scrollpane
		Text display = new Text(); 
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		scrollPane.setContent(display);

		//Simple interest calculation, using anonymous inner class
		buttonSimpleInterest.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				//Values passed on to simpleInterest method
				double principal = Double.parseDouble(principalText.getText());
				double rate = Double.parseDouble(rateText.getText());
				double years = slider.getValue(); 

				//Displays starting information 
				String valueDisplay = "Principal: $" + 
						String.format("%, .2f", principal) + " Rate: " 
						+ rate + "\n" + "Year, Simple Interest Amount" + "\n";
				//Iterates through annual calculations set by user and displays them 
				for(int i = 1; i < years + 1; i++) {
					Double value = 
							//Calling simpleInterest to calculate
							Interest.simpleInterest(principal, rate, (byte) i); 
					valueDisplay += i + "-->" + "$" 
							+ String.format("%, .2f", value) + "\n"; 
					display.setText(valueDisplay); //set to display 
				}
			}
		});

		//compoundInterest calculation, using an inner class
		class CompoundInterestHandle implements EventHandler<ActionEvent> {
			@Override
			public void handle(ActionEvent e) {
				double principal = Double.parseDouble(principalText.getText());
				double rate = Double.parseDouble(rateText.getText());
				double years = slider.getValue();

				//Displays starting information 
				String valueDisplay = "Principal: $" + 
						String.format("%, .2f", principal) + " Rate: " 
						+ rate + "\n" + "Year, Compound Interest Amount" + "\n"; 
				//Iterates through annual calculations set by user and displays them
				for(int i = 1; i < years + 1; i++) {
					Double value = 
							//Calling compoundInterest to calculate
							Interest.compoundInterest(principal, rate, (byte) i); 
					valueDisplay += i + "-->" + "$" 
							+ String.format("%, .2f", value) + "\n"; 
					display.setText(valueDisplay); //set to display
				}	
			}
		}
		Button buttonCompoundInterest = new Button("CompoundInterest");
		buttonCompoundInterest.setOnAction(new CompoundInterestHandle());
		System.out.println(buttonCompoundInterest.toString()); 

		//bothInterest, using a lambda expression 
		buttonBothInterest.setOnAction(e -> {
			double principal = Double.parseDouble(principalText.getText());
			double rate = Double.parseDouble(rateText.getText());
			double years = slider.getValue();

			//Displays starting information
			String valueDisplay = "Principal: $" + 
					String.format("%, .2f", principal) + " Rate: " 
					+ rate + "\n" + "Year, Simple Interest Amount, "
					+ "Compound Interest Amount" + "\n";
			//Iterates through annual calculations set by user and displays them
			for(int i = 1; i < years + 1; i++) {
				Double valueSimple = //Calling simpleInterest method
						Interest.simpleInterest(principal, rate, (byte) i); 
				Double valueCompound = //Calling compoundInterest method
						Interest.compoundInterest(principal, rate, (byte) i); 
				valueDisplay += i + "-->" + "$" + 
						String.format("%, .2f", valueSimple) 
				+ "-->" + "$" + String.format("%, .2f", valueCompound) + "\n"; 
				display.setText(valueDisplay); //set to display
			}
		});

		//Generates scene, send references into Vbox object 
		VBox pane = new VBox(buttonSimpleInterest, buttonCompoundInterest, 
				buttonBothInterest, principalLabel, principalText, 
				rateLabel, rateText, sliderLabel, slider, scrollPane);

		//Displays stage
		Scene scene = new Scene(pane, sceneWidth, sceneHeight);
		primaryStage.setTitle("Interest Table Calculator");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	//Main method executes application
	public static void main(String[] args) {
		Application.launch(args);
	}
}