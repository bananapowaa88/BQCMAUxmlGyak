package hu.domparse.bqcmau;

import org.w3c.dom.*;


import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.*;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;



public class DOMModifyBQCMAU {
	public static void main(String args[]) throws ParserConfigurationException, IOException, SAXException, TransformerException {
	
		String filePath = "C:\\Users\\Pirella\\eclipse-workspace\\DOMParseBQCMAU\\src\\hu\\domparse\\bqcmau\\XMLBQCMAU.xml";
		File xmlFile = new File(filePath);
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			//XML bet�lt�se �s parseol�sa
			Document doc = dBuilder.parse(xmlFile);
			
			doc.getDocumentElement().normalize();
			
			//Elem m�dos�t�sa
			gazdaNevModify(doc);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Cim fgv
	public static String cimModositas() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Cim:");
		String id = sc.nextLine();
		return id;
	}
	
	//Gazda ID fgv
		public static String idGazda() {
			Scanner sc = new Scanner(System.in);
			System.out.print("Gazda ID:");
			String id = sc.nextLine();
			return id;
		}
	
	//F�ggv�ny, amely l�trehozza az �j filet
	public static void xmlFileIras(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("src/hu/domparse/nrthzz/BQCMAU.frissitett.xml"));
        transformer.transform(source, result);
	}
	
	
	//Megh�vja az xmlFileIras f�ggv�nyt, amellyel l�trehoz egy �j f�jlt
	public static void gazdaNevModify(Document doc) throws TransformerException {
		
		//El�sz�r beolvassuk az ID-t
		System.out.println("Gazda id: ");
		String beolvasottId = idGazda();

		//Gazda n�v
		Scanner sc = new Scanner(System.in);
		System.out.print("Gazda nev: ");
		String nev = sc.nextLine();

		//Megkeress�k azt a nodeot, aminek az ID-je egyezik a user �ltal megadott idvel
		NodeList nList = doc.getElementsByTagName("gazda");
		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				String id = element.getAttribute("id");

				if (id.equals(id)) {

					Node node1 = element.getElementsByTagName("nev").item(0);
					node1.setTextContent(nev);

					System.out.println("Siker!");
					//L�trehozzuk az �j xml-t
					xmlFileIras(doc);
				}
			}
		}
	}
}
