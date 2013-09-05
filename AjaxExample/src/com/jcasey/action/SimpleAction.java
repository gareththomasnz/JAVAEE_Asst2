package com.jcasey.action;


public class SimpleAction {
	boolean toggle;
	
	public boolean isToggle() {
		return toggle;
	}

	public void setToggle(boolean toggle) {
		this.toggle = toggle;
	}
	
	public String execute()
	{
		
		
		System.err.println("AJAX call");
		
		System.err.println(toggle);
		
		return "success";
	}
}
