package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by User on 020 20.08.17.
 */
public class SessionHelper extends HelperBase {


    public SessionHelper(WebDriver wd) {

        super(wd);
    }

    public void login(String username, String password) throws InterruptedException {
        Thread.sleep(10000);
        type(By.name("user"), username);
        type(By.name("pass"), password);
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
}
