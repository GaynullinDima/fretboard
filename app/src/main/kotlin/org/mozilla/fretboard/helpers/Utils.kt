package org.mozilla.fretboard.helpers

import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader


fun readStream(inputStream: BufferedInputStream): String {
    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
    val stringBuilder = StringBuilder()
    bufferedReader.forEachLine { stringBuilder.append(it) }
    return stringBuilder.toString()
}

