package org.coc.tools.server.dataimp;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.coc.tools.shared.model.Clan;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*
<DATA>

	<ROW>
		<CLAN_ID>1</CLAN_ID>
		<CLAN_NAME>nakama CK</CLAN_NAME>
		<CLAN_SYMBOL>40</CLAN_SYMBOL>
		<REGISTERED>1</REGISTERED>
		<CLAN_LOCATE></CLAN_LOCATE>
		<CLAN_TAG>#9YR89JU</CLAN_TAG>
		<CLAN_WAR_FREQUENCY_WEEKLY>0</CLAN_WAR_FREQUENCY_WEEKLY>
		<TROPHIES_REQUIRED>0</TROPHIES_REQUIRED>
	</ROW>
*/
public class XmlClanReader {

	public Map<String, Clan> getAll(InputStream stream) throws ParserConfigurationException, SAXException, IOException, XmlDataImpException  {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(stream);
		doc.getDocumentElement().normalize();
		NodeList listOfRow = doc.getElementsByTagName("ROW");

		Map<String, Clan> map = new HashMap<String, Clan>();
		for (int i = 0; i < listOfRow.getLength(); i++) {

			Node rowOne = listOfRow.item(i);
			DataUnit<String,Clan> parsed = praseOne(rowOne);
			map.put(parsed.getId(), parsed.getData());
		}
		return map;
	}

	private DataUnit<String,Clan> praseOne(Node node) throws XmlDataImpException   {

		if (Node.ELEMENT_NODE != node.getNodeType()) {
			throw new XmlDataImpException("not a ELEMENT_NODE");
		}
		//Element clanElement = (Element) node;
		Map<String,String> propertys=new HashMap<String,String>();
		propertys.put("CLAN_ID", "");
		propertys.put("CLAN_TAG", "");
		propertys.put("CLAN_NAME", "");
		propertys.put("CLAN_SYMBOL", "");
		propertys.put("REGISTERED", "");
		XmlReaderHelper.grabProperty(propertys, (Element) node);
		/*
		String id=clanElement.getElementsByTagName("CLAN_ID").item(0).getFirstChild().getNodeValue();
		String tag=clanElement.getElementsByTagName("CLAN_TAG").item(0).getFirstChild().getNodeValue();
		String name=clanElement.getElementsByTagName("CLAN_NAME").item(0).getFirstChild().getNodeValue();
		String symbol=clanElement.getElementsByTagName("CLAN_SYMBOL").item(0).getFirstChild().getNodeValue();
		String regStat=clanElement.getElementsByTagName("REGISTERED").item(0).getFirstChild().getNodeValue();
		 */

		String id=propertys.get("CLAN_ID");
		String tag=propertys.get("CLAN_TAG");
		String name=propertys.get("CLAN_NAME");
		String symbol=propertys.get("CLAN_SYMBOL");
		String regStat=propertys.get("REGISTERED");

		Clan.REG_STATUS stat = Clan.REG_STATUS.NON_REGED;
		if(regStat.equals("1")){
			stat = Clan.REG_STATUS.REGED;
		}
		Clan entity = new Clan();
		entity.setClanName(name);
		entity.setClanSymbol(symbol);
		entity.setClanTag(tag);
		entity.setRegistered(stat);
		DataUnit<String,Clan> data=new DataUnit<String,Clan>(id,entity);
		return data;
	}
}
