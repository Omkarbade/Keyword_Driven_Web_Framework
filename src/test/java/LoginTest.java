import Base.Base;
import engine.KeyWordEngine;


import org.testng.annotations.Test;


public class LoginTest extends Base {
    public KeyWordEngine keyWordEngine;

//login page test
    @Test
    public void loginTest(){
        keyWordEngine = new KeyWordEngine();
        keyWordEngine.startExecution("Sheet1");

    }

// signup page test
    @Test
    public void signUpTest() {
        keyWordEngine = new KeyWordEngine();
        keyWordEngine.startExecution("Sheet2");
    }


}
