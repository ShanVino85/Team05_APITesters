package api.resourses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import api.pojo.UserMPostLoginPojo;
import api.pojo.UserMPostPojo;
import api.pojo.UserMPostRoleMapPojo;
import api.utils.ExcelReader;

public class UserPostReqdata {

	ExcelReader er = new ExcelReader();

	 public UserMPostPojo dataBuild() throws IOException {
		
		 UserMPostPojo userData = new UserMPostPojo();
		
		 userData.setUserComments(er.getCellData("UserPostData", 1, 0));
		 userData.setUserEduPg(er.getCellData("UserPostData", 1, 1));
		 userData.setUserEduUg(er.getCellData("UserPostData", 1, 2));
		 userData.setUserFirstName(er.getCellData("UserPostData", 1, 3));
		 userData.setUserLastName(er.getCellData("UserPostData", 1, 4));
		 userData.setUserLinkedinUrl(er.getCellData("UserPostData", 1, 5));
		 userData.setUserLocation(er.getCellData("UserPostData", 1, 6));
		 
		 UserMPostLoginPojo L1 = new UserMPostLoginPojo();
		 L1.setLoginStatus(er.getCellData("UserPostData", 1, 7));
		 L1.setPassword(er.getCellData("UserPostData", 1, 8));
		 L1.setUserLoginEmail(er.getCellData("UserPostData", 1, 9));
		 userData.setUserLogin(L1);
		 
		userData.setUserMiddleName(er.getCellData("UserPostData", 1, 10));
		userData.setUserPhoneNumber(er.getCellData("UserPostData", 1, 11));
		
		UserMPostRoleMapPojo urm =new UserMPostRoleMapPojo();
		urm.setRoleId(er.getCellData("UserPostData", 1, 12));
		urm.setUserRoleStatus(er.getCellData("UserPostData", 1, 13));
		List<UserMPostRoleMapPojo> userMpostRoleMapList = new ArrayList<UserMPostRoleMapPojo>();
		userMpostRoleMapList.add(urm);
		userData.setUserRoleMaps(userMpostRoleMapList);
		
		userData.setUserTimeZone(er.getCellData("UserPostData", 1, 14));
		userData.setUserVisaStatus(er.getCellData("UserPostData", 1, 15));
		return userData;
	}
}
