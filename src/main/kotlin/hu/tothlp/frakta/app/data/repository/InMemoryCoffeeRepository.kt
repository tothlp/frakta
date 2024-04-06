package hu.tothlp.hu.tothlp.frakta.app.data.repository

import hu.tothlp.hu.tothlp.frakta.app.data.Coffee

class InMemoryCoffeeRepository: CoffeeRepository {
	override fun addCoffee(coffee: Coffee): Long {
		TODO("Not yet implemented")
	}

	override fun getCoffee(id: Long): Coffee {
		TODO("Not yet implemented")
	}

	override fun listCoffees(): List<Coffee> {
		TODO("Not yet implemented")
	}

	override fun deleteCoffee(id: Long) {
		TODO("Not yet implemented")
	}
}