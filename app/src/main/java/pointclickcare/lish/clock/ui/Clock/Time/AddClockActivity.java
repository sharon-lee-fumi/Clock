package pointclickcare.lish.clock.ui.Clock.Time;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import pointclickcare.lish.clock.R;
import pointclickcare.lish.clock.databinding.ContentAddClockBinding;
import pointclickcare.lish.clock.model.Zone;
import pointclickcare.lish.clock.model.ZoneList;
import pointclickcare.lish.clock.ui.Clock.Services.TimeZoneDBClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddClockActivity extends AppCompatActivity {
    ContentAddClockBinding binding;
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
        toolbar.setTitle(R.string.add_clock_title);
        setSupportActionBar(toolbar);

        View contentView = findViewById(R.id.content_add_clock);
        binding = DataBindingUtil.bind(contentView);
        adapter = new ZoneListAdapter();
        binding.listZone.setAdapter(adapter);
        binding.listZone.setLayoutManager(new LinearLayoutManager(this));
        binding.listZone.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
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
