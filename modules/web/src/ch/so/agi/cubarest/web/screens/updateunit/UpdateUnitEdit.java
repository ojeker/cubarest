package ch.so.agi.cubarest.web.screens.updateunit;

import com.haulmont.cuba.gui.screen.*;
import ch.so.agi.cubarest.entity.UpdateUnit;

@UiController("cubarest_UpdateUnit.edit")
@UiDescriptor("update-unit-edit.xml")
@EditedEntityContainer("updateUnitDc")
@LoadDataBeforeShow
public class UpdateUnitEdit extends StandardEditor<UpdateUnit> {
}