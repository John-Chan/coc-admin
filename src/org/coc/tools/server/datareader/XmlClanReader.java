package org.coc.tools.server.datareader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
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

public class XmlClanReader {

	public Map<String, Clan> parseClan(InputStream stream) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(stream);
		doc.getDocumentElement().normalize();
		NodeList listOfRow = doc.getElementsByTagName("ROW");
		// int totalBooks = listOfRow.getLength();
		// System.out.println("Total no of books : " + totalBooks);

		Map<String, Clan> map = new HashMap<String, Clan>();
		for (int i = 0; i < listOfRow.getLength(); i++) {

			Node rowOne = listOfRow.item(i);
			Clan clan = praseClan(rowOne);
			map.put(clan.getClanTag(), clan);
		}

		// doc.getDocumentElement().normalize();
		return map;
	}

	private Clan praseClan(Node clanNode) throws Exception {
		/*
		 * <ROW> <CLAN_ID>1</CLAN_ID> <CLAN_NAME>nakama CK</CLAN_NAME>
		 * <CLAN_SYMBOL>40</CLAN_SYMBOL> <REGISTERED>1</REGISTERED>
		 * <CLAN_LOCATE></CLAN_LOCATE> <CLAN_TAG>#9YR89JU</CLAN_TAG>
		 * <CLAN_WAR_FREQUENCY_WEEKLY>0</CLAN_WAR_FREQUENCY_WEEKLY>
		 * <TROPHIES_REQUIRED>0</TROPHIES_REQUIRED> </ROW>
		 */

		if (Node.ELEMENT_NODE != clanNode.getNodeType()) {
			throw new Exception("not a ELEMENT_NODE");
		}
		Element clanElement = (Element) clanNode;
		NodeList clanSymbolList = clanElement.getElementsByTagName("CLAN_ID");
		Element clanSymbol = (Element) clanSymbolList.item(0);

		NodeList clanNameList = clanElement.getElementsByTagName("CLAN_NAME");
		Element clanName = (Element) clanNameList.item(0);

		NodeList clanTagList = clanElement.getElementsByTagName("CLAN_TAG");
		Element clanTag = (Element) clanTagList.item(0);

		Clan.REG_STATUS stat = Clan.REG_STATUS.REGED;
		Clan clan = new Clan();
		clan.setClanName(clanName.getNodeValue());
		clan.setClanSymbol(clanSymbol.getNodeValue());
		clan.setClanTag(clanTag.getNodeValue());
		clan.setRegistered(stat);
		return clan;
	}
}
