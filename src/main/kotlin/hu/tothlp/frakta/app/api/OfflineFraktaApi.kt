package hu.tothlp.hu.tothlp.frakta.app.api

import hu.tothlp.hu.tothlp.frakta.app.api.dto.CoffeeDto

class OfflineFraktaApi: FraktaApi {
	override fun getVersion(): String = "1.0"

	override fun addCoffee(request: CoffeeDto): Long {
		TODO("Not yet implemented")
	}

	override fun getCoffee(id: Long): CoffeeDto {
		TODO("Not yet implemented")
	}

	override fun listCoffees(): List<CoffeeDto> {
		TODO("Not yet implemented")
	}

	override fun deleteCoffee(id: Long) {
		TODO("Not yet implemented")
	}
}