package org.coc.tools.client.widget;

import org.coc.tools.client.misc.ModelFactory;
import org.coc.tools.client.view.UIConstants;
import org.coc.tools.client.view.ViewConstants;
import org.coc.tools.shared.CocConstant;
import org.gwt.advanced.client.datamodel.ComboBoxDataModel;
import org.gwt.advanced.client.ui.widget.ComboBox;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class ClanEditPanel {
	private final TextBox clanTag;
	private final TextBox clanName;
	//private final TextBox clanSymbol;
	
	private ComboBox<ComboBoxDataModel> clanSymbolBox;
	private ComboBoxDataModel clanSymbolDataModel;
	//private	Clan clan=null;
	private HorizontalPanel	holder=new HorizontalPanel();
	private FlexTable container=new FlexTable();
	
	public ClanEditPanel(){
		clanTag = new TextBox();
		clanTag.setMaxLength(16);
		clanName = new TextBox(); 
		clanName.setMaxLength(24);
		clanSymbolBox = new ComboBox<ComboBoxDataModel>();
		
		//ComboBoxDataModel model = DemoModelFactory.createsCountriesModel();
		clanSymbolDataModel = ModelFactory.createClanSymbolModel(ModelFactory.CLAN_SYMBOL_IMG_SZIE.SZ_32_PIX, null);
		clanSymbolDataModel.setSelectedIndex(0);
		clanSymbolBox.setModel(clanSymbolDataModel);
		clanSymbolBox.setCustomTextAllowed(false);
		clanSymbolBox.setLazyRenderingEnabled(false);
		//comboBox.setLazyRenderingEnabled(true);
		//clanSymbolBox.
		//clanSymbolDataModel.


		initLayout();
		updateVal("N/A","N/A",1);
	}
	public void	setEnableEdit(boolean eidtClanTag,boolean eidtClanName,boolean editClanSymbol){
		clanSymbolBox.setEnabled(editClanSymbol);
		clanTag.setEnabled(eidtClanTag);
		clanName.setEnabled(eidtClanName);
	}
	public Widget getWidget(){
		return holder;
	}
	public String	getClanTag(){
		return clanTag.getValue();
	}

	public void	setClanTag(String val){
		clanTag.setValue(val);
	}
	public String	getClanName(){
		return clanName.getValue();
	}

	public void	setClanName(String val){
		clanName.setValue(val);
	}
	public int	getClanSymbol(){
		return clanSymbolDataModel.getSelectedIndex();
	}

	public void	setClanSymbol(int val){
		if(val >=CocConstant.ClanInfo.MIN_CLANSYMBOL_VALUE && val<= CocConstant.ClanInfo.MAX_CLANSYMBOL_VALUE ){
			clanSymbolDataModel.setSelectedIndex(val);
		}
	}

	private void	updateVal(String tag,String name,int symbol){

		setClanTag(tag);
		setClanName(name);
		setClanSymbol(symbol);
		//clanName.setText(name);
		//clanTag.setText(tag);
		//clanSymbolDataModel.setSelectedIndex( Integer.parseInt(symbol )-1);
	}
	
	private void	initLayout(){
		
		int rowIndex=0;
		int celIndex=0;
		
		container.setWidget(rowIndex, celIndex++, new Label(ViewConstants.ValueNames.CLAN_TAG));
		container.setWidget(rowIndex, celIndex++, new HTML(""));//padding
		container.setWidget(rowIndex, celIndex++, clanTag);
		celIndex=0;
		rowIndex++;
		
		container.setWidget(rowIndex, celIndex++, new Label(ViewConstants.ValueNames.CLAN_NAME));
		container.setWidget(rowIndex, celIndex++, new HTML(""));//padding
		container.setWidget(rowIndex, celIndex++, clanName);
		celIndex=0;
		rowIndex++;

		container.setWidget(rowIndex, celIndex++, new Label(ViewConstants.ValueNames.CLAN_SYMBOL));
		container.setWidget(rowIndex, celIndex++, new HTML(""));//padding
		container.setWidget(rowIndex, celIndex++, clanSymbolBox);
		celIndex=0;
		rowIndex++;
		
		
		container.setWidth("100%");
		rowIndex=0;
		celIndex=0;
		container.getCellFormatter().setWidth(rowIndex, 0, UIConstants.Px.IMG_WIDTH_32);
		container.getCellFormatter().setWidth(rowIndex, 1, UIConstants.Px.MAX_CLAN_NAME_WIDTH);
		container.getCellFormatter().setWidth(rowIndex, 2, "5px");
		//container.getCellFormatter().setWidth(rowIndex, 3, UIConstants.Px.MAX_CLAN_TAG_WIDTH);
		
		for(int row=0;row< container.getRowCount();++row){

			for(int col=0;col<container.getCellCount(row);++col){

				container.getFlexCellFormatter().setVerticalAlignment(row, col,
						DockPanel.ALIGN_TOP);
				//container.getWidget(row, col).setHeight("32px");
			}
			container.getCellFormatter().setWidth(row, 0, "80px");
			container.getCellFormatter().setWidth(row, 1, "20px");
			//container.getCellFormatter().setWidth(row, 2, "80px");
		}
		

		
		clanSymbolBox.setWidth("95%");
		clanTag.setWidth("95%");
		clanName.setWidth("95%");
		holder.add(container);
	}
}
