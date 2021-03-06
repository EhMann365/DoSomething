package ch.m335.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import ch.m335.classes.HomeworkItemArrayAdapter;
import ch.m335.dao.HomeworkDao;
import ch.m335.entities.HomeworkItem;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Brian on 17.06.2014.
 */
public class MainActivity extends Activity {

    private HomeworkDao homeworkDao;
    private ArrayList<HomeworkItem> homeworkItems;
    private HomeworkItemArrayAdapter<HomeworkItem> homeworkItemArrayAdapter;

    public MainActivity() {
        homeworkDao = new HomeworkDao(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        homeworkDao = new HomeworkDao(this.getApplicationContext());
        homeworkDao.getWritableDatabase();

        // Load items into the listview
        loadList();
        
        // Set listener for navigating to the detail view after clicking an item
        ((ListView) findViewById(R.id.lvHomeworkItems)).setOnItemClickListener(new OnListViewItemClickListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.itNewHomeworkItem:
                Intent intent = new Intent(this, DetailActivity.class);
                intent.putExtra("homeworkItem", new HomeworkItem());
                startActivityForResult(intent, 0);
                return true;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        loadList();
    }

    private void loadList() {
        homeworkItems = (ArrayList<HomeworkItem>) homeworkDao.selectAllHomeworks();
        homeworkItemArrayAdapter = new HomeworkItemArrayAdapter<>(this, homeworkItems);
        ((ListView) findViewById(R.id.lvHomeworkItems)).setAdapter(homeworkItemArrayAdapter);
    }

    public class OnListViewItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("homeworkItem", homeworkItems.get(position));
            startActivityForResult(intent, 0);
        }
    }
}