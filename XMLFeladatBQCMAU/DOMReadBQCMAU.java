package hu.domparse.bqcmau;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class DOMReadBQCMAU {
	public static void main(String[] args)
			throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
		try {
			File inputFile = new File(
					"C:\\Users\\Pirella\\eclipse-workspace\\DOMParseBQCMAU\\src\\hu\\domparse\\bqcmau\\XMLBQCMAU.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			// Gyökérelem név kiíratása
			System.out.println("Egyed neve: \t" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("telep");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				// Telep egyed név kiíratása
				System.out.println("\nEgyed neve: \t" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					// Telep egyed elemeinek kiírása
					System.out.println("telep id: " + eElement.getAttribute("id"));
				}
			}

			nList = doc.getElementsByTagName("cim");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				System.out.println("\nElem neve: \t" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					// cim elem elemeinek kiírása
					System.out.println("irSzam: \t" + eElement.getElementsByTagName("irSzam").item(i).getTextContent());
					System.out.println(
							"telepules: \t" + eElement.getElementsByTagName("telepules").item(i).getTextContent());
					System.out.println("utca: \t\t" + eElement.getElementsByTagName("utca").item(i).getTextContent());
				}
			}

			nList = doc.getElementsByTagName("gazda");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				// Gazda egyed név kiíratása
				System.out.println("\nEgyed neve: \t" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					// gazda egyed elemeinek kiírása
					System.out.println("gazda id: \t" + eElement.getAttribute("id"));
					System.out.println("nev: \t\t" + eElement.getElementsByTagName("nev").item(0).getTextContent());
					System.out
							.println("fizetes: \t" + eElement.getElementsByTagName("fizetes").item(0).getTextContent());
					System.out.println("kor: \t\t" + eElement.getElementsByTagName("kor").item(0).getTextContent());
				}
			}

			nList = doc.getElementsByTagName("kezeltAllat");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				// KezeltAllat egyed név kiíratása
				System.out.println("\nEgyed neve: \t" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					// kezeltAllat egyed elemeinek kiírása
					System.out.println("kezeltAllatId: \t" + eElement.getAttribute("id"));
					System.out.println(
							"betegseg: \t\t" + eElement.getElementsByTagName("betegseg").item(0).getTextContent());
					System.out.println(
							"allatNev: \t\t" + eElement.getElementsByTagName("allatNev").item(0).getTextContent());
					System.out.println("eloallitottTermek: \t"
							+ eElement.getElementsByTagName("eloallitottTermek").item(0).getTextContent());
				}
			}

			nList = doc.getElementsByTagName("alkalmGyogyszer");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				// alkalmGyogyszer egyed név kiíratása
				System.out.println("\nEgyed neve: \t" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					// alkalmGyogyszer egyed elemeinek kiírása
					System.out.println("gyogyszerId: \t" + eElement.getAttribute("id"));
					System.out.println(
							"mennyiseg: \t" + eElement.getElementsByTagName("mennyiseg").item(0).getTextContent());
					System.out.println("ar: \t\t" + eElement.getElementsByTagName("ar").item(0).getTextContent());
					System.out.println(
							"megnevezes: \t" + eElement.getElementsByTagName("megnevezes").item(0).getTextContent());
				}
			}

			nList = doc.getElementsByTagName("alkalmazas");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				// alkalmazas kapcsolat név kiíratása
				System.out.println("\nEgyed neve: \t" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					// alkalmGyogyszer kapcsolat elemeinek kiírása
					System.out.println("kezeltAllatId: \t\t" + eElement.getAttribute("kezeltAllatId")
							+ "\ngyogyszerId \t\t" + eElement.getAttribute("gyogyszerId"));
					System.out.println("alkalmazasModja: \t"
							+ eElement.getElementsByTagName("alkalmazasModja").item(0).getTextContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}