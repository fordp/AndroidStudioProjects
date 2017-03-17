package com.example.gmuras.ancientbritaingesture;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class DetailActivity extends AppCompatActivity {
    private ImageView detailImage;
    private ArrayList<MainDataDef> detailData;

    private static final int MIN_DISTANCE = 150;
    private static final int OFF_PATH = 100;
    private static final int VELOCITY_THRESHOLD = 75;
    private GestureDetector detector;
    View.OnTouchListener listener;
    private int ImageIndex;

    private static final String DEBUG_TAG = "tag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(DEBUG_TAG, "testing <-----------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detector = new GestureDetector(this, new GalleryGestureDetector());
        listener = new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return detector.onTouchEvent(event);
            }
        };

        ImageIndex = 0;

        detailImage = (ImageView) findViewById(R.id.detail_image);
        detailImage.setOnTouchListener(listener);

        TextView detailName = (TextView) findViewById(R.id.detail_name);
        TextView detailDistance = (TextView) findViewById(R.id.detail_distance);

        TextView detailText = (TextView) findViewById(R.id.detail_text);
        detailText.setMovementMethod(new ScrollingMovementMethod());

        ImageView detailWebLink = (ImageView) findViewById(R.id.detail_web_link);

        int i = MainActivity.currentItem;
        Random n = new Random();
        int m = n.nextInt((600 - 20) + 1) + 20;

        setTitle(getString(R.string.app_name) + " - " + MainData.nameArray[i]);

        detailImage.setImageResource(MainData.detailImageArray[i]);
        detailName.setText(MainData.nameArray[i]);
        detailDistance.setText(String.valueOf(m) + " miles");
        detailText.setText(MainData.detailTextArray[i]);

        detailWebLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(MainData.detailWebLink[MainActivity.currentItem]));
                startActivity(intent);
            }
        });



    }


    class GalleryGestureDetector implements GestureDetector.OnGestureListener {
        private int item;
        {
            item = MainActivity.currentItem;
        }

        /**
         * Notified when a tap occurs with the down {@link MotionEvent}
         * that triggered it. This will be triggered immediately for
         * every down event. All other events should be preceded by this.
         *
         * @param e The down motion event.
         */
        @Override
        public boolean onDown(MotionEvent e) {
            Log.d(DEBUG_TAG, "onDown  <-----------------------------");
            return true;
        }

        /**
         * The user has performed a down {@link MotionEvent} and not performed
         * a move or up yet. This event is commonly used to provide visual
         * feedback to the user to let them know that their action has been
         * recognized i.e. highlight an element.
         *
         * @param e The down motion event
         */
        @Override
        public void onShowPress(MotionEvent e) {
            Log.d(DEBUG_TAG, "onShowPress  <-----------------------------");
            detailImage.setElevation(4);
        }

        /**
         * Notified when a tap occurs with the up {@link MotionEvent}
         * that triggered it.
         *
         * @param e The up motion event that completed the first tap
         * @return true if the event is consumed, else false
         */
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.d(DEBUG_TAG, "onSingleTapUp  <-----------------------------");
            return false;
        }

        /**
         * Notified when a scroll occurs with the initial on down {@link MotionEvent} and the
         * current move {@link MotionEvent}. The distance in x and y is also supplied for
         * convenience.
         *
         * @param e1        The first down motion event that started the scrolling.
         * @param e2        The move motion event that triggered the current onScroll.
         * @param distanceX The distance along the X axis that has been scrolled since the last
         *                  call to onScroll. This is NOT the distance between {@code e1}
         *                  and {@code e2}.
         * @param distanceY The distance along the Y axis that has been scrolled since the last
         *                  call to onScroll. This is NOT the distance between {@code e1}
         *                  and {@code e2}.
         * @return true if the event is consumed, else false
         */
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d(DEBUG_TAG, "onScroll  <-----------------------------");
            return false;
        }

        /**
         * Notified when a long press occurs with the initial on down {@link MotionEvent}
         * that trigged it.
         *
         * @param e The initial on down motion event that started the longpress.
         */
        @Override
        public void onLongPress(MotionEvent e) {
            Log.d(DEBUG_TAG, "onLongPress");
        }

        /**
         * Notified of a fling event when it occurs with the initial on down {@link MotionEvent}
         * and the matching up {@link MotionEvent}. The calculated velocity is supplied along
         * the x and y axis in pixels per second.
         *
         * @param e1        The first down motion event that started the fling.
         * @param e2        The move motion event that triggered the current onFling.
         * @param velocityX The velocity of this fling measured in pixels per second
         *                  along the x axis.
         * @param velocityY The velocity of this fling measured in pixels per second
         *                  along the y axis.
         * @return true if the event is consumed, else false
         */
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d("tag", "onFling  <-----------------------------");
            if (Math.abs(e1.getY() - e2.getY()) > OFF_PATH)
                return false;

            if (MainData.galleryArray[item].length != 0) {
                // Swipe left
                if (e1.getX() - e2.getX() > MIN_DISTANCE && Math.abs(velocityX) > VELOCITY_THRESHOLD) {
                    Log.d(DEBUG_TAG, "left");
                    ImageIndex++;
                    if (ImageIndex == MainData.galleryArray[item].length) ImageIndex = 0;
                    detailImage.setImageResource(MainData.galleryArray[item][ImageIndex]);
                } else {
                    // Swipe right
                    if (e2.getX() - e1.getX() > MIN_DISTANCE && Math.abs(velocityX) > VELOCITY_THRESHOLD) {
                        Log.d(DEBUG_TAG, "right");
                        ImageIndex--;
                        if (ImageIndex < 0) ImageIndex = MainData.galleryArray[item].length - 1;
                        detailImage.setImageResource(MainData.galleryArray[item][ImageIndex]);
                    }
                }
            }
            detailImage.setElevation(0);
            return true;
        }
    }
}
