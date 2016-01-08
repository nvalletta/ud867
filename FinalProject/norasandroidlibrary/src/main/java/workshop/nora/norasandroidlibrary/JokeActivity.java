package workshop.nora.norasandroidlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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

}
