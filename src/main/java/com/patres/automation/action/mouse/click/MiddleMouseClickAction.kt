package com.patres.automation.action.mouse.click

import com.patres.automation.menuItem.MenuItem
import com.patres.automation.model.RootSchemaGroupModel
import com.patres.automation.model.SchemaGroupModel
import com.sun.glass.ui.Robot.MOUSE_MIDDLE_BTN

class MiddleMouseClickAction(
        root: RootSchemaGroupModel,
        parent: SchemaGroupModel
) : MouseClickAction(root, parent) {

    init {
        controller.actionLabel.text = MenuItem.CLICK_MIDDLE_MOUSE_BUTTON.actionName
    }

    override val buttonBit: Int = MOUSE_MIDDLE_BTN

}