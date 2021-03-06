package by.epam.javatraining.matrix.util;

import by.epam.javatraining.matrix.exception.UtilException;
import by.epam.javatraining.matrix.validator.Validator;
import org.junit.Assert;
import org.junit.Test;

public class StringParserTest {

    @Test(expected = UtilException.class)
    public void testParseStringToMatrixSizeNullDataParameterShouldThrowUtilException() throws UtilException {
        Validator validator = Validator.getInstance();
        String data = null;

        if (!validator.checkDataFromFile(data)) {
            throw new UtilException();
        }
    }

    @Test(expected = UtilException.class)
    public void testParseStringToMatrixSizeZeroParametersDataParameterShouldThrowUtilException() throws UtilException {
        Validator validator = Validator.getInstance();
        String data = "0 0";

        if (!validator.checkDataFromFile(data)) {
            throw new UtilException();
        }
    }

    @Test(expected = UtilException.class)
    public void testParseStringToMatrixSizeWrongThreadCountParameterShouldThrowUtilException() throws UtilException {
        Validator validator = Validator.getInstance();
        String data = "5 9";

        if (!validator.checkDataFromFile(data)) {
            throw new UtilException();
        }
    }

    @Test(expected = UtilException.class)
    public void testParseStringToMatrixSizeMoreThenTwoParameterShouldThrowUtilException() throws UtilException {
        Validator validator = Validator.getInstance();
        String data = "4 8 12 100";

        if (!validator.checkDataFromFile(data)) {
            throw new UtilException();
        }
    }

    @Test
    public void testParseStringToMatrixSizeShouldSizeResultTen() throws UtilException {
        Validator validator = Validator.getInstance();
        StringParser parser = StringParser.getInstance();
        String data = "10 20";

        if (!validator.checkDataFromFile(data)) {
            throw new UtilException();
        }

        int excepted = 10;

        int actual = parser.parseStringToMatrixSize(data);

        Assert.assertEquals(excepted, actual);
    }
}
