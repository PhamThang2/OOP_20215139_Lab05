package hust.soict.globalict.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PainterController {

    @FXML
    private RadioButton penRadioButton;

    @FXML
    private Pane drawingAreaPane;

    @FXML
    private RadioButton eraserRadioButton;

    @FXML
    private ToggleGroup radioGroup;

	@FXML
	void drawingAreaMouseDragged(MouseEvent event) {
		Color color = penRadioButton.isSelected() ? Color.BLACK : Color.WHITE;
		Circle newCircle = new Circle(event.getX(), event.getY(), 4, color);
		drawingAreaPane.getChildren().add(newCircle);
	}

	@FXML
	void clearButtonPressed(ActionEvent event) {
		drawingAreaPane.getChildren().clear();
	}

}
