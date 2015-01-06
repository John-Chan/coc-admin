package org.coc.tools.client.presenter;

import org.coc.tools.client.view.UiTestView;

import com.google.gwt.user.client.ui.HasWidgets;

public class UiTestPresenter implements Presenter {

	private UiTestView	view=new UiTestView();
	
	@Override
	public void go(HasWidgets container) {

		container.clear();
		container.add(view);

	}

	public UiTestPresenter(){
		//
	}
}
