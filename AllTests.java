import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)

@SuiteClasses({ GebaeudeTest.class, 
				KundeHighTest.class, 
				PlayerTest.class,
				MaschinenparkTest.class, 
				MitarbeiterschaftTest.class
				})

public class AllTests {

}
