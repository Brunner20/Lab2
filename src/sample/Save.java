package sample;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javafx.collections.ObservableList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Save {
    public void parser(String path, ObservableList<Tournament> list){

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


            StreamResult file = new StreamResult(new File(path));
            transformer.transform(source, file);
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
       // language.appendChild(getTourElements(doc,  "name",String. tour.getName()));
        language.appendChild(getTourElements(doc,  "prize",String.valueOf(tour.getPrize())));

        return language;
    }



    private static Node getTourElements(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}
