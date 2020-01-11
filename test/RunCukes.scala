import io.cucumber.junit.CucumberOptions
import io.cucumber.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("features"),
  glue = Array("steps"),
  tags = Array("not @pending"),
  plugin = Array("html:target/report.html", "pretty")
)
class RunCukes
