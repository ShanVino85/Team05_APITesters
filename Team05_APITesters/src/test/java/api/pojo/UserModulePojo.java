package api.pojo;
public class UserModulePojo {
	public class UpdateAdminLoginStatus {
	    private String updateloginStatus;
	    private String updatepassword;
	    private String updateroleId;
	    private String updatestatus;
	    private String updateLoginEmail;
	    public String getLoginStatus() {
	        return updateloginStatus;
	    }

	    public void setLoginStatus(String updatepassword) {
	        this.updateloginStatus = updatepassword;
	    }

	    public String getupdatePassword() {
	        return updatepassword;
	    }

	    public void setPassword(String updatepassword) {
	        this.updatepassword = updatepassword;
	    }

	    public String getupdateRoleId() {
	        return updateroleId;
	    }

	    public void setRoleIds(String updateroleId) {
	        this.updateroleId = updateroleId;
	    }

	    public String getupdatestatus() {
	        return updatestatus;
	    }

	    public void setStatus(String updatestatus) {
	        this.updatestatus = updatestatus;
	    }

	    public String getUserLoginEmail() {
	        return updateLoginEmail;
	    }

	    public void setUserLoginEmail(String updateLoginEmail) {
	        this.updateLoginEmail = updateLoginEmail;
	    }
	}


}
