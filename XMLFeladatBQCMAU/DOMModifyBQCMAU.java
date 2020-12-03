package hu.domparse.bqcmau;

import java.io.*;
import java.text.ParseException;
import java.time.*;
import java.time.format.*;

import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.xml.sax.*;

public class DOMModifyBQCMAU {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException,
			XPathExpressionException, DOMException, ParseException {
		// XML fájl beolvasása
		File xml = new File("XMLBQCMAU.xml");

		// XML fájl DOM document való formában való alakítása
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(xml);

		DomModifier.modifyDom(document);

		DocumentTraversal traversal = (DocumentTraversal) document;

		// DOM TreeWalker inicializálása

		TreeWalker walker = traversal.createTreeWalker(document.getDocumentElement(),
				NodeFilter.SHOW_ELEMENT | NodeFilter.SHOW_TEXT, null, true);

		// a DOM bejárása
		DomTraverser.traverseLevel(walker, "");
	}

	private static class DomModifier {
		public static void modifyDom(Document document) throws XPathExpressionException, DOMException, ParseException {
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();

			// Az alkalmazasnál ahol 1000 a kezeltAllatId, ott módosítsa a gyogyszerId-t
			// '913'-ra
			Node csapat = (Node) xpath.evaluate("//alkalmazas[./@kezeltAllatId='1000']/@gyogyszerId", document,
					XPathConstants.NODE);

			csapat.setTextContent("913");

			// A Sentor nevu gyogyszer árának módosítása '50000'-re
			Node alkalmGyogyszer = (Node) xpath.evaluate("//alkalmGyogyszer[./megnevezes='Sentor']/ar", document,
					XPathConstants.NODE);

			int ar = Integer.parseInt(alkalmGyogyszer.getTextContent());
			ar = 50000;
			alkalmGyogyszer.setTextContent(Integer.toString(ar));

		}
	}

	private static class DomTraverser {
		public static void traverseLevel(TreeWalker walker, String indent) {
			// Menti a jelenlegi csomópontot
			Node node = walker.getCurrentNode();

			// Kiíratjuk
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				printElementNode(node, indent);
			} else {
				printTextNode(node, indent);
			}

			for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
				traverseLevel(walker, indent + "    ");
			}

			walker.setCurrentNode(node);
		}

		private static void printElementNode(Node node, String indent) {
			System.out.print(indent + node.getNodeName());

			printElementAttributes(node.getAttributes());
		}

		private static void printElementAttributes(NamedNodeMap attributes) {
			int length = attributes.getLength();

			// Attributum(ok) kiírása
			if (length > 0) {
				System.out.print(" [ ");

				for (int i = 0; i < length; i++) {
					Node attribute = attributes.item(i);

					System.out.printf("%s=%s%s", attribute.getNodeName(), attribute.getNodeValue(),
							i != length - 1 ? ", " : "");
				}

				System.out.println(" ]");
			} else {
				System.out.println();
			}
		}

		private static void printTextNode(Node node, String indent) {
			String content_trimmed = node.getTextContent().trim();

			if (content_trimmed.length() > 0) {
				System.out.print(indent);
				System.out.printf("{ %s }%n", content_trimmed);
			}
		}
	}
}