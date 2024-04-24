package hu.tothlp.hu.tothlp.frakta.app.api.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import com.github.ajalt.clikt.parameters.types.long
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.Terminal
import hu.tothlp.hu.tothlp.frakta.app.api.cli.Errors.fail
import hu.tothlp.hu.tothlp.frakta.app.core.coffee.CoffeeService
import hu.tothlp.hu.tothlp.frakta.app.core.common.dto.CoffeeDto
import hu.tothlp.hu.tothlp.frakta.app.core.di.BeanRegistry


class AddCoffeeCommand(
	private val coffeeService: CoffeeService = BeanRegistry.getBean<CoffeeService>(),
	private val terminal: Terminal = BeanRegistry.getBean<Terminal>()
) : CliktCommand(name = "add", help = "Add a new coffee") {
	private val name: String by option("-name").prompt()
	private val roaster: String by option("-roaster").prompt()
	private val origin: String by option("-origin").prompt()
	private val roast: String by option("-roast").prompt()
	override fun run() {
		val request = CoffeeDto(
			name = name,
			roaster = roaster,
			origin = origin,
			roast = roast,
		)
		val coffee = coffeeService.addCoffee(request) ?: fail("Failed to add coffee")
		terminal.info("Coffee added successfully")
		terminal.println(table {
			header {
				row(
					"id",
					"name",
					"roaster",
					"origin",
					"roast",
				)
			}
			body {
				row(coffee.id, coffee.name, coffee.roaster, coffee.origin, coffee.roast)
			}
		})
	}
}