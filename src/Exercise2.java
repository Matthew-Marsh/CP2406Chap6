import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Exercise2 extends Application {

    private double redSquareX, redSquareY;
    private double blueSquareX, blueSquareY;
    private boolean draggingRedSquare;
    private double offsetX;
    private double offsetY;
    private GraphicsContext g;
    private Canvas canvas;
    private boolean dragging;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {

        canvas = new Canvas(400,400);


        Pane root = new Pane(canvas);
        Scene scene = new Scene( root );
        stage.setScene(scene);
        stage.setTitle("Movable Squares");
        stage.setResizable(false);
        stage.show();

        redSquareX = 10;
        redSquareY = 10;
        blueSquareX = 200;
        blueSquareY = 200;

        draw();

        canvas.setOnMousePressed( e -> mousePressed(e) );
        canvas.setOnMouseDragged( e -> mouseDragged(e) );
        canvas.setOnMouseReleased( e -> mouseReleased(e) );

        scene.setOnKeyPressed( e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                redSquareX = 10;
                redSquareY = 10;
                blueSquareX = 200;
                blueSquareY = 200;
                draw();
            }
        });
    }

    private void draw() {
        g = canvas.getGraphicsContext2D();
        g.setFill(Color.WHITE);
        g.fillRect(0,0,400,400);
        g.setFill(Color.BLACK);
        g.setFill(Color.BLUE);
        g.fillRect(blueSquareX, blueSquareY, 100, 100);
        g.setStroke(Color.BLACK);
        g.setFill(Color.RED);
        g.fillRect(redSquareX, redSquareY, 100, 100);
        g.setStroke(Color.BLACK);
    }

    private void mousePressed(MouseEvent evt) {
        if (dragging) {
            return;
        }
        draggingRedSquare = false;
        double x = evt.getX();
        double y = evt.getY();
        if (x > redSquareX && x < redSquareX + 100 && y > redSquareY && y < redSquareY + 100) {
            dragging = true;
            draggingRedSquare = true;
            offsetX = x - redSquareX;
            offsetY = y - redSquareY;
        } else if (x > blueSquareX && x < blueSquareX + 100 && y > blueSquareY && y < blueSquareY + 100) {
            dragging = true;
            offsetX = x - blueSquareX;
            offsetY = y - blueSquareY;
        }
        draw();
    }

    private void mouseDragged(MouseEvent evt) {
        if (!dragging) {
            return;
        }

        double x = evt.getX();
        double y = evt.getY();

        if (draggingRedSquare) {
            redSquareX = x - offsetX;
            redSquareY = y - offsetY;
        } else {
            blueSquareX = x - offsetX;
            blueSquareY = y - offsetY;
        }
        draw();
    }

    private void mouseReleased(MouseEvent evt) {
        dragging = false;
    }
}
