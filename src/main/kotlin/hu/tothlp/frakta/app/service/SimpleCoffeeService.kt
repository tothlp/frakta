package hu.tothlp.hu.tothlp.frakta.app.service

import hu.tothlp.hu.tothlp.frakta.app.api.dto.CoffeeDto
import hu.tothlp.hu.tothlp.frakta.app.data.Coffee
import hu.tothlp.hu.tothlp.frakta.app.data.repository.CoffeeRepository
import hu.tothlp.hu.tothlp.frakta.app.di.Beans.getBean

class SimpleCoffeeService(
	private val coffeeRepository: CoffeeRepository = getBean("coffeeRepository") as CoffeeRepository
): CoffeeService {
	override fun addCoffee(request: CoffeeDto): Long {
		val coffee = request.toCoffee()
		return coffeeRepository.addCoffee(coffee)
	}

	override fun getCoffee(id: Long): CoffeeDto {
		TODO("Not yet implemented")
	}

	override fun listCoffees(): List<CoffeeDto> {
		TODO("Not yet implemented")
	}

	override fun deleteCoffee(id: Long) {
		TODO("Not yet implemented")
	}

	private fun CoffeeDto.toCoffee() = Coffee().also {
		it.name = this.name
		it.roaster = this.roaster
		it.origin = this.origin
		it.roast = this.roast
	}
}