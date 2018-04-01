package org.mozilla.fretboard.storage

import org.mozilla.fretboard.extensions.getStorageSharedPrefs
import org.mozilla.fretboard.helpers.CONFIG_JSON
import org.mozilla.fretboard.helpers.FretboardConfiguration


// TODO Decide with API
interface FretboardExperimentsStorage {
    fun getExperimentJson(): String?
    fun setExperimentJson(experimentJson: String)
}

class SharedPreferenceFretboardExperimentsStorage(configuration: FretboardConfiguration)
    : FretboardExperimentsStorage {
    private val TAG = "SharedPrefConfigStorage"

    private val prefs = configuration.context.getStorageSharedPrefs()

    override fun getExperimentJson(): String? = prefs.getString(CONFIG_JSON, null)

    override fun setExperimentJson(experimentJson: String) =
            prefs.edit().putString(CONFIG_JSON, experimentJson).apply()

}