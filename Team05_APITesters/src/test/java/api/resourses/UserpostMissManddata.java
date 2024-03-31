package api.resourses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import api.pojo.UserMPostLoginPojo;
import api.pojo.UserMPostPojo;
import api.pojo.UserMPostRoleMapPojo;
import api.utils.ExcelReader;

public class UserpostMissManddata {
	ExcelReader er = new ExcelReader();

	 public UserMPostPojo dataBuild() throws IOException {
		
		 UserMPostPojo userData = new UserMPostPojo();
		
		 userData.setUserComments(er.getCellData("UserPostData", 3, 0));
		 userData.setUserEduPg(er.getCellData("UserPostData", 3, 1));
		 userData.setUserEduUg(er.getCellData("UserPostData", 3, 2));
		 userData.setUserFirstName(er.getCellData("UserPostData", 3, 3));
		 userData.setUserLastName(er.getCellData("UserPostData", 3, 4));
		 userData.setUserLinkedinUrl(er.getCellData("UserPostData", 3, 5));
		 userData.setUserLocation(er.getCellData("UserPostData", 3, 6));
		 
		 UserMPostLoginPojo L1 = new UserMPostLoginPojo();
		 L1.setLoginStatus(er.getCellData("UserPostData", 3, 7));
		 L1.setPassword(er.getCellData("UserPostData", 3, 8));
		 L1.setUserLoginEmail(er.getCellData("UserPostData", 3, 9));
		 userData.setUserLogin(L1);
		 
		userData.setUserMiddleName(er.getCellData("UserPostData", 3, 10));
		userData.setUserPhoneNumber(er.getCellData("UserPostData", 3, 11));
		
		UserMPostRoleMapPojo urm =new UserMPostRoleMapPojo();
		urm.setRoleId(er.getCellData("UserPostData", 3, 12));
		urm.setUserRoleStatus(er.getCellData("UserPostData", 3, 13));
		List<UserMPostRoleMapPojo> userMpostRoleMapList = new ArrayList<UserMPostRoleMapPojo>();
		userMpostRoleMapList.add(urm);
		userData.setUserRoleMaps(userMpostRoleMapList);
		
		userData.setUserTimeZone(er.getCellData("UserPostData", 3, 14));
		userData.setUserVisaStatus(er.getCellData("UserPostData", 3, 15));
		return userData;
    }
}