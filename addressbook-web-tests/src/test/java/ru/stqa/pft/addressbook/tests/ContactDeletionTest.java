package ru.stqa.pft.addressbook.tests;

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
        app.getContactHelper().selectFirstContact();
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().acceptDeletion();
    }

    @Test
    public void testContactDeletionFromForm(){
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Name", "Last", null,
                    null,null, "test1"));
        }
        app.getContactHelper().selectFirstContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().deleteSelectedContacts();
    }
}
