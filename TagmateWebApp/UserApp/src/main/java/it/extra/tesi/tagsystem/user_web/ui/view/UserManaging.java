package it.extra.tesi.tagsystem.user_web.ui.view;

import org.springframework.beans.factory.annotation.Value;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.HorizontalLayout;

import client.UserDto;
import it.extra.tesi.tagsystem.user_web.ui.view.editor.DataEditor;

public class UserManaging extends HorizontalLayout implements View {

	private UserDto admin;
	
	private DataEditor editor;

	private UserSearch searcher;

	public UserManaging(String userUri) {
		setSizeFull();
		
		editor=new DataEditor();
		editor.setVisible(false);
		searcher=new UserSearch(userUri, this);
		addComponent(searcher);
		addComponent(editor);
	}

	public void enter(ViewChangeEvent event) {
		admin = (UserDto) VaadinSession.getCurrent().getAttribute("user");
		if (admin == null)
			getUI().getNavigator().navigateTo("");
		VaadinSession.getCurrent().setAttribute("selectedUser", null);
	}
	

	public DataEditor getEditor() {
		return editor;
	}

	public void setEditor(DataEditor editor) {
		
		if(!this.editor.equals(editor))
			if(this.components.contains(this.editor))
				this.removeComponent(this.editor);
		this.editor = editor;
		addComponent(this.editor);
	}

}
