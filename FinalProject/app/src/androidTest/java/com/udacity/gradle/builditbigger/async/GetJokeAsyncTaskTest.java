package com.udacity.gradle.builditbigger.async;

import android.test.AndroidTestCase;

import listeners.JokeListener;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Nora on 1/3/2016.
 */
public class GetJokeAsyncTaskTest extends AndroidTestCase {

    public void testGetJokeAsyncTask_ShouldReturnNonNullJokeString() {
        final CountDownLatch signalWhenDone = new CountDownLatch(1);
        final String[] jokeString = new String[1];

        JokeListener jokeListener = new JokeListener() {
            @Override
            public void checkOutThisHilariousJoke(String joke) {
                jokeString[0] = joke;
                signalWhenDone.countDown();
            }
        };
        GetJokeAsyncTask getJokeAsyncTask = new GetJokeAsyncTask(jokeListener);
        getJokeAsyncTask.execute();

        try {
            signalWhenDone.await();
            assertNotNull(jokeString[0]);
        } catch (InterruptedException e) {
            e.printStackTrace();
            assert(false);
        }
    }

    public void testGetJokeAsyncTask_ShouldReturnNonEmptyJokeString() {
        final CountDownLatch signalWhenDone = new CountDownLatch(1);
        final String[] jokeString = new String[1];

        JokeListener jokeListener = new JokeListener() {
            @Override
            public void checkOutThisHilariousJoke(String joke) {
                jokeString[0] = joke;
                signalWhenDone.countDown();
            }
        };
        GetJokeAsyncTask getJokeAsyncTask = new GetJokeAsyncTask(jokeListener);
        getJokeAsyncTask.execute();

        try {
            signalWhenDone.await();
            assertNotSame("", jokeString[0]);
        } catch (InterruptedException e) {
            e.printStackTrace();
            assert(false);
        }
    }

}