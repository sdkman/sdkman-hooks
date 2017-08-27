package repos

import db.MongoConnectivity
import org.mongodb.scala.{Document, MongoCollection}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{BeforeAndAfter, Matchers, WordSpec}
import play.api.Configuration
import repo.ApplicationRepo
import steps.support.Mongo

class ApplicationRepoSpec extends WordSpec with Matchers with ScalaFutures with BeforeAndAfter {

  before {
    Mongo.dropAppCollection()
  }

  "application repo" should {
    "determine if service is alive" when {
      "a valid OK is stored in the datastore" in new ApplicationRepo(new TestMongoConnectivity) {
        Mongo.insertAliveOk()
        whenReady(isAlive) { alive =>
          alive shouldBe true
        }
      }

      "no valid OK is stored in the datastore" in new ApplicationRepo(new TestMongoConnectivity) {
        Mongo.insertAliveKo()
        whenReady(isAlive) { alive =>
          alive shouldBe false
        }
      }
    }

    "determine stable cli version" in new ApplicationRepo(new TestMongoConnectivity) {
      Mongo.insertCliVersions("1.0.0", "2.0.0")
      whenReady(stableCliVersion) { version =>
        version shouldBe "1.0.0"
      }
    }

    "determine beta cli version" in new ApplicationRepo(new TestMongoConnectivity) {
      Mongo.insertCliVersions("1.0.0", "2.0.0")
      whenReady(betaCliVersion) { version =>
        version shouldBe "2.0.0"
      }
    }

    class TestMongoConnectivity extends MongoConnectivity(Configuration.empty) {
      override def appCollection: MongoCollection[Document] = Mongo.appCollection
    }

  }
}
