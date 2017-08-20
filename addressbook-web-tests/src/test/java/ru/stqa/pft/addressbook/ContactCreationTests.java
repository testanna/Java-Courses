package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        initContactCreation();
        fillContactForm(new ContactData("Name", "Last", "Penza", "666666",
                "email@test.ru"));
        submitContactCreation();
    }

}
