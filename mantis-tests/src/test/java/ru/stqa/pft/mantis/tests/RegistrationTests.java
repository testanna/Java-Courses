package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by User on 007 07.10.17.
 */
public class RegistrationTests extends TestBase {
    @Test
    public void testRegistration(){
        app.registration().start("username", "username@localhost.localdomain");
    }
}
