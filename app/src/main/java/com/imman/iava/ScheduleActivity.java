package com.imman.iava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.imman.iava.Home.HomeCardPagerAdapter;
import com.imman.iava.Schedule.HandleNetwork;
import com.imman.iava.Schedule.SubjectClassAdapter;
import com.imman.iava.Schedule.SubjectDataParser;
import com.imman.iava.Schedule.SubjectModel;

import java.util.ArrayList;
import java.util.Calendar;

public class ScheduleActivity extends AppCompatActivity {
    RecyclerView classRecyclerView;
    ConstraintLayout week_card;
    long backPressedTime = 0;
    LinearLayout[] wc= new LinearLayout[7];
    TextView title,card_title;
//    ViewPager2 viewPager2;
    CardView bottomSheetCard;
    ShimmerFrameLayout classShimmerLayout;
    BottomSheetBehavior<View> sheetBehaviour;
    ArrayList<ArrayList<SubjectModel>> fModel;
    static Context t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        classRecyclerView = findViewById(R.id.class_recyclerView);
        week_card = findViewById(R.id.week_card_group);
        title = findViewById(R.id.appTitle);
//        card_title = findViewById(R.id.card_title);
        bottomSheetCard = findViewById(R.id.bottom_sheet_card);
        classShimmerLayout = findViewById(R.id.class_recyclerView_shimmer);
//        viewPager2 = findViewById(R.id.home_card_viewpager);
//        HomeCardPagerAdapter viewPager2Adapter = new HomeCardPagerAdapter();
//        viewPager2.setAdapter(viewPager2Adapter);
        t=this;
        test();
    }

    @Override
    public void onBackPressed() {
        if(sheetBehaviour.getState()!=BottomSheetBehavior.STATE_EXPANDED){
            sheetBehaviour.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
        else{
            long t = System.currentTimeMillis();
            if (t - backPressedTime > 2000) {
                backPressedTime = t;
                Toast.makeText(this, "Press back again to logout",
                        Toast.LENGTH_SHORT).show();
            }
            else
            {
                super.onBackPressed();
            }
        }

    }

    private void test() {
        classShimmerLayout.startShimmer();

        //Extras
        sheetBehaviour = BottomSheetBehavior.from(findViewById(R.id.bottom_sheet_layout));
        sheetBehaviour.setState(BottomSheetBehavior.STATE_EXPANDED);
        sheetBehaviour.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_EXPANDED:
                        title.setText("Schedule");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        title.setText("Home");
                        break;
                }
            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                //offset = 1(expanded-3) to 0(collapsed-4)
                week_card.setY((slideOffset*225)-75);
                bottomSheetCard.setRadius(slideOffset*80);
//                card_title.setAlpha((1-slideOffset)-.50f);
            }
        });
        classRecyclerView.setHasFixedSize(true);
        classRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        new asyncTasks().execute();
    }

    void populateList(){
        System.out.println("Populating List.");
        fModel = SubjectDataParser.run(t);
        SubjectClassAdapter adapter = new SubjectClassAdapter(fModel.get(0));
        classRecyclerView.setAdapter(adapter);
        weekChangeHandle();
        classShimmerLayout.stopShimmer();
        classShimmerLayout.setVisibility(View.GONE);
        classRecyclerView.setVisibility(View.VISIBLE);
    }

    void weekChangeHandle(){
        int[] wcid = {0,R.id.week_card_1,R.id.week_card_2,R.id.week_card_3,R.id.week_card_4,R.id.week_card_5,R.id.week_card_6};

        for (int i = 1; i <= 6; i++) {
            wc[i] = findViewById(wcid[i]);
        }
        for (int i = 1; i <= 6; i++) {
            int finalI = i;
            wc[i].setOnClickListener(v -> {
                for (int j = 1; j <= 6; j++) {
                    wc[j].setBackground(AppCompatResources.getDrawable(t,R.drawable.bg_week_card));
                }
                wc[finalI].setBackground(AppCompatResources.getDrawable(t,R.drawable.bg_week_card_selected));
//                classRecyclerView.setAdapter(new SubjectClassAdapter(fModel.get(finalI-1)));

            });
        }
        Calendar calendar = Calendar.getInstance();
        wc[calendar.get(Calendar.DAY_OF_WEEK)-1].setBackground(AppCompatResources.getDrawable(t,R.drawable.bg_week_card_selected));
    }

    class asyncTasks extends AsyncTask<Void, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(HandleNetwork.ifPossible(t))
                populateList();
        }

        @Override
        protected String doInBackground(Void... voids) {
            if(HandleNetwork.run(t))
                populateList();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}