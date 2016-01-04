package com.udacity.gradle.builditbigger.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.udacity.gradle.builditbigger.R;

public class JokeActivity extends AppCompatActivity {

    public static final String INTENT_KEY_JOKE = "INTENT_KEY_JOKE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Intent intent = getIntent();
        if (null != intent && intent.getExtras() != null) {
            String joke = intent.getExtras().getString(JokeActivity.INTENT_KEY_JOKE);
            TextView jokeTextView = (TextView) findViewById(R.id.text_joke);
            if (null != joke && jokeTextView != null) {
                if (joke.contains("failed to connect")) {
                    jokeTextView.setText(getString(R.string.error_message));
                }
                jokeTextView.setText(joke);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_joke, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
