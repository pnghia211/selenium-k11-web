package models.components;

public class ExUserLogin extends LoginPage {
    @Override
    protected String userID() {
        return "External || userID";
    }

    @Override
    protected String userPassword() {
        return "External || userPassword";
    }
}
