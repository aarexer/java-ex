package clock;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ArcClock {
    public static Color BLUE1 = Color.rgb(126, 166, 212, 0.6);
    public static Color BLUE2 = Color.rgb(126, 166, 222, 0.5);
    public static Color BLUE3 = Color.rgb(130, 166, 230, 0.5);
    public static Color GREEN1 = Color.rgb(130, 230, 166, 0.5);
    public static Color RED1 = Color.rgb(230, 130, 166, 0.5);

    public ArcPiece longPiece;
    public ArcPiece[] arcPieces;
    public int maxDiameter;
    public double radius;

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddhhmmssa");

    public ArcClock(int numArcs, Color longPieceColor, Color manyPieceColor, int maxDiameter) {
        this.maxDiameter = maxDiameter;
        radius = maxDiameter / 2;
        longPiece = new ArcPiece(longPieceColor, 5, 0, 0, maxDiameter, maxDiameter, 45, 240, 1000, 1);

        arcPieces = createRandomArcs(numArcs, manyPieceColor, maxDiameter / 2);
    }

    public void update(long now) {
        longPiece.update(now);
        for (ArcPiece ap : arcPieces) {
            ap.update(now);
        }
    }

    public void draw(GraphicsContext gc) {
        longPiece.draw(gc);
        for (ArcPiece ap : arcPieces) {
            ap.draw(gc);
        }
        // draw hour
        gc.setFont(Font.font("Calibri", 40));
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.CENTER);

        String dateTimeStr = DATE_FORMAT.format(new Date());
        //yyyyMMddhhmmssa
        gc.fillText(dateTimeStr.substring(8, 10), radius, radius + 18);
        gc.setFont(Font.font("Calibri", 20));
        gc.fillText(dateTimeStr.substring(10, 12) + " " + dateTimeStr.substring(14), maxDiameter - 40, radius - 40);
        gc.fillText(dateTimeStr.substring(12, 14), maxDiameter - 40, maxDiameter - 40);

    }

    public static int randomIntRange(int min, int max) {
        Random rand = new Random();
        int range = max - min + 1;
        return rand.nextInt(range) + min;
    }

    public static ArcPiece[] createRandomArcs(int num, Color color, double radius) {
        final ArcPiece[] manyPieces = new ArcPiece[num];
        for (int i = 0; i < num; i++) {
            manyPieces[i] = randomArcPiece(color, radius);
        }
        return manyPieces;
    }

    public static ArcPiece randomArcPiece(Color color, double radius) {
        int width = randomIntRange(60, (int) radius * 2);
        int randomStrokeWidth = randomIntRange(1, 10);
        int randomStartAngle = randomIntRange(1, 270);
        int randomExtentAngle = randomIntRange(10, 360 - randomStartAngle);
        long randomMillis = randomIntRange(0, 33);
        Color someColor = color;

        if (color == null) {
            someColor = BLUE1;
        }

        final ArcPiece arcPiece = new ArcPiece(someColor, randomStrokeWidth, radius - (width / 2), radius - (width / 2),
                width, width, randomStartAngle, randomExtentAngle, randomMillis, 2);

        arcPiece.setClockwise(new Random().nextBoolean());

        return arcPiece;
    }
}