package api.resourses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import api.utils.ExcelReader;

public class UserControllerData {
    private ExcelReader er = new ExcelReader();

    public List<String> getInvalidUserIds() throws IOException {
        List<String> invalidUserIds = new ArrayList<>();
        int rowNumber = 1; 

        while (true) {
            String userId;
            try {
                userId = er.getCellData("UserControllerTestData", rowNumber, 0);
            } catch (NullPointerException e) {
                System.err.println(" Null value encountered while reading cell data so terminating request");
                break;
            }

            if (userId == null || userId.isEmpty()) {
                break; 
            }

            System.out.println("Reading user ID: " + userId); 
            invalidUserIds.add(userId);
            rowNumber++; 
        }

        return invalidUserIds;
    }
}
