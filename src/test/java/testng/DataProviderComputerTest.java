package testng;

import org.testng.annotations.Test;

public class DataProviderComputerTest {

    @Test(dataProvider = "computerData")
    public void testDataProviderComputer() {
        System.out.println();
    }


}

