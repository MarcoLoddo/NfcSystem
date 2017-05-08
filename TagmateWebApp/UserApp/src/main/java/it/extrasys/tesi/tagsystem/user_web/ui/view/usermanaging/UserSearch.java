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
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.ItemClickListener;
import com.vaadin.ui.themes.ValoTheme;

import it.extrasys.tesi.tagsystem.user_web.client.UserDto;
import it.extrasys.tesi.tagsystem.user_web.ui.components.events.CustomLayoutEvents;
import it.extrasys.tesi.tagsystem.user_web.ui.components.events.StartEditUserListener;
import it.extrasys.tesi.tagsystem.user_web.ui.components.menubars.CommandMenu;

/**
 * User search UI.
 */

public class UserSearch extends CustomLayoutEvents implements View {

    /** The name. */
    private String pageName = "UserSearch";

    /** The users data. */
    private final Grid<UserDto> usersData;

    private CommandMenu menuBar;
    private Button submitSerch;
    /**
     * Instantiates a new user search.
     *
     * @param userUri
     *            the user uri
     */
    public UserSearch(String userUri) {
        this.menuBar = new CommandMenu(userUri);
        addComponent(this.menuBar);
        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.addStyleName(ValoTheme.LAYOUT_CARD);
        verticalLayout.setSizeUndefined();
        addComponent(verticalLayout);
        setComponentAlignment(verticalLayout, Alignment.MIDDLE_CENTER);
        verticalLayout.setSpacing(true);
        verticalLayout.setMargin(new MarginInfo(true, true, true, true));

        TextField searchbar = new TextField();

        verticalLayout.addComponent(searchbar);
        verticalLayout
                .setCaption("Write 'all' if you want to list all the users");

        searchbar.setCaption("Enter a name:");
        searchbar.setValue("");
        searchbar.focus();
        this.submitSerch = new Button("Search");

        this.submitSerch.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                RestTemplate restTemplate = new RestTemplate();
                String name = searchbar.getValue();
                Map<String, String> map = new HashMap<>();
                map.put("name", name);
                String uri = userUri + "/{name}/findByName";

                ResponseEntity<UserDto[]> responseEntity = restTemplate
                        .getForEntity(uri, UserDto[].class, map);
                List<UserDto> dtos = Arrays.asList(responseEntity.getBody());
                UserSearch.this.usersData.setItems(dtos);
            }
        });

        verticalLayout.addComponent(this.submitSerch);
        verticalLayout.setComponentAlignment(this.submitSerch,
                Alignment.MIDDLE_RIGHT);

        // griglie dati
        this.usersData = new Grid<UserDto>();
        this.usersData.addColumn(UserDto::getName).setCaption("Name");
        this.usersData.addColumn(UserDto::getEmail).setCaption("Email");
        this.usersData.addColumn(UserDto::getPassword).setCaption("Pwd");
        this.usersData.addColumn(UserDto::sizeNfcTags)
                .setCaption("Nfc list size");

        this.usersData.setSizeUndefined();
        this.usersData.setSelectionMode(SelectionMode.SINGLE);
        verticalLayout.addComponent(this.usersData);
        this.usersData.addItemClickListener(new ItemClickListener<UserDto>() {

            @Override
            public void itemClick(ItemClick<UserDto> event) {
                if (event.getMouseEventDetails().isDoubleClick()) {
                    fireStartEdit(event.getItem().getUserId());
                }

            }
        });
    }
    @Override
    public void addStartEditListener(StartEditUserListener listener) {
        super.addStartEditListener(listener);
        this.menuBar.addStartEditListener(listener);
    }
    @Override
    public void enter(ViewChangeEvent event) {
        if (VaadinSession.getCurrent().getAttribute("user") == null) {
            UI.getCurrent().getNavigator().navigateTo("");
        }

    }

    public String getPageName() {
        return this.pageName;
    }
    @Override
    public void refresh() {
        this.submitSerch.click();
    }

}
