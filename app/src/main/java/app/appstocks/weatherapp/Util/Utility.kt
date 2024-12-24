package app.appstocks.weatherapp.Util

class Utility {
    companion object {
        var http = "https://"
        fun get128IconUrl(url: String): String {
            var icon128 = url.replace("64", "128")
            return "$http$icon128"
        }
    }
}