package itemzhop;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
public class File {

    public static FileWriter fileWriter ;
    public static FileReader fileReader ;

    public static final String USERSITEM= "usersItem.csv";
    public static final String SESSION= "session.csv";
    public static final String SALES= "sales.csv";
    public static final String ACCOUNT= "account.csv";
    public static final String FOLLOWERS= "account.csv";
    public static final String ITEMS= "items.csv";

    public static void AppendStringFile(String str,String option ){
        try {
            FileWriter fWriter = new FileWriter("null");
            switch (option) {
                case "USERITEM": fWriter = new FileWriter(USERSITEM,true); break;
                case "SESSION": fWriter = new FileWriter(SESSION,true); break;
                case "SALES": fWriter = new FileWriter(SALES,true); break;
                case "ACCOUNT": fWriter = new FileWriter(ACCOUNT,true); break;
                case "FOLLOWERS": fWriter = new FileWriter(FOLLOWERS,true); break;
                case "ITEMS": fWriter = new FileWriter(ITEMS,true); break;
            }

            BufferedWriter bw = new BufferedWriter(fWriter);
            PrintWriter out = new PrintWriter(bw);
            out.println(str);
            out.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
