package org.coc.tools.server.dataimp;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.xml.parsers.ParserConfigurationException;

import org.coc.tools.shared.model.Clan;
import org.coc.tools.shared.model.WarResult;
import org.xml.sax.SAXException;

public class XmlDataImpoter {

	private static final String IMP_DATA_DIR = "/WEB-INF/data-imp/";
	private static final String DATA_FILE_CLAN_INFO = "clan-list.xml";
	private static final String DATA_FILE_WAR_RESULT_INFO = "war-detail-list.xml";
	private static final String DATA_FILE_WAR_LOG_INFO = "war-log-list.xml";
	
	private Map<String, Clan>	clanTab=new HashMap<String, Clan>();
	private Map<String, Map<String,String>>	warLogTab=new HashMap<String, Map<String,String>>();
	private Map<String, WarResult>	warResultTab=new HashMap<String, WarResult>();
	private ServletContext context;
	public XmlDataImpoter(ServletContext context){
		this.context=context;
	}
	
	public void	load() throws ParserConfigurationException, SAXException, IOException, XmlDataImpException{
		clanTab=loadClan();
		warLogTab=loadWarLog();
		warResultTab=loadWarResult();
	}
	public Map<String, Clan> getClanTab() {
		return clanTab;
	}


	public Map<String, Map<String, String>> getWarLogTab() {
		return warLogTab;
	}


	public Map<String, WarResult> getWarResultTab() {
		return warResultTab;
	}


	public void setClanTab(Map<String, Clan> clanTab) {
		this.clanTab = clanTab;
	}


	public void setWarLogTab(Map<String, Map<String, String>> warLogTab) {
		this.warLogTab = warLogTab;
	}


	public void setWarResultTab(Map<String, WarResult> warResultTab) {
		this.warResultTab = warResultTab;
	}


	private Map<String, Clan> loadClan() throws ParserConfigurationException, SAXException, IOException, XmlDataImpException{
		//Map<String, Clan> XmlClanReader
		InputStream is = context.getResourceAsStream(IMP_DATA_DIR
				+ DATA_FILE_CLAN_INFO);
		return new XmlClanReader().getAll(is);
	}


	private Map<String, Map<String,String>> loadWarLog() throws ParserConfigurationException, SAXException, IOException, XmlDataImpException{
		//Map<String, Map<String,String>> XmlWarLogReader
		InputStream is = context.getResourceAsStream(IMP_DATA_DIR
				+ DATA_FILE_WAR_LOG_INFO);
		return new XmlWarLogReader().getAll(is);
	}
	private Map<String, WarResult> loadWarResult() throws ParserConfigurationException, SAXException, IOException, XmlDataImpException{
		//Map<String, WarResult> XmlWarResultReader
		InputStream is = context.getResourceAsStream(IMP_DATA_DIR
				+ DATA_FILE_WAR_RESULT_INFO);
		return new XmlWarResultReader().getAll(is);
	}
}
