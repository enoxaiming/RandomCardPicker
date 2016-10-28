package co.boxbreakers.cardpicker;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class CardGameActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Bitmap currentBitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_game);
        mImageView = (ImageView) findViewById(R.id.imageView);
        String[] projection = new String[]{
                MediaStore.Images.Media.DATA,
        };

        Uri images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor cur = getContentResolver().query(images,
                projection,
                "",
                null,
                ""
        );

        final ArrayList<String> imagesPath = new ArrayList<String>();
        if (cur.moveToFirst()) {
            int dataColumn = cur.getColumnIndex(
                    MediaStore.Images.Media.DATA);
            do {
                imagesPath.add(cur.getString(dataColumn));
            } while (cur.moveToNext());
        }
        cur.close();
        final Random random = new Random();
        final int count = imagesPath.size();
        int number = random.nextInt(count);
        String path = imagesPath.get(number);
        if (currentBitmap != null)
            currentBitmap.recycle();
        currentBitmap = BitmapFactory.decodeFile(path);
        mImageView.setImageBitmap(currentBitmap);
    }
}
