package api.resourses;

import java.io.IOException;

import api.pojo.CreateBatchPojo;
import api.utils.ExcelReader;
/**
 * CreateBatchData class
 */
public class CreateBatchData {
	ExcelReader er = new ExcelReader();

	// Valid data for Post request
	public CreateBatchPojo dataBuild() throws IOException {

		CreateBatchPojo createBatchPojo = new CreateBatchPojo();

		createBatchPojo.setBatchDescription(er.getCellData("programBatchController", 1, 0));
		createBatchPojo.setBatchName(er.getCellData("programBatchController", 1, 1));
		createBatchPojo.setBatchStatus(er.getCellData("programBatchController", 1, 2));
		createBatchPojo.setBatchNoOfClasses(er.getCellData("programBatchController", 1, 3));

		return createBatchPojo;
	}
	
	// Valid data for Put request
	public CreateBatchPojo put_dataBuild() throws IOException {

		CreateBatchPojo createBatchPojo = new CreateBatchPojo();

		createBatchPojo.setBatchDescription(er.getCellData("programBatchController", 1, 4));
		createBatchPojo.setBatchName(er.getCellData("programBatchController", 1, 5));
		createBatchPojo.setBatchStatus(er.getCellData("programBatchController", 1, 6));
		createBatchPojo.setBatchNoOfClasses(er.getCellData("programBatchController", 1, 7));

		return createBatchPojo;
	}
}
