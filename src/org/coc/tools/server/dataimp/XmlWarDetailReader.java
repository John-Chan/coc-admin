package org.coc.tools.server.dataimp;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.coc.tools.shared.model.WarDetail;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XmlWarDetailReader {
	public Map<String, WarDetail> getAll(InputStream stream)  throws ParserConfigurationException, SAXException, IOException, XmlDataImpException  {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(stream);
		doc.getDocumentElement().normalize();
		NodeList listOfRow = doc.getElementsByTagName("ROW");

		Map<String, WarDetail> map = new HashMap<String, WarDetail>();
		for (int i = 0; i < listOfRow.getLength(); i++) {

			Node rowOne = listOfRow.item(i);
			DataUnit<String,WarDetail> parsed = praseOne(rowOne);
			map.put(parsed.getId(), parsed.getData());
		}
		return map;
	}

	private DataUnit<String,WarDetail> praseOne(Node node) throws XmlDataImpException {

		if (Node.ELEMENT_NODE != node.getNodeType()) {
			throw new XmlDataImpException("not a ELEMENT_NODE");
		}
		return null;
	}
}
