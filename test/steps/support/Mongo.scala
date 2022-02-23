package steps.support

import io.sdkman.model.Application
import java.util.concurrent.TimeUnit
import org.mongodb.scala.{MongoClient, _}
import org.mongodb.scala.bson.collection.immutable.Document
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.bson.collection.immutable.Document
import org.mongodb.scala.{MongoClient, ScalaObservable, _}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Mongo {

  import Helpers._

  val codecRegistry = fromRegistries(fromProviders(classOf[Application]), DEFAULT_CODEC_REGISTRY)

  lazy val mongoClient = MongoClient("mongodb://localhost:27017")

  lazy val db = mongoClient.getDatabase("sdkman").withCodecRegistry(codecRegistry)

  lazy val appCollection: MongoCollection[Application] = db.getCollection("application")

  def insertAliveOk(): Seq[Completed] =
    appCollection.insertOne(Application("OK", "", "", "")).results()

  def insertAliveKo(): Seq[Completed] =
    appCollection.insertOne(Application("KO", "", "", "")).results()

  def insertCliVersions(
      stableCliVersion: String,
      betaCliVersion: String,
      stableNativeCliVersion: String
  ): Seq[Completed] =
    appCollection
      .insertOne(Application("OK", stableCliVersion, betaCliVersion, stableNativeCliVersion))
      .results()

  def dropAppCollection(): Seq[Completed] = appCollection.drop().results()
}

object Helpers {

  implicit class DocumentObservable[C](val observable: Observable[Document])
      extends ImplicitObservable[Document] {
    override val converter: (Document) => String = (doc) => doc.toJson
  }

  implicit class GenericObservable[C](val observable: Observable[C]) extends ImplicitObservable[C] {
    override val converter: (C) => String = (doc) => doc.toString
  }

  trait ImplicitObservable[C] {
    val observable: Observable[C]
    val converter: (C) => String

    def results(): Seq[C] = Await.result(observable.toFuture(), Duration(10, TimeUnit.SECONDS))

    def headResult() = Await.result(observable.head(), Duration(10, TimeUnit.SECONDS))

    def printResults(initial: String = ""): Unit = {
      if (initial.length > 0) print(initial)
      results().foreach(res => println(converter(res)))
    }

    def printHeadResult(initial: String = ""): Unit = println(s"$initial${converter(headResult())}")
  }

}
