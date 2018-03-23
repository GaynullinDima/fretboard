package org.mozilla.fretboard

import android.content.Context
import android.content.SharedPreferences
import android.R.id.edit


// TODO Decide with API
interface ConfigStorage {
    fun getConfigJson(context: Context): String?
    fun setConfigJson(context: Context, configJson: String)
    fun getOverrideValue(context: Context, experimentName: String): Boolean?
    fun setOverrideValue(context : Context, experimentName: String, isEnabled: Boolean)
    fun clearOverrideValue(context: Context, experimentName: String)
}

class SharedPreferenceConfigStorage : ConfigStorage {
    private val fretBoardSettings = "org.mozilla.fretboard.settings"

    private val CONFIG_JSON = "config-json"
    private val OVERRIDE_PREFIX = "experiment.override."

    override fun getConfigJson(context: Context): String? {
        val prefs: SharedPreferences = context.getApplicationContext().getSharedPreferences(fretBoardSettings, Context.MODE_PRIVATE)
        return prefs.getString(CONFIG_JSON, null)
    }

    override fun setConfigJson(context: Context, configJson: String) {
        val sharedPref = context.applicationContext.getSharedPreferences(
                fretBoardSettings, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(CONFIG_JSON, configJson)
            apply()
        }
    }


    override fun getOverrideValue(context: Context, experimentName: String): Boolean? {
        val sharedPref = context.applicationContext.getSharedPreferences(
                fretBoardSettings, Context.MODE_PRIVATE)

        val key = OVERRIDE_PREFIX + experimentName
        return if (sharedPref.contains(key)) {
            // This will never fall back to the default value.
            sharedPref.getBoolean(key, false)
        } else null

        // Default to returning null if no override was found.
    }


    override fun setOverrideValue(context: Context, experimentName: String, isEnabled: Boolean) {
        val sharedPref = context.applicationContext.getSharedPreferences(
                fretBoardSettings, Context.MODE_PRIVATE)

        with(sharedPref.edit()) {
            putBoolean(OVERRIDE_PREFIX + experimentName, isEnabled)
            apply()
        }
    }


    override fun clearOverrideValue(context: Context, experimentName: String) {
        val sharedPref = context.applicationContext.getSharedPreferences(
                fretBoardSettings, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            remove(OVERRIDE_PREFIX + experimentName)
            apply()
        }
    }

}