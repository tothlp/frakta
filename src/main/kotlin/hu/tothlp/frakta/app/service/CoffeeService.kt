package hu.tothlp.hu.tothlp.frakta.app.service

import hu.tothlp.hu.tothlp.frakta.app.api.dto.CoffeeDto

interface CoffeeService {
	fun addCoffee(request: CoffeeDto): Long
	fun getCoffee(id: Long): CoffeeDto
	fun listCoffees(): List<CoffeeDto>
	fun deleteCoffee(id: Long)
}