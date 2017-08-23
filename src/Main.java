import java.util.Random;
/**
 * Created by ziyanzha on 8/22/17.
 */
public class Main {
    public static void main(String[] args) {
       ReplyGenerator ag = null;
       ag = new PixelReplyGenerator("majin");
       System.out.println(ag.getReply());
    }
}
