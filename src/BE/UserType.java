package BE;

public class UserType {
    private int userType;
    private String userTypeName;

    public UserType(int userType, String userTypeName) {
        this.userType = userType;
        this.userTypeName = userTypeName;
    }

    public int getUserType() {
        return userType;
    }

    public String getUserTypeName() {
        return userTypeName;
    }
}
