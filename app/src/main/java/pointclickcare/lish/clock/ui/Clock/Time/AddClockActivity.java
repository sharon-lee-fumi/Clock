package pointclickcare.lish.clock.ui.Clock.Time;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pointclickcare.lish.clock.R;
import pointclickcare.lish.clock.databinding.ActivityAddClockBinding;
import pointclickcare.lish.clock.model.Time;
import pointclickcare.lish.clock.model.Zone;
import pointclickcare.lish.clock.model.ZoneList;
import pointclickcare.lish.clock.ui.Clock.Services.TimeZoneDBClient;
import pointclickcare.lish.clock.ui.MainActivity;
import pointclickcare.lish.clock.util.Extras;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddClockActivity extends AppCompatActivity {
    public List<Time> savedZone = new ArrayList<>();
    ActivityAddClockBinding binding;
    ZoneListAdapter adapter;
    Callback<ZoneList> mZoneListCallback = new Callback<ZoneList>() {
        @Override
        public void onResponse(Call<ZoneList> call, Response<ZoneList> response) {
            if (response.code() == 200 || response.code() == 202) {
                List<Zone> zoneList = response.body().getZones();
                updateList(zoneList);
            } else {
                showErrorToast(getString(R.string.server_error_message));
            }
        }

        @Override
        public void onFailure(Call<ZoneList> call, Throwable t) {
            showErrorToast(getString(R.string.server_error_message));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clock);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View contentView = findViewById(R.id.add_clock);
        binding = DataBindingUtil.bind(contentView);

        Intent getIntent = this.getIntent();
        Bundle bundle = getIntent.getExtras();

        List<Time> getZone = (List<Time>) bundle.getSerializable(Extras.ALARM);

        adapter = new ZoneListAdapter(getZone);

        binding.contentAddClock.listZone.setAdapter(adapter);
        binding.contentAddClock.listZone.setLayoutManager(new LinearLayoutManager(this));
        binding.contentAddClock.listZone.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        binding.backToClockButton.setOnClickListener(
                viewBtn -> {
                    savedZone = adapter.getSavedZone();

                    String uri = "content://pointclickcare.lish.ClockContentProvider/zones";
                    Uri zones = Uri.parse(uri);
                    ContentResolver cr = this.getContentResolver();

                    cr.delete(zones, null, null);

                    for (int i = 0; i < savedZone.size(); i++) {
                        ContentValues cv = new ContentValues();

                        cv.put("ZONE_NAME", savedZone.get(i).getZoneName());
                        cv.put("GMT_OFFSET", savedZone.get(i).getGmtOffset());

                        cr.insert(zones, cv);
                    }

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
        );

    }

    @Override
    public void onStart() {
        super.onStart();
        new TimeZoneDBClient().getZoneList(mZoneListCallback);
    }

    private void updateList(List<Zone> zoneList) {
        adapter.setSource(zoneList);
        adapter.notifyDataSetChanged();
    }

    private void showErrorToast(String message) {
        Toast.makeText(AddClockActivity.this, message, Toast.LENGTH_SHORT);
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
}
