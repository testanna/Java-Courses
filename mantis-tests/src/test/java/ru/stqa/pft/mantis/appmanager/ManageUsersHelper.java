package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.mantis.model.User;

import java.util.ArrayList;
import java.util.List;

public class ManageUsersHelper extends HelperBase {

    public ManageUsersHelper(ApplicationManager app) {
        super(app);
    }

    public void goToManageUserPage(){
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
    }

    public List<User> allUsers(){
        List<WebElement> usernames = wd.findElements(By.xpath("//table/tbody/tr/td[1]"));
        List<WebElement> emails = wd.findElements(By.xpath("//table/tbody/tr/td[3]"));
        List<User> users = new ArrayList<User>();
        int userCount = usernames.size();

        for (int i =0; i < userCount; i++ ){
            String username = usernames.get(i).getText();
            String email = emails.get(i).getText();
            users.add(new User(username, email));
        }

        return users;
    }

    public User userForResetPassword(List<User> allUsers){
        List<User> usersForReset = new ArrayList<User>();
        for (User user : allUsers){
            if ((user.email.length() != 0) & (user.username.equals(app.getProperty("web.adminLogin")) == false)){
                    usersForReset.add(user);
                }
            }

        return usersForReset.iterator().next();
    }

    public void openUser(User user){
        click(By.xpath("//a[text()='" + user.username + "']"));
    }

    public void resetPassword(){
        click(By.xpath("//*[@id='manage-user-reset-form']/fieldset/span/input"));
    }


}
