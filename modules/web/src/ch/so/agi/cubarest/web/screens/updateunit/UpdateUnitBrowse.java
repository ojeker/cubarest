package ch.so.agi.cubarest.web.screens.updateunit;

import com.haulmont.cuba.gui.screen.*;
import ch.so.agi.cubarest.entity.UpdateUnit;

@UiController("cubarest_UpdateUnit.browse")
@UiDescriptor("update-unit-browse.xml")
@LookupComponent("updateUnitsTable")
@LoadDataBeforeShow
public class UpdateUnitBrowse extends StandardLookup<UpdateUnit> {
}