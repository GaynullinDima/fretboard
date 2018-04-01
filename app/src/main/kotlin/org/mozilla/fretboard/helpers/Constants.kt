package org.mozilla.fretboard.helpers

// Kinto connection
const val DEFAULT_USER_AGENT = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:59.0) Gecko/20100101 Firefox/59.0"
const val DEFAULT_PASSWORD = "my-secret"
const val DEFAULT_USER_NAME = "token"
const val DEFAULT_SERVER_ENDPOINT = "https://kinto.dev.mozaws.net"
const val DEFAULT_CONNECT_TIMEOUT = 10000
const val DEFAULT_READ_TIMEOUT = 30000
const val DEFAULT_TIMESTAMP = "0"
const val DEFAULT_SERVER_PATH = "/v1/buckets/default/collections/tasks/records"


// JobScheduler
const val DEFAULT_PERIODIC_INTERVAL: Long = 90000
const val DEFAULT_INITAL_BACKOFF_FOR_UPLOAD: Long = 30000


// Configuration Shared Preferences
const val CONFIG_PREFS_KEY = "org.mozilla.fretboard.config"
const val USER_AGENT = "user_agent"
const val PASSWORD = "auth_password"
const val USER_NAME = "user_name"
const val SERVER_ENDPOINT = "server_endpoint"
const val CONNECT_TIMEOUT = "connect_timeout"
const val READ_TIMEOUT = "read_timeout"
const val TIMESTAMP = "timestamp"
const val SERVER_PATH = "server_path"
const val INITAL_BACKOFF_FOR_UPLOAD = "initial_backoff_for_upload"
const val PERIODIC_INTERVAL = "periodic_interval"

// Storage Shared Preferences
const val STORAGE_PREFS_KEY = "org.mozilla.fretboard.storage"
const val CONFIG_JSON = "config-json"
const val OVERRIDE_PREFIX = "experiment.override."


// JSON Experiment match keys
const val KEY_APP_ID = "appId"
const val KEY_COUNTRY = "country"
const val KEY_REGION = "regions"
const val KEY_DEVICE = "device"
const val KEY_LANG = "lang"
const val KEY_MANUFACTURER = "manufacturer"
const val KEY_VERSION = "version"

// JSON Experiment bucket keys
const val KEY_MIN = "min"
const val KEY_MAX = "max"