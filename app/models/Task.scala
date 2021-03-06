package models

import java.sql.Timestamp

import play.api.libs.json._

case class Task(id: Int, project_id: Int, ts: Timestamp, duration: Long, volume: Option[Int], description: Option[String], isDeleted: Option[Timestamp])

object Task {
  def timestampToString(t: Timestamp): String = t.toString
  def stringToTimestamp(dt: String): Timestamp = Timestamp.valueOf(dt)

  implicit val timestampFormat: Format[Timestamp] = new Format[Timestamp] {
    def writes(t: Timestamp): JsValue = Json.toJson(timestampToString(t))
    def reads(json: JsValue): JsResult[Timestamp] = Json.fromJson[String](json).map(stringToTimestamp)
  }

  implicit val format: Format[Task] = Json.format
}
