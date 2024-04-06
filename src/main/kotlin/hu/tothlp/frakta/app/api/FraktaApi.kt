package hu.tothlp.hu.tothlp.frakta.app.api

import hu.tothlp.hu.tothlp.frakta.app.api.dto.CoffeeDto

interface FraktaApi {
	fun getVersion(): String
	fun addCoffee(request: CoffeeDto): Long
	fun getCoffee(id: Long): CoffeeDto
	fun listCoffees(): List<CoffeeDto>
	fun deleteCoffee(id: Long)
}