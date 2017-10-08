package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ResetPasswordHelper extends HelperBase {

    public ResetPasswordHelper(ApplicationManager app){
        super(app);
    }

    public void finish(String link, String password){
        wd.get(link);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button"));;
    }
}
