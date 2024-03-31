package api.resourses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import api.pojo.UserMPostLoginPojo;
import api.pojo.UserMPostMainPojo;
import api.pojo.UserMPostRoleMapPojo;
import api.pojo.UserMPutPojo;
import api.utils.ExcelReader;

public class UserModuledata {

	ExcelReader er = new ExcelReader();

	public UserMPostMainPojo usermodulePostdata() throws IOException {

		UserMPostMainPojo Ul = new UserMPostMainPojo();

		Ul.setUserComments(er.getCellData("UserModule", 1, 0));
		Ul.setUserEduUg(er.getCellData("UserModule", 1, 1));
		Ul.setUserEduPg(er.getCellData("UserModule", 1, 2));
		Ul.setUserFirstName(er.getCellData("UserModule", 1, 3));
		Ul.setUserLastName(er.getCellData("UserModule", 1, 4));
		Ul.setUserLinkedinUrl(er.getCellData("UserModule", 1, 5));
		Ul.setUserLocation(er.getCellData("UserModule", 1, 6));

		UserMPostLoginPojo L = new UserMPostLoginPojo();

		L.setLoginStatus(er.getCellData("UserModule", 1, 7));
		L.setPassword(er.getCellData("UserModule", 1, 8));
		L.setUserLoginEmail(er.getCellData("UserModule", 1, 9));
		Ul.setUserLogin(L);

		Ul.setUserMiddleName(er.getCellData("UserModule", 1, 10));
		Ul.setUserPhoneNumber(er.getCellData("UserModule", 1, 11));

		UserMPostRoleMapPojo R = new UserMPostRoleMapPojo();
		R.setRoleId(er.getCellData("UserModule", 1, 12));
		R.setUserRoleStatus(er.getCellData("UserModule", 1, 13));

		List<UserMPostRoleMapPojo> MyList = new ArrayList<>();
		MyList.add(R);
		Ul.setUserRoleMaps(MyList);

		Ul.setUserTimeZone(er.getCellData("UserModule", 1, 14));
		Ul.setUserVisaStatus(er.getCellData("UserModule", 1, 15));

		return Ul;
	}
	

	// ----update user by userID----
	public UserMPutPojo userPUTdata() throws IOException {

		UserMPutPojo Uput = new UserMPutPojo();

		Uput.setUserFirstName(er.getCellData("UserPUT", 1, 0));
		Uput.setUserLastName(er.getCellData("UserPUT", 1, 1));
		Uput.setUserPhoneNumber(er.getCellData("UserPUT", 1, 2));
		Uput.setUserTimeZone(er.getCellData("UserPUT", 1, 3));
		Uput.setUserVisaStatus(er.getCellData("UserPUT", 1, 4));

		return Uput;
	}

	// ----update user by rolestatus-----
	public UserMPostRoleMapPojo userPUTRoleStatusdata() throws IOException {

		UserMPostRoleMapPojo role = new UserMPostRoleMapPojo();
		role.setRoleId(er.getCellData("UserPUT", 1, 5));
		role.setUserRoleStatus(er.getCellData("UserPUT", 1, 6));

		return role;
	}

}
