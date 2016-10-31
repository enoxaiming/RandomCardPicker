package co.boxbreakers.cardpicker;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewAnimator;

import java.util.ArrayList;
import java.util.Random;


import co.boxbreakers.cardpicker.Animation.AnimationFactory;

import static android.graphics.Bitmap.createScaledBitmap;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private int i = 0;
    private BitmapFactory.Options options = new BitmapFactory.Options();
    private Bitmap[] bitmap = new Bitmap[8];
    private int imageList[] = {R.id.imageView1,R.id.imageView2,R.id.imageView3};
    private int backlist[] = {R.id.back1,R.id.back2,R.id.back3};
    private int viewFlipper[] = {R.id.viewFlipper1,R.id.viewFlipper2,R.id.viewFlipper3};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getRandomBitmap();
        options.inSampleSize = 4;
        Bitmap bitmaps = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.back);

        for (int v = 0 ; v < 3 ; v++) {
            final ViewAnimator viewAnimator = (ViewAnimator)this.findViewById(viewFlipper[v]);
            mImageView = (ImageView) findViewById(imageList[v]);
            mImageView.setImageBitmap(bitmap[v]);
            mImageView = (ImageView) findViewById(backlist[v]);
            mImageView.setImageBitmap(bitmaps);
            this.findViewById(imageList[v]).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AnimationFactory.flipTransition(viewAnimator, AnimationFactory.FlipDirection.LEFT_RIGHT);
                }
            });
            this.findViewById(backlist[v]).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AnimationFactory.flipTransition(viewAnimator, AnimationFactory.FlipDirection.LEFT_RIGHT);
                }
            });
        }


    }

    public Bitmap[] getRandomBitmap() {
        options.inSampleSize = 4;
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
            while(i < 4) {
                final Random random = new Random();
                final int count = imagesPath.size();
                int number = random.nextInt(count);
                String path = imagesPath.get(number);
                bitmap[i] = createScaledBitmap(BitmapFactory.decodeFile(path,options),200,200,true);
                i++;
            }
        return bitmap;
    }

}
