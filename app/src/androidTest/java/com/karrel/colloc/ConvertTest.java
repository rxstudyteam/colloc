package com.karrel.colloc;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.karrel.colloc.ui.main.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ConvertTest {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        appContext.startActivity(new Intent(appContext, NoahMainActivity.class));

        assertEquals(appContext.getPackageName(), "com.karrel.colloc");
    }



}
