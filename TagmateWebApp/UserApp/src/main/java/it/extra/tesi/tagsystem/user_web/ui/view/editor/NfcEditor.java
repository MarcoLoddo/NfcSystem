package it.extra.tesi.tagsystem.user_web.ui.view.editor;

import org.springframework.web.client.RestTemplate;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import client.NfcTagDto;
import client.UserDto;

public class NfcEditor extends DataEditor {

	private CheckBox disable;
	private TextField id;
	public NfcEditor(Grid g, NfcTagDto item, UserDto user, String userUri, Layout caller) {
		super();
		cancel.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				event.getComponent().getParent().setVisible(false);
				g.setEnabled(true);
			}
		});
		id = new TextField();
		id.focus();
		disable = new CheckBox();
		id.setCaption("Set new id");
		id.setValue(item.getNfc_id());

		disable.setCaption("Disable status");
		disable.setValue(item.isDisabled());

		submit.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				item.setDisabled(disable.getValue());
				item.setNfc_id(id.getValue());

				RestTemplate restTemplate = new RestTemplate();
				String uri = userUri + "/update";
				restTemplate.postForEntity(uri, user, UserDto.class);

				g.getDataProvider().refreshAll();
				g.setEnabled(true);
				caller.removeComponent(event.getComponent().getParent());
			}
		});
		addComponents(id, disable);
	}
}
