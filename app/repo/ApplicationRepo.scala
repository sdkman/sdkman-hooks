package repo

import com.typesafe.config.{Config, ConfigFactory}
import io.sdkman.db.{MongoConfiguration, MongoConnectivity}
import io.sdkman.repos.{ApplicationRepo => AppRepo}
import javax.inject.Singleton

@Singleton
class ApplicationRepo extends AppRepo with MongoConnectivity with MongoConfiguration {
  override val config: Config = ConfigFactory.load()
}
