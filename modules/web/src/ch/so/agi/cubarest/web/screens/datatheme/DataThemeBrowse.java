package ch.so.agi.cubarest.web.screens.datatheme;

import com.haulmont.cuba.gui.screen.*;
import ch.so.agi.cubarest.entity.DataTheme;

@UiController("cubarest_DataTheme.browse")
@UiDescriptor("data-theme-browse.xml")
@LookupComponent("table")
@LoadDataBeforeShow
public class DataThemeBrowse extends MasterDetailScreen<DataTheme> {
}