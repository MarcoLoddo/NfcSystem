package it.extra.tesi.tagsystem.user_web.ui.view.editor;

import org.springframework.web.client.RestTemplate;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import client.NfcTagDto;
import client.UserDto;

public class DataEditor extends VerticalLayout {

	protected Button submit, cancel;

	public DataEditor() {
		setStyleName("background-white");
		setSizeFull();
		submit = new Button("Submit changes");
		cancel = new Button("Cancel");

		addComponents(cancel, submit);
	}
}
