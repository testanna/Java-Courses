package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by User on 020 20.08.17.
 */
public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification(){
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectFirstContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Name1", "Last1", "Penza1",
                "666666","email@test.ru"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToContactPage();


    }
}
