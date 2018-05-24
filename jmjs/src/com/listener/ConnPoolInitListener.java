package com.listener;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import com.util.ConnectionPool;

/**
 * Application Lifecycle Listener implementation class ConnPoolInitListener
 *
 */
@WebListener
public class ConnPoolInitListener implements ServletContextListener {
	private static final Logger logger = LoggerFactory.getLogger(ConnPoolInitListener.class);

    public void contextDestroyed(ServletContextEvent arg0)  { 
    	ConnectionPool instance = ConnectionPool.getInstance();
    	try {
			DataSources.destroy( instance.getCpds() );
			logger.info("连接池销毁了");
    	} catch (SQLException e) {
			e.printStackTrace();
			logger.error("连接池销毁时报异常");
		}
    	
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
        ConnectionPool instance = ConnectionPool.getInstance();
        instance.setCpds(new ComboPooledDataSource());
        logger.info("连接池初始化了");
    }
	
}
