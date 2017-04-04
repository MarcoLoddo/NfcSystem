package it.extra.tagmate.userapp.ui.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ibm.icu.impl.CalendarAstronomer.Horizon;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.themes.ValoTheme;

import data.NfcTagDto;
import data.RestManager;
import data.UserDto;

@SuppressWarnings("serial")
public class UserManaging extends VerticalLayout implements View {

	private UserDto admin;
	private Grid<UserDto> usersData;
	private Grid<NfcTagDto> nfcData;

	public UserManaging() {
		setSizeFull();

		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.addStyleName(ValoTheme.LAYOUT_CARD);
		horizontalLayout.setSizeUndefined();
		addComponent(horizontalLayout);
		setComponentAlignment(horizontalLayout, Alignment.TOP_CENTER);
		horizontalLayout.setSpacing(true);
		horizontalLayout.setMargin(new MarginInfo(true, true, true, true));

		TextField searchbar = new TextField();
		horizontalLayout.addComponent(searchbar);
		
		searchbar.setCaption("Enter a name:");
		searchbar.setValue("");
		searchbar.focus();
		Button submitSerch = new Button("Search");
		
		submitSerch.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				List<UserDto> dtos = RestManager.sendSearchName("http://localhost:8090/findeUserByName",
						searchbar.getValue());
				usersData.setItems(dtos);

			}
		});

		horizontalLayout.addComponent(submitSerch);
		horizontalLayout.setComponentAlignment(submitSerch, Alignment.MIDDLE_RIGHT);
		

		HorizontalLayout horizontalLayout2 = new HorizontalLayout();
		addComponent(horizontalLayout2);
		usersData = new Grid<UserDto>();
		horizontalLayout2.addComponent(usersData);
		usersData.setSizeUndefined();
		usersData.addColumn(UserDto::getName).setCaption("Name");
		usersData.addColumn(UserDto::getEmail).setCaption("Email");
		usersData.addSelectionListener(new SelectionListener<UserDto>() {

			@Override
			public void selectionChange(SelectionEvent<UserDto> event) {
				Optional<UserDto> userSelected = event.getFirstSelectedItem();
				List<NfcTagDto> nfcs = userSelected.get().getNfcTags();
				if (nfcs.size() > 0)
					nfcData.setItems(nfcs);
				else
					nfcData.setItems(new ArrayList<>());
			}
		});

		nfcData = new Grid<NfcTagDto>();
		horizontalLayout2.addComponent(nfcData);
		nfcData.addColumn(NfcTagDto::getNfc_id).setCaption("Nfc ID");

	}

	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		admin = (UserDto) VaadinSession.getCurrent().getAttribute("user");
		if (admin == null)
			getUI().getNavigator().navigateTo("");

	}

}
