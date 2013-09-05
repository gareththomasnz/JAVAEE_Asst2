package com.jcasey.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

public class Attendance extends ActionSupport{
	
	private class Session
	{
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public boolean isPresent() {
			return present;
		}
		public void setPresent(boolean present) {
			this.present = present;
		}
		public Session(String name, boolean present) {
			super();
			this.name = name;
			this.present = present;
		}
		String name;
		boolean present;
		@Override
		public String toString() {
			return "Session [name=" + name + ", present=" + present + "]";
		}
	}
	
	List <Session> sessions = new ArrayList <Session> ();
	
	public Attendance()
	{
		sessions.add(new Session("Week 1",false));
		sessions.add(new Session("Week 2",true));
		sessions.add(new Session("Week 3",false));
		sessions.add(new Session("Week 4",false));
		sessions.add(new Session("Week 6",false));
		sessions.add(new Session("Week 7",true));
		sessions.add(new Session("Week 8",false));
		
	}

	public String execute()
	{
//		ValueStack stack = ActionContext.getContext().getValueStack();
//		
//		stack.getContext();
		
		for(Session sess: sessions)
		{
			System.err.println(sess);			
		}
		

		
		return "success";
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}
	
}
