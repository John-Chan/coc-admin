package org.coc.tools.client.widget;

import org.coc.tools.client.misc.GridHelper;
import org.coc.tools.client.misc.ModelFactory;
import org.coc.tools.client.misc.ResHelper;
import org.coc.tools.client.view.UiSizeConstants;
import org.coc.tools.client.view.UiStrConstants;
import org.coc.tools.shared.CocConstant;
import org.gwt.advanced.client.datamodel.ComboBoxDataModel;
import org.gwt.advanced.client.ui.widget.ComboBox;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class ClanEditPanel {
	private final TextBox clanTag;
	private final TextBox clanName;
	
	private final ComboBox<ComboBoxDataModel> clanSymbolBox;
	private final ComboBoxDataModel clanSymbolDataModel;
	private final HorizontalPanel	holder;
	private final FlexTable container;
	private final HTML clanSymbolImg;
	
	public ClanEditPanel(){
		holder=new HorizontalPanel();
		container=new FlexTable();
		clanSymbolImg=new HTML( ResHelper.makeImgHtml( ResHelper.getDefClanSymbolAbsUrl(),UiSizeConstants.Px.IMG_WIDTH_32,UiSizeConstants.Px.IMG_HIGHT_32 ));
		clanTag = new TextBox();
		clanName = new TextBox(); 
		clanSymbolBox = new ComboBox<ComboBoxDataModel>();

		clanTag.setMaxLength(16);
		clanName.setMaxLength(24);
		//ComboBoxDataModel model = DemoModelFactory.createsCountriesModel();
		clanSymbolDataModel = ModelFactory.createClanSymbolModel(ModelFactory.CLAN_SYMBOL_IMG_SZIE.SZ_32_PIX, null);
		clanSymbolDataModel.setSelectedIndex(0);
		clanSymbolBox.setModel(clanSymbolDataModel);
		clanSymbolBox.setCustomTextAllowed(false);
		clanSymbolBox.setLazyRenderingEnabled(false);
		initLayout();
		updateVal("","",0);
		bindUiEvt();
	}
	
	public void setReadOnly(boolean roClanTag,boolean roClanName,boolean roClanSymbol){
		//clanSymbolBox.setChoiceButtonVisible(!roClanSymbol);
		clanSymbolBox.setEnabled(!roClanSymbol);
		clanTag.setReadOnly(roClanTag);
		clanName.setReadOnly(roClanName);
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
	public int	getClanSymbolIndex(){
		return clanSymbolDataModel.getSelectedIndex();
	}

	public String	getClanSymbol(){
		return  Integer.toString(clanSymbolDataModel.getSelectedIndex()+1);
	}
	public void	setClanSymbolIndex(int val){
		if((val+1) >=CocConstant.ClanInfo.MIN_CLANSYMBOL_VALUE && (val+1)<= CocConstant.ClanInfo.MAX_CLANSYMBOL_VALUE ){
			clanSymbolDataModel.setSelectedIndex(val);
		}
	}
	public void	setClanSymbol(String val){
		int index=Integer.parseInt(val);
		if(index >=CocConstant.ClanInfo.MIN_CLANSYMBOL_VALUE && index<= CocConstant.ClanInfo.MAX_CLANSYMBOL_VALUE ){
			clanSymbolDataModel.setSelectedIndex(index-1);
		}
	}
	public void	updateVal(String tag,String name,int symbolIndex){

		setClanTag(tag);
		setClanName(name);
		setClanSymbolIndex(symbolIndex);
		
		clanSymbolImg.setHTML(ClanInfoWidget.getSymbolImg32Html(Integer.toString(symbolIndex+1)));
	}
	public void	updateVal(String tag,String name,String symbol){
		updateVal(tag,name,Integer.parseInt(symbol)-1);
	}
	
	private void	initLayout(){

		holder.clear();
		GridHelper pusher=new GridHelper(container);
		pusher.pushBack(new Label(UiStrConstants.ValueNames.CLAN_TAG)).pushBack(new HTML("")).pushBack(clanTag);

		pusher.nextRow();
		pusher.pushBack(new Label(UiStrConstants.ValueNames.CLAN_NAME)).pushBack(new HTML("")).pushBack(clanName);

		HorizontalPanel symbolBoxAndImg=new HorizontalPanel();
		symbolBoxAndImg.add(clanSymbolBox);
		symbolBoxAndImg.add(clanSymbolImg);
		pusher.nextRow();
		pusher.pushBack(new Label(UiStrConstants.ValueNames.CLAN_SYMBOL)).pushBack(new HTML("")).pushBack(symbolBoxAndImg);
		
		container.setWidth("100%");
		
		GridHelper.VerticalAlign.alignAllToTop(container);
		GridHelper.setColWidth(container, new String[]{"80px","20px"});
		

		clanSymbolImg.setWidth(UiSizeConstants.Px.IMG_WIDTH_32);
		clanSymbolBox.setHeight(UiSizeConstants.Px.IMG_WIDTH_32);
		symbolBoxAndImg.setWidth("95%");
		clanSymbolBox.setWidth("95%");
		clanTag.setWidth("95%");
		clanName.setWidth("95%");
		holder.add(container);
	}
	private void	bindUiEvt(){
		clanSymbolBox.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				int symbolIndex=clanSymbolBox.getSelectedIndex();
				clanSymbolImg.setHTML(ClanInfoWidget.getSymbolImg32Html(Integer.toString(symbolIndex+1)));
			}
			//
		});
		clanTag.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				String tag=clanTag.getText().trim().toUpperCase();
				if(!tag.startsWith(CocConstant.ClanInfo.CLAN_TAG_PREFIX)){
					tag=CocConstant.ClanInfo.CLAN_TAG_PREFIX+tag;
				}
				clanTag.setText(tag);
			}
			//
		});
		clanName.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				String name=clanName.getText().trim();
				clanName.setText(name);
			}
			//
		});
	}
}
