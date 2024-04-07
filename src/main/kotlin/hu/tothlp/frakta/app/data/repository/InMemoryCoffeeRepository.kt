package hu.tothlp.hu.tothlp.frakta.app.data.repository

import hu.tothlp.hu.tothlp.frakta.app.core.coffee.CoffeeRepository
import hu.tothlp.hu.tothlp.frakta.app.core.common.model.Coffee
import hu.tothlp.hu.tothlp.frakta.app.data.repository.entity.CoffeeEntity

class InMemoryCoffeeRepository : CoffeeRepository {

	private val dataSource = mutableSetOf<CoffeeEntity>()
	override fun init() {
		dataSource.addAll(
			listOf(
				CoffeeEntity(
					id = generateId(),
					name = "Awaken Espresso",
					roaster = "Awaken",
					origin = "Colombia",
					roast = "Medium"
				),
				CoffeeEntity(
					id = generateId(),
					name = "Casino Mocca Filter",
					roaster = "Casino Mocca",
					origin = "Ethiopia",
					roast = "Light"
				),
				CoffeeEntity(
					id = generateId(),
					name = "Awaken Cascara",
					roaster = "Awaken",
					origin = "Panama",
					roast = "Dark"
				),
				CoffeeEntity(
					id = generateId(),
					name = "Casino Mocca Espresso",
					roaster = "Casino Mocca",
					origin = "Brazil",
					roast = "Medium"
				),
				CoffeeEntity(
					id = generateId(),
					name = "Awaken Nespresso Compatible",
					roaster = "Awaken",
					origin = "Vietnam",
					roast = "Light"
				),
				CoffeeEntity(
					id = generateId(),
					name = "Casino Mocca Kapszulás",
					roaster = "Casino Mocca",
					origin = "Guatemala",
					roast = "Medium"
				)
			)
		)
	}

	override fun addCoffee(coffee: Coffee): Long {
		val entity = coffee.toEntity()
		dataSource.add(entity)
		return entity.id
	}


	override fun getCoffee(id: Long): Coffee? {
		return dataSource.find { it.id == id }?.toModel()
	}

	override fun listAllCoffees(): List<Coffee> {
		return dataSource.map { it.toModel() }
	}

	override fun deleteCoffee(id: Long) {
		dataSource.removeIf { it.id == id }
	}

	private fun generateId() = dataSource.size.toLong() + 1

	private fun Coffee.toEntity() = CoffeeEntity(
		id = id.takeIf { it != 0L } ?: generateId(),
		name = this.name,
		roaster = this.roaster,
		origin = this.origin,
		roast = this.roast
	)

	private fun CoffeeEntity.toModel() = Coffee().also {
		it.id = this.id
		it.name = this.name
		it.roaster = this.roaster
		it.origin = this.origin
		it.roast = this.roast
	}
}