package tests;
import object.CalculatorPage;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class DataDrivenTest extends WebDriverSet {
    private static CalculatorPage calc;

    @Parameterized.Parameter
    public String expression;

    @Parameterized.Parameter(1)
    public String expected;

    @BeforeClass
    public static void setup() {
        calc = new CalculatorPage(driver);
        calc.open();
    }

    @Before
    public void cleanup() {
        calc.clear();
    }

    @Parameterized.Parameters(name = "Test: {0}={1}")
    public static Collection <Object[]> data(){
        return Arrays.asList(new Object[][]{
                { "",""},
                { "0+1","1"},
                { "0-+10","-10"},
                { "0*-10","0"},
                { "1*123","123"},
                { "-4-4","-8"},
                { "-4--4","0"},
                { "-4-+4","-4"},
                { "1.0001*1000","1000.1"},
                { "1.+1.1", "2.1" },
                { "2+.5", "2.5" },
                { "999999+1","1000000"},
                { "10/0","Error"},
                { "0/0","Error"},
                { "0.7*10","7"},
                { "2++","2++"},
                { "*2","*2"},
                { "-1/10","-0.1"},
                { "-10/-1000","0.01"},
                { "1.00000000001*0.0000000009","9.00000000009e-10"},


        });
    }

    @Test
    public void calculator_DataDrivenTest(){
        Assert.assertEquals(expected, calc.calculate(expression));
    }

    @AfterClass
    public static void stop() {
        driver.quit();
    }

}
