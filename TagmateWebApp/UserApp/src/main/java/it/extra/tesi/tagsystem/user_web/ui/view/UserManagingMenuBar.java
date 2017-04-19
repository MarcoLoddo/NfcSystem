package it.extra.tesi.tagsystem.user_web.ui.view;

import org.springframework.web.client.RestTemplate;

import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import client.NfcTagDto;
import client.UserDto;

public class UserManagingMenuBar extends VerticalLayout {

	public UserManagingMenuBar(Grid<UserDto> usersData, Layout gridLayout, String userUri, Grid<NfcTagDto> nfcData) {
		setWidth("100%");
		MenuBar menuBar = new MenuBar();

		menuBar.setWidth("100%");
		MenuItem nfcOpt = menuBar.addItem("Nfc operations", null);
		MenuBar.Command addNfcCommand = new MenuBar.Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				UserDto userDto = ((UserDto) getSession().getAttribute("selectedUser"));
				if (userDto == null) {

					Notification notification=new Notification("No user selected",Notification.TYPE_ERROR_MESSAGE);
					notification.setPosition(Position.BOTTOM_RIGHT);
					notification.show(getUI().getPage());
					
				} else {
					VerticalLayout submitContainer = new VerticalLayout();
					usersData.setEnabled(false);
					TextField id = new TextField();
					id.focus();
					id.setCaption("NFC ID:");
					Button submitRow = new Button("Submit");
					Button cancel = new Button("Cancel");
					submitContainer.addComponents(id, cancel, submitRow);
					gridLayout.addComponent(submitContainer);
					cancel.addClickListener(new ClickListener() {

						@Override
						public void buttonClick(ClickEvent event) {
							gridLayout.removeComponent(submitContainer);
							usersData.setEnabled(true);
							usersData.focus();

						}
					});

					submitRow.addClickListener(new ClickListener() {

						@Override
						public void buttonClick(ClickEvent event) {
							NfcTagDto newNfc = new NfcTagDto();
							newNfc.setNfc_id(id.getValue());
							userDto.getNfcTags().add(newNfc);
							RestTemplate restTemplate = new RestTemplate();
							String uri = userUri + "/update";
							restTemplate.postForEntity(uri, userDto, UserDto.class);
							nfcData.getDataProvider().refreshAll();

							gridLayout.removeComponent(submitContainer);
							usersData.setEnabled(true);
							usersData.focus();
							getSession().setAttribute("selectedUser", null);
						}
					});
				}
			}
		};
		MenuItem addNfcItem = nfcOpt.addItem("Add Nfc", addNfcCommand);

		addComponent(menuBar);
	}
}
