package com.tutrit.util;

import com.tutrit.persistence.core.bean.Engineer;
import com.tutrit.stoservice.context.ApplicationContext;
import com.tutrit.stoservice.repository.EngineerRepository;
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

public class EngineerRepositoryToXml {

    private EngineerRepositoryToXml() {
        throw new IllegalStateException("Utility class");
    }

    public static void saveToXml(File directory) {
        directory.mkdirs();

        EngineerRepository customerRepository = ApplicationContext.get(EngineerRepository.class);
        Iterable<Engineer> engineers = customerRepository.findAll();
        for (Engineer engineer : engineers) {
            saveOneUser(engineer, directory);
        }
    }

    private static void saveOneUser(Engineer engineer, File directory) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element tagEngineer = document.createElement("engineer");
            document.appendChild(tagEngineer);

            tagEngineer.setAttribute("id", engineer.getId());

            Element tagFirstName = document.createElement("firstName");
            tagFirstName.setTextContent(engineer.getFirstName());
            tagEngineer.appendChild(tagFirstName);

            Element tagLastName = document.createElement("lastName");
            tagLastName.setTextContent(engineer.getLastName());
            tagEngineer.appendChild(tagLastName);

            Element tagFunction = document.createElement("function");
            tagFunction.setTextContent(engineer.getFunction());
            tagEngineer.appendChild(tagFunction);

            Element tagCategory = document.createElement("category");
            tagCategory.setTextContent(engineer.getCategory());
            tagEngineer.appendChild(tagCategory);

            Element tagEducation = document.createElement("education");
            tagEducation.setTextContent(engineer.getEducation());
            tagEngineer.appendChild(tagEducation);

            Element tagExperience = document.createElement("experience");
            tagExperience.setTextContent(String.valueOf(engineer.getExperience()));
            tagEngineer.appendChild(tagExperience);

            Element tagGeneralExperience = document.createElement("generalExperience");
            tagGeneralExperience.setTextContent(String.valueOf(engineer.getGeneralExperience()));
            tagEngineer.appendChild(tagGeneralExperience);

            TransformerFactory factory = TransformerFactory.newInstance();
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
            Transformer transformer = factory.newTransformer();
            DOMSource source = new DOMSource(document);

            File xml = new File(directory, engineer.getId() + ".xml");
            if (xml.createNewFile()) {
                StreamResult result = new StreamResult(xml);
                transformer.transform(source, result);
            }
        } catch (ParserConfigurationException | TransformerException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
