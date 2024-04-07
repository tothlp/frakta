package hu.tothlp.hu.tothlp.frakta.app.api

import hu.tothlp.hu.tothlp.frakta.app.core.FraktaApi
import hu.tothlp.hu.tothlp.frakta.app.core.coffee.CoffeeService
import hu.tothlp.hu.tothlp.frakta.app.core.common.dto.CoffeeDto
import hu.tothlp.hu.tothlp.frakta.app.core.common.infrastructure.error.ServerError
import hu.tothlp.hu.tothlp.frakta.app.core.common.infrastructure.error.logger.Logger
import hu.tothlp.hu.tothlp.frakta.app.core.common.infrastructure.error.logger.LoggerFactory
import hu.tothlp.hu.tothlp.frakta.app.core.di.Beans.getBean
import java.util.*

class ConsoleFraktaApi(
	private val coffeeService: CoffeeService = getBean<CoffeeService>(),
	private val logger: Logger = getBean<LoggerFactory>().getLogger(ConsoleFraktaApi::class.java)
): FraktaApi {
	override fun getVersion(): String {
		val properties = Properties()
		properties.load(javaClass.classLoader.getResourceAsStream("version.properties"))
		return properties.getProperty("version") ?: "n/a"
	}

	override fun addCoffee(request: CoffeeDto): Long? {
		val id = try {
			coffeeService.addCoffee(request)
		} catch (e: ServerError) {
			logger.error("Error adding coffee: ${e.message}")
			null
		}
		return id
	}

	override fun getCoffee(id: Long): CoffeeDto? {
		val coffee = try {
			coffeeService.getCoffee(id)
		} catch (e: ServerError) {
			logger.error("Coffee not found with id $id: ${e.message}")
			null
		}
		return coffee
	}

	override fun listCoffees(): List<CoffeeDto> {
		return coffeeService.listCoffees()
	}

	override fun deleteCoffee(id: Long) {
		coffeeService.deleteCoffee(id)
	}
}