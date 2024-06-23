
import java.text.SimpleDateFormat;
import java.util.Date;

public class demo {

    public static void main(String[] args) {
//        Date currentDate = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//        String currString = formatter.format(currentDate);
//        
//        System.out.println(formatter.format(currentDate));
        java.util.Date utilDate = new java.util.Date();
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());
        System.out.println("utilDate:" + utilDate);
        System.out.println("sqlDate:" + sqlDate);

    }
}
