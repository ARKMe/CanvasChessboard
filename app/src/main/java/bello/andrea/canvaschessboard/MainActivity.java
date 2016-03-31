package bello.andrea.canvaschessboard;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.container);

        relativeLayout.post(new Runnable() {
            @Override
            public void run() {
                Bitmap background = Bitmap.createBitmap(relativeLayout.getWidth(), relativeLayout.getHeight(), Bitmap.Config.ARGB_8888);

                Paint paint = new Paint();
                paint.setColor(ContextCompat.getColor(MainActivity.this, android.R.color.black));

                Canvas canvas = new Canvas(background);

                int chessBoardSide = 8;
                int padding = 100;
                int borderWidth = 100;
                int minDimension;
                if(relativeLayout.getWidth() < relativeLayout.getHeight())
                    minDimension = relativeLayout.getWidth();
                else
                    minDimension = relativeLayout.getHeight();

                int squareSide = (minDimension - 2 * padding - 2*borderWidth)/chessBoardSide;

                for(int rawIndex=0; rawIndex<chessBoardSide; rawIndex++){
                    drawRow(canvas, padding, rawIndex, chessBoardSide, squareSide, borderWidth);
                }

                drawBorder(canvas, padding, squareSide, chessBoardSide, borderWidth);

                relativeLayout.setBackgroundDrawable(new BitmapDrawable(getResources(), background));
            }
        });

    }

    private void drawRow(Canvas canvas, int padding, int rowIndex, int columnCount, int sideDimension, int borderWidth){
        Paint lightPaint = new Paint();
        lightPaint.setColor(ContextCompat.getColor(this, android.R.color.white));

        Paint darkPaint = new Paint();
        darkPaint.setColor(ContextCompat.getColor(this, android.R.color.black));

        for(int columnIndex=0; columnIndex<columnCount; columnIndex++){
            int left = padding + borderWidth + columnIndex*sideDimension;
            int top = padding + borderWidth + rowIndex*sideDimension;
            int right = padding + borderWidth + columnIndex*sideDimension + sideDimension;
            int bottom = padding + borderWidth + rowIndex*sideDimension + sideDimension;

            if(columnIndex%2 == rowIndex%2)
                canvas.drawRect(left, top, right, bottom, darkPaint);
            else
                canvas.drawRect(left, top, right, bottom, lightPaint);
        }
    }

    private void drawBorder(Canvas canvas, int padding, int cellDimension, int chessBoardSide, int borderWidth){
        Paint borderPaint = new Paint();
        borderPaint.setColor(ContextCompat.getColor(this, android.R.color.holo_purple));
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(borderWidth);

        int left = padding + borderWidth/2;
        int top = padding + borderWidth/2;
        int right = padding + 3*borderWidth/2 + chessBoardSide*cellDimension;
        int bottom = padding + 3*borderWidth/2 + chessBoardSide*cellDimension;

        canvas.drawRect(left, top, right, bottom, borderPaint);
    }
}
