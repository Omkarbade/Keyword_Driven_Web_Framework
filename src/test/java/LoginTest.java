import Utility.KeywordDrivenEngine;
import org.testng.annotations.Test;

public class LoginTest {
    public KeywordDrivenEngine keyword;

    //Method: To Test Login Data
    @Test
    public void loginTest() {

        keyword = new KeywordDrivenEngine();
        keyword.startExecution("LoginTestData");
    }
}
