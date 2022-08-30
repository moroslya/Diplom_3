package site.nomoreparties.stellarburgers.data;

public class User {

    private String email;
    private String password;
    private String name;

    private String accessToken;
    private String token;

    public User(String email, String password, String name) {

        this.email = email;
        this.password = password;
        this.name = name;

    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getAccessToken() {
        return accessToken;
    }

}
