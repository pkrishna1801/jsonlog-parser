import java.nio.file.{Files, Paths}
import java.time.{Instant, ZoneId, ZonedDateTime}
import com.github.plokhotnyuk.jsoniter_scala.core._
import com.github.plokhotnyuk.jsoniter_scala.macros._

case class Source(createdDateTime: Long)
case class IndexRecord(_index: String, _source: Source)
case class TimeFeatures(dayOfYear: Int, dayOfWeek: Int, hourOfDay: Int)


object RealTimeJsonParser {
  implicit val sourceCodec: JsonValueCodec[Source] = JsonCodecMaker.make
  implicit val indexRecordCodec: JsonValueCodec[IndexRecord] = JsonCodecMaker.make
  implicit val indexRecordArrayCodec: JsonValueCodec[Array[IndexRecord]] = JsonCodecMaker.make

  def extractTimeFeatures(epochMillis: Long): TimeFeatures = {
    val instant = Instant.ofEpochMilli(epochMillis)
    val zdt = ZonedDateTime.ofInstant(instant, ZoneId.of("UTC"))
    TimeFeatures(
      dayOfYear = zdt.getDayOfYear,
      dayOfWeek = zdt.getDayOfWeek.getValue,
      hourOfDay = zdt.getHour
    )
  }
}


object Main {
  def main(args: Array[String]): Unit = {
    val inputJson = Files.readAllBytes(Paths.get("sample_sessions.json"))

    import RealTimeJsonParser._

    val records = readFromArray[Array[IndexRecord]](inputJson)

    records.foreach { rec =>
      val features = extractTimeFeatures(rec._source.createdDateTime)
      println(s"Extracted Features: $features")
    }
  }
}
