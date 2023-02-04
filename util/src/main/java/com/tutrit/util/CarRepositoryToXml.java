package com.tutrit.util;

import com.tutrit.persistence.core.bean.Car;
import com.tutrit.stoservice.context.ApplicationContext;
import com.tutrit.stoservice.repository.CarRepository;
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

public class CarRepositoryToXml {

    private CarRepositoryToXml() {
        throw new IllegalStateException("Utility class");
    }

    public static void saveToXml(File directory) {
        directory.mkdirs();

        CarRepository carRepository = ApplicationContext.get(CarRepository.class);
        Iterable<Car> cars = carRepository.findAll();
        for (Car car : cars) {
            saveOneUser(car, directory);
        }
    }

    private static void saveOneUser(Car car, File directory) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element tagCar = document.createElement("car");
            document.appendChild(tagCar);
            tagCar.setAttribute("id", car.getId());

            Element tagOwner = document.createElement("owner");
            tagOwner.setTextContent(car.getOwner());
            tagCar.appendChild(tagOwner);

            Element tagVin = document.createElement("vin");
            tagVin.setTextContent(car.getVin());
            tagCar.appendChild(tagVin);

            Element tagPlateNumber = document.createElement("plateNumber");
            tagPlateNumber.setTextContent(car.getPlateNumber());
            tagCar.appendChild(tagPlateNumber);

            Element tagBrand = document.createElement("brand");
            tagBrand.setTextContent(car.getBrand());
            tagCar.appendChild(tagBrand);

            Element tagModel = document.createElement("model");
            tagModel.setTextContent(car.getModel());
            tagCar.appendChild(tagModel);

            Element tagGeneration = document.createElement("generation");
            tagGeneration.setTextContent(car.getGeneration());
            tagCar.appendChild(tagGeneration);

            Element tagModification = document.createElement("modification");
            tagModification.setTextContent(car.getModification());
            tagCar.appendChild(tagModification);

            Element tagEngine = document.createElement("engine");
            tagEngine.setTextContent(car.getEngine());
            tagCar.appendChild(tagEngine);

            Element tagYear = document.createElement("year");
            tagYear.setTextContent(String.valueOf(car.getYear()));
            tagCar.appendChild(tagYear);

            TransformerFactory factory = TransformerFactory.newInstance();
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
            Transformer transformer = factory.newTransformer();
            DOMSource source = new DOMSource(document);

            File xml = new File(directory, car.getId() + ".xml");
            if (xml.createNewFile()) {
                StreamResult result = new StreamResult(xml);
                transformer.transform(source, result);
            }
        } catch (ParserConfigurationException | TransformerException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
