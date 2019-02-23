package com.patres.automation.model

import com.patres.automation.keyboard.GlobalKeyListener
import com.patres.automation.gui.controller.model.AutomationController
import com.patres.automation.gui.controller.model.SchemaGroupController
import com.patres.automation.util.LoaderFactory
import com.patres.automation.util.swap
import javafx.scene.Node
import java.util.*

class SchemaGroupModel(root: RootSchemaGroupModel, parent: SchemaGroupModel?) : AutomationModel<SchemaGroupController>(root, parent) {

    override val controller: SchemaGroupController = LoaderFactory.createSchemaGroupController(this)

    val actionBlocks = ArrayList<AutomationModel<out AutomationController>>()

    var schemaGroups = emptyList<SchemaGroupModel>()
        get() = actionBlocks.filterIsInstance<SchemaGroupModel>()

    var allChildrenActionBlocks = emptyList<AutomationModel<out AutomationController>>()
        get() = actionBlocks + schemaGroups.flatMap { it.allChildrenActionBlocks }

    init {
        controller.afterInit()
    }

    fun addNewSchemaGroup(name: String = "Group") {
        val newSchemaGroup = SchemaGroupModel(root, this)
        newSchemaGroup.controller.groupNameTextField.text = name
        addActionBlocks(newSchemaGroup)
    }

    fun addActionBlocks(actionBlock: AutomationModel<out AutomationController>) {
        actionBlocks.add(actionBlock)
        actionBlock.parent = this
        controller.getMainInsideNode().children.add(actionBlock.getMainNode())
    }

    fun addActionBlockToList(actionBlock: AutomationModel<out AutomationController>, index: Int) {
        actionBlocks.add(index, actionBlock)
        actionBlock.parent = this
        controller.getMainInsideNode().children.add(index, actionBlock.getMainNode())
    }

    fun leaveGroupToUp(actionBlock: AutomationModel<out AutomationController>) {
        actionBlock.parent?.removeNode(actionBlock)
        val index = parent?.controller?.getMainInsideNode()?.children?.indexOf(getMainNode()) ?: 0
        parent?.addActionBlockToList(actionBlock, index)
    }

    fun leaveGroupToDown(actionBlock: AutomationModel<out AutomationController>) {
        actionBlock.parent?.removeNode(actionBlock)
        val index = (parent?.controller?.getMainInsideNode()?.children?.indexOf(getMainNode()) ?: 0) + 1
        parent?.addActionBlockToList(actionBlock, index)
    }

    fun moveActionBlockToTop(actionBlock: AutomationModel<out AutomationController>) {
        actionBlock.parent?.removeNode(actionBlock)
        actionBlocks.add(0, actionBlock)
        actionBlock.parent = this
        controller.getMainInsideNode().children.add(0, actionBlock.getMainNode())
    }

    fun moveActionBlockToBottom(actionBlock: AutomationModel<out AutomationController>) {
        actionBlock.parent?.removeNode(actionBlock)
        actionBlocks.add(actionBlock)
        actionBlock.parent = this
        controller.getMainInsideNode().children.add(actionBlock.getMainNode())
    }

    fun removeNode(actionBlock: AutomationModel<out AutomationController>) {
        actionBlocks.remove(actionBlock)
        controller.getMainInsideNode().children.remove(actionBlock.getMainNode())
    }

    fun swap(actionBlock: AutomationModel<out AutomationController>, actionBlockToSwap: AutomationModel<out AutomationController>) {
        controller.getMainInsideNode().swap(actionBlock.getMainNode(), actionBlockToSwap.getMainNode())
        actionBlocks.swap(actionBlock, actionBlockToSwap)
    }

    fun checkValidation() {
        actionBlocks.forEach { it.checkValidations()  }
    }

    fun getNumberOfIteration(): Int {
        val text = controller.iterationsTextField.text
        return if (text == "INF") {
            Integer.MAX_VALUE
        } else {
            try {
                Integer.parseInt(controller.iterationsTextField.text)
            } catch (nfe: NumberFormatException) {
                1
            }
        }
    }

    override fun runAction() {
        for (i in 0 until getNumberOfIteration()) {
            for (action in actionBlocks) {
                if (!GlobalKeyListener.isStop()) {
                    action.runAction()
                } else {
                    return
                }
            }
        }
    }

    override fun getMainNode(): Node = controller.getMainNode()

    override fun getMainInsideNode(): Node = controller.getMainInsideNode()



}