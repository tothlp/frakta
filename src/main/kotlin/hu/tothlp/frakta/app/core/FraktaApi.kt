package hu.tothlp.hu.tothlp.frakta.app.core

import hu.tothlp.hu.tothlp.frakta.app.core.common.dto.CoffeeDto

/**
 * This interface defines the API of the Frakta application. The main intent is to provide a common interface for communication.
 * The implementation can either define a REST API, use CLI arguments, or read directly from files (eg. via a Scheduler).
 */
interface FraktaApi {
	fun getVersion(): String
	fun addCoffee(request: CoffeeDto): Long?
	fun getCoffee(id: Long): CoffeeDto?
	fun listCoffees(): List<CoffeeDto>
	fun deleteCoffee(id: Long): Unit
}