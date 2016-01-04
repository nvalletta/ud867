package com.udacity.gradle.builditbigger.async;

import android.os.AsyncTask;

import com.builditbigger.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.JokeListener;

import java.io.IOException;

/**
 * Created by Nora on 1/1/2016.
 */
public class GetJokeAsyncTask extends AsyncTask<Void, Void, String> {

    private static MyApi myApi = null;
    private JokeListener jokeListener;

    public GetJokeAsyncTask(JokeListener jokeListener) {
        this.jokeListener = jokeListener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            return getMyApi().getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        super.onPostExecute(joke);
        jokeListener.checkOutThisHilariousJoke(joke);
    }

    private MyApi getMyApi() {
        if (null == myApi) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver
            myApi = builder.build();
        }
        return myApi;
    }

}
