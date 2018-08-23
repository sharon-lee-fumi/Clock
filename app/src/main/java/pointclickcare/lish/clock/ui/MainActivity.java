package pointclickcare.lish.clock.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import pointclickcare.lish.clock.R;
import pointclickcare.lish.clock.model.Timer;
import pointclickcare.lish.clock.ui.Alarm.AlarmFragment;
import pointclickcare.lish.clock.ui.Clock.Time.AddClockActivity;
import pointclickcare.lish.clock.ui.Clock.Time.ClockFragment;
import pointclickcare.lish.clock.ui.Timer.RunTimerFragment;
import pointclickcare.lish.clock.ui.Timer.SetTimerFragment;
import pointclickcare.lish.clock.ui.Timer.TimerFragment;
import pointclickcare.lish.clock.util.UiUtil;

public class MainActivity extends AppCompatActivity {

    StopwatchFragment stopwatchFragment = null;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Integer iconId = null;
                switch (tab.getPosition()) {
                    case 0:
                        iconId = android.R.drawable.ic_input_add;
                        break;
                    case 1:
                        iconId = android.R.drawable.ic_lock_idle_alarm;
                        break;
                    case 3:
                        iconId = android.R.drawable.ic_media_play;
                        //iconId = android.R.drawable.ic_media_pause;
                        break;
                    case 2:
                        iconId = android.R.drawable.ic_media_play;
                    default:
                        break;
                }

                FloatingActionButton fab = findViewById(R.id.fab);
                if (fab != null) {
                    if (iconId != null) {
                        fab.setVisibility(View.VISIBLE);
                        fab.setImageDrawable(getResources().getDrawable(iconId, getTheme()));
                    } else {
                        fab.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Fragment fragment;
            int tabPosition = tabLayout.getSelectedTabPosition();

            switch (tabPosition) {
                case 1:
                    AddClock();
                    break;
                case 2:
                    fragment = mSectionsPagerAdapter.getFragmentAtPosition(2);

                    if (fragment instanceof TimerFragment) {
                        Fragment fragmentPlaceholder = fragment.getFragmentManager().findFragmentById(R.id.timerPlaceholder);
                        if (fragmentPlaceholder != null && fragmentPlaceholder instanceof SetTimerFragment) {
                            SetTimerFragment setTimerFragment = (SetTimerFragment) fragmentPlaceholder;
                            Timer newTimer = setTimerFragment.getTimer();

                            Fragment runTimer = RunTimerFragment.newInstance(newTimer);
                            UiUtil.switchFragment(getSupportFragmentManager(), R.id.timerPlaceholder, runTimer);
                        }
                    }
                    break;
                case 3:
                    fragment = mSectionsPagerAdapter.getFragmentAtPosition(3);
                    if (fragment instanceof StopwatchFragment) {
                        StopwatchFragment stopwatchFragment = (StopwatchFragment) fragment;
                        stopwatchFragment.runStopWatch();
                    }
                    break;
            }
        });

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void AddClock() {
        Intent intent = new Intent(this, AddClockActivity.class);
        //intent.putExtra(Extra.DATA, "Data from Clock");
        startActivity(intent);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {

            switch (sectionNumber) {
                case 1:
                    return AlarmFragment.getInstance();
                case 2:
                    return ClockFragment.getInstance();
                case 3:
                    return TimerFragment.getInstance();
                case 4:
                    return StopwatchFragment.getInstance();
                default:
                    return AlarmFragment.getInstance();
            }
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        SparseArray<Fragment> fragmentList = new SparseArray<>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Fragment fragment = PlaceholderFragment.newInstance(position + 1);
            fragmentList.put(position, fragment);
            return fragment;
        }

        public Fragment getFragmentAtPosition(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }
    }
}
