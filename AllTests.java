import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)

@SuiteClasses({ GebaeudeTest.class, 
				KundeTest.class, 
				KundeTestPMock.class,
				PlayerTest.class,
				PlayerTestPMock.class,
				MaschinenparkTest.class, 
				MitarbeiterschaftTest.class,
				MaterialTestPMock.class
				})

public class AllTests {

}

