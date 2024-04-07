package hu.tothlp.hu.tothlp.frakta.app.core.coffee

import hu.tothlp.hu.tothlp.frakta.app.core.common.model.Coffee

interface CoffeeRepository {
	fun init()
	fun addCoffee(coffee: Coffee): Long
	fun getCoffee(id: Long): Coffee?
	fun listAllCoffees(): List<Coffee>
	fun deleteCoffee(id: Long)
}