package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by User on 001 01.10.17.
 */
public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Groups groups() {
        List<GroupData> result;
        try (Session session = sessionFactory.openSession()){
            result = session.createQuery("from GroupData").list();
            for (GroupData group : result) {
                System.out.println(group);
            }
        }
        return new Groups(result);
    }

    public Contacts contacts() {
        List<ContactData> result;
        try (Session session = sessionFactory.openSession()){
            result = session.createQuery
                    ("from ContactData where deprecated='0000-00-00'").list();
            for (ContactData contact : result) {
                System.out.println(contact);
            }
        }
        return new Contacts(result);
    }

    public Contacts contactsForGroup() {
        int groupsSize = groups().size();
        Contacts allContacts = contacts();

        Contacts contactsForGroup = new Contacts();

        for (ContactData contact : allContacts) {
            if (contact.getGroups().size() < groupsSize ^ contact.getGroups().size() == 0) {
                contactsForGroup.add(contact);
            }
        }

        return contactsForGroup;
    }

    public ContactData getContactById(int id){
        List<ContactData> result;
        try (Session session = sessionFactory.openSession()) {
            result = session.createQuery
                    ("from ContactData where id='" + id + "'").list();
        }
        return new Contacts(result).iterator().next();
    }

    public Groups groupsForContacts(ContactData contact) {
        Set<Integer> groupIds = contact.getGroups().stream().map(GroupData::getId).collect(Collectors.toSet());

        List<GroupData> result;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<GroupData> criteria = builder.createQuery(GroupData.class);
            Root<GroupData> root = criteria.from(GroupData.class);
            root.fetch("contacts");

            if (!groupIds.isEmpty()) {
                criteria.where(builder.not(root.get("id").in(groupIds)));
            }

            result = session.createQuery(criteria).getResultList();
        }

        return new Groups(result);
    }

    public GroupData getGroupById(int id){
        List<GroupData> result;
        try (Session session = sessionFactory.openSession()){
            result = session.createQuery
                    ("from GroupData where id='" + id + "'").list();
        }
        return new Groups(result).iterator().next();
    }
}
