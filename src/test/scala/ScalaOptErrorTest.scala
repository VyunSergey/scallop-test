import Main.Arguments
import org.rogach.scallop.exceptions.{ExcessArguments, WrongOptionFormat}
import org.rogach.scallop.throwError
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ScalaOptErrorTest extends AnyFlatSpec with Matchers {
  throwError.value = true

  "Arguments" should "throw error if not boolean argument provided" in {
    assertThrows[WrongOptionFormat] {
      new Arguments(Seq("--doBackup", "kek")).doBackup()
    }
  }

  it should "throw error if multiple not boolean arguments provided" in {
    assertThrows[WrongOptionFormat] {
      new Arguments(Seq("--doBackup", "lol", "kek")).doBackup()
    }
  }

  it should "throw error if multiple boolean arguments provided" in {
    assertThrows[ExcessArguments] {
      new Arguments(Seq("--doBackup", "true", "true")).doBackup()
    }

    assertThrows[ExcessArguments] {
      new Arguments(Seq("--doBackup", "true", "false")).doBackup()
    }

    assertThrows[ExcessArguments] {
      new Arguments(Seq("--doBackup", "false", "true")).doBackup()
    }

    assertThrows[ExcessArguments] {
      new Arguments(Seq("--doBackup", "false", "false")).doBackup()
    }
  }
}
