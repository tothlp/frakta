package hu.tothlp.hu.tothlp.frakta.app.core.version

import java.util.*

class VersionHandler {
	fun getVersion(): String {
		val properties = Properties()
		properties.load(javaClass.classLoader.getResourceAsStream("version.properties"))
		return properties.getProperty("version") ?: "n/a"
	}
}