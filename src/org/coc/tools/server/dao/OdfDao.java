package org.coc.tools.server.dao;

import java.io.IOException;
import java.io.InputStream;

import org.jopendocument.dom.ODPackage;
import org.jopendocument.model.OpenDocument;


public class OdfDao {

	//OpenDocument doc = new OpenDocument();
	ODPackage odPackage=null;
	//TextDocument doc;
	public OdfDao(){
	}
	public boolean load(InputStream is){
		try {
			odPackage=new ODPackage(is);
			//odPackage.getTextDocument();
			//odPackage.getODDocument();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
