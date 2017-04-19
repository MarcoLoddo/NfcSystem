package it.extra.tesi.tagsystem.user_web.ui.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.ItemClick;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.ItemClickListener;
import com.vaadin.ui.themes.ValoTheme;

import client.NfcTagDto;
import client.UserDto;
import it.extra.tesi.tagsystem.user_web.ui.view.editor.DataEditor;
import it.extra.tesi.tagsystem.user_web.ui.view.editor.NfcEditor;
import it.extra.tesi.tagsystem.user_web.ui.view.editor.UserEditor;

public class UserSearch extends VerticalLayout {

	private Grid<NfcTagDto> nfcData;

	private Grid<UserDto> usersData;

	private UserManagingMenuBar menubar;

	public UserSearch(String userUri, UserManaging caller) {
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.addStyleName(ValoTheme.LAYOUT_CARD);
		horizontalLayout.setSizeUndefined();
		addComponent(horizontalLayout);
		setComponentAlignment(horizontalLayout, Alignment.TOP_CENTER);
		horizontalLayout.setSpacing(true);
		horizontalLayout.setMargin(new MarginInfo(true, true, true, true));

		TextField searchbar = new TextField();

		horizontalLayout.addComponent(searchbar);
		horizontalLayout.setCaption("Write 'all' if you want to list all the users");

		searchbar.setCaption("Enter a name:");
		searchbar.setValue("");
		searchbar.focus();
		Button submitSerch = new Button("Search");

		submitSerch.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				RestTemplate restTemplate = new RestTemplate();
				String name = searchbar.getValue();
				Map<String, String> map = new HashMap<>();
				map.put("name", name);
				String uri = userUri + "/{name}/find";

				ResponseEntity<UserDto[]> responseEntity = restTemplate.getForEntity(uri, UserDto[].class, map);
				List<UserDto> dtos = Arrays.asList(responseEntity.getBody());
				usersData.setItems(dtos);
				Notification.show(Integer.toString(dtos.size()));
			}
		});

		horizontalLayout.addComponent(submitSerch);
		horizontalLayout.setComponentAlignment(submitSerch, Alignment.MIDDLE_RIGHT);

		HorizontalLayout gridLayout = new HorizontalLayout();
		gridLayout.addStyleName(ValoTheme.LAYOUT_CARD);
		gridLayout.setSizeFull();
		gridLayout.setMargin(new MarginInfo(true, true, true, true));
		gridLayout.setSpacing(true);
		addComponent(gridLayout);

		// griglie dati
		usersData = new Grid<UserDto>();
		usersData.setSizeFull();
		usersData.setSelectionMode(SelectionMode.SINGLE);
		gridLayout.addComponent(usersData);

		usersData.addColumn(UserDto::getName).setCaption("Name");
		usersData.addColumn(UserDto::getEmail).setCaption("Email");
		usersData.addColumn(UserDto::getPassword).setCaption("Password");
		usersData.addItemClickListener(new ItemClickListener<UserDto>() {

			public void itemClick(ItemClick<UserDto> event) {
				UserDto userSelected = event.getItem();

				VaadinSession.getCurrent().getLockInstance().lock();
				VaadinSession.getCurrent().setAttribute("selectedUser", userSelected);
				VaadinSession.getCurrent().getLockInstance().unlock();

				if (event.getMouseEventDetails().isDoubleClick()) {

					usersData.setEnabled(false);
					caller.setEditor(new UserEditor(usersData, userSelected, userUri, (Layout) event.getSource().getParent()));
					caller.getEditor().setVisible(true);
					
				} else {

					List<NfcTagDto> nfcs = userSelected.getNfcTags();
					if (nfcs.size() > 0)
						nfcData.setItems(nfcs);
					else
						nfcData.setItems(new ArrayList<>());

				}
			}
		}

		);

		nfcData = new Grid<NfcTagDto>();
		nfcData.setSizeFull();

		gridLayout.addComponent(nfcData);
		nfcData.addColumn(NfcTagDto::getNfc_id).setCaption("Nfc ID");
		nfcData.addItemClickListener(clickEvent -> {
			if (clickEvent.getMouseEventDetails().isDoubleClick()) {
				NfcTagDto item = clickEvent.getItem();
				caller.setEditor(new NfcEditor(nfcData, item, (UserDto) getSession().getAttribute("selectedUser"),
						userUri, gridLayout));
				nfcData.setEnabled(false);
				caller.getEditor().setVisible(true);
			}
		});

		nfcData.addColumn(NfcTagDto::isDisabled).setCaption("Disabled");

		menubar = new UserManagingMenuBar(usersData, gridLayout, userUri, nfcData);
		addComponentAsFirst(menubar);
	}

}
