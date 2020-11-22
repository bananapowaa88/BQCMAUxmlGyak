package hu.meiit.xpathparsebqcmau;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;

public class XpathParseBQCMAU {

	public static void main(String[] args)
			throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse("C:\\Users\\Pirella\\eclipse-workspace\\BQCMAU_1110\\src\\hu\\meiit\\xpathparsebqcmau\\studentBQCMAU.xml");

		XPathFactory xpathfactory = XPathFactory.newInstance();
		XPath xpath = xpathfactory.newXPath();

		System.out.println("Current Element : student" + "\n");

		
		XPathExpression firstNameExpr = xpath.compile("//student/firstname/text()");
		XPathExpression lastNameExpr = xpath.compile("//student/lastname/text()");
		XPathExpression nickNameExpr = xpath.compile("//student/nickname/text()");
		XPathExpression marksExpr = xpath.compile("//student/marks/text()");

		Object firstNameResult = firstNameExpr.evaluate(doc, XPathConstants.NODESET);
		Object lastNameResult = lastNameExpr.evaluate(doc, XPathConstants.NODESET);
		Object nickNameResult = nickNameExpr.evaluate(doc, XPathConstants.NODESET);
		Object marksResult = marksExpr.evaluate(doc, XPathConstants.NODESET);

		NodeList firstNameNodes = (NodeList) firstNameResult;
		NodeList lastNameNodes = (NodeList) lastNameResult;
		NodeList nickNodes = (NodeList) nickNameResult;
		NodeList marksNodes = (NodeList) marksResult;
		

		for (int i = 0; i < firstNameNodes.getLength(); i++) {
			
			System.out.println("Student rollno : ");
			System.out.println("First Name : " + firstNameNodes.item(i).getNodeValue());
			System.out.println("Last Name : " + lastNameNodes.item(i).getNodeValue());
			System.out.println("Nick Name : " + nickNodes.item(i).getNodeValue());
			System.out.println("Marks : " + marksNodes.item(i).getNodeValue() + "\n"); 
		}
	}
}