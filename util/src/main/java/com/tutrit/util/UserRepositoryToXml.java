package com.tutrit.util;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.stoservice.repository.UserRepository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class UserRepositoryToXml {

    private UserRepositoryToXml() {
        throw new IllegalStateException("Utility class");
    }

    public static void saveToXml(File directory) {
        directory.mkdirs();

        var userRepository = new UserRepository();
        Iterable<User> users = userRepository.findAll();
        for (User user : users) {
            saveOneUser(user, directory);
        }
    }

    private static void saveOneUser(User user, File directory) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element tagUser = document.createElement("user");
            document.appendChild(tagUser);

            tagUser.setAttribute("id", user.getId());

            Element tagName = document.createElement("name");
            tagName.setTextContent(user.getName());
            tagUser.appendChild(tagName);

            Element tagPhoneNumber = document.createElement("phoneNumber");
            tagPhoneNumber.setTextContent(user.getPhoneNumber());
            tagUser.appendChild(tagPhoneNumber);

            TransformerFactory factory = TransformerFactory.newInstance();
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
            Transformer transformer1 = factory.newTransformer();
            DOMSource source = new DOMSource(document);

            File xml = new File(directory, user.getId() + ".xml");
            if (xml.createNewFile()) {
                StreamResult result = new StreamResult(xml);
                transformer1.transform(source, result);
            }
        } catch (ParserConfigurationException | TransformerException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
