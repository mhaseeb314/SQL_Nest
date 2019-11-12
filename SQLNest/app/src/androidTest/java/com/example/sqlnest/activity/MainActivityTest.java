package com.example.sqlnest.activity;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.example.sqlnest.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =  new ActivityTestRule<>(MainActivity.class);
    private MainActivity mainActivity = null;


    @Before
    public void setUp() throws Exception {

        mainActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void launchTest(){
        //Espresso.onView(ViewMatchers.withId(R.id.tvToolbar)).check();
        assertNotNull(mainActivity.tvToolbar);
        //assertTrue(mainActivity.tvToolbar.equals("Toolbar"));
    }

    @After
    public void tearDown() throws Exception {
    }
}