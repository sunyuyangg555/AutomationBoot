package com.patres.automation.mapper.model

import com.patres.automation.action.mouse.MousePointAction
import com.patres.automation.action.mouse.point.ImagePointDetector
import com.patres.automation.action.mouse.point.SpecificPointDetector
import com.patres.automation.helpers.JfxSerializedSpec
import com.patres.automation.helpers.shouldBeInstanceOfAndCheck
import com.patres.automation.helpers.shouldNotBeNullAndCheck
import com.patres.automation.mapper.AutomationMapper
import com.patres.automation.mapper.BrowserActionMapperTest
import com.patres.automation.mapper.RootSchemaGroupMapper
import com.patres.automation.point.Point
import com.patres.automation.type.ActionBootMousePoint
import com.patres.automation.util.Base64Converter
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import java.io.File

class MousePointActionSerializedTest : JfxSerializedSpec({

    val actionDirectory = "/actions/MousePointAction"
    val pointXFromFile = 823
    val pointYFromFile = 213
    val vectorXFromFile = 154
    val vectorYFromFile = 135
    val thresholdFromFile = 85
    val ignoreIfNotFoundFromFile = true
    val base64FromFile = "Qk1SHgAAAAAAADYAAAAoAAAAPgAAACkAAAABABgAAAAAABweAAAAAAAAAAAAAAAAAAAAAAAA7bl27bl2HiAZHyEaHyEaHiAZHyEaHyEaHyEaICIbHyEa7bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl27bl2ISMcHiAZICIbHyEaICIbICIbIiQdHiAZHyEaISMcHyEaAAAeIBofIRsgIhwhIx0fIRogIhsgIhsfIRoeIBkfIRogIhshIxwfIRogIhsfIRofIRofIRoeIBkgIhseIBkfIRoeIBkfIRogIhsfIRoeIBkgIhsgIhseIBkgIhsfIRohIxweIBkfIRofIRogIhsgIhshIxweIBkgIhsgIhsgIhsgIhsfIRofIRohIxweIBkfIRohIxwgIhsfIRogIhsgIhsfIRofIRogIhsfIRofIRogIhsfIRofIRogIhsAACAiHCAiHCAiHCAiHB8hGiEjHB8hGh8hGiAiGx8hGh8hGh4gGR4gGSAiGx8hGiAiGx8hGiAiGyEjHB4gGSAiGyAiGx8hGiEjHB8hGiAiGyAiGyAiGx8hGiAiGyAiGx8hGh4gGR8hGiAiGx8hGh8hGh4gGSAiGyAiGx8hGiAiGx8hGh8hGiEjHCAiGyEjHCAiGx8hGiAiGx8hGh4gGR8hGh8hGh8hGiEjHB8hGiIkHSAiGx8hGiAiGx8hGgAAHyEbHiAaHiAZHyEaIiQeHyEaHiAZHyEaHyEaICIbHyEaHyEaHyEaICIbHyEaICIbHyEaHyEaICIbHyEaHyEaHiAZICIbHyEaISMcHR8YISMcICIbHyEaHiAZICIbISMcICIbHyEaHiAZICIbHyEaHyEaICIbHyEaICIbISMcICIbICIbICIbISMcICIbHyEaICIbICIbISMcISMcHiAZHiAZHyEaHyEaICIbICIbICIbISMcHyEaICIbAAAfIRsfIRshIx0fIRogIhsfIRofIRohIxwhIxwfIRogIhsfIRofIRogIhshIxwfIRogIhshIxwhIxwfIRohIxwfIRofIRoeIBkfIRoeIBkfIRogIhshIxwfIRofIRohIxwgIhseIBkhIxwhIxwgIhsfIRohIxwfIRodHxgfIRofIRofIRogIhseIBkeIBkgIhsgIhsiJB0gIhsfIRogIhsdHxgfIRofIRogIhsgIhsgIhsgIhsgIhsfIRoAAB8hGx8hGyIkHiAiHCAiGx4gGSIkHSAiGyAiGyEjHB8hGh8hGiAiGyAiGyAiGx8hGh8hGiEjHCAiGyAiGyAiGx8hGiAiGyAiGx4gGR8hGh8hGh8hGh4gGSAiGyAiGyAiGx8hGh8hGiAiGyAiGx8hGh8hGiEjHB8hGiAiGx8hGh8hGiEjHCEjHB8hGiAiGx8hGh8hGiAiGyEjHB8hGiAiGyEjHCEjHB8hGiEjHB8hGh8hGh4gGR8hGh4gGQAAHiAaHiAaHyEbISMdHiAZHyEaHR8YHyEaICIbHiAZHiAZICIbHyEaHR8YHyEaHyEaHyEaHiAZHyEaICIbICIbHiAZHyEaHyEaHyEaHiAZHyEaICIbICIbIiQdICIbICIbHyEaHyEaICIbHyEaICIbHyEaHyEaHyEaISMcHR8YHyEaHyEaHyEaICIbHyEaICIbICIbHyEaICIbISMcHyEaICIbICIbICIbICIbHiAZHyEaICIbHyEaHR8YAAAfIRsiJB4gIhwgIhweIBkiJB0gIhsgIhshIxwfIRofIRogIhsgIhsgIhsfIRofIRohIxwgIhsgIhsgIhsfIRogIhsgIhseIBkfIRogIhsqLCUxMiw3ODNUVVJwcW54eXZjZGFFR0IyNC4wMSskJh8hIxwfIRogIhsfIRofIRohIxwhIxwfIRogIhsfIRofIRogIhshIxwfIRogIhshIxwhIxwfIRohIxwfIRofIRoeIBkfIRoeIBkfIRoAACAiHB8hGyAiHCAiHB8hGiEjHCAiGyEjHCEjHB8hGh4gGSAiGyEjHCEjHB4gGR8hGh8hGh4gGSAiGx8hGh8hGh8hGh8hGh8hGiIjHWNkYMLCwfv7+////////////////////////////+rq6o2Oi0tMRyAiGx8hGh8hGh8hGiAiGx8hGiAiGx8hGiAiGyAiGyAiGyAiGyAiGx8hGh8hGh8hGiEjHCEjHB8hGh8hGiAiGx8hGh8hGiEjHAAAICIcHyEbICIcIiQeICIbHyEaHiAZISMcICIbICIbICIbISMcICIbISMcHyEaICIbICIbISMcHyEaISMcHyEaHiAZICIbNzkzvL279PT0////////////////////////////////////////////5+fnb3BsIyUeHiAZHyEaICIbHyEaHyEaISMcHyEaISMcHyEaICIbHiAZISMcICIbHyEaHyEaICIbICIbHyEaHyEaICIbICIbHiAZAAAgIhwfIRshIx0fIRofIRogIhsgIhsgIhsgIhshIxwfIRofIRofIRohIxwgIhsfIRofIRohIxwfIRogIhsgIhseIBk4OjTQ0ND////////////t0aD69Of//v3///////////////////////////////////9+fnwiJB0gIhsfIRoeIBkgIhsfIRoeIBkgIhsiJB0eIBkgIhsgIhseIBkgIhsgIhsfIRogIhsfIRohIxwgIhshIxwgIhsAAB8hGx8hGx8hGx4gGSAiGyAiGyAiGyEjHCAiGyAiGx8hGiAiGyEjHB0fGCAiGyEjHCAiGx8hGiAiGyEjHCAiGyIjHaanpf///////////////9+tUdmeMPHbsv379////////////////////////////////////25vbCAiGx8hGh4gGR4gGSAiGx8hGh8hGh8hGh8hGh8hGiAiGx8hGh8hGiAiGx4gGSEjHB4gGSAiGx8hGiAiGx4gGQAAISMdICIcHyEbHyEaHyEaHiAZISMcICIbIiQdICIbHyEaISMcICIbISMcHyEaICIbHiAZHyEaHyEaHyEaHiAZlpeU/v7+////////////////361R0YgC1JAT36xP9+zX////////////////////////////////5+jnS01IISMcHiAZHyEaHyEaHyEaHyEaHyEaICIbHyEaICIbICIbICIbICIbICIbICIbISMcICIbICIbICIbICIbAAAfIRsfIRsgIhwfIRofIRogIhseIBkgIhsgIhsgIhsfIRoeIBkgIhsgIhsgIhsgIhsfIRogIhsgIhsgIhtCQz/v7+/////////////////////frVHRiALRiALRiALVkhjx2rL9+/b///////////////////////////+OjowkJh8fIRofIRoeIBkhIxwgIhsiJB0gIhsfIRohIxwgIhshIxwfIRogIhseIBkfIRofIRofIRofIRoeIBkAAB8hGyEjHSEjHR8hGiAiGx8hGh8hGiAiGyEjHB8hGiAiGyEjHCEjHB8hGiEjHB8hGh8hGh4gGR8hGh8hGqKioPv7+////////////////////9+tUdGIAtGIAtGIAtGIAtONDuW8cPfr1v/+/f///////////////////+rq6jQ2MCIkHSAiGx8hGiAiGx0fGB8hGh8hGiAiGyAiGyAiGyAiGyAiGx8hGh4gGR8hGh4gGSAiGyAiGyEjHAAAHyEbHyEbISMdICIcICIbISMcIiQdHyEaHyEaISMcHyEaHyEaICIbHyEaHyEaICIbISMcHyEaISMcLzEqyMjH////////////////////////361R0YgC0YgC0YgC0YgC0YgC0YgC15kn7dGe/v36////////////////////Z2hlICIbHiAZHiAZHyEaICIbICIbICIbICIbICIbISMcHyEaICIbHiAZHyEaHyEaISMcHyEaHyEaICIbAAAfIRsfIRsgIhwgIhwgIhsfIRogIhsgIhshIxwgIhseIBkfIRogIhsgIhseIBkhIxwfIRoeIBkfIRpFRkHp6ej////////////////////////frVHRiALRiALRiALRiALRiALRiALRiALRiALeq0358eL//v3////////////AwL8fIRogIhsgIhshIxwfIRofIRofIRofIRofIRoeIBkfIRofIRogIhsgIhsfIRogIhseIBkfIRogIhsAAB8hGx8hGyAiHB8hGh4gGR4gGR4gGR4gGR4gGSEjHCEjHCEjHB8hGiAiGx4gGSEjHB8hGh8hGiAiG1VXUvz8/P///////////////////////9+tUdGIAtGIAtGIAtGIAtGIAtGIAtGIAtGIAtGIAtiaKu/YrP369P////////j4+CEjHCEjHCAiGyAiGyAiGx8hGh8hGh8hGh4gGSAiGx8hGh4gGSAiGyAiGx8hGiEjHCAiGyEjHB8hGgAAICIcHyEbICIcHyEaICIbISMcICIbICIbICIbISMcHyEaHyEaISMcHyEaHyEaHyEaHyEaHR8YHyEaV1hT////////////////////////////361R0YgC0YgC0YgC0YgC0YgC0YgC0YgC0YgC0YgC0YgC1I8Q4K9W/vz5////////ICIbICIbHyEaHyEaISMcHyEaICIbHyEaHyEaHyEaHiAZICIbICIbHyEaHyEaICIbISMcHiAZICIbAAAfIRseIBoeIBkgIhwfIRofIRogIhshIxwgIhsfIRogIhsgIhsfIRofIRofIRofIRohIxwfIRogIhtXWFT////////////////////////////frVHRiALRiALRiALRiALRiALRiALRiALRiALRiALSigfeqUn58eH////////+/v4fIRogIhsfIRofIRohIxwfIRohIxwgIhsfIRogIhsfIRofIRogIhsgIhsgIhshIxwgIhsfIRogIhsAACAiHB4gGh8hGx4gGR4gGR4gGSAiGyAiGx4gGR8hGiAiGx8hGiAiGx8hGiAiGx8hGiAiGyAiGyAiG09QS/T09P///////////////////////9+tUdGIAtGIAtGIAtGIAtGIAtGIAtGIAtGIAtaVHufAevz27f///////////+Li4h8hGiAiGyAiGyEjHCEjHCAiGx8hGh8hGh4gGR8hGiAiGx8hGiEjHB4gGSAiGyAiGx8hGh8hGiEjHAAAHiAaISMdHyEbICIcHyEaICIbHyEaHyEaICIbHiAZHyEaICIbICIbICIbHyEaHyEaISMcHyEaICIbNjgy09PS////////////////////////361R0YgC0YgC0YgC0YgC0YgC0YgC0ooH4rRg9unQ/v37////////////////h4eFICIbICIbISMcHyEaHyEaHiAZHyEaHyEaHiAZHyEaHyEaHiAZICIbHyEaICIbICIbHyEaHyEaICIbAAAfIRsfIRseIBkfIRofIRogIhsfIRofIRofIRofIRofIRogIhsfIRogIhsgIhsfIRofIRoeIBkfIRogIhu2trX////////////////////////frVHRiALRiALRiALRiALRiQTTjxDpyY3+/Pr////////////////////7+/s9PjkgIhsfIRogIhsgIhsfIRodHxgfIRogIhsgIhsfIRoeIBkfIRofIRogIhsfIRogIhshIxwhIxwfIRoAACEjHR8hGyAiHCAiHCAiGx8hGiAiGyAiGx8hGh4gGR8hGiAiGx8hGh8hGh4gGSAiGyAiGx8hGiAiGx8hGnd4dff39////////////////////9+tUdGIAtGIAtGIAtKMCt6rTfTjxf///////////////////////////8LCwSwtJx4gGR4gGSEjHCAiGyIkHR8hGiAiGx0fGB8hGh8hGiEjHCEjHB8hGh8hGh4gGR8hGh4gGR8hGh8hGgAAICIcIiQeHiAZHyEaHiAZICIbHR8YHyEaISMcIiQdICIbICIbICIbISMcICIbHiAZICIbHyEaICIbISMcJighyMjH////////////////////361R0YgC0YgC1JEW7dGe/Pjv////////////////////////////9PT0Y2RgISMcHiAZHyEaHyEaICIbICIbHiAZHyEaHyEaIiQdHyEaICIbHiAZISMcHyEaICIbISMcHiAZICIbHiAZAAAiJB4fIRseIBkgIhwfIRofIRofIRofIRofIRoeIBkgIhsfIRoeIBkeIBkeIBkfIRohIxwgIhsfIRogIhseIBkpKiXw8PD////////////////frVHSiwjfrlP69Oj///////////////////////////////////+8vbskJiAfIRofIRoeIBkeIBkgIhsfIRogIhseIBkhIxwhIxwfIRogIhshIxwfIRohIxwgIhsgIhshIxwfIRogIhsAAB4gGiAiHCAiHCAiHCAiGyAiGyAiGyAiGyEjHCAiGx8hGh4gGSEjHCIkHR4gGSAiGx4gGR8hGiAiGyAiGx8hGh8hGmJjYO3t7P///////////+S6bujEgvz48P///////////////////////////////////9DQzzY4MyAiGyAiGyAiGx8hGh4gGR8hGh4gGSAiGyEjHB4gGSEjHB4gGSEjHB4gGR8hGiAiGx8hGiAiGyEjHB4gGR8hGgAAHyEbICIcHiAZICIcHyEaHiAZHiAZISMcHyEaHiAZHiAZISMcICIbHiAZHiAZICIbICIbIiQdICIbICIbISMcICIbJCYfYmNf8PDw////////+/Tp/v79/////////////////////////////////v7+pqelNzkzHyEaISMcHyEaICIbHyEaICIbHyEaHyEaICIbHyEaHyEaICIbHyEaISMcIiQdHyEaHyEaHiAZHiAZICIbHyEaICIbAAAgIhwgIhwgIhwfIRogIhsgIhshIxwhIxwgIhsfIRofIRoeIBkfIRogIhsfIRohIxweIBkgIhsgIhsfIRofIRohIxwhIxwgIhsnKSPIyMf39/f////////////////////////////////7+/vv7++Wl5QiJB4fIRofIRogIhsiJB0fIRofIRofIRogIhsfIRofIRogIhsfIRogIhshIxwgIhsgIhsgIhshIxwdHxghIxwhIxwgIhsfIRoAACIkHiEjHR8hGx4gGR0fGCEjHB8hGiAiGyAiGyEjHB8hGiAiGx4gGR4gGSAiGyAiGx8hGiAiGyAiGyAiGyEjHB0fGCIkHSAiGx8hGiYoIXh5dba3tdPT0vT09P////////z8/Onp6MjIx6Chn0NFQB8hGiAiGyEjHB4gGR8hGiAiGx8hGh8hGh8hGh8hGh8hGiAiGyAiGyEjHB8hGh8hGiEjHCAiGyEjHCAiGx8hGh8hGiAiGyAiGx4gGQAAICIcISMdHyEbICIcICIbHyEaHiAZHyEaICIbISMcHyEaICIbHyEaHyEaHyEaHiAZICIbHiAZHyEaHiAZHyEaICIbHyEaHiAZICIbICIbHiAZIyUeNjgyUVNNVldSV1hTVVdSRkhCLzAqIiQdHiAZICIbICIbICIbICIbHyEaHyEaISMcHiAZHyEaISMcICIbHyEaICIbICIbHyEaHyEaICIbHyEaHyEaICIbHyEaHyEaICIbICIbICIbAAAgIhwgIhwfIRsdHxggIhsfIRofIRodHxgeIBkgIhsgIhsgIhsfIRoeIBkgIhseIBkfIRoeIBkeIBkeIBkgIhsgIhseIBkfIRogIhsfIRogIhsfIRogIhsfIRogIhsgIhsgIhseIBkhIxwiJB0fIRogIhsgIhsgIhsgIhsfIRohIxwhIxwgIhsiJB0fIRogIhsfIRodHxgfIRohIxwgIhshIxwgIhsgIhsgIhsfIRogIhsgIhshIxwhIxwAACAiHCAiHB8hGx8hGh0fGB8hGh8hGiAiGyAiGyAiGx8hGiAiGyEjHCAiGx4gGSEjHB8hGh4gGSAiGx4gGR8hGh8hGh4gGR8hGiAiGx8hGiAiGx8hGh0fGCAiGyEjHB4gGR8hGh4gGR8hGiEjHCAiGx8hGh8hGh8hGiEjHCEjHB8hGh8hGh4gGSAiGyAiGx4gGSAiGyEjHB8hGh8hGiEjHCAiGx8hGh8hGiAiGx8hGh8hGh8hGh8hGiAiGwAAICIcHR8ZHyEbIiQeHiAZHyEaICIbICIbHyEaHyEaHyEaICIbHiAZICIbISMcICIbHyEaHiAZICIbHiAZISMcICIbICIbHyEaICIbICIbHyEaISMcHyEaICIbISMcHyEaHyEaHyEaHyEaHyEaICIbIiQdHiAZISMcHyEaHyEaIiQdICIbHiAZHyEaHR8YHyEaHyEaHyEaICIbHyEaICIbICIbIiQdISMcISMcHiAZICIbHyEaHiAZHyEaAAAhIx0gIhwfIRsgIhweIBkgIhsgIhsgIhshIxwfIRofIRofIRoiJB0fIRohIxwfIRogIhsfIRoeIBkhIxweIBkfIRohIxwgIhsgIhshIxwfIRofIRoeIBkeIBkgIhsfIRogIhseIBkhIxwhIxwfIRogIhshIxwfIRohIxwgIhsgIhshIxwfIRogIhshIxwgIhshIxwgIhsfIRogIhsgIhseIBkeIBkgIhsgIhseIBkfIRogIhshIxwfIRoAACEjHR8hGyAiHCEjHR8hGh8hGh8hGh8hGh8hGiAiGyIkHR4gGSEjHB8hGh8hGiIkHSAiGx4gGR8hGh0fGB8hGh8hGh8hGiAiGx8hGiAiGyAiGyIkHSEjHCEjHB4gGSAiGx8hGh4gGR8hGiAiGyAiGyAiGx8hGh4gGSAiGx8hGiAiGx8hGiAiGyEjHCAiGyAiGyAiGyEjHB8hGh8hGiEjHB8hGh8hGh8hGh8hGh0fGB8hGh8hGh8hGh8hGgAAISMdICIcHiAZICIcHyEaICIbISMcICIbHyEaHyEaICIbICIbHiAZHyEaHyEaICIbHyEaHyEaICIbHiAZHR8YHyEaHyEaICIbICIbICIbHyEaIiQdHyEaICIbHiAZHyEaHyEaICIbICIbHiAZHyEaHyEaIiQdHyEaICIbHiAZISMcHyEaICIbISMcHiAZICIbHiAZICIbHyEaICIbHiAZHyEaHR8YISMcHyEaICIbHiAZISMcISMcICIbAAAgIhwfIRshIx0fIRofIRohIxweIBkgIhsfIRohIxwfIRoeIBkiJB0fIRofIRogIhsgIhsgIhsfIRohIxwhIxwgIhsfIRogIhsgIhsgIhsfIRogIhsfIRofIRogIhsfIRogIhshIxwgIhsgIhsfIRodHxggIhsfIRofIRodHxgeIBkgIhsgIhsgIhsfIRoeIBkgIhseIBkfIRoeIBkeIBkeIBkgIhsgIhseIBkfIRogIhsfIRogIhsfIRoAAB4gGh4gGh8hGyEjHR4gGR8hGh0fGB8hGiAiGx4gGR4gGSAiGx8hGh0fGB8hGh8hGh8hGh4gGR8hGiAiGyAiGx4gGR8hGh8hGh8hGh4gGR8hGiAiGyAiGyIkHSAiGyAiGx8hGh8hGiAiGx8hGiAiGx8hGh8hGh8hGiEjHB0fGB8hGh8hGh8hGiAiGx8hGiAiGyAiGx8hGiAiGyEjHB8hGiAiGyAiGyAiGyAiGx4gGR8hGiAiGx8hGh0fGAAAQT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88QT88AABBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzxBPzwAAA=="

    "Should map file to serialized" - {
        "Point" - {
            ActionBootMousePoint.values().map { verifiedAction: ActionBootMousePoint ->
                verifiedAction.name {
                    // given
                    val type = "point"
                    val filePath = "$actionDirectory/$type/$verifiedAction.ab"

                    // when
                    val textFromFile = BrowserActionMapperTest::class.java.getResource(filePath).readText()
                    val rootGroupSerialized: RootSchemaGroupSerialized = AutomationMapper.toObject(textFromFile)
                    val serializedToModel = RootSchemaGroupMapper.serializedToModel(rootGroupSerialized, File("/"))

                    // then
                    serializedToModel.schemaGroupModel.shouldNotBeNullAndCheck {
                        actions shouldHaveSize 1
                        actions[0].shouldBeInstanceOfAndCheck<MousePointAction> {
                            actionBootType shouldBe verifiedAction
                            pointDetector.shouldBeInstanceOfAndCheck<SpecificPointDetector> {
                                point shouldBe Point(pointXFromFile, pointYFromFile)
                                point.vector shouldBe false
                            }
                        }
                    }
                }
            }
            "Vector" - {
                ActionBootMousePoint.values().map { verifiedAction: ActionBootMousePoint ->
                    verifiedAction.name {
                        // given
                        val type = "vector"
                        val filePath = "$actionDirectory/$type/$verifiedAction.ab"

                        // when
                        val textFromFile = BrowserActionMapperTest::class.java.getResource(filePath).readText()
                        val rootGroupSerialized: RootSchemaGroupSerialized = AutomationMapper.toObject(textFromFile)
                        val serializedToModel = RootSchemaGroupMapper.serializedToModel(rootGroupSerialized, File("/"))

                        // then
                        serializedToModel.schemaGroupModel.shouldNotBeNullAndCheck {
                            actions shouldHaveSize 1
                            actions[0].shouldBeInstanceOfAndCheck<MousePointAction> {
                                actionBootType shouldBe verifiedAction
                                pointDetector.shouldBeInstanceOfAndCheck<SpecificPointDetector> {
                                    point shouldBe Point(vectorXFromFile, vectorYFromFile, true)
                                    point.vector shouldBe true
                                }
                            }
                        }
                    }
                }
            }
            "Image" - {
                ActionBootMousePoint.values().map { verifiedAction: ActionBootMousePoint ->
                    verifiedAction.name {
                        // given
                        val type = "image"
                        val filePath = "$actionDirectory/$type/$verifiedAction.ab"

                        // when
                        val textFromFile = loadTextFromFile(filePath)
                        val rootGroupSerialized: RootSchemaGroupSerialized = AutomationMapper.toObject(textFromFile)
                        val serializedToModel = RootSchemaGroupMapper.serializedToModel(rootGroupSerialized, File("/"))

                        // then
                        serializedToModel.schemaGroupModel.shouldNotBeNullAndCheck {
                            actions shouldHaveSize 1
                            actions[0].shouldBeInstanceOfAndCheck<MousePointAction> {
                                actionBootType shouldBe verifiedAction
                                pointDetector.shouldBeInstanceOfAndCheck<ImagePointDetector> {
                                    templateByteArray shouldBe Base64Converter.base64ToByteArray(base64FromFile)
                                    threshold shouldBe thresholdFromFile
                                    ignoreIfNotFound shouldBe ignoreIfNotFoundFromFile
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    "Should map file to serialized to file" - {
        listOf("point", "vector", "image").map { type ->
            type.capitalize() - {
                ActionBootMousePoint.values().map { verifiedAction: ActionBootMousePoint ->
                    verifiedAction.name {
                        val filePath = "$actionDirectory/$type/$verifiedAction.ab"
                        testMapFileToSerializedToFile(filePath)
                    }
                }
            }
        }
    }

})