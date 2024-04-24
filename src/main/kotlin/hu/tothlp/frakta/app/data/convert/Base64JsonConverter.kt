package hu.tothlp.frakta.app.data.convert

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*


inline fun <reified T> T.encodeEntry(): String {
	return Json.encodeToString(this).let { Base64.getEncoder().encodeToString(it.toByteArray()) }
}

inline fun <reified T> decodeEntry(source: String): T {
	return Base64.getDecoder().decode(source).let { String(it) }.let { Json.decodeFromString<T>(it) }
}
