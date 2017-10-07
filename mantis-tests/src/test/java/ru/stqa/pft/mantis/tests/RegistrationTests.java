package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

/**
 * Created by User on 007 07.10.17.
 */
public class RegistrationTests extends TestBase {
    @Test
    public void testRegistration() throws InterruptedException {
        app.registration().start("username", "username@localhost.localdomain");
        sleep(3000);
    }
}
