package api.pojo;

import java.util.List;

public class UserLoginstatusPojo {
	
	    private String loginStatus;
	    private String password;
	    private String roleId;
	    private String status;
	    private String userLoginEmail;
	    public UserLoginstatusPojo(String loginStatus, String password, String roleId, String status, String userLoginEmail) {
	        this.loginStatus = loginStatus;
	        this.password = password;
	        this.roleId = roleId;
	        this.status = status;
	        this.userLoginEmail = userLoginEmail;
	    }

	    // Getters and Setters
	    public String getLoginStatus() {
	        return loginStatus;
	    }

	    public void setLoginStatus(String loginStatus) {
	        this.loginStatus = loginStatus;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getRoleIds() {
	        return roleId;
	    }

	    public void setRoleIds(String roleId) {
	        this.roleId = roleId;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public String getUserLoginEmail() {
	        return userLoginEmail;
	    }

	    public void setUserLoginEmail(String userLoginEmail) {
	        this.userLoginEmail = userLoginEmail;
	    }
	}


