package com.imman.iava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.imman.iava.Schedule.HandleSchedule;
import com.imman.iava.Schedule.SubjectClassAdapter;

public class ScheduleActivity extends AppCompatActivity {
    RecyclerView classRecyclerView;
    ConstraintLayout week_card;
    TextView title;
    CardView bottomSheetCard;
    ShimmerFrameLayout classShimmerLayout;
    BottomSheetBehavior<View> sheetBehaviour;
    static Context t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        classRecyclerView = findViewById(R.id.class_recyclerView);
        week_card = findViewById(R.id.week_card_group);
        title = findViewById(R.id.appTitle);
        bottomSheetCard = findViewById(R.id.bottom_sheet_card);
        classShimmerLayout = findViewById(R.id.class_recyclerView_shimmer);
        t=this;
        test();
    }

    private void test() {
//        classShimmerLayout.startShimmer();
        classShimmerLayout.setVisibility(View.GONE);
        classRecyclerView.setVisibility(View.VISIBLE);

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
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_HALF_EXPANDED:
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                //offset = 1(expanded-3) to 0(collapsed-4)
                week_card.setY((slideOffset*225)-75);
                bottomSheetCard.setRadius(slideOffset*80);
            }
        });
        String s[] = new String[15];
        SubjectClassAdapter adapter = new SubjectClassAdapter(s);
        classRecyclerView.setHasFixedSize(true);
        classRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        classRecyclerView.setAdapter(adapter);
        new asyncTasks().execute();
    }

    static void populateList(){

    }

    static class asyncTasks extends AsyncTask<Void, Void, String>{

        asyncTasks(){

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            HandleSchedule.run(t);

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}