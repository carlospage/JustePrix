package live.page.chen.justeprix.utils;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import live.page.chen.justeprix.MainActivity;
import live.page.chen.justeprix.R;
import live.page.chen.justeprix.SettingsActivity;

public class GrandMereActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        if (item.getItemId() == R.id.prefs) {
            Intent i = new Intent(this, SettingsActivity.class);

            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
