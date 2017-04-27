package it.extrasys.tesi.tagsystem.user_web.ui.view.usermanaging;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

import it.extrasys.tesi.tagsystem.user_web.client.UserDto;
import it.extrasys.tesi.tagsystem.user_web.ui.view.editor.DataEditor;

/**
 * User profile Ui.
 */
public class UserEditing extends VerticalLayout implements View {

    private UserDto userDto;
    private UserForm form;
    private DataEditor editor;
    private String userUri;
    /**
     * Instantiates a new user view.
     *
     * @param userUri
     *            the user uri
     */
    public UserEditing(String userUri) {
        this.userUri = userUri;
    }

    @Override
    public void enter(ViewChangeEvent event) {
        this.userDto = (UserDto) getSession().getAttribute("selectedUser");
        if (this.userDto == null) {
            getUI().getNavigator().navigateTo("UserManaging");
        } else {
            if (getComponentIndex(this.form) >= 0) {
                removeComponent(this.form);
            }
            this.form = new UserForm(this.userDto, this.userUri);
            addComponent(this.form);
            setComponentAlignment(this.form, Alignment.MIDDLE_CENTER);
        }

    }

}
