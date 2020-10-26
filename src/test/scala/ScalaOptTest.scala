import Main.Arguments
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ScalaOptTest extends AnyFlatSpec with Matchers {
  "Arguments" should "setup doBackup with default true" in {
    new Arguments(Seq()).doBackup() shouldBe true
  }

  it should "setup doBackup with true" in {
    new Arguments(Seq("--doBackup", "true")).doBackup() shouldBe true
  }

  it should "setup doBackup with false" in {
    new Arguments(Seq("--doBackup", "false")).doBackup() shouldBe false
  }

  it should "setup doBackup with empty true" in {
    new Arguments(Seq("--doBackup")).doBackup() shouldBe true
  }
}
