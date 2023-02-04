package com.tutrit.util;

import com.tutrit.persistence.core.bean.Customer;
import com.tutrit.stoservice.context.ApplicationContext;
import com.tutrit.stoservice.repository.CustomerRepository;
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

public class CustomerRepositoryToXml {

    private CustomerRepositoryToXml() {
        throw new IllegalStateException("Utility class");
    }

    public static void saveToXml(File directory) {
        directory.mkdirs();

        CustomerRepository customerRepository = ApplicationContext.get(CustomerRepository.class);
        Iterable<Customer> customers = customerRepository.findAll();
        for (Customer customer : customers) {
            saveOneUser(customer, directory);
        }
    }

    private static void saveOneUser(Customer customer, File directory) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element tagUser = document.createElement("customer");
            document.appendChild(tagUser);

            tagUser.setAttribute("id", customer.getId());

            Element tagName = document.createElement("name");
            tagName.setTextContent(customer.getName());
            tagUser.appendChild(tagName);

            Element tagCity = document.createElement("city");
            tagCity.setTextContent(customer.getCity());
            tagUser.appendChild(tagCity);

            Element tagPhoneNumber = document.createElement("phoneNumber");
            tagPhoneNumber.setTextContent(customer.getPhoneNumber());
            tagUser.appendChild(tagPhoneNumber);

            Element tagEmail = document.createElement("email");
            tagEmail.setTextContent(customer.getEmail());
            tagUser.appendChild(tagEmail);

            TransformerFactory factory = TransformerFactory.newInstance();
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
            Transformer transformer = factory.newTransformer();
            DOMSource source = new DOMSource(document);

            File xml = new File(directory, customer.getId() + ".xml");
            if (xml.createNewFile()) {
                StreamResult result = new StreamResult(xml);
                transformer.transform(source, result);
            }
        } catch (ParserConfigurationException | TransformerException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
