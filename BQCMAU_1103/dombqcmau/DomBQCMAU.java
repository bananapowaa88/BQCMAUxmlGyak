package dombqcmau;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class DomBQCMAU {

    public static void main(String argv[]) throws SAXException,
            IOException, ParserConfigurationException {

        File xmlFile = new File("szemelyek.xml");
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("szemely");

        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);

            System.out.println("\nCurrent Element: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element elem = (Element) nNode;

                String uid = elem.getAttribute("id");

                Node node1 = elem.getElementsByTagName("nev").item(0);
                String fname = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("kor").item(0);
                String lname = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("varos").item(0);
                String occup = node3.getTextContent();

                System.out.printf("Személy id: %s%n", uid);
                System.out.printf("Név: %s%n", fname);
                System.out.printf("Kor: %s%n", lname);
                System.out.printf("Város: %s%n", occup);
            }
        }
    }
}