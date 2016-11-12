package co.boxbreakers.cardpicker;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewAnimator;

import java.util.ArrayList;
import java.util.Random;


import co.boxbreakers.cardpicker.Animation.AnimationFactory;

import static android.graphics.Bitmap.createScaledBitmap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImageView;
    private int i = 0;
    private int t;
    private int first,second;
    private BitmapFactory.Options options = new BitmapFactory.Options();
    private Bitmap[] bitmap = new Bitmap[4];
    private int imageList[] = {R.id.imageView1,R.id.imageView2,R.id.imageView3,R.id.imageView4,R.id.imageView5,R.id.imageView6,R.id.imageView7,R.id.imageView8};
    private int backlist[] = {R.id.back1,R.id.back2,R.id.back3,R.id.back4,R.id.back5,R.id.back6,R.id.back7,R.id.back8};
    private int viewFlipper[] = {R.id.viewFlipper1,R.id.viewFlipper2,R.id.viewFlipper3,R.id.viewFlipper4,R.id.viewFlipper5,R.id.viewFlipper6,R.id.viewFlipper7,R.id.viewFlipper8};
    private ViewAnimator[] viewAnimator = new ViewAnimator[8];
    private ViewAnimator vm1,vm2,vm3,vm4,vm5,vm6,vm7,vm8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getRandomBitmap();
        int[] k = getRandomint();
        System.out.println(k[0]+" "+k[1]+" " +k[2]);

        options.inSampleSize = 4;
        Bitmap bitmaps = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.back);

        for(i =0; i<8; i++) {
            viewAnimator[i] = (ViewAnimator)this.findViewById(viewFlipper[i]);
            mImageView = (ImageView) findViewById(imageList[i]);
            mImageView.setImageBitmap(bitmap[k[i]]);
            mImageView = (ImageView) findViewById(backlist[i]);
            mImageView.setImageBitmap(bitmaps);
            viewAnimator[i].setOnClickListener(this);
        }

        vm1 = (ViewAnimator)this.findViewById(R.id.viewFlipper1);
        vm2 = (ViewAnimator)this.findViewById(R.id.viewFlipper2);
        vm3 = (ViewAnimator)this.findViewById(R.id.viewFlipper3);
        vm4 = (ViewAnimator)this.findViewById(R.id.viewFlipper4);
        vm5 = (ViewAnimator)this.findViewById(R.id.viewFlipper5);
        vm6 = (ViewAnimator)this.findViewById(R.id.viewFlipper6);
        vm7 = (ViewAnimator)this.findViewById(R.id.viewFlipper7);
        vm8 = (ViewAnimator)this.findViewById(R.id.viewFlipper8);

        vm1.setOnClickListener(this);
        vm2.setOnClickListener(this);
        vm3.setOnClickListener(this);
        vm4.setOnClickListener(this);
        vm5.setOnClickListener(this);
        vm6.setOnClickListener(this);
        vm7.setOnClickListener(this);
        vm8.setOnClickListener(this);
    }

    public int[] getRandomint() {
        int[] a = new int[8];
        Random random = new Random();
        Random random1 = new Random();
        for(int i = 0 ; i < 8 ; i+=2) {
            a[i] = random.nextInt(4);
        }
        for(int j = 1 ; j < 8 ; j+=2) {
            a[j] = random1.nextInt(4);
        }
        return a;
    }

    public void imageCheck() {
        
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.viewFlipper1:
                AnimationFactory.flipTransition(vm1,
                        AnimationFactory.FlipDirection.LEFT_RIGHT);
                break;

            case R.id.imageView1:
                AnimationFactory.flipTransition(vm1,
                        AnimationFactory.FlipDirection.LEFT_RIGHT);

                break;
            case R.id.back1:
                AnimationFactory.flipTransition(vm1,
                        AnimationFactory.FlipDirection.RIGHT_LEFT);
                break;


            case R.id.viewFlipper2:
                AnimationFactory.flipTransition(vm2,
                        AnimationFactory.FlipDirection.LEFT_RIGHT);
                break;

            case R.id.imageView2:
                AnimationFactory.flipTransition(vm2,
                        AnimationFactory.FlipDirection.LEFT_RIGHT);

                break;
            case R.id.back2:
                AnimationFactory.flipTransition(vm2,
                        AnimationFactory.FlipDirection.RIGHT_LEFT);
                break;

            case R.id.viewFlipper3:
                AnimationFactory.flipTransition(vm3,
                        AnimationFactory.FlipDirection.LEFT_RIGHT);
                break;

            case R.id.imageView3:
                AnimationFactory.flipTransition(vm3,
                        AnimationFactory.FlipDirection.LEFT_RIGHT);

                break;
            case R.id.back3:
                AnimationFactory.flipTransition(vm3,
                        AnimationFactory.FlipDirection.RIGHT_LEFT);
                break;


            case R.id.viewFlipper4:
                AnimationFactory.flipTransition(vm4,
                        AnimationFactory.FlipDirection.LEFT_RIGHT);
                break;

            case R.id.imageView4:
                AnimationFactory.flipTransition(vm4,
                        AnimationFactory.FlipDirection.LEFT_RIGHT);

                break;
            case R.id.back4:
                AnimationFactory.flipTransition(vm4,
                        AnimationFactory.FlipDirection.RIGHT_LEFT);
                break;


            case R.id.viewFlipper5:
                AnimationFactory.flipTransition(vm5,
                        AnimationFactory.FlipDirection.LEFT_RIGHT);
                break;

            case R.id.imageView5:
                AnimationFactory.flipTransition(vm5,
                        AnimationFactory.FlipDirection.LEFT_RIGHT);

                break;
            case R.id.back5:
                AnimationFactory.flipTransition(vm5,
                        AnimationFactory.FlipDirection.RIGHT_LEFT);
                break;


            case R.id.viewFlipper6:
                AnimationFactory.flipTransition(vm6,
                        AnimationFactory.FlipDirection.LEFT_RIGHT);
                break;

            case R.id.imageView6:
                AnimationFactory.flipTransition(vm6,
                        AnimationFactory.FlipDirection.LEFT_RIGHT);

                break;
            case R.id.back6:
                AnimationFactory.flipTransition(vm6,
                        AnimationFactory.FlipDirection.RIGHT_LEFT);
                break;


            case R.id.viewFlipper7:
                AnimationFactory.flipTransition(vm7,
                        AnimationFactory.FlipDirection.LEFT_RIGHT);
                break;

            case R.id.imageView7:
                AnimationFactory.flipTransition(vm7,
                        AnimationFactory.FlipDirection.LEFT_RIGHT);

                break;
            case R.id.back7:
                AnimationFactory.flipTransition(vm7,
                        AnimationFactory.FlipDirection.RIGHT_LEFT);
                break;


            case R.id.viewFlipper8:
                AnimationFactory.flipTransition(vm8,
                        AnimationFactory.FlipDirection.LEFT_RIGHT);
                break;

            case R.id.imageView8:
                AnimationFactory.flipTransition(vm8,
                        AnimationFactory.FlipDirection.LEFT_RIGHT);

                break;
            case R.id.back8:
                AnimationFactory.flipTransition(vm8,
                        AnimationFactory.FlipDirection.RIGHT_LEFT);
                break;
        }
    }


}
