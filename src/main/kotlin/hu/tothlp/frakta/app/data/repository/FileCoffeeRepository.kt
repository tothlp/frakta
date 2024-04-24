package hu.tothlp.hu.tothlp.frakta.app.data.repository

import hu.tothlp.frakta.app.data.convert.decodeEntry
import hu.tothlp.frakta.app.data.convert.encodeEntry
import hu.tothlp.hu.tothlp.frakta.app.core.coffee.CoffeeRepository
import hu.tothlp.hu.tothlp.frakta.app.core.common.model.Coffee
import hu.tothlp.hu.tothlp.frakta.app.data.entity.CoffeeEntity
import java.io.File

class FileCoffeeRepository(
) : CoffeeRepository {

	private val dataSource = mutableMapOf<Int, CoffeeEntity>()
	private var lastId = 0L
	private fun generateId() = ++lastId
	private var dataFile: File = File(dataFileName()).apply { this.createNewFile() }

	private fun dataFileName() = "data.db"

	init {
		dataFile.useLines { lines ->
			val items = lines.toList()
			if (items.none()) {
				dataFile.writer().use { writer ->
					dataSeed().mapIndexed { index, it ->
						dataSource[index] = it
						it.encodeEntry()
					}.forEach {
						writer.appendLine(it)
					}
				}
			} else {
				items.forEachIndexed { index, it ->
					dataSource[index] = decodeEntry(it)
				}
			}

		}
		println(dataSource)
		lastId = dataSource.values.maxOf { it.id }
	}


	private fun dataSeed() = listOf(
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

	override fun addCoffee(coffee: Coffee): Long {
		val entity = coffee.toEntity()
		dataFile.writer().use { it.appendLine(entity.encodeEntry()) }
		return entity.id
	}


	override fun getCoffee(id: Long): Coffee? {
		return dataSource.values.find { it.id == id }?.toModel()
	}

	override fun listAllCoffees(): List<Coffee> {
		return dataSource.values.map { it.toModel() }
	}

	override fun deleteCoffee(id: Long) {
		dataSource.entries.find { it.value.id == id }?.let {
			dataSource.remove(it.key)
		}
		val content = dataSource.values.joinToString(System.lineSeparator()) { it.encodeEntry() }
		dataFile.writeText(content)
	}

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