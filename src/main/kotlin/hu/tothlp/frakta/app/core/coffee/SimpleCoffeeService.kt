package hu.tothlp.hu.tothlp.frakta.app.core.coffee

import hu.tothlp.hu.tothlp.frakta.app.core.common.dto.CoffeeDto
import hu.tothlp.hu.tothlp.frakta.app.core.common.infrastructure.error.failNotFoundError
import hu.tothlp.hu.tothlp.frakta.app.core.common.infrastructure.logger.Logger
import hu.tothlp.hu.tothlp.frakta.app.core.common.infrastructure.logger.LoggerFactory
import hu.tothlp.hu.tothlp.frakta.app.core.common.model.Coffee
import hu.tothlp.hu.tothlp.frakta.app.core.di.BeanRegistry.getBean

class SimpleCoffeeService(
	private val coffeeRepository: CoffeeRepository = getBean<CoffeeRepository>(),
	private val logger: Logger = getBean<LoggerFactory>().getLogger(SimpleCoffeeService::class.java)
): CoffeeService {
	override fun addCoffee(request: CoffeeDto): Long? {
		val coffee = request.toCoffee()
		return coffeeRepository.addCoffee(coffee)
	}

	override fun getCoffee(id: Long): CoffeeDto {
		return coffeeRepository.getCoffee(id)?.toDto() ?: failNotFoundError("Coffee not found with id: $id")
	}

	override fun listCoffees(): List<CoffeeDto> {
		return coffeeRepository.listAllCoffees().map { it.toDto() }
	}

	override fun deleteCoffee(id: Long) {
		coffeeRepository.deleteCoffee(id)
	}

	private fun CoffeeDto.toCoffee() = Coffee().also {
		it.name = this.name
		it.roaster = this.roaster
		it.origin = this.origin
		it.roast = this.roast
	}

	private fun Coffee.toDto() = CoffeeDto(
		id = this.id,
		name = this.name,
		roaster = this.roaster,
		origin = this.origin,
		roast = this.roast,
	)
}