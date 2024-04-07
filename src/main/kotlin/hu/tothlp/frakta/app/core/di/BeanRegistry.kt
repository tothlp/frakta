package hu.tothlp.hu.tothlp.frakta.app.core.di

import hu.tothlp.hu.tothlp.frakta.app.api.CliHandler
import hu.tothlp.hu.tothlp.frakta.app.api.ConsoleFraktaApi
import hu.tothlp.hu.tothlp.frakta.app.core.coffee.SimpleCoffeeService
import hu.tothlp.hu.tothlp.frakta.app.core.common.infrastructure.error.logger.ConsoleLoggerFactory
import hu.tothlp.hu.tothlp.frakta.app.data.repository.InMemoryCoffeeRepository

object BeanRegistry {

	val beanContainer = mutableMapOf<String,Any>()

	fun init() {
		// TODO: Make configurable from cli args, which API bean to use. this way starting the app as a cli app, or a REST api server, can be configured.
		registerBean("loggerFactory", ConsoleLoggerFactory())
		registerBean("coffeeRepository", InMemoryCoffeeRepository())
		registerBean("coffeeService", SimpleCoffeeService())
		registerBean("fraktaApi", ConsoleFraktaApi())
		registerBean("inputHandler", CliHandler())
	}

	fun getBeanByName(name: String): Any? {
		return beanContainer[name]
	}

	inline fun <reified T> getBean(name: String? = null): T {
		return if (!name.isNullOrBlank()) getBeanByName(name) as T
		else beanContainer.values.first { it is T } as T
	}

	private fun registerBean(name: String, bean: Any) {
		beanContainer[name] = bean
	}

}
