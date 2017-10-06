package com.industries.hackateam.hackathon;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class DayLessonActivity extends AppCompatActivity {

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_lesson);
        Intent intent = getIntent();
        title = (TextView) findViewById(R.id.title);
        //intent.putExtra("idcours", intent.getStringExtra("idmatiere"));




        title.setText(intent.getStringExtra("title"));
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        DescriptionFragment descripFrag = new DescriptionFragment();
        QuestionFragment questFrag = new QuestionFragment();


        int idCoursInt;
        try {
            idCoursInt = Integer.parseInt(getIntent().getStringExtra("idcours"));
        } catch(NumberFormatException e) {
            idCoursInt = -1;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("idcours", idCoursInt);
        questFrag.setArguments(bundle);
        descripFrag.setArguments(bundle);

        adapter.addFragment(descripFrag, "Description");
        adapter.addFragment(questFrag, "Questions");
        viewPager.setAdapter(adapter);
    }
}
