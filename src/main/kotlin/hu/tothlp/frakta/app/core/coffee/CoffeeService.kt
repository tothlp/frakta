package hu.tothlp.hu.tothlp.frakta.app.core.coffee

import hu.tothlp.hu.tothlp.frakta.app.core.common.dto.CoffeeDto
import hu.tothlp.hu.tothlp.frakta.app.core.common.infrastructure.error.ServerError

interface CoffeeService {
	@Throws(ServerError::class)
	fun addCoffee(request: CoffeeDto): CoffeeDto?
	fun getCoffee(id: Long): CoffeeDto?
	fun listCoffees(): List<CoffeeDto>
	fun deleteCoffee(id: Long)
}