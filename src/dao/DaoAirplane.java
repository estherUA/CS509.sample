package dao;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import airplane.Airplane;
import airplane.Airplanes;

public class DaoAirplane {
    public static Airplanes addAll(String xmlAirplanes) throws NullPointerException {
        Airplanes airplanes = new Airplanes();

        Document docAirplanes = buildDomDoc (xmlAirplanes);
        NodeList nodesAirplanes = docAirplanes.getElementsByTagName("Airplane");

        for (int i =0; i < nodesAirplanes.getLength(); i++) {
            Element elementAirplane = (Element) nodesAirplanes.item(i);
            Airplane airplane = buildAirplane (elementAirplane);

            if (airplane.isValid()) {
                airplanes.add(airplane);
            }
        }
        return airplanes;
    }
    static private Airplane buildAirplane (Node nodeAirplane) {
        Airplane airplane = new Airplane();

        String manufacturer;
        String model;
        int firstclass;
        int coach;

        //the airplane element has attributes of Manufacturer and model
        Element elementAirplane = (Element) nodeAirplane;
        manufacturer = elementAirplane.getAttributeNode("Manufacturer").getValue();
        model = elementAirplane.getAttributeNode("Model").getValue();

        //the firstclass and coach seats are child elements
        Element elementSeating;
        elementSeating = (Element)elementAirplane.getElementsByTagName("FirstClassSeats").item(0);
        firstclass = Integer.parseInt(getCharacterDataFromElement(elementSeating));

        elementSeating = (Element)elementAirplane.getElementsByTagName("CoachSeats").item(0);
        coach = Integer.parseInt(getCharacterDataFromElement(elementSeating));

        /**
         * Update the airplane object with values from xml node
         */
        airplane.manufacturer(manufacturer);
        airplane.model(model);
        airplane.firstclass(firstclass);
        airplane.coach(coach);

        return airplane;

    }
    static private Document buildDomDoc (String xmlString) {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            InputSource inputSource = new InputSource();
            inputSource.setCharacterStream(new StringReader(xmlString));

            return docBuilder.parse(inputSource);
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        catch (SAXException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }


}
