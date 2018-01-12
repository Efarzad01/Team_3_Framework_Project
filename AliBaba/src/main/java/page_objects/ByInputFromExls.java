package page_objects;

import base.CommonAPI;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utility.DataReader;

import java.io.IOException;

import static org.openqa.selenium.support.How.ID;
import static org.openqa.selenium.support.How.XPATH;

public class ByInputFromExls extends CommonAPI {
    @FindBy(how = XPATH, using = ".//*[@id='fm-login-id']")
    public static WebElement account;
    @FindBy(how = ID, using = "fm-login-password")
    public static WebElement password;
    @FindBy(className = "notice-descript")
    public static WebElement signInErrorMesage;

    DataReader dtr = new DataReader();
    //T3ALI_BE _TC01
    public String[] getDataCol2(String fileName) throws IOException {
        String path = "../AliBaba/data/" + fileName;
        String[] output = dtr.colReader(path, 2); //col 2 = email
        return output;
    }
    //T3ALI_BE _TC01
    public String[] getDataCol3(String fileName) throws IOException {
        String path = "../AliBaba/data/" + fileName;
        String[] output = dtr.colReader(path, 3); //col 3 = password
        return output;
    }
    //T3ALI_BE _TC01
    public String[] getAssertData(String fileName) throws IOException {
        String path = "../AliBaba/data/" + fileName;
        String[] output = dtr.colReader(path, 4);
        return output;
    }

    //T3ALI_BE _TC01 LogIn by using excel sheet data
    public String[] getVerificationValue(String fileName) throws IOException, InterruptedException {
        String[] col2Value = getDataCol2(fileName);
        String[] col3Value = getDataCol3(fileName);
        String[] actual = new String[col2Value.length];
        for (int i = 0; i < col2Value.length; i++) {
            sleepFor(1);
            inputValueInTextBoxByWebElement(account, col2Value[i]);
            inputValueInTextBoxByWebElement(password, col3Value[i]);
            sleepFor(1);
            // actual[i] = getCurrentPageTitle();
            actual[i] = getTextByWebElement(signInErrorMesage);
            clearInputBox(account);
            clearInputBox(password);
            sleepFor(1);
        }
        return actual;
    }
}
