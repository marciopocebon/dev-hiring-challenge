package dev.hiring.challenge.application.setup

import com.typesafe.config.ConfigFactory
import javax.sql.DataSource
import org.koin.dsl.module

object ConfigModule {

    val module = module {
        single { EnvironmentConfig(ConfigFactory.load()) }
        single { ObjectMapperProvider.provide() }
        single<DataSource> {
            with(get<EnvironmentConfig>()) {
                DataSourceProvider.provide(databaseUrl, databaseUsername, databasePassword)
            }
        }
    }
}
