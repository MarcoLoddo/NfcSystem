package it.extra.tagmate.userapp.ui.view;

import java.awt.Label;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.ItemClick;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.components.grid.ItemClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.themes.ValoTheme;

import data.NfcTagDto;
import data.UserDto;

@SuppressWarnings("serial")
public class UserManaging extends VerticalLayout implements View {

	private UserDto admin;
	private Grid<UserDto> usersData;
	private Grid<NfcTagDto> nfcData;

	@SuppressWarnings("unchecked")
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
		horizontalLayout.setCaption("Write 'all' to list all users");
		searchbar.setCaption("Enter a name:");
		searchbar.setValue("");
		searchbar.focus();
		Button submitSerch = new Button("Search");

		submitSerch.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				RestTemplate restTemplate = new RestTemplate();
				String name = searchbar.getValue();
				ResponseEntity<UserDto[]> responseEntity = restTemplate
						.postForEntity("http://localhost:8090/user/name/find", name, UserDto[].class);
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
		usersData = new Grid<UserDto>();
		usersData.setSizeFull();
		usersData.setSelectionMode(SelectionMode.SINGLE);
		gridLayout.addComponent(usersData);
		usersData.addColumn(UserDto::getName).setCaption("Name");
		usersData.addColumn(UserDto::getEmail).setCaption("Email");
		usersData.addItemClickListener(new ItemClickListener<UserDto>() {

			public void itemClick(ItemClick<UserDto> event) {
				// TODO Auto-generated method stub
				try {

					UserDto userSelected = event.getItem();

					List<NfcTagDto> nfcs = userSelected.getNfcTags();
					if (nfcs.size() > 0)
						nfcData.setItems(nfcs);
					else
						nfcData.setItems(new ArrayList<>());

					VaadinSession.getCurrent().getLockInstance().lock();
					VaadinSession.getCurrent().setAttribute("selectedUser", userSelected);

					VaadinSession.getCurrent().getLockInstance().unlock();

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

		);

		nfcData = new Grid<NfcTagDto>();
		nfcData.setSizeFull();
		Button addNfc = new Button("Add Nfc");

		addNfc.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				VerticalLayout submitContainer = new VerticalLayout();
				usersData.setEnabled(false);
				TextField id = new TextField();
				id.focus();
				Button submitRow = new Button("Submit");
				Button cancel = new Button("Cancel");
				submitContainer.addComponents(id, cancel, submitRow);
				gridLayout.addComponent(submitContainer);
				cancel.addClickListener(new ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						// TODO Auto-generated method stub
						gridLayout.removeComponent(submitContainer);
						usersData.setEnabled(true);
						usersData.focus();

					}
				});

				submitRow.addClickListener(new ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						// TODO Auto-generated method stub
						NfcTagDto newNfc = new NfcTagDto();
						newNfc.setNfc_id(id.getValue());
						UserDto userDto = ((UserDto) getSession().getAttribute("selectedUser"));
						userDto.getNfcTags().add(newNfc);
						RestTemplate restTemplate = new RestTemplate();
						restTemplate.postForEntity("http://localhost:8090/user/update", userDto, UserDto.class);
						nfcData.getDataProvider().refreshAll();

						gridLayout.removeComponent(submitContainer);
						usersData.setEnabled(true);
						usersData.focus();
					}
				});
			}
		});
		gridLayout.addComponent(nfcData);
		nfcData.addColumn(NfcTagDto::getNfc_id).setCaption("Nfc ID");
		nfcData.addColumn(NfcTagDto::isDisabled).setCaption("Status");
		nfcData.addColumn(nfc -> "Disable", new ButtonRenderer<>(clickEvent -> {
			NfcTagDto nfcTagDto = ((NfcTagDto) clickEvent.getItem());
			nfcTagDto.setDisabled(true);
			UserDto userDto = ((UserDto) getSession().getAttribute("selectedUser"));
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.postForEntity("http://localhost:8090/user/update", userDto, UserDto.class);
			nfcData.getDataProvider().refreshAll();
		}));
		gridLayout.addComponent(addNfc);
	}

	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		admin = (UserDto) VaadinSession.getCurrent().getAttribute("user");
		if (admin == null)
			getUI().getNavigator().navigateTo("");

	}

}
