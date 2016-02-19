package com.example.junyizhou.recyclerviewdemo.test;

import com.example.junyizhou.recyclerviewdemo.activity.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class TestCaseI extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public TestCaseI() {
		super(MainActivity.class);
  	}

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testRun() {
        //Wait for activity: 'com.example.junyizhou.recyclerviewdemo.activity.MainActivity'
		solo.waitForActivity(com.example.junyizhou.recyclerviewdemo.activity.MainActivity.class, 2000);
        //Click on linear
		solo.clickOnView(solo.getView(com.example.junyizhou.recyclerviewdemo.R.id.btn_linear));
        //Click on com.example.junyizhou.recyclerviewdemo.R.id.tv_char
		solo.clickOnView(solo.getView(com.example.junyizhou.recyclerviewdemo.R.id.tv_char));
        //Click on B
		solo.clickInRecyclerView(1, 0);
        //Click on com.example.junyizhou.recyclerviewdemo.R.id.tv_char
		solo.clickOnView(solo.getView(com.example.junyizhou.recyclerviewdemo.R.id.tv_char, 2));
        //Click on C
		solo.clickInRecyclerView(3, 0);
        //Set default small timeout to 15333 milliseconds
		Timeout.setSmallTimeout(15333);
        //Press menu back key
		solo.goBack();
        //Click on grid
		solo.clickOnView(solo.getView(com.example.junyizhou.recyclerviewdemo.R.id.btn_grid));
        //Press menu back key
		solo.goBack();
        //Click on grid_span_size_diy
		solo.clickOnView(solo.getView(com.example.junyizhou.recyclerviewdemo.R.id.btn_grid_span_size_diy));
        //Set default small timeout to 38029 milliseconds
		Timeout.setSmallTimeout(38029);
        //Press menu back key
		solo.goBack();
        //Click on staggered_grid_vertical_flow
		solo.clickOnView(solo.getView(com.example.junyizhou.recyclerviewdemo.R.id.btn_staggered_grid_vertical_flow));
        //Click on v
		solo.clickInRecyclerView(18, 0);
        //Click on y
		solo.clickInRecyclerView(21, 0);
	}
}
