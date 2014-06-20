package ch.m335.controllers;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import ch.m335.classes.ShakeListener;
import ch.m335.dao.HomeworkDao;
import ch.m335.entities.HomeworkItem;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Joshua on 18.06.2014.
 */
public class DetailActivity extends Activity {

    protected HomeworkItem homeworkItem;
    protected HomeworkDao homeworkDao;
    // TODO: Search for use of these static final property --> Always 100?
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;
    private ShakeListener shakeListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        homeworkDao = new HomeworkDao(this.getApplicationContext());
        homeworkDao.getWritableDatabase();

        // Get the homework item sent as parameter
        Intent intent = getIntent();
        loadData((HomeworkItem) intent.getSerializableExtra("homeworkItem"));

        // Set the listeners
        findViewById(R.id.btnPicture).setOnClickListener(new OnPictureButtonClickListener());
        findViewById(R.id.tvPictureLink).setOnClickListener(new OnPictureLinkClickListener());
        findViewById(R.id.btnSave).setOnClickListener(new OnSaveButtonClickListener());
        findViewById(R.id.btnDelete).setOnClickListener(new OnDeleteButtonClickListener());
        shakeListener = new ShakeListener(this);
        shakeListener.setOnShakeListener(new ShakeListener.OnShakeListener() {
            @Override
            public void onShake() {
                HomeworkDao dao = new HomeworkDao(DetailActivity.this);
                dao.deleteHomework(homeworkItem);
                finish();
            }
        });
    }

    @Override
    public void onResume() {
        shakeListener.resume();
        super.onResume();
    }

    @Override
    public  void onPause() {
        shakeListener.pause();
        super.onPause();
    }

    private void loadData(HomeworkItem homeworkItem) {
        this.homeworkItem = homeworkItem;

        // Use Calender instead of the methods of Date
        // because those are deprecated
        Calendar calender = Calendar.getInstance();
        calender.setTime(this.homeworkItem.getDueDate());
        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH);
        int day = calender.get(Calendar.DAY_OF_MONTH);

        // Set correct, full filepath
        fileUri = Uri.parse(this.homeworkItem.getPicture());

        ((EditText) findViewById(R.id.etTitle)).setText(this.homeworkItem.getTitle());
        ((EditText) findViewById(R.id.etSubject)).setText(this.homeworkItem.getSubject());
        ((DatePicker) findViewById(R.id.dpDueDate)).updateDate(year, month, day);
        ((TextView) findViewById(R.id.tvPictureLink)).setText(fileUri.getLastPathSegment());
        ((EditText) findViewById(R.id.etComment)).setText(this.homeworkItem.getComment());

        if (this.homeworkItem.getId() == 0) {
            findViewById(R.id.btnDelete).setEnabled(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Image saved to:\n" +
                    fileUri.toString(), Toast.LENGTH_LONG).show();

                this.homeworkItem.setPicture(fileUri.toString());
                ((TextView) findViewById(R.id.tvPictureLink)).setText(fileUri.getLastPathSegment());
            }
            else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "The operation has been cancelled", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, "An unkown error occured while taking/saving the picture", Toast.LENGTH_LONG).show();
            }
        }
    }

    private static File createPictureFile() {
        // Check if SDCard ist mounted to store a file to it
        if (Environment.getExternalStorageState().equals("mounted")) {
            // Get the directory where the file will be stored
            File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "DoSomething");

            // If directory does not exist, it will create it (and all its parent folders)
            if (!storageDir.exists()) {
                if (!storageDir.mkdirs()) {
                    Log.d("DoSomething", "Failed to create directory");
                    return null;
                }
            }

            // Create the picture file with the prefix 'IMG' concatenated with the current timestamp and
            // return it
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            return new File(storageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        }

        return null;
    }

    public class OnSaveButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            homeworkItem.setTitle(((EditText) findViewById(R.id.etTitle)).getText().toString());
            homeworkItem.setSubject(((EditText) findViewById(R.id.etSubject)).getText().toString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                DatePicker dp = (DatePicker) findViewById(R.id.dpDueDate);
                String dateString = dp.getYear() + "-" + dp.getMonth() + "-" + dp.getDayOfMonth();
                java.util.Date date = sdf.parse(dateString);
                homeworkItem.setDueDate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            homeworkItem.setPicture(fileUri.toString());
            homeworkItem.setComment(((EditText) findViewById(R.id.etComment)).getText().toString());

            if (homeworkItem.getId() == 0) {
                homeworkDao.insertHomework(homeworkItem);
            } else {
                homeworkDao.updateHomework(homeworkItem);
            }
            finish();
        }
    }

    public class OnDeleteButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            homeworkItem.setTitle(((EditText) findViewById(R.id.etTitle)).getText().toString());
            homeworkItem.setSubject(((EditText) findViewById(R.id.etSubject)).getText().toString());
            DatePicker dp = (DatePicker) findViewById(R.id.dpDueDate);
            String dateString = dp.getYear() + "-" + dp.getMonth() + "-" + dp.getDayOfMonth();
            homeworkItem.setDueDate(java.sql.Date.valueOf(dateString));
            homeworkItem.setPicture(((TextView) findViewById(R.id.tvPictureLink)).getText().toString());
            homeworkItem.setComment(((EditText) findViewById(R.id.etComment)).getText().toString());

            homeworkDao.deleteHomework(homeworkItem);
            finish();
        }
    }

    public class OnPictureButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            fileUri = Uri.fromFile(createPictureFile());
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }

    public class OnPictureLinkClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (((TextView)findViewById(v.getId())).getText().length() > 0) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(fileUri.toString()), "image/*");
                startActivity(intent);
            }
        }
    }
}