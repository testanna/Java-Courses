package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by User on 020 20.08.17.
 */
public class ContactDeletionTest extends TestBase{
    @Test
    public void testContactDeletionFromList() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Name", "Last", null,
                    null,null, "test1"));
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact(before-1);
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().acceptDeletion();
        app.getNavigationHelper().goToHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before-1);
    }

    @Test
    public void testContactDeletionFromForm(){
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Name", "Last", null,
                    null,null, "test1"));
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact(before-1);
        app.getContactHelper().initContactModification();
        app.getContactHelper().deleteSelectedContacts();
        app.getNavigationHelper().goToHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before-1);
    }
}
