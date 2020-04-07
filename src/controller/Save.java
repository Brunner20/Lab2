package controller;
import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import model.Tournament;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Save {
    public void parser(File file, List<Tournament> list){

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();


            Document doc = builder.newDocument();
            Element rootElement = doc.createElementNS("","tounaments");
            doc.appendChild(rootElement);

            for(Tournament tour: list)
            rootElement.appendChild(getTour(doc, tour));



            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);


            StreamResult file1 = new StreamResult(file);
            transformer.transform(source, file1);
            System.out.println("Создание XML файла закончено");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private static Node getTour(Document doc, Tournament tour) {
        Element language = doc.createElement("tournament");

        language.appendChild(getTourElements(doc,  "nameOfTournament", tour.getNameOfTournament()));
        language.appendChild(getTourElements(doc,  "typeOfSport", tour.getTypeOfSport()));
        language.appendChild(getTourElements(doc,  "dateOfTournament", tour.getDateOfTournament().toString()));
        language.appendChild(getTourElements(doc,  "lastName", tour.getWinner().getLastName()));
        language.appendChild(getTourElements(doc,  "firstName", tour.getWinner().getFirstName()));
        language.appendChild(getTourElements(doc,  "middleName", tour.getWinner().getMiddleName()));
        language.appendChild(getTourElements(doc,  "prize",String.valueOf(tour.getPrize())));

        return language;
    }



    private static Node getTourElements(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}
