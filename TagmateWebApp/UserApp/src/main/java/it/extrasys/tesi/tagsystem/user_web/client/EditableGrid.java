package it.extrasys.tesi.tagsystem.user_web.client;

import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.components.grid.ItemClickListener;

/**
 * The Class CustomGrid.
 *
 * @param <T>
 *
 */
public class EditableGrid<T> extends Grid<T> {

    /**
     * Instantiates a new custom grid.
     */
    @SuppressWarnings("unchecked")
    public EditableGrid(Class<T> bean) {
        super(bean);
        addItemClickListener(new ItemClickListener() {

            @Override
            public void itemClick(ItemClick event) {
                if (event.getMouseEventDetails().isDoubleClick()) {
                    getEditor().setEnabled(true);
                    getEditor().setBuffered(true);
                    event.getColumn().setEditorComponent(new TextField());
                }
            }
        });
    }
}
