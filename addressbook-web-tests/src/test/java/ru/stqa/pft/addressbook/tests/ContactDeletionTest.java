package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by User on 020 20.08.17.
 */
public class ContactDeletionTest extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().list().size() == 0){
            app.contact().create( new ContactData().withFirstName("Name").withLastName("Last").withGroup("test1"));
        }
    }

    @Test
    public void testContactDeletionFromList() {
        List<ContactData> before = app.contact().list();
        int index = before.size()-1;
        app.contact().delete(index);
        app.goTo().homePage();

        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size()-1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }
}
