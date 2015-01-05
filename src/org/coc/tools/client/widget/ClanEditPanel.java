package org.coc.tools.client.widget;

import org.coc.tools.client.misc.ModelFactory;
import org.coc.tools.client.misc.ResHelper;
import org.coc.tools.client.view.UIConstants;
import org.coc.tools.shared.model.Clan;
import org.gwt.advanced.client.datamodel.ComboBoxDataModel;
import org.gwt.advanced.client.ui.widget.ComboBox;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class ClanEditPanel {
	private final TextBox clanTag;
	private final TextBox clanName;
	//private final TextBox clanSymbol;
	
	private ComboBox<ComboBoxDataModel> clanSymbolBox;
	private ComboBoxDataModel clanSymbolDataModel;
	private	Clan clan=null;
	private HorizontalPanel	holder=new HorizontalPanel();
	private FlexTable container=new FlexTable();
	
	public ClanEditPanel(){
		clanTag = new TextBox();
		clanName = new TextBox();
		
		clanSymbolBox = new ComboBox<ComboBoxDataModel>();
		clanSymbolBox.setWidth("64pix%");
		//ComboBoxDataModel model = DemoModelFactory.createsCountriesModel();
		clanSymbolDataModel = ModelFactory.createClanSymbolModel(ModelFactory.CLAN_SYMBOL_IMG_SZIE.SZ_32_PIX, null);
		clanSymbolDataModel.setSelectedIndex(0);
		clanSymbolBox.setModel(clanSymbolDataModel);
		clanSymbolBox.setCustomTextAllowed(true);
		clanSymbolBox.setLazyRenderingEnabled(false);
		//comboBox.setLazyRenderingEnabled(true);
		//comboBox.set
		menuPanel.add(comboBox);
	}
	public Widget getWidget(){
		return holder;
	}
	private void	initData(){
		if(clan != null){
			clanName.setText(clan.getClanName());
			clanTag.setText(clan.getClanTag());
			clanSymbolDataModel.setSelectedIndex( Integer.parseInt(clan.getClanSymbol() )-1);
			
		}
	}
}
