package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by User on 020 20.08.17.
 */
public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification(){
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Name", "Last", null,
                    null,null, "test1"));
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectFirstContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Name1", "Last1", "Penza1",
                "666666","email@test.ru", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToContactPage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);


    }
}
