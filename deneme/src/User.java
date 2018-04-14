import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.LinkedList;

public class User {
    private boolean isAdmin = false;
    private String nickName = "";

    public User(boolean isAdmin, String nickName, String password) {
        this.isAdmin = isAdmin;
        this.nickName = nickName;
        this.password = password;
    }
    public boolean checkLogIn(String userName,String password){
        return userName == nickName && password == this.password;
    }
    private String password;
}
