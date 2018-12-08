package com.example.oluwafemi.slidenavigation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jaeger.library.StatusBarUtil;

import java.util.Timer;
import java.util.TimerTask;

public class welcome extends AppCompatActivity {

    private ViewPager viewPager;
   private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout linearDot;
    private TextView[] dots;
    private int[] layouts;
    private Button skip, next;
    private collect collect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Checking for first time launch - before calling setContentView()

      //  collect = new collect(this);
      //  if (!collect.firstTime()) {
            // launchHomeScreen();
      //      finish();
      //  }

        // Making notification bar transparent

        if (Build.VERSION.SDK_INT >= 27) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }


        setContentView(R.layout.activity_welcome);




        viewPager = (ViewPager) findViewById(R.id.pager);
        skip = (Button) findViewById(R.id.btn_skip);
        next = (Button) findViewById(R.id.btn_next);
        linearDot = (LinearLayout) findViewById(R.id.dots);


        //layout of all slider
        layouts = new int[]{
                R.layout.welcome1,
                R.layout.welcome2,
                R.layout.welcome3,
                R.layout.welcome4,
        };

        StatusBarUtil.setTranslucent(welcome.this);

        //adding bottom dots
       // addBottomDots(0);
       //changeStatusBarColor();



        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);

        viewPager.addOnPageChangeListener(ViewPagerPageChangeListener);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int current = getItem(+1);
                if (current < layouts.length) {

                    viewPager.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                }
            }
        });



        }
        /*

   private void addBottomDots(int currentPage){
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        linearDot.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            linearDot.addView(dots[i]);
        }

        if(dots.length > 0){
            dots[currentPage].setTextColor(colorsActive[currentPage]);
        }


    }
    */

    private int getItem(int i){
        return viewPager.getCurrentItem()+i;
    }

    private void launchHomeScreen() {
        //collect.setFirstLaunch(false);
        Intent intent = new Intent(welcome.this , MainActivity.class);
        startActivity(intent);
        //finish();
    }

    ViewPager.OnPageChangeListener ViewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
       // addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                next.setText(getString(R.string.start));
                skip.setVisibility(View.GONE);
            } else {
                // still pages are left
                next.setText(getString(R.string.next));
                skip.setVisibility(View.VISIBLE);
            }


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    private void changeStatusBarColor() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);

        }
    }

    public class MyViewPagerAdapter extends PagerAdapter{

        private LayoutInflater layoutInflater;
        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

}


