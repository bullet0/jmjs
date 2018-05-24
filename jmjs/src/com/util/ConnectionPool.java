package com.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class ConnectionPool {
	private static ConnectionPool instance = new ConnectionPool();
	private ComboPooledDataSource cpds = null;
	private ConnectionPool() {}
	public static ConnectionPool getInstance() {
		return instance;
	}
	// 在反序列化时，直接调用这个方法，返回指定的对象，无需再新建一个对象
    private Object readResolve() {
        return instance;
    }
	/**
	 * @return the cpds
	 */
	public ComboPooledDataSource getCpds() {
		return cpds;
	}
	/**
	 * @param cpds the cpds to set
	 */
	public void setCpds(ComboPooledDataSource cpds) {
		this.cpds = cpds;
	}
    
}
