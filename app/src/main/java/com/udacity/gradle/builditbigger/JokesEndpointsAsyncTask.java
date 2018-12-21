package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.gradle.builditbigger.backend.myApi.model.MyBean;

import java.io.IOException;


/**
 * Created by meets on 12/17/2018.
 */

public class JokesEndpointsAsyncTask extends AsyncTask<JokesEndpointsAsyncTask.JokesListener, Void, MyBean> {
    private static final String TAG = JokesEndpointsAsyncTask.class.getCanonicalName();
    private static MyApi myApiService = null;
    private JokesListener listener;

    @Override
    protected MyBean doInBackground(JokesListener... params) {
        listener = params[0];
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://192.168.1.158:8080/_ah/api/").setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }


        try {
            return myApiService.getJokesFromJavaModule().execute();
        } catch (IOException e) {
            e.printStackTrace();
            Log.w(TAG, "API didn't get the joke. /s");
            return null;
        }
    }

    @Override
    protected void onPostExecute(MyBean result) {
        if (result != null) {
            Log.i(TAG, "Some joke went thru");
        }
        listener.onComplete(result);
    }

    public interface JokesListener {
        void onComplete(MyBean joke);
    }
}
