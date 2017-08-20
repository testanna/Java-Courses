package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by User on 020 20.08.17.
 */
public class ContactDeletionTest extends TestBase{
    @Test
    public void testContactDeletionFromList() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectFirstContact();
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().acceptDeletion();
    }

    @Test
    public void testContactDeletionFromForm(){
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectFirstContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().acceptDeletion();
    }
}
