import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Modified version of the SimpleTrackMouse program for Exercise 6.1.
 **/


public class Exercise1 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Canvas canvas;
    private boolean dragging;
    private GraphicsContext g;


    public void start(Stage stage) {

        /* Create the canvas, and set up the GUI */

        canvas = new Canvas(550,400);

        g = canvas.getGraphicsContext2D();
        g.setFill(Color.WHITE);
        g.fillRect(0,0,550,400);
        g.setFill(Color.BLACK);

        Pane root = new Pane(canvas);
        Scene scene = new Scene( root );
        stage.setScene(scene);
        stage.setTitle("Dragging Mouse Program");
        stage.setResizable(false);
        stage.show();  // make the window visible

        canvas.setOnMousePressed( e -> mousePressed(e) );
        canvas.setOnMouseDragged( e -> mouseDragged(e) );

    } // end start()

    public void mousePressed(MouseEvent evt) {

        if ( evt.isSecondaryButtonDown()) {
            g.setFill(Color.WHITE);
            g.fillRect( 0, 0, g.getCanvas().getWidth(), g.getCanvas().getHeight() );
            g.setFill(Color.BLACK);
            dragging = false;
            return;
        }

        dragging = true;

        double x = evt.getX();
        double y = evt.getY();

        if ( evt.isShiftDown() ) {
            g.setFill( Color.BLUE );
            g.fillOval( x - 30, y - 15, 60, 30 );
        }
        else {
            g.setFill( Color.RED );
            g.fillRect( x - 30, y - 15, 60, 30 );
        }
    }

    public void mouseDragged(MouseEvent evt) {
        if (!dragging) {
            return;
        }

        double x = evt.getX();
        double y = evt.getY();

        if ( evt.isShiftDown() ) {
            g.setFill( Color.BLUE );
            g.fillOval( x - 30, y - 15, 60, 30 );
        }
        else {
            g.setFill( Color.RED );
            g.fillRect( evt.getX() - 30, evt.getY() - 15, 60, 30 );
        }
    }

}
