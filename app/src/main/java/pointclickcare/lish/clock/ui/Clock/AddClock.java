package pointclickcare.lish.clock.ui.Clock;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.Toolbar;

import pointclickcare.lish.clock.R;
import pointclickcare.lish.clock.databinding.ContentAddClockBinding;
import pointclickcare.lish.clock.ui.Clock.Services.TimeZoneDBClient;

public class AddClock extends AppCompatActivity {
    ContentAddClockBinding binding;
    ZoneListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clock);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        binding = DataBindingUtil.setContentView(this, R.layout.content_add_clock);
        adapter = new ZoneListAdapter();
        binding.listZone.setAdapter(adapter);
        binding.listZone.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onStart() {
        super.onStart();
        new TimeZoneDBClient().getZoneList(adapter);
    }
}
