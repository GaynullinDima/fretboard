package org.mozilla.fretboard.storage

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import org.mozilla.fretboard.config.FretboardConfiguration


// TODO Decide with API
interface ConfigStorage {
    fun getExperimentJson(config: FretboardConfiguration): String?
    fun setExperimentJson(config: FretboardConfiguration, experimentJson: String)
    fun getOverrideValue(config: FretboardConfiguration, experimentName: String): Boolean?
    fun setOverrideValue(config: FretboardConfiguration, experimentName: String, isEnabled: Boolean)
    fun clearOverrideValue(config: FretboardConfiguration, experimentName: String)
}

class SharedPreferenceConfigStorage : ConfigStorage {

    //TODO add config to storage and refactor
    private val TAG = "SharedPrefConfigStorage"
    private val fretBoardSettings = "org.mozilla.fretboard.settings"

    private val CONFIG_JSON = "config-json"
    private val OVERRIDE_PREFIX = "experiment.override."


    override fun getExperimentJson(config: FretboardConfiguration): String? {
        val context = config.getContext()
        if (context != null) {
            val prefs: SharedPreferences = context.getApplicationContext().getSharedPreferences(
                    fretBoardSettings, Context.MODE_PRIVATE)
            return prefs.getString(CONFIG_JSON, null)
        } else return null
    }

    override fun setExperimentJson(config: FretboardConfiguration, configJson: String) {
        val context = config.getContext()
        if (context != null) {
            val sharedPref = context.applicationContext.getSharedPreferences(
                    fretBoardSettings, Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString(CONFIG_JSON, configJson)
                apply()
            }
        }
        Log.d(TAG, "The context is null, so sharedpreference is not edited")
    }


    override fun getOverrideValue(config: FretboardConfiguration, experimentName: String): Boolean? {
        val context = config.getContext()
        if (context != null) {
            val sharedPref = context.applicationContext.getSharedPreferences(
                    fretBoardSettings, Context.MODE_PRIVATE)

            val key = OVERRIDE_PREFIX + experimentName
            return if (sharedPref.contains(key)) {
                // This will never fall back to the default value.
                sharedPref.getBoolean(key, false)
            } else null
        } else return null
        // Default to returning null if no override was found.
    }


    override fun setOverrideValue(config: FretboardConfiguration, experimentName: String,
                                  isEnabled: Boolean) {
        val context = config.getContext()
        if (context != null) {
            val sharedPref = context.applicationContext.getSharedPreferences(
                    fretBoardSettings, Context.MODE_PRIVATE)

            with(sharedPref.edit()) {
                putBoolean(OVERRIDE_PREFIX + experimentName, isEnabled)
                apply()
            }
        }
        Log.d(TAG, "context is null, so setoverridvalue isn't edited")
    }


    override fun clearOverrideValue(config: FretboardConfiguration, experimentName: String) {
        val context = config.getContext()
        if (context != null) {
            val sharedPref = context.applicationContext.getSharedPreferences(
                    fretBoardSettings, Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                remove(OVERRIDE_PREFIX + experimentName)
                apply()
            }
        }
        Log.d(TAG, "Context is null, so override value didn't change")
    }
}