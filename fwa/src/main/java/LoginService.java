public class LoginService {

    public boolean isUserValid(String user, String password) {

        if ("pesho".equals(user) && "1".equals(password)) {
            return true;
        }

        return false;
    }
}
