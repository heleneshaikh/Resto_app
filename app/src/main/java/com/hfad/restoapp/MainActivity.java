package com.hfad.restoapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ShareActionProvider;

public class MainActivity extends Activity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SET LV LISTENER
        listView = (ListView) findViewById(R.id.lv_drawer);
        listView.setOnItemClickListener(new ListViewListener());
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

    //CREATE LV LISTENER
    private class ListViewListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            itemSelected(position);
        }
    }

    private void itemSelected(int position) {
        Fragment fragment;
        switch (position) {
            case 1:
                fragment = new PizzaFragment();
                break;
            case 2:
                fragment = new PastaFragment();
                break;
            case 3:
                fragment = new StoreFragment();
                break;
            default:
                fragment = new TopFragment();
        }
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }
}
