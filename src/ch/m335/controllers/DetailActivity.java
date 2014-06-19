package ch.m335.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.*;
import ch.m335.controllers.R;
import ch.m335.entities.HomeworkItem;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Joshua on 18.06.2014.
 */
public class DetailActivity extends Activity {

    protected HomeworkItem homeworkItem;
    // TODO: Search for use of these static final property --> Always 100?
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        // Get the homework item sent as parameter
        Intent intent = getIntent();
        loadData((HomeworkItem) intent.getSerializableExtra("homeworkItem"));

        // Set the listeners
        findViewById(R.id.btnPicture).setOnClickListener(new OnPictureButtonClickListener());
        findViewById(R.id.tvPictureLink).setOnClickListener(new OnPictureLinkClickListener());
    }

    private void loadData(HomeworkItem homeworkItem) {
        this.homeworkItem = homeworkItem;

        // TODO: Change from deprecated 'Date' to 'Calendar'
        int year = this.homeworkItem.getDueDate().getYear();
        int month = this.homeworkItem.getDueDate().getMonth();
        int day = this.homeworkItem.getDueDate().getDay();

        ((EditText) findViewById(R.id.etTitle)).setText(this.homeworkItem.getTitle());
        ((EditText) findViewById(R.id.etSubject)).setText(this.homeworkItem.getSubject());
        ((DatePicker) findViewById(R.id.dpDueDate)).updateDate(year, month, day);
        ((TextView) findViewById(R.id.tvPictureLink)).setText(this.homeworkItem.getPicture());
        ((EditText) findViewById(R.id.etComment)).setText(this.homeworkItem.getComment());
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

            // Create the picture file with the prefix 'IMG' concatenated with the current timestamp
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            File mediaFile = new File(storageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");

            return mediaFile;
        }

        return null;
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
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse(fileUri.toString()), "image/*");
            startActivity(intent);
        }
    }
}