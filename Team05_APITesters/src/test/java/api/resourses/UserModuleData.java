package api.resourses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import api.pojo.UserModulePojo;
import api.utils.ExcelReader;

public class UserModuleData {
    private ExcelReader excelReader = new ExcelReader();

    public List<UserModulePojo.UpdateAdminLoginStatus> readUpdateAdminLoginStatusData(String sheetName) throws IOException {
        List<UserModulePojo.UpdateAdminLoginStatus> adminLoginStatusList = new ArrayList<>();
        int rowNumber = 1;

        // Fetch the first row to determine the column indices
        List<String> columnHeaders = new ArrayList<>();
        for (int col = 0; ; col++) {
            String columnHeader = excelReader.getCellData(sheetName, 0, col);
            if (columnHeader.isEmpty()) {
                break; // Stop when the column header is empty
            }
            columnHeaders.add(columnHeader);
        }

        // Continue fetching data until all rows are empty
        while (true) {
            // Fetch data for each column in the current row
            List<String> rowData = new ArrayList<>();
            for (int col = 0; col < columnHeaders.size(); col++) {
                String cellData = excelReader.getCellData(sheetName, rowNumber, col);
                rowData.add(cellData);
            }

            // Check if all columns in the current row are empty
            boolean allEmpty = rowData.stream().allMatch(String::isEmpty);
            if (allEmpty) {
                // If all columns are empty, stop fetching rows
                break;
            }

            // Create an instance of the outer class UserModulePojo
            UserModulePojo pojoInstance = new UserModulePojo();

            // Create UpdateAdminLoginStatus object using the outer class instance
            UserModulePojo.UpdateAdminLoginStatus adminLoginStatus = pojoInstance.new UpdateAdminLoginStatus();
            adminLoginStatus.setLoginStatus(rowData.get(columnHeaders.indexOf("updateloginStatus")));
            adminLoginStatus.setPassword(rowData.get(columnHeaders.indexOf("updatepassword")));
            adminLoginStatus.setRoleIds(rowData.get(columnHeaders.indexOf("updateroleId")));
            adminLoginStatus.setStatus(rowData.get(columnHeaders.indexOf("updatestatus")));
            adminLoginStatus.setUserLoginEmail(rowData.get(columnHeaders.indexOf("updateLoginEmail")));
            adminLoginStatusList.add(adminLoginStatus);

            // Move to the next row
            rowNumber++;
        }

        return adminLoginStatusList;
    }
}
