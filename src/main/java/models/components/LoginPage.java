package models.components;

public abstract class LoginPage {

    public void login () {
        System.out.println(userID());
        System.out.println(userPassword());
    }

    protected abstract String userID();
    protected abstract String userPassword();

}
