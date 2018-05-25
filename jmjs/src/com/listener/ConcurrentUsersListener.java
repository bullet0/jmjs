package com.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class ConcurrentUsersListener
 *
 */
@WebListener
public class ConcurrentUsersListener implements HttpSessionAttributeListener,HttpSessionListener {

	private List<String> list = new ArrayList(); 
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("创建一个session");  
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("销毁一个session");  
        if (!list.isEmpty()) {  
            list.remove(event.getSession().getAttribute("userName").toString());  
            event.getSession().getServletContext().setAttribute("concurrentUsers", list.size());  
        } 
	}
	
    public void attributeAdded(HttpSessionBindingEvent event)  { 
    	Object name = event.getSession().getAttribute("userName");
    	if(name != null) {
    		if(!list.contains(name.toString())) {
    			list.add(name.toString());
    			event.getSession().getServletContext().setAttribute("concurrentUsers", list.size());  
    		}
    	}
    }

    public void attributeRemoved(HttpSessionBindingEvent event)  { 
    	
    }

    public void attributeReplaced(HttpSessionBindingEvent event)  { 
    	
    }

	
	
}
