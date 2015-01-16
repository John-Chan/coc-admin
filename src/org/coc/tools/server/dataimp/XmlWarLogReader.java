package org.coc.tools.server.dataimp;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*
<DATA>

	<ROW>
		<WAR_ID>1</WAR_ID>
		<END_DATE>2014-10-05 00:00:00</END_DATE>
		<PLAYER_COUNT>35</PLAYER_COUNT>
		<POST_DATE>2014-10-13 21:36:30</POST_DATE>
		<STATUS>0</STATUS>
		<ENEMY_CLAN_ID>3</ENEMY_CLAN_ID>
		<ENEMY_CLAN_WAR_DETAIL_ID>2</ENEMY_CLAN_WAR_DETAIL_ID>
		<HOME_CLAN_ID>1</HOME_CLAN_ID>
		<HOME_CLAN_WAR_DETAIL_ID>1</HOME_CLAN_WAR_DETAIL_ID>
		<REPOTOR_GUID>1</REPOTOR_GUID>
	</ROW>

*/
public class XmlWarLogReader {
	public Map<String, Map<String,String>> getAll(InputStream stream)  throws ParserConfigurationException, SAXException, IOException, XmlDataImpException  {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(stream);
		doc.getDocumentElement().normalize();
		NodeList listOfRow = doc.getElementsByTagName("ROW");

		Map<String, Map<String,String>> map = new HashMap<String, Map<String,String>>();
		for (int i = 0; i < listOfRow.getLength(); i++) {

			Node rowOne = listOfRow.item(i);
			DataUnit<String,Map<String,String>> parsed = praseOne(rowOne);
			map.put(parsed.getId(), parsed.getData());
		}
		return map;
	}
	
	private DataUnit<String,Map<String,String>> praseOne(Node node) throws XmlDataImpException {

		if (Node.ELEMENT_NODE != node.getNodeType()) {
			throw new XmlDataImpException("not a ELEMENT_NODE");
		}
		Map<String,String> propertys=new HashMap<String,String>();
	
		propertys.put("WAR_ID", "");
		propertys.put("END_DATE", "");
		propertys.put("PLAYER_COUNT", "");
		propertys.put("POST_DATE", "");
		propertys.put("STATUS", "");
		propertys.put("ENEMY_CLAN_ID", "");
		propertys.put("ENEMY_CLAN_WAR_DETAIL_ID", "");
		propertys.put("HOME_CLAN_ID", "");
		propertys.put("HOME_CLAN_WAR_DETAIL_ID", "");
		propertys.put("REPOTOR_GUID", "");

		XmlReaderHelper.grabProperty(propertys, (Element) node);
		String id=propertys.get("WAR_ID");
		
		//CWIndex entity=new CWIndex();
		
		DataUnit<String,Map<String,String>> data=new DataUnit<String,Map<String,String>>(id,propertys);
		return data;
	
	}
}
