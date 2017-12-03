package reservation;

import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import dao.ServerInterface;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ReserveFlight {
    public ReserveFlight() {
    }

    public static String makeFlightXML (ArrayList<String> flightnumber, ArrayList<String> seating) throws Exception{
        //int no = 3;

        int no = flightnumber.size();
        String root = "Flights";
        //abstract class, can't not be extentiated ??? must use as static method? more research needed!
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();
        Element rootElement = document.createElement(root);
        document.appendChild(rootElement);

        for(int i=0 ; i < flightnumber.size(); i++) {
            String element = "Flight";
            Element em = document.createElement(element);
            em.setAttribute("number", flightnumber.get(i));
            em.setAttribute("seating", seating.get(i));
            rootElement.appendChild(em);

        }
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            //StreamResult result = new StreamResult(System.out);
            //transformer.transform(source, result);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            //TransformerFactory tf = TransformerFactory.newInstance();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.transform(source, result);
            writer.flush();
            return writer.toString();
        }
        catch(TransformerException ex){
            ex.printStackTrace();
            return null;
        }

        //return prettyPrint(document);
       // return document;




    }
    public static final String prettyPrint(Document xml) throws Exception{
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        Writer out = new StringWriter();
        tf.transform(new DOMSource(xml), new StreamResult(out));
        System.out.println(out.toString());
        return out.toString();
    }


    public static void makeReservation(String teamName,ArrayList<String> flightnumber, ArrayList<String> seating ) throws Exception {
        ServerInterface.INSTANCE.lock(teamName);
        String xmlflights = makeFlightXML(flightnumber, seating);
        ServerInterface.INSTANCE.reserveFlight(teamName, xmlflights);
        ServerInterface.INSTANCE.unlock(teamName);

    }

}
