package aoc.utils

import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

fun pr(message: Any?) {
    println(message)

    val stringSelection = StringSelection(message.toString())
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(stringSelection, null)
}
