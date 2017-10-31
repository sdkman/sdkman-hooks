package repo

import javax.inject.{Inject, Singleton}

import db.MongoConnectivity
import org.mongodb.scala.Document
import org.mongodb.scala.bson.BsonString

import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class ApplicationRepo @Inject()(mongo: MongoConnectivity) {

  def isAlive: Future[Boolean] = fetchApplicationRow().map { implicit doc =>
    extractKey("alive").contains("OK")
  }

  def stableCliVersion: Future[String] = fetchApplicationRow().map(implicit doc => extractKey("stableCliVersion").getOrElse("notfound"))

  def betaCliVersion: Future[String] = fetchApplicationRow().map(implicit doc => extractKey("betaCliVersion").getOrElse("notfound"))

  private def extractKey(key: String)(implicit doc: Document): Option[String] = doc.get[BsonString](key).map(_.getValue)

  private def fetchApplicationRow() = mongo.appCollection.find().first().head
}
