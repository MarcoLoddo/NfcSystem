package it.extrasys.tesi.tagsystem.user_web.ui.view;

import it.extrasys.tesi.tagsystem.user_web.events.CustomLayoutEvents;
import it.extrasys.tesi.tagsystem.user_web.events.StartEditUserListener;
import it.extrasys.tesi.tagsystem.user_web.ui.view.usermanaging.CommandMenu;

/**
 * The Class MenuBarPage.
 */
public class MenuBarPage extends CustomLayoutEvents {

    /** The menu bar. */
    private CommandMenu menuBar;

    /**
     * Instantiates a new menu bar page.
     *
     * @param userUri
     *            the user uri
     */
    public MenuBarPage(String userUri) {

        setMenuBar(new CommandMenu(userUri));
    }

    @Override
    public void addStartEditListener(StartEditUserListener listener) {
        // TODO Auto-generated method stub
        super.addStartEditListener(listener);
        this.menuBar.addStartEditListener(listener);
    }

    public CommandMenu getMenuBar() {
        return this.menuBar;
    }
    /**
     * Sets the menu bar.
     *
     * @param menuBar
     *            the new menu bar
     */
    public void setMenuBar(CommandMenu menuBar) {
        if (getComponentIndex(menuBar) > 0) {
            removeComponent(menuBar);
        }
        this.menuBar = menuBar;
        addComponentAsFirst(menuBar);

    }

}
