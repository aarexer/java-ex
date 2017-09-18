package clock;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

/**
 * @author Alexander Kuchuk.
 */

public class ArcPiece {
    private double x;

    private double y;
    private double w;
    private double h;
    private double startAngle;
    private double arcExtent;
    private double strokeWidth;
    private double pixelsToMove;
    private Color strokeColor;
    private boolean clockwise = false;

    private long startTime = 0;

    private long displayTimePerFrameMillis = 60;

    private long displayTimePerFrameNano = 60 * 1_000_000;

    public ArcPiece(Color pieceColor, double strokeWidth, double x, double y, double w, double h, double startAngle, double extentAngle, long mills, double pixelsMove) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.startAngle = startAngle;
        this.arcExtent = extentAngle;
        this.pixelsToMove = pixelsMove;
        this.strokeColor = pieceColor;
    }

    public ArcPiece() {

    }

    public void update(long now) {
        if (startTime == 0) {
            startTime = now;
            displayTimePerFrameNano = displayTimePerFrameMillis * 1_000_000;
        }

        long elapsed = now - startTime;
        if (elapsed > displayTimePerFrameNano) {
            if (!clockwise) {
                startAngle = startAngle + pixelsToMove;
                if (startAngle > 360) {
                    startAngle = 0;
                }
            } else {
                startAngle = startAngle - pixelsToMove;
                if (startAngle < -360) {
                    startAngle = 0;
                }
            }
            startTime = 0;
        }
    }

    public void draw(GraphicsContext gc) {
        gc.setStroke(strokeColor);
        gc.setLineWidth(strokeWidth);
        gc.strokeArc(x, y, w, h, startAngle, arcExtent, ArcType.OPEN);
    }

    public boolean isClockwise() {
        return clockwise;
    }

    public void setClockwise(boolean clockwise) {
        this.clockwise = clockwise;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setW(double w) {
        this.w = w;
    }

    public void setH(double h) {
        this.h = h;
    }

    public void setStartAngle(double startAngle) {
        this.startAngle = startAngle;
    }

    public void setArcExtent(double arcExtent) {
        this.arcExtent = arcExtent;
    }
}