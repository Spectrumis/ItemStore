package itemzhop;
import java.io.*;
import java.util.*;
public class FileOpearation {

    public static FileWriter fileWriter ;
    public static FileReader fileReader ;

    public static final String USERSITEMS= "F:\\GitKrakenRepository\\CSE222Project\\ItemStore\\UiWithDesign\\usersItems.csv";
    public static final String SESSION= "session.csv";
    public static final String SALES= "sales.csv";
    public static final String ACCOUNT= "account.csv";
    public static final String FOLLOWERS= "account.csv";
    public static final String ITEMS= "items.csv";

    public static void AppendStringFile(String str,String option ){
        try {
            switch (option) {
                case "USERITEMS": fileWriter = new FileWriter(USERSITEMS,true); break;
                case "SESSION": fileWriter = new FileWriter(SESSION,true); break;
                case "SALES": fileWriter = new FileWriter(SALES,true); break;
                case "ACCOUNT": fileWriter = new FileWriter(ACCOUNT,true); break;
                case "FOLLOWERS": fileWriter = new FileWriter(FOLLOWERS,true); break;
                case "ITEMS": fileWriter = new FileWriter(ITEMS,true); break;
            }
            if(fileWriter != null){
                fileWriter.write(str+"\n");
                fileWriter.close();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public static int getSessionId() {

        BufferedReader getSession = null;
        try {
            String line ;
            String[] tmpUser = new String[0];

            getSession = new BufferedReader(new FileReader(SESSION));
            if ((line = getSession.readLine()) != null) 
             tmpUser = line.split(",");
             return Integer.parseInt(tmpUser[0].toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
