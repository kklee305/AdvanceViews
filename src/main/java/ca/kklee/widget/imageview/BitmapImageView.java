package ca.kklee.widget.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Keith on 18/08/2014.
 */
public class BitmapImageView extends ImageView {

    private final int MAX_RESOLUTION = 4096;
    private final String LOG_TAG = this.getClass().getSimpleName();

    public BitmapImageView(Context context) {
        super(context);
    }

    public BitmapImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BitmapImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable drawable = getDrawable();
        if (drawable instanceof BitmapDrawable
                && (drawable.getIntrinsicWidth() > MAX_RESOLUTION
                || drawable.getIntrinsicHeight() > MAX_RESOLUTION)) {
           resizeImageResolution();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void resizeImageResolution() {
        int newWidth = getDrawable().getIntrinsicWidth();
        int newHeight = getDrawable().getIntrinsicHeight();

        if (newWidth > newHeight) {
            float ratio = newWidth / (float) MAX_RESOLUTION;
            newWidth = MAX_RESOLUTION;
            newHeight = (int) (newHeight / ratio);
        } else if (newHeight > newWidth) {
            float ratio = newHeight / (float) MAX_RESOLUTION;
            newHeight = MAX_RESOLUTION;
            newWidth = (int) (newWidth / ratio);
        } else {
            newWidth = newHeight = MAX_RESOLUTION;
        }
        Bitmap bitmap = Bitmap.createScaledBitmap(((BitmapDrawable) getDrawable()).getBitmap(), newWidth, newHeight, true);
        setImageBitmap(bitmap);
    }
}
