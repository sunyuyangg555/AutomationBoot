package com.patres.automation.action.keyboard

import com.patres.automation.menuItem.MenuItem
import com.patres.automation.model.RootSchemaGroupModel
import com.patres.automation.model.SchemaGroupModel


class PressKeyboardButtonAction(
        root: RootSchemaGroupModel,
        parent: SchemaGroupModel
) : KeyboardButtonAction(root, parent) {

    init {
        controller.actionLabel.text = MenuItem.PRESS_KEYBOARD_BUTTON.actionName
    }

    override fun runAction() {
        controller.keyboardField.keys
                .flatMap { it.keyValues }
                .forEach { robot.keyPress(it) }

        controller.keyboardField.keys
                .reversed()
                .flatMap { it.keyValues }
                .forEach { robot.keyRelease(it) }
    }

}