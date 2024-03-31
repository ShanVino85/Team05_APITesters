package api.resourses;

import java.io.IOException;

import api.pojo.CreateBatchPojo;
import api.utils.ExcelReader;

public class CreateBatchData {

	ExcelReader er = new ExcelReader();

	public CreateBatchPojo dataBuild() throws IOException {

		CreateBatchPojo createBatchPojo = new CreateBatchPojo();

		createBatchPojo.setBatchDescription(er.getCellData("programBatchController", 1, 0));
		createBatchPojo.setBatchName(er.getCellData("programBatchController", 1, 1));
		createBatchPojo.setBatchStatus(er.getCellData("programBatchController", 1, 2));
		createBatchPojo.setBatchNoOfClasses(er.getCellData("programBatchController", 1, 3));

		return createBatchPojo;
	}
}
