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
                canvas.drawRect(50, 50, 200, 200, paint);
                canvas.drawRect(350, 50, 500, 200, paint);
                canvas.drawRect(200, 200, 350, 350, paint);

                relativeLayout.setBackground(new BitmapDrawable(getResources(), background));
            }
        });
    }
}
