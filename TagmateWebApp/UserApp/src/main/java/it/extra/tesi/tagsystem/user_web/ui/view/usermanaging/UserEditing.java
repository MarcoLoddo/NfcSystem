package it.extra.tesi.tagsystem.user_web.ui.view.usermanaging;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

import client.UserDto;
import it.extra.tesi.tagsystem.user_web.ui.view.editor.DataEditor;

/**
 * User profile Ui.
 */
public class UserEditing extends VerticalLayout implements View {

    private UserDto userDto;
    private UserForm form;
    private DataEditor editor;
    /**
     * Instantiates a new user view.
     *
     * @param userUri
     *            the user uri
     */
    public UserEditing(String userUri) {

        this.form = new UserForm();
        setComponentAlignment(this.form, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        this.userDto = (UserDto) getSession().getAttribute("selectedUser");
        if (this.userDto == null) {
            getUI().getNavigator().navigateTo("UserManaging");
        } else {
            this.form.init(this.userDto);
        }

    }

}
