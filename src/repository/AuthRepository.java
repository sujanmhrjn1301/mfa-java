package repository;

public class AuthRepository {
    private static final String DEFAULT_USERNAME = "sujan_mhrjn";
    private static final String DEFAULT_PASSWORD = "letmepass";
    private int generatedOTP;

    public boolean validateUser(String username, String password) {
        return username.equals(DEFAULT_USERNAME) && password.equals(DEFAULT_PASSWORD);
    }

    public int generateOTP() {
        generatedOTP = (int) (Math.random() * 900000) + 100000;
        return generatedOTP;
    }

    public boolean verifyOTP(int enteredOTP) {
        return enteredOTP == generatedOTP;
    }
}
