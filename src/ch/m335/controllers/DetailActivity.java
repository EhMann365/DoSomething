package ch.m335.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import ch.m335.controllers.R;
import ch.m335.dao.HomeworkDao;
import ch.m335.entities.HomeworkItem;

import java.util.Date;

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

    private HomeworkItem hwi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

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
                createHomeworkItem();
            }
        };
        btnSave.setOnClickListener(oclSave);
    }

    private void createHomeworkItem() {
        hwi = new HomeworkItem(etTitle.getText().toString(), etSubject.getText().toString(), new Date(etDueDate.getYear(), etDueDate.getMonth(), etDueDate.getDayOfMonth()), etPicture.getText(), etComment.getText().toString());
    }
}