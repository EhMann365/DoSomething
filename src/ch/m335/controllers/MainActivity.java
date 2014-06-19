package ch.m335.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        homeworkDao = new HomeworkDao(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        homeworkDao = new HomeworkDao(this.getApplicationContext());
        Log.d("DoSomething: ", "HomeworkDao created");
        homeworkDao.getWritableDatabase();

        /*homeworkDao.insertHomework(new HomeworkItem("Test 1", "Neu", Date.valueOf("2014-06-19"), new String(),"A Comment 1"));
        Log.d("DoSomething: ", "Data 1 inserted");
        homeworkDao.insertHomework(new HomeworkItem("Test 2", "Neu", Date.valueOf("2014-06-18"), new String(), "A Comment 2"));
        Log.d("DoSomething: ", "Data 2 inserted");*/
        /*homeworkDao.insertHomework(new HomeworkItem("Test 3", "Neu", Date.valueOf("2014-06-17"), new String(), "A Comment 3"));
        Log.d("DoSomething: ", "Data 3 inserted");*/

        /*homeworkDao.updateHomework(new HomeworkItem(3, "Test 3 Test", "Neu Updatet", Date.valueOf("2014-06-18"), new String(), "A Comment 3"));
        homeworkDao.deleteHomework(new HomeworkItem(1, "Test 3 Test", "Neu Updatet", Date.valueOf("2014-06-18"), new String(), "A Comment 3"));

        List<HomeworkItem> list = homeworkDao.selectAllHomeworks();
        Log.d("DoSomething: ", "List created");

        for (HomeworkItem hwi : list) {
            Log.d("DoSomething: ",hwi.getId() + ";" + hwi.getTitle() + ";" + hwi.getSubject() + ";" + hwi.getDueDate().toString() + ";" + hwi.getPicture() + ";" + hwi.getComment());
        }

        HomeworkItem hwi = homeworkDao.selectHomework(4);
        Log.d("DoSomething: ",hwi.getId() + ";" + hwi.getTitle() + ";" + hwi.getSubject() + ";" + hwi.getDueDate().toString() + ";" + hwi.getPicture() + ";" + hwi.getComment());
*/

        // Load items into the listview
        loadList();

        // Set listener for navigating to the detail view after clicking an item
        ((ListView) findViewById(R.id.lvHomeworkItems)).setOnItemClickListener(this);
    }

    private void loadList() {
        homeworkItems = (ArrayList<HomeworkItem>) homeworkDao.selectAllHomeworks();
        homeworkItemArrayAdapter = new HomeworkItemArrayAdapter<>(this, homeworkItems);
        ((ListView)findViewById(R.id.lvHomeworkItems)).setAdapter(homeworkItemArrayAdapter);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("homeworkItem", homeworkItems.get(position));
        startActivity(intent);
    }
}