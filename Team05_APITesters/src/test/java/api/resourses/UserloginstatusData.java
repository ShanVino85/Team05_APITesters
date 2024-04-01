package api.resourses;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import api.pojo.UserLoginstatusPojo;
import api.utils.ExcelReader;

public class UserloginstatusData {

    public static List<UserLoginstatusPojo> buildUserRequestBodies(String sheetName) {
        List<UserLoginstatusPojo> requestBodies = new ArrayList<>();

        ExcelReader excelReader = new ExcelReader();
        try {
            int rowNum = 1; 
            while (true) {
                String loginStatus = excelReader.getCellData(sheetName, rowNum, 1);
                String password = excelReader.getCellData(sheetName, rowNum, 0);
                String roleId = excelReader.getCellData(sheetName, rowNum, 2);
                String status = excelReader.getCellData(sheetName, rowNum, 3);
                String userLoginEmail = excelReader.getCellData(sheetName, rowNum, 4);
                if (loginStatus.isEmpty() || password.isEmpty() || roleId.isEmpty() || status.isEmpty() || userLoginEmail.isEmpty()) {
                    break;
                }

                UserLoginstatusPojo requestBody = new UserLoginstatusPojo(loginStatus, password, roleId, status, userLoginEmail);
                requestBodies.add(requestBody);

                rowNum++; 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return requestBodies;
    }
}
