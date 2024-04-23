package hu.tothlp.hu.tothlp.frakta.app.api

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.options.versionOption
import com.github.ajalt.clikt.parameters.types.long
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.Terminal
import hu.tothlp.hu.tothlp.frakta.app.api.Errors.fail
import hu.tothlp.hu.tothlp.frakta.app.core.coffee.CoffeeService
import hu.tothlp.hu.tothlp.frakta.app.core.di.BeanRegistry.getBean
import hu.tothlp.hu.tothlp.frakta.app.core.interaction.InteractionHandler
import java.util.*

class CliHandler : InteractionHandler {

	override fun start(argv: List<String>) = try {
		FraktaCli()
		.versionOption(getVersion(), names = setOf("--version", "-v"))
		.subcommands(listOf(
			ListCoffees(),
			GetCoffee(),
		)).main(argv)
	} catch (e: Exception) {
		val t = Terminal()
		t.danger("An error occurred: ${e.message}")
	}

	private fun getVersion(): String {
		val properties = Properties()
		properties.load(javaClass.classLoader.getResourceAsStream("version.properties"))
		return properties.getProperty("version") ?: "n/a"
	}

	class FraktaCli : CliktCommand() {
		override fun run() = Unit
	}

	class ListCoffees(
		private val coffeeService: CoffeeService = getBean<CoffeeService>()
	) : CliktCommand() {
		override fun run() {
			val coffees = coffeeService.listCoffees()
			val t = Terminal()
			t.println(table {
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
					coffees.forEach {
						row(it.id, it.name, it.roaster, it.origin, it.roast)
					}
				}
			})
		}
	}

	class GetCoffee(
		private val coffeeService: CoffeeService = getBean<CoffeeService>()
	) : CliktCommand() {
		private val coffeeId by option().long().required()

		override fun run() {
			val t = Terminal()
			val coffee = coffeeService.getCoffee(coffeeId) ?: fail("Coffee not found")
			t.println(table {
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
}

object Errors{
	fun fail(message: String): Nothing = throw Exception(message)
}