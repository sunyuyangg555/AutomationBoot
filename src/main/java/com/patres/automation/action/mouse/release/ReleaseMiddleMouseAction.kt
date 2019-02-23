package com.patres.automation.action.mouse.release

import com.patres.automation.menuItem.MenuItem
import com.patres.automation.model.RootSchemaGroupModel
import com.patres.automation.model.SchemaGroupModel
import com.sun.glass.ui.Robot.MOUSE_MIDDLE_BTN

class ReleaseMiddleMouseAction(
        root: RootSchemaGroupModel,
        parent: SchemaGroupModel
) : ReleaseMouseAction(root, parent) {


    init {
        controller.actionLabel.text = MenuItem.RELEASE_MIDDLE_MOUSE_BUTTON.actionName
    }

    override val buttonBit: Int = MOUSE_MIDDLE_BTN

}