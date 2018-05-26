package com.danielev86.domxml.utility;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.stereotype.Component;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


@Component
public class XmlWriterUtility {
	
	private Document document;
	
	public void init() throws ParserConfigurationException {
		DocumentBuilderFactory docBuildFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuildFactory.newDocumentBuilder();
		setDocument(docBuilder.newDocument());
	}
	
	public Element createAndAppendRootElement(String tagElement) {
		Element rootElement = createElement(tagElement);
		getDocument().appendChild(rootElement);
		return rootElement;
	}
	
	public Element createElement(String tagElement) {
		return getDocument().createElement(tagElement);
	}
	
	public void createAndAppendElement(Element root, String tagChild, String value) {
		Element child = createElement(tagChild);
		child.appendChild(getDocument().createTextNode(value));
		appendChildToRootElement(root, child);
	}
	
	public void addAttributeToElement(Element element, String attrName, String attrValue) {
		Attr attrType = getDocument().createAttribute(attrName);
		attrType.setValue(attrValue);
		element.setAttributeNode(attrType);
	}
	
	public void writeContentToXmlFile(String pathFile) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource domSource = new DOMSource(getDocument());
		StreamResult streamConsole = new StreamResult(new File(pathFile));
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		transformer.transform(domSource, streamConsole);
	}
	
	public void appendChildToRootElement(Element root, Element child) {
		root.appendChild(child);
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	
	
	
	

}
