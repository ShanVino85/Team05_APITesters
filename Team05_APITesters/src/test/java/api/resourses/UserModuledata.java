package api.resourses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import api.pojo.UserMPostLoginPojo;
import api.pojo.UserMPostMainPojo;
import api.pojo.UserMPostRoleMapPojo;
import api.utils.ExcelReader;
import api.utils.IdHolder;

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
		
		 UserMPostLoginPojo L=new UserMPostLoginPojo();
		 
		 L.setLoginStatus(er.getCellData("UserModule", 1, 7));
		 L.setPassword(er.getCellData("UserModule", 1, 8));
		 L.setUserLoginEmail(er.getCellData("UserModule", 1, 9));
		 Ul.setUserLogin(L);
		 
		 Ul.setUserMiddleName(er.getCellData("UserModule", 1, 10));
		 Ul.setUserPhoneNumber(er.getCellData("UserModule", 1, 11));
		 
		
		 UserMPostRoleMapPojo R=new UserMPostRoleMapPojo();
		 R.setRoleId(er.getCellData("UserModule", 1, 12));
		 R.setUserRoleStatus(er.getCellData("UserModule", 1, 13));
		 
		 List<UserMPostRoleMapPojo> MyList=new ArrayList<>();
			MyList.add(R);
		Ul.setUserRoleMaps(MyList);
		 
		 Ul.setUserTimeZone(er.getCellData("UserModule", 1, 14));
		 Ul.setUserVisaStatus(er.getCellData("UserModule", 1, 15));
		
		
		return Ul;
	}
	 
	 public String  UpdateUserRoleProgramBatchStatus(int PID,int BID ) {
		 
		 return"{\r\n  \"programId\":"+ PID+ ",\r\n  \"roleId\": \"R03\",\r\n  \"userRoleProgramBatches\": "
		 		+ "[\r\n    {\r\n      \"batchId\": "+BID+",\r\n      \"userRoleProgramBatchStatus\": \"Active\"\r\n    }\r\n  ]\r\n}\r\n";
		 
		/*return "{\r\n  \"programId\": programId,\r\n  \"roleId\": \""+userId+""\",\r\n  \"userRoleProgramBatches\":"
		 		+ " [\r\n    {\r\n      \"batchId\":batchId ,\r\n      \"userRoleProgramBatchStatus\": "
		 		+ "\"Active\"\r\n    }\r\n  ]\r\n}\r\n";
			return "{\r\n\"place_id\":\""+place_id+"\",\r\n\"address\":\"61,Downey place,USA\",\r\n\"key\":\"qaclick123\"\r\n}";*/
			
		}

}
