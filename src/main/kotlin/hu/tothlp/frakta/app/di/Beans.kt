package hu.tothlp.hu.tothlp.frakta.app.di

import hu.tothlp.hu.tothlp.frakta.app.api.OfflineFraktaApi
import hu.tothlp.hu.tothlp.frakta.app.common.logger.ConsoleLoggerFactory
import hu.tothlp.hu.tothlp.frakta.app.data.repository.InMemoryCoffeeRepository
import hu.tothlp.hu.tothlp.frakta.app.service.SimpleCoffeeService

object Beans {

	fun init() {
		registerBean("loggerFactory", ConsoleLoggerFactory())
		registerBean("coffeeRepository", InMemoryCoffeeRepository())
		registerBean("coffeeService", SimpleCoffeeService())
		registerBean("fraktaApi", OfflineFraktaApi())
	}

	fun getBeanByName(name: String): Any? {
		return beanContainer[name]
	}

	inline fun <reified T> getBean(name: String? = null): T {
		return if (!name.isNullOrBlank()) getBeanByName(name) as T
		else beanContainer.values.first { it is T } as T
	}

	val beanContainer = mutableMapOf<String,Any>()

	private fun registerBean(name: String, bean: Any) {
		beanContainer[name] = bean
	}

}
