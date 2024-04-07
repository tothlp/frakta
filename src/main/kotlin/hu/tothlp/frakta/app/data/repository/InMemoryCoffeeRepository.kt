package hu.tothlp.hu.tothlp.frakta.app.data.repository

import hu.tothlp.hu.tothlp.frakta.app.core.coffee.CoffeeRepository
import hu.tothlp.hu.tothlp.frakta.app.core.common.model.Coffee

class InMemoryCoffeeRepository: CoffeeRepository {

	private val dataSource = mutableSetOf<Coffee>()

	override fun addCoffee(coffee: Coffee): Long {
		coffee.id = dataSource.size.toLong() + 1
		dataSource.add(coffee)
		return coffee.id
	}

	override fun getCoffee(id: Long): Coffee? {
		return dataSource.find { it.id == id }
	}

	override fun listAllCoffees(): List<Coffee> {
		return dataSource.toList()
	}

	override fun deleteCoffee(id: Long) {
		dataSource.removeIf { it.id == id }
	}
}