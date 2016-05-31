package com.hfad.restoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // INFLATE AB ITEMS WHEN MENU GETS CREATED
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        MenuItem menuItem = menu.findItem(R.id.share_action_provider);
        ShareActionProvider shareActionProvider = (ShareActionProvider) menuItem.getActionProvider();
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Devastated, gravimetric transformators never accelerate a collective, evasive creature.");
        shareActionProvider.setShareIntent(shareIntent);
        return super.onCreateOptionsMenu(menu);
    }

    // LISTENER FOR CLICKING ON AB ITEM
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        getActionBar().setHomeButtonEnabled(true);
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_shop:
                Intent intent = new Intent(this, OrderActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
