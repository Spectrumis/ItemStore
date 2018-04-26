package itemzhop;

public class User {
    private boolean isAdmin = false;
    private String nickName = "";

    public User(boolean isAdmin, String nickName, String password) {
        this.isAdmin = isAdmin;
        this.nickName = nickName;
        this.password = password;
    }
    public User( String nickName, String password) {

        this.nickName = nickName;
        this.password = password;
    }
    public boolean checkLogIn(String userName,String password){
        return userName == nickName && password == this.password;
    }
    public String getName(){
        return nickName;
    }
    public String getPassword(){
        return password;
    }
    private String password;
}
