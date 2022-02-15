import com.appliedselenium.base.CheckLogin;
import com.appliedselenium.base.TestBase;

import com.appliedselenium.base.Utils.ReadExcel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @DataProvider(name="Auth")
    public Object[][] LoginDetails(){
        ReadExcel read = new ReadExcel();
        String filePath = System.getProperty("user.dir")+ "\\src\\main\\resources\\FBEdata.xlsx";
        Object[][] data = read.getExcelData(filePath,"login");
        return data;

    }

    // @Test annotation is used to create a test method(dataProvider = "Auth")*/
    @Test(dataProvider = "Auth")
    public void TestingLogin(String username, String password){
        CheckLogin login = new CheckLogin();
        login.testLogin(username,password);


    }
}
