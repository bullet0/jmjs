package com.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UsersDTO {
	private int uId;
	private String uName;
	private String uPwd;
	private String uRealName;
	private String uEmail;
	private Set<String> rolesSet = new HashSet<String>();
	private Set<String> promissionSet = new HashSet<String>();;
	
	/**
	 * @return the rolesSet
	 */
	public Set<String> getRolesSet() {
		return rolesSet;
	}
	/**
	 * @param rolesSet the rolesSet to set
	 */
	public void setRolesSet(Set<String> rolesSet) {
		this.rolesSet = rolesSet;
	}
	/**
	 * @return the promissionSet
	 */
	public Set<String> getPromissionSet() {
		return promissionSet;
	}
	/**
	 * @param promissionSet the promissionSet to set
	 */
	public void setPromissionSet(Set<String> promissionSet) {
		this.promissionSet = promissionSet;
	}
	/**
	 * @return the uId
	 */
	public int getuId() {
		return uId;
	}
	/**
	 * @param uId the uId to set
	 */
	public void setuId(int uId) {
		this.uId = uId;
	}
	/**
	 * @return the uName
	 */
	public String getuName() {
		return uName;
	}
	/**
	 * @param uName the uName to set
	 */
	public void setuName(String uName) {
		this.uName = uName;
	}
	/**
	 * @return the uPwd
	 */
	public String getuPwd() {
		return uPwd;
	}
	/**
	 * @param uPwd the uPwd to set
	 */
	public void setuPwd(String uPwd) {
		this.uPwd = uPwd;
	}
	/**
	 * @return the uRealName
	 */
	public String getuRealName() {
		return uRealName;
	}
	/**
	 * @param uRealName the uRealName to set
	 */
	public void setuRealName(String uRealName) {
		this.uRealName = uRealName;
	}
	/**
	 * @return the uEmail
	 */
	public String getuEmail() {
		return uEmail;
	}
	/**
	 * @param uEmail the uEmail to set
	 */
	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UsersDTO [uId=" + uId + ", uName=" + uName + ", uPwd=" + uPwd + ", uRealName=" + uRealName + ", uEmail="
				+ uEmail + ", rolesSet=" + rolesSet + ", promissionSet=" + promissionSet + "]";
	}
	
	
}
