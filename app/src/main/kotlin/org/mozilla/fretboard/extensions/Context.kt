package org.mozilla.fretboard.extensions

import android.content.Context
import org.mozilla.fretboard.helpers.CONFIG_PREFS_KEY
import org.mozilla.fretboard.helpers.STORAGE_PREFS_KEY


fun Context.getConfigSharedPrefs() = getSharedPreferences(CONFIG_PREFS_KEY, Context.MODE_PRIVATE)

fun Context.getStorageSharedPrefs() = getSharedPreferences(STORAGE_PREFS_KEY, Context.MODE_PRIVATE)

