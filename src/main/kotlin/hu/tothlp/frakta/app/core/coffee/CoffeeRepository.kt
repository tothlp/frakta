package hu.tothlp.hu.tothlp.frakta.app.core.coffee

import hu.tothlp.hu.tothlp.frakta.app.core.common.model.Coffee

interface CoffeeRepository {
	fun addCoffee(coffee: Coffee): Coffee?
	fun getCoffee(id: Long): Coffee?
	fun listAllCoffees(): List<Coffee>
	fun deleteCoffee(id: Long)
}