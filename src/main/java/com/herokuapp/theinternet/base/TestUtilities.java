package com.herokuapp.theinternet.base;

public class TestUtilities extends BaseTest {
	
	// Static sleep
	protected void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
