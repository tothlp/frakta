package hu.tothlp.hu.tothlp.frakta.app.core.common.model

data class Coffee(
	var id: Long = 0L,
	var name: String,
	var roaster: String,
	var origin: String,
	var roast: String,
) {
	constructor(
	) : this(0, "", "", "", "")
}
