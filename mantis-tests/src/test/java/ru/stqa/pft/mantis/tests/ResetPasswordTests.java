package ru.stqa.pft.mantis.tests;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.User;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {
    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testResetPassword() throws IOException, InterruptedException, MessagingException {
        app.login().authorization(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.manageUser().goToManageUserPage();
        List<User> allUsers = app.manageUser().allUsers();
        User userForResetPassword = app.manageUser().userForResetPassword(allUsers);
        app.manageUser().openUser(userForResetPassword);
        app.manageUser().resetPassword();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 30000);
        String resetLink = findResetLink(mailMessages, userForResetPassword.email);
        String newPassword = "password12";
        app.resetPassword().finish(resetLink, newPassword);
        assertTrue(app.newSession().login(userForResetPassword.username, newPassword));

    }

    private String findResetLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
