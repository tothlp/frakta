package hu.tothlp.hu.tothlp.frakta.app.core.common.dto

data class CoffeeDto(
	var id: Long? = null,
	val name: String,
	val roaster: String,
	val origin: String,
	val roast: String,
)
