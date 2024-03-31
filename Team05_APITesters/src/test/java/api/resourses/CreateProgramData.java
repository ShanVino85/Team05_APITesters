package api.resourses;

import java.io.IOException;

import api.pojo.CreateProgramPojo;
import api.utils.ExcelReader;

public class CreateProgramData {

	ExcelReader er = new ExcelReader();


    public CreateProgramPojo dataBuild() throws IOException {
           
            CreateProgramPojo cp = new CreateProgramPojo();
            
           cp.setProgramDescription(er.getCellData("program", 1, 0));
           cp.setProgramName(er.getCellData("program", 1, 1));
           cp.setProgramStatus(er.getCellData("program", 1, 2));
           
           return cp;
   }
}
