import java.util.*;

/**
 * Created by ziyanzha on 8/23/17.
 */
public class RandomReplyGenerator implements ReplyGenerator{
    private String[] answers = {"gg", "lol", "nb"};
    public String getReply() {
        Random rand = new Random();
        return answers[rand.nextInt(3)];
    }
}
