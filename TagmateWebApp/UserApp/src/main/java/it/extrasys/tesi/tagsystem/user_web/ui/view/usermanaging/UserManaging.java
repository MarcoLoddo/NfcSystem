package it.extrasys.tesi.tagsystem.user_web.ui.view.usermanaging;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.ItemClickListener;
import com.vaadin.ui.themes.ValoTheme;

import it.extrasys.tesi.tagsystem.user_web.client.UserDto;

/**
 * User search UI.
 */

public class UserManaging extends VerticalLayout implements View {

    /** The users data. */
    private final Grid<UserDto> usersData;

    /**
     * Instantiates a new user search.
     *
     * @param userUri
     *            the user uri
     */
    public UserManaging(String userUri) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addStyleName(ValoTheme.LAYOUT_CARD);
        horizontalLayout.setSizeUndefined();
        addComponent(horizontalLayout);
        setComponentAlignment(horizontalLayout, Alignment.TOP_CENTER);
        horizontalLayout.setSpacing(true);
        horizontalLayout.setMargin(new MarginInfo(true, true, true, true));

        TextField searchbar = new TextField();

        horizontalLayout.addComponent(searchbar);
        horizontalLayout
                .setCaption("Write 'all' if you want to list all the users");

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

                ResponseEntity<UserDto[]> responseEntity = restTemplate
                        .getForEntity(uri, UserDto[].class, map);
                List<UserDto> dtos = Arrays.asList(responseEntity.getBody());
                UserManaging.this.usersData.setItems(dtos);
                Notification.show(Integer.toString(dtos.size()));
            }
        });

        horizontalLayout.addComponent(submitSerch);
        horizontalLayout.setComponentAlignment(submitSerch,
                Alignment.MIDDLE_RIGHT);

        HorizontalLayout gridLayout = new HorizontalLayout();
        gridLayout.addStyleName(ValoTheme.LAYOUT_CARD);
        gridLayout.setSizeFull();
        gridLayout.setMargin(new MarginInfo(true, true, true, true));
        gridLayout.setSpacing(true);
        addComponent(gridLayout);

        // griglie dati
        this.usersData = new Grid<UserDto>();
        this.usersData.setSizeFull();
        this.usersData.setSelectionMode(SelectionMode.SINGLE);
        gridLayout.addComponent(this.usersData);

        this.usersData.addColumn(UserDto::getName).setCaption("Name");
        this.usersData.addColumn(UserDto::getEmail).setCaption("Email");
        this.usersData.addColumn(UserDto::getPassword).setCaption("Password");
        this.usersData.addItemClickListener(new ItemClickListener<UserDto>() {

            @Override
            public void itemClick(ItemClick<UserDto> event) {
                UserDto userSelected = event.getItem();

                if (event.getMouseEventDetails().isDoubleClick()) {
                    VaadinSession.getCurrent().getLockInstance().lock();
                    VaadinSession.getCurrent().setAttribute("selectedUser",
                            userSelected);
                    VaadinSession.getCurrent().getLockInstance().unlock();
                    getUI().getNavigator().navigateTo("UserView");

                }
            }
        });

    }

    @Override
    public void enter(ViewChangeEvent event) {
        UserDto userDto = (UserDto) getSession().getAttribute("user");
        if (userDto == null) {
            getUI().getNavigator().navigateTo("");
        }

    }

}
