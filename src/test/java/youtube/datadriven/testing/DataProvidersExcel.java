package youtube.datadriven.testing;

import org.testng.annotations.DataProvider;


public class DataProvidersExcel {
	
	@DataProvider(name = "TutorialsNinjaDATASupplier")
	public Object[][] getTNDataSupply() {
		Object[][] data = ExcelData.getTestDataFromExcel("TNLogin");
		return data;
	}
	
	@DataProvider(name = "RediffDATASupplier")
	public Object[][] getRediffDataSupply() {
		Object[][] data = ExcelData.getTestDataFromExcel("RediffLogin");
		return data;
	}
	

}
