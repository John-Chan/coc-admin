package org.coc.tools.server.dataimp;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.coc.tools.shared.WarResultBuilder;
import org.coc.tools.shared.model.WarResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlWarResultReader {

	public Map<String, WarResult> getAll(InputStream stream) throws ParserConfigurationException, SAXException, IOException, XmlDataImpException  {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(stream);
		doc.getDocumentElement().normalize();
		NodeList listOfRow = doc.getElementsByTagName("ROW");

		Map<String, WarResult> map = new HashMap<String, WarResult>();
		for (int i = 0; i < listOfRow.getLength(); i++) {

			Node rowOne = listOfRow.item(i);
			DataUnit<String,WarResult> parsed = praseOne(rowOne);
			map.put(parsed.getId(), parsed.getData());
		}
		return map;
	}
	
	/// TODO:
	/// entity.setClanId(clanId);
	/// entity.setLocked(locked);
	private DataUnit<String,WarResult> praseOne(Node node) throws IllegalArgumentException, XmlDataImpException {

		if (Node.ELEMENT_NODE != node.getNodeType()) {
			throw new XmlDataImpException("not a ELEMENT_NODE");
		}
		Map<String,String> propertys=new HashMap<String,String>();
		propertys.put("WAR_DETAIL_ID", "");
		propertys.put("ATTACKS_LOST", "");
		propertys.put("ATTACKS_REMAINING", "");
		propertys.put("ATTACKS_USED", "");
		propertys.put("ATTACKS_WON", ""); 
		propertys.put("AVERAGE_ATTACKDURATION_SECOND", "");
		propertys.put("AVERAGE_DESTRUCTION", "");
		propertys.put("HEROIC_ATTACK_PLAYER", "");
		propertys.put("HEROIC_DEFENSE_PLAYER", "");
		propertys.put("NEW_STAR_PEER_ATTACK", "");
		propertys.put("PLAYER_COUNT", "");
		propertys.put("STARS1", "");
		propertys.put("STARS2", "");
		propertys.put("STARS3", "");
		propertys.put("STARS_FINAL", "");
		XmlReaderHelper.grabProperty(propertys, (Element) node);
	
		int playerCount, attacksUsed, attacksWon, star1, star2, star3;
		String id=propertys.get("WAR_DETAIL_ID");
		playerCount=Integer.parseInt(propertys.get("PLAYER_COUNT"));
		attacksUsed=Integer.parseInt(propertys.get("ATTACKS_USED"));
		attacksWon=Integer.parseInt(propertys.get("ATTACKS_WON"));
		star1=Integer.parseInt(propertys.get("STARS1"));
		star2=Integer.parseInt(propertys.get("STARS2"));
		star3=Integer.parseInt(propertys.get("STARS3"));
		WarResult entity =WarResultBuilder.makeSimpleWarResult(playerCount, attacksUsed, attacksWon, star1, star2, star3);
		entity.setHeroicAttackPlayer(propertys.get("HEROIC_ATTACK_PLAYER"));
		entity.setHeroicDefensePlayer(propertys.get("HEROIC_DEFENSE_PLAYER"));
		/*
		WarResult entity = new WarResult();
		entity.setAttacksLost(attacksLost);
		entity.setAttacksRemaining(attacksRemaining);
		entity.setAttacksUsed(attacksUsed);
		entity.setAttacksWon();
		entity.setAverageAttackDurationSecond(averageAttackDurationSecond);
		entity.setAverageDestruction(averageDestruction);
		entity.setClanId(clanId);
		entity.setFinalStars(finalStars);
		entity.setHeroicAttackPlayer(heroicAttackPlayer);
		entity.setHeroicDefensePlayer(heroicDefensePlayer);
		entity.setLocked(locked);
		entity.setMaxAccackCount(maxAccackCount);
		entity.setAttacksLost(attacksLost);
		entity.setPlayerCount(playerCount);
		entity.setTotalStars1Count(totalStars1Count);
		*/
		//entity.setAttackDate(attackDate);
		DataUnit<String,WarResult> data=new DataUnit<String,WarResult>(id,entity);
		return data;
	}
}
