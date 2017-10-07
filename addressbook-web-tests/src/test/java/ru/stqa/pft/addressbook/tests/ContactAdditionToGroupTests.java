package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAdditionToGroupTests extends TestBase {

    private GroupData group;
    private ContactData contact;

    @BeforeMethod
    public void ensurePreconditions(){
        Contacts contactsForGroup = app.db().contactsForGroup();
        if (contactsForGroup.size() == 0){
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstName("Name").withLastName("Last"));
            contact = app.db().getContactById(app.db().contacts().stream().mapToInt((c) -> c.getId()).max().getAsInt());
        } else {
            contact = contactsForGroup.iterator().next();
        }

        Groups groupsForContacts = app.db().groupsForContacts(contact);
        if (groupsForContacts.size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
            group = app.db().getGroupById(app.db().groups().stream().mapToInt((g) -> g.getId()).max().getAsInt());
        } else {
            group = groupsForContacts.iterator().next();
        }
    }

    @Test
    public void testContactAdditionToGroup(){
        Groups groupsBefore = contact.getGroups();
        app.goTo().homePage();
        app.contact().addToGroup(contact, group);
        Groups groupsAfter = app.db().getContactById(contact.getId()).getGroups();
        assertThat(groupsAfter, equalTo(groupsBefore.withAdded(group)));
    }
}
