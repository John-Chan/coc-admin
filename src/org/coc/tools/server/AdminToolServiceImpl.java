package org.coc.tools.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.coc.tools.client.AdminToolService;
import org.coc.tools.shared.RpcData;
import org.coc.tools.shared.RpcResult;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class AdminToolServiceImpl  extends RemoteServiceServlet  implements AdminToolService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8891635212735822317L;

	private static final String IMP_DATA_DIR="WEB-INF/data-imp/";
	private static final String DATA_FILE_CLAN_INFO="clan-list.xml";
	
	@Override
	public RpcResult authRootUser(String loginId, String passwrod) {
		return new RpcResult();
	}

	@Override
	public RpcData<Map<String, String>> getImpDataInfo(String loginId,
			String passwrod) {
		RpcData<Map<String, String>> rsp=new RpcData<Map<String, String>>();
		Map<String, String> moreInfo=new HashMap<String, String>();
		
		try {
			Document doc=readClanInfo();
			moreInfo.put(DATA_FILE_CLAN_INFO, "ready");
		} catch (Exception e) {
			rsp.setErrorCode(RpcResult.ERROR_CODE.EC_FAILED);
			moreInfo.put(DATA_FILE_CLAN_INFO, e.getMessage());
			//rsp.setMsg("miss file:"+IMP_DATA_DIR+DATA_FILE_CLAN_INFO);
			e.printStackTrace();
		}
		rsp.setData(moreInfo);
		return rsp;
	}

	@Override
	public RpcResult doImpData(String loginId, String passwrod) {
		// TODO Auto-generated method stub
		//XMLReader reader;
		return null;
	}

	private Document	readClanInfo() throws IOException, ParserConfigurationException, SAXException{
		//http://www.tutorialspoint.com/java/xml/javax_xml_parsers_documentbuilder_inputstream.htm
		
		//SAXParserFactory saxFactory = SAXParserFactory.newInstance();
		// create a new DocumentBuilderFactory
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
		ServletContext context = getServletContext();
		InputStream is = context.getResourceAsStream(IMP_DATA_DIR+DATA_FILE_CLAN_INFO);
		Document doc = builder.parse(is);
		return doc;        
		/*
		XMLReader reader = XMLReaderFactory.createXMLReader();
		FileInputStream stream;
		InputSource is;
		stream = new FileInputStream(IMP_DATA_DIR+DATA_FILE_CLAN_INFO);
		is = new InputSource(new InputStreamReader(stream, "UTF-8"));
		is.setEncoding("UTF-8");
		reader.parse(is);*/
	}
}
