package ch.m335.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import ch.m335.dao.HomeworkDao;
import ch.m335.entities.HomeworkItem;

import java.util.ArrayList;

/**
 * Created by Brian on 17.06.2014.
 */
public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private HomeworkDao homeworkDao;
    private ArrayList<HomeworkItem> homeworkItems;
    private ArrayAdapter<HomeworkItem> homeworkItemArrayAdapter;

    public MainActivity() {
        homeworkDao = new HomeworkDao();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
        setContentView(R.layout.main);

        loadList();
        ((ListView) findViewById(R.id.lvHomeworkItems)).setOnItemClickListener(this);
    }

    private void loadList() {
        homeworkItems = homeworkDao.getHomeworkItems();
        homeworkItemArrayAdapter = new ArrayAdapter<HomeworkItem>(this, android.R.layout.simple_list_item_1, homeworkItems);
        ((ListView)findViewById(R.id.lvHomeworkItems)).setAdapter(homeworkItemArrayAdapter);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, homeworkItems.get(position).toString(), Toast.LENGTH_LONG).show();
    }
}