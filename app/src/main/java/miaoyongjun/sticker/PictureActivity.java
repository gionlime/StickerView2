package miaoyongjun.sticker;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by miaoyongjun.
 * Date:  2017/8/1
 */

public class PictureActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageBitmap(BitmapUtil.FINAL_BITMAP);

    }
}
