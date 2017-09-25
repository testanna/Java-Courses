package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().all().size() == 0){
            app.contact().create( new ContactData().withFirstName("Name").withGroup("test1").withLastName("Last")
                    .withAddress("Penza"));
        }
    }

    @Test
    public void testContactAddresses()  {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }


}
