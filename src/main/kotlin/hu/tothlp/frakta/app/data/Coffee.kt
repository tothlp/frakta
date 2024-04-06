package hu.tothlp.hu.tothlp.frakta.app.data

data class Coffee(
	var id: Long?,
	var name: String,
	var roaster: String,
	var origin: String,
	var roast: String,
) {
	constructor(
	) : this(null, "", "", "", "")
}
