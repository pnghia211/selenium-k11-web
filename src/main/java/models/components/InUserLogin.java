package models.components;

public class InUserLogin extends LoginPage {
    @Override
    protected String userID() {
        return "Internal || userID";
    }

    @Override
    protected String userPassword() {
        return "Internal || userPassword";
    }
}
