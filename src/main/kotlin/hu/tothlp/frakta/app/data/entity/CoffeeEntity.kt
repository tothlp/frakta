package hu.tothlp.hu.tothlp.frakta.app.data.entity

import kotlinx.serialization.Serializable

@Serializable
class CoffeeEntity(
	var id: Long = 0L,
	var name: String,
	var roaster: String,
	var origin: String,
	var roast: String,
)