package org.coc.tools.server.dataimp;

import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlReaderHelper {

	public static Map<String,String>	grabProperty(Map<String,String> map,Element elem){
		for (Entry<String, String> entry: map.entrySet()) {
		    String key = entry.getKey();
		    String val="";
		    NodeList nodeList=elem.getElementsByTagName(key);
		    if(nodeList == null || nodeList.getLength()<=0){
		    	//entry.setValue("");
		    }else{
		    	Node node=nodeList.item(0);
		    	if(node !=null) node=node.getFirstChild();
		    	if(node !=null) val=node.getNodeValue();
		    	//String val=nodeList.item(0).getFirstChild().getNodeValue();
		    	//entry.setValue("");
		    }
		    entry.setValue(val);
		    
		}
		return map;
	}
}
