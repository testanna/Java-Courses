package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 027 27.09.17.
 */
public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Current file")
    public String file;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        saveAsXml(contacts, new File(file));
    }


    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i<count; i++){
            contacts.add(new ContactData().withFirstName(String.format("First%s", i))
                    .withLastName(String.format("Last%s", i)).withAddress(String.format("Address%s", i))
                    .withEmail1(String.format("Email1%s", i)).withEmail2(String.format("Email2%s", i))
                    .withEmail3(String.format("Email3%s", i)).withHomePhone(String.format("112233%s", i))
                    .withMobilePhone(String.format("445566%s", i)).withWorkPhone(String.format("778899%s", i)));

                    //.withGroup("test1"));
        }
        return contacts;
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        String xml = xStream.toXML(contacts);
        try(Writer writer = new FileWriter(file)){
            writer.write(xml);
        }
    }
}
