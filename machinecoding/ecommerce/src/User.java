public class User {
    int userId;
    int userPincode;

    public User(int userId, int userPincode) {
        this.userId = userId;
        this.userPincode = userPincode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserPincode() {
        return userPincode;
    }

    public void setUserPincode(int userPincode) {
        this.userPincode = userPincode;
    }
}
