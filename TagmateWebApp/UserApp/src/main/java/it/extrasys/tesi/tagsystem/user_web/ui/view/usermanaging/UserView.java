package it.extrasys.tesi.tagsystem.user_web.ui.view.usermanaging;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

import it.extrasys.tesi.tagsystem.user_web.ui.components.events.CustomLayoutEvents;
import it.extrasys.tesi.tagsystem.user_web.ui.components.events.EndEditUserListener;
import it.extrasys.tesi.tagsystem.user_web.ui.components.events.StartEditUserListener;
import it.extrasys.tesi.tagsystem.user_web.ui.components.menubars.CommandMenu;

/**
 * The Class UserForm.
 */
public class UserView extends CustomLayoutEvents
        implements
            View,
            StartEditUserListener,
            EndEditUserListener {

    private UserSearch userSearchView;
    private EditUser editUserView;
    private CommandMenu menuBar;
    private String userUri;
    /**
     * Instantiates a new user form.
     *
     * @param userUri
     *            the user uri
     * @param editUser
     * @param userSearch
     */
    public UserView(String userUri, UserSearch userSearch, EditUser editUser) {
        setSizeFull();

        this.userSearchView = userSearch;
        this.editUserView = editUser;
        this.userUri = userUri;
        this.menuBar = new CommandMenu(userUri);
        addComponent(this.menuBar);

    }
    @Override
    public void addStartEditListener(StartEditUserListener listener) {
        // TODO Auto-generated method stub
        super.addStartEditListener(listener);
        this.menuBar.addStartEditListener(listener);
    }
    @Override
    public void endEditUser() {
        System.out.println("Fire edit!\n\n\n");
        goSearch();
    }
    @Override
    public void enter(ViewChangeEvent event) {
        if (VaadinSession.getCurrent().getAttribute("user") == null) {
            UI.getCurrent().getNavigator().navigateTo("");
        }
    }
    private void goEdit(int id) {

        this.editUserView.init(id);
        UI.getCurrent().getNavigator()
                .navigateTo(this.editUserView.getPageName());
    }
    private void goSearch() {
        UI.getCurrent().getNavigator()
                .navigateTo(this.userSearchView.getPageName());
    }

    @Override
    public void startEdit(int id) {

        if (id < 0) {
            goSearch();
        } else {
            goEdit(id);
        }
    }

}
