package support.verification;

public class Verifier {
    public static void verifyEquals(String actualResult, String expectedResult) {
        if(!actualResult.equals(expectedResult)) {
            throw new AssertionError("Actual result and expected result is different");
        }
    }
}
