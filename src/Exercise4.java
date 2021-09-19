import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Exercise4 extends Application {
    private GraphicsContext g;
    private Button rollButton;
    private int frameNumber;

    public static void main(String[] args) {
        launch(args);
    }

    private AnimationTimer timer = new AnimationTimer() {
        public void handle(long time) {
            int slowRollTime = frameNumber % 10;
            if (slowRollTime == 0) {
                int die1 = (int) (Math.random() * 6) + 1;
                int die2 = (int) (Math.random() * 6) + 1;
                drawDie(die1, 10, 10);
                drawDie(die2, 110, 110);
            }
            frameNumber++;
            if (frameNumber == 240) {
                timer.stop();
                rollButton.setDisable(false);
            }
        }
    };

    public void start(Stage stage) {
        Canvas canvas = new Canvas(200, 200);

        g = canvas.getGraphicsContext2D();
        g.setFont( Font.font(18) );
        g.setFill(Color.BLUE);
        g.fillRect(0,0,200,200);

        rollButton = new Button("Roll Dice");
        rollButton.setMaxWidth(200);
        rollButton.setOnAction( e -> rollDie());


        BorderPane root = new BorderPane();
        root.setCenter(canvas);
        root.setBottom(rollButton);

        Scene scene = new Scene( root );
        stage.setScene(scene);
        stage.setTitle("Dice Rolling Program - Extended");
        stage.setResizable(false);
        stage.show();
        int die1 = (int) (Math.random() * 6) + 1;
        int die2 = (int) (Math.random() * 6) + 1;
//      First Die
        drawDie(die1, 10, 10);
//      Second Die
        drawDie(die2, 110, 110);


        canvas.setOnMousePressed(e -> rollDie() );

    }

    private void rollDie() {
        rollButton.setDisable(true);
        frameNumber = 0;
        timer.start();
    }

    public void drawDie(int val, int x, int y) {
        g.setFill(Color.WHITE);
        g.fillRect(x, y, 80, 80);
        g.setStroke(Color.BLACK);

        switch (val) {
            case 1:
                g.setFill(Color.BLACK);
                g.fillOval(x + 30, y + 30, 20, 20);
                break;
            case 2:
                g.setFill(Color.BLACK);
                g.fillOval(x + 50, y + 10, 20, 20);
                g.fillOval(x + 10, y + 50, 20, 20);
                break;
            case 3:
                g.setFill(Color.BLACK);
                g.fillOval(x + 10, y + 10, 20, 20);
                g.fillOval(x + 30, y + 30, 20, 20);
                g.fillOval(x + 50, y + 50, 20, 20);
                break;
            case 4:
                g.setFill(Color.BLACK);
                g.fillOval(x + 50, y + 10, 20, 20);
                g.fillOval(x + 10, y + 50, 20, 20);
                g.fillOval(x + 10, y + 10, 20, 20);
                g.fillOval(x + 50, y + 50, 20, 20);
                break;
            case 5:
                g.setFill(Color.BLACK);
                g.fillOval(x + 50, y + 10, 20, 20);
                g.fillOval(x + 10, y + 50, 20, 20);
                g.fillOval(x + 10, y + 10, 20, 20);
                g.fillOval(x + 50, y + 50, 20, 20);
                g.fillOval(x + 30, y + 30, 20, 20);
                break;
            case 6:
                g.setFill(Color.BLACK);
                g.fillOval(x + 50, y + 10, 18, 18);
                g.fillOval(x + 10, y + 54, 18, 18);
                g.fillOval(x + 10, y + 32, 18, 18);
                g.fillOval(x + 10, y + 10, 18, 18);
                g.fillOval(x + 50, y + 54, 18, 18);
                g.fillOval(x + 50, y + 32, 18, 18);
                break;
            default:
                break;
        }
    }
}
