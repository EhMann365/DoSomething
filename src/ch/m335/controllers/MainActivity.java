package ch.m335.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import ch.m335.controllers.R;

/**
 * Created by Brian on 17.06.2014.
 */
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }
}