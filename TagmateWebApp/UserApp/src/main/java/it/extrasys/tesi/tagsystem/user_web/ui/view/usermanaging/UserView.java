package it.extrasys.tesi.tagsystem.user_web.ui.view.usermanaging;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

import it.extrasys.tesi.tagsystem.user_web.events.EndEditUserListener;
import it.extrasys.tesi.tagsystem.user_web.events.StartEditUserListener;
import it.extrasys.tesi.tagsystem.user_web.ui.view.MenuBarPage;

/**
 * The Class UserForm.
 */
public class UserView extends MenuBarPage
        implements
            View,
            StartEditUserListener,
            EndEditUserListener {

    private UserSearch userSearchView;
    private EditUser editUserView;

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
        super(userUri);
        setSizeFull();

        this.userSearchView = userSearch;
        this.editUserView = editUser;
        this.userUri = userUri;
        addStartEditListener(this);

    }
    @Override
    public void endEditUser() {
        goSearch();
    }
    @Override
    public void enter(ViewChangeEvent event) {

    }
    private void goEdit(int id) {
        this.editUserView.addEndEditListener(this);
        this.editUserView.addStartEditListener(this);
        this.editUserView.init(id);
        getUI().getCurrent().getNavigator()
                .navigateTo(this.editUserView.getPageName());
    }
    private void goSearch() {
        this.userSearchView.addStartEditListener(this);
        getUI().getCurrent().getNavigator()
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
