package selenium.pure.data.user;

public enum UserType {

    STANRARD("standard_user", "secret_sauce");

    private final String userName;
    private final String userPassword;

    UserType(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
