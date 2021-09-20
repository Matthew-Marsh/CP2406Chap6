import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Exercise5 extends Application {

    private int selectedRow;
    private int selectedColumn;
    private Canvas canvas;
    private final int width = 400;
    private final int height = 400;

    public void start(Stage stage) {
        canvas = new Canvas(width,height);
        BorderPane root = new BorderPane(canvas);
        root.setStyle("-fx-border-width: 4px; -fx-border-color: #444");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Clickable Checkers Board.");
        stage.show();
        stage.setResizable(false);

        selectedRow = -1;
        drawPicture();

        canvas.setOnMousePressed( e -> mousePressed(e) );

    }

    public static void main(String[] args) {
        launch();
    }

    public void drawPicture() {
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, width, height);

        int cornerX = 0;
        int cornerY = 0;
        int row = 0;
        int colorChoice;
        int count;
        int x,y;

        for (count = 0; count <= 64; count++) {
            colorChoice = ((row + count) % 2);
            switch (colorChoice) {
                case 0:
                    g.setFill(Color.RED);
                    break;
                case 1:
                    g.setFill(Color.BLACK);
                    break;
            }

            g.fillRect( cornerX, cornerY, 50, 50 );
            if (cornerX < 350) {
                cornerX += 50;
            } else {
                row++;
                cornerX = 0;
                cornerY += 50;
            }
        }

        if (selectedRow >= 0) {
            g.setStroke(Color.YELLOWGREEN);
            g.setLineWidth(2);
            y = selectedRow * 50;
            x = selectedColumn * 50;
            g.strokeRect(x+1, y+1, 48, 48);
        }


    }

    private void mousePressed(MouseEvent evt) {
        int x = (int) (evt.getX() / 50);
        int y = (int) (evt.getY() / 50);

        if (selectedRow == y && selectedColumn == x) {
            selectedRow = -1;
            selectedColumn = -1;
        } else {
            selectedRow = y;
            selectedColumn = x;
        }
        drawPicture();
    }

}
