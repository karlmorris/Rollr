package edu.temple.rollr;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class RollerView extends View {

    Rotatable parent;

    float circleX = 0, circleY = 0;
    float circleR = 100;

    int multiplier = 50;

    int rightBound, bottomBound;

    Paint circleColor;

    public RollerView(Context context) {
        super(context);
        parent = (Rotatable) context;
        circleColor = new Paint();
        circleColor.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        rightBound = canvas.getWidth();
        bottomBound = canvas.getHeight();

        if (circleX == 0){
            circleX = rightBound / 2;
            circleY = bottomBound / 2;
        }

        float[] values = parent.getOrientationAngles();

        if (values[1] > 0) {
            if ((circleX + circleR) + (values[1] * multiplier) < rightBound)
                circleX += multiplier * values[1];
            else
                circleX = rightBound - circleR;

        } else if (values[1] <= 0) {
            if ((circleX - circleR) + (values[1] * multiplier) > 1)
                circleX += multiplier * values[1];
            else
                circleX = 1 + circleR;
        }

        if (values[2] > 0) {
            if ((circleY + circleR) + (values[2] * multiplier) < bottomBound)
                circleY += multiplier * values[2];
            else
                circleY = bottomBound - circleR;

        } else if (values[2] <= 0) {
            if ((circleY - circleR) + (values[2] * multiplier) > 1)
                circleY += multiplier * values[2];
            else
                circleY = 1 + circleR;
        }

        canvas.drawCircle(circleX, circleY, circleR, circleColor);
    }

    public interface Rotatable {
        public float[] getOrientationAngles();
    }
}
