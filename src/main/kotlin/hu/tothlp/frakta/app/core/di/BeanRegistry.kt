package hu.tothlp.hu.tothlp.frakta.app.core.di

import com.github.ajalt.mordant.terminal.Terminal
import hu.tothlp.frakta.app.api.rest.RestHandler
import hu.tothlp.hu.tothlp.frakta.app.api.cli.CliHandler
import hu.tothlp.hu.tothlp.frakta.app.core.coffee.SimpleCoffeeService
import hu.tothlp.hu.tothlp.frakta.app.core.common.infrastructure.logger.ConsoleLoggerFactory
import hu.tothlp.hu.tothlp.frakta.app.core.version.VersionHandler
import hu.tothlp.hu.tothlp.frakta.app.data.repository.FileCoffeeRepository

object BeanRegistry {

	val beanContainer = mutableMapOf<String,Any>()

	var useRest: Boolean = false

	init {
		// TODO: Make configurable from cli args, which API bean to use. this way starting the app as a cli app, or a REST api server, can be configured.
		registerBean("terminal", Terminal())
		registerBean("versionHandler", VersionHandler())
		registerBean("loggerFactory", ConsoleLoggerFactory())
		registerBean("coffeeRepository", FileCoffeeRepository())
		registerBean("coffeeService", SimpleCoffeeService())
		if (useRest) registerBean("inputHandler", RestHandler())
		else registerBean("inputHandler", CliHandler())
	}

	fun getBeanByName(name: String): Any? {
		return beanContainer[name] ?: throw IllegalArgumentException("No bean found with name $name")
	}

	inline fun <reified T> getBean(name: String? = null): T {
		if (beanContainer.none { it.value is T }) throw IllegalArgumentException("No bean found for type ${T::class.simpleName}")
		return if (!name.isNullOrBlank()) getBeanByName(name) as T
		else beanContainer.values.first { it is T } as T
	}

	private fun registerBean(name: String, bean: Any) {
		beanContainer[name] = bean
	}

}
