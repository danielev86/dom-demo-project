package com.danielev86.domxml.main;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.w3c.dom.Element;

import com.danielev86.domxml.bean.PersonBean;
import com.danielev86.domxml.repository.PersonRepository;
import com.danielev86.domxml.utility.XmlWriterUtility;

@ComponentScan(basePackages = "com.danielev86")
public class MainApp {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainApp.class);
		XmlWriterUtility xmlWriterUtility = ctx.getBean(XmlWriterUtility.class);
		PersonRepository personRepository = ctx.getBean(PersonRepository.class);
		try {
			xmlWriterUtility.init();
			xmlWriterUtility.createElement("team");
			Element root = xmlWriterUtility.createAndAppendRootElement("team");
			for (PersonBean person : personRepository.getPersons()) {
				Element cyclist = xmlWriterUtility.createElement("cyclist");
				xmlWriterUtility.createAndAppendElement(cyclist, "firstName", person.getFirstName());
				xmlWriterUtility.createAndAppendElement(cyclist, "lastName", person.getLastName());
				xmlWriterUtility.createAndAppendElement(cyclist, "age", person.getAge().toString());
				xmlWriterUtility.appendChildToRootElement(root, cyclist);
			}
			xmlWriterUtility.writeContentToXmlFile(args[0]);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
