package ch.m335.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import ch.m335.controllers.R;
import ch.m335.entities.HomeworkItem;

import java.util.Calendar;

/**
 * Created by Joshua on 18.06.2014.
 */
public class DetailActivity extends Activity {
    private EditText etTitle;
    private EditText etSubject;
    private DatePicker etDueDate;
    private EditText etPicture;
    private Button btnPicture;
    private EditText etComment;
    private Button btnSave;
    private View.OnClickListener oclPicture;
    private View.OnClickListener oclSave;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        // Get the homework item sent as parameter
        Intent intent = getIntent();
        loadData((HomeworkItem) intent.getSerializableExtra("homeworkItem"));

        init();
    }

    private void init() {
        etTitle = (EditText) findViewById(R.id.etTitle);
        etSubject = (EditText) findViewById(R.id.etSubject);
        etDueDate = (DatePicker) findViewById(R.id.dpDueDate);
        etPicture = (EditText) findViewById(R.id.etPicture);
        btnPicture = (Button) findViewById(R.id.btnPicture);
        etComment = (EditText) findViewById(R.id.etComment);
        btnSave = (Button) findViewById(R.id.btnSave);

        oclPicture = new View.OnClickListener() {
            public void onClick(View v) {

            }
        };
        btnPicture.setOnClickListener(oclPicture);

        oclPicture = new View.OnClickListener() {
            public void onClick(View v) {

            }
        };
        btnSave.setOnClickListener(oclSave);
    }

    private void loadData(HomeworkItem homeworkItem) {
        int year = homeworkItem.getDueDate().getYear();
        int month = homeworkItem.getDueDate().getMonth();
        int day = homeworkItem.getDueDate().getDay();

        ((EditText) findViewById(R.id.etTitle)).setText(homeworkItem.getTitle());
        ((EditText) findViewById(R.id.etSubject)).setText(homeworkItem.getSubject());
        ((DatePicker) findViewById(R.id.dpDueDate)).updateDate(year, month, day);
        // TODO: Show picture
        ((EditText) findViewById(R.id.etComment)).setText(homeworkItem.getComment());
    }
}