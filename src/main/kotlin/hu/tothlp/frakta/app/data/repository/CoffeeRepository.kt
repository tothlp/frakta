package hu.tothlp.hu.tothlp.frakta.app.data.repository

import hu.tothlp.hu.tothlp.frakta.app.data.Coffee

interface CoffeeRepository {
	fun addCoffee(coffee: Coffee): Long
	fun getCoffee(id: Long): Coffee
	fun listCoffees(): List<Coffee>
	fun deleteCoffee(id: Long)
}