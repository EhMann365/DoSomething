package ch.m335.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import ch.m335.classes.HomeworkItemArrayAdapter;
import ch.m335.dao.HomeworkDao;
import ch.m335.entities.HomeworkItem;

import java.util.ArrayList;

/**
 * Created by Brian on 17.06.2014.
 */
public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private HomeworkDao homeworkDao;
    private ArrayList<HomeworkItem> homeworkItems;
    private HomeworkItemArrayAdapter<HomeworkItem> homeworkItemArrayAdapter;

    public MainActivity() {
        homeworkDao = new HomeworkDao();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Load items into the listview
        loadList();

        // Set listener for navigating to the detail view after clicking an item
        ((ListView) findViewById(R.id.lvHomeworkItems)).setOnItemClickListener(this);
    }

    private void loadList() {
        homeworkItems = homeworkDao.getHomeworkItems();
        homeworkItemArrayAdapter = new HomeworkItemArrayAdapter<>(this, homeworkItems);
        ((ListView)findViewById(R.id.lvHomeworkItems)).setAdapter(homeworkItemArrayAdapter);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("homeworkItem", homeworkItems.get(position));
        startActivity(intent);
    }
}