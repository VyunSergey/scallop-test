import org.rogach.scallop._

import scala.util.Try

object Main extends App {

  class Arguments(args: Seq[String]) extends ScallopConf(args) {
    def stringToBooleanConverter(converter: String => Boolean,
                                 default: => Option[Boolean]): ValueConverter[Boolean] =
      new ValueConverter[Boolean] {
        override def parse(s: List[(String, List[String])]): Either[String, Option[Boolean]] = s match {
          case (_, Nil) :: Nil => Right(default)
          case (_, i :: Nil) :: Nil => Try(Some(converter(i))).toEither.left.map(_ => s"Can`t convert argument '$i' to Boolean")
          case Nil => Right(default)
          case _ => Left(s"Can`t convert multiple arguments ${s.flatMap(_._2).mkString("['", "', '", "']")} to Boolean")
        }

        override val argType: ArgType.V = ArgType.LIST
      }

    val a: ScallopOption[String] = opt[String](name = "a", required = false)

    val doBackup: ScallopOption[Boolean] =
      opt[Boolean](name = "doBackup")(stringToBooleanConverter(_.trim.toLowerCase.toBoolean, default = Some(true)))

    verify()
  }

  val arguments = new Arguments(args)
  println(s"Arguments.doBackup = ${arguments.doBackup()}")

}
