package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase {

    public LoginHelper(ApplicationManager app) {
        super(app);
    }

    public void goToLoginPage(){
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    }

    public void enterLogin(String login){
        type(By.name("username"), login);
        click(By.cssSelector("input[value='Войти']"));
    }

    public void enterPassword(String password){
        type(By.id("password"), password);
        click(By.cssSelector("input[value='Войти']"));
    }

    public void authorization(String login, String password){
        goToLoginPage();
        enterLogin(login);
        enterPassword(password);
    }
}
