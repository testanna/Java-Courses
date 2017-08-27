package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToGroupPage();
        app.getContactHelper().createContact(new ContactData("Name", "Last", null,
                null,null, "test1"));
    }

}
