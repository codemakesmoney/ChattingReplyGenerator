
import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

/**
 * Created by ziyanzha on 8/22/17.
 */
public class PixelReplyGenerator implements ReplyGenerator{
    private Map<Character, PixelLetter> patterns;
    private final static String PIXEL_PATTERN_JSON_FILE = "template//PixelPatternJSON";
    private final static String PIXEL_PATTERN_XML_FILE = "..//template//PixelPatternXML";
    private String inputStr;
    @Override
    public String getReply() {
        StringBuilder sb = new StringBuilder();
        PixelLetter plCurr = null;
        Integer width;
        String emoji;
        Integer[] pixelRow;
        for (Integer i = 0; i < PixelLetter.LETTER_HEIGHT; i++) {
            for (Character c:inputStr.toCharArray()) {
                c = Character.toUpperCase(c);
                if (patterns.containsKey(c)) {
                    plCurr = patterns.get(c);
                    width = plCurr.getWidth();
                    emoji = plCurr.getEmoji();
                    pixelRow = plCurr.getPixelByRow(i);
                    for (Integer j = 0; j < width; j++) {
                        if (pixelRow[j] == 1) {
                            sb.append(emoji);
                        } else {
                            /* TODO: Solve the problem that emoji and space doesn't have same width. */

                            sb.append("     ");
                        }
                    }
                    sb.append("      ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    public PixelReplyGenerator() {
    }

    public PixelReplyGenerator(String inputStr) {
        patterns = new HashMap<Character, PixelLetter>();
        //HardCodedPatterns();
        ReadPatternsFromJSON();
        this.inputStr = inputStr;
    }

    /* TODO: find a parser without denpendencies */
    private void ReadPatternsFromJSON() {
        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(PIXEL_PATTERN_JSON_FILE));
            Iterator<JSONObject> iterator = jsonArray.iterator();

            while (iterator.hasNext()) {
                JSONObject jsonObject = iterator.next();

                Character c = ((String) jsonObject.get("char")).charAt(0);
                Integer width = Integer.parseInt((String) jsonObject.get("width"));
                String pixel = (String) jsonObject.get("pixel");
                String emoji = (String) jsonObject.get("emoji");
                Integer[][] pixelArray = new Integer[PixelLetter.LETTER_HEIGHT][width];
                for (int i = 0; i < PixelLetter.LETTER_HEIGHT; i++) {
                    for (int j = 0; j < width; j++) {
                        pixelArray[i][j] = Character.getNumericValue(pixel.charAt(i* width + j));
                    }
                }

                PixelLetter pl = new PixelLetter();
                pl.setWidth(width);
                pl.setEmoji(emoji);
                pl.setPixel(pixelArray);

                patterns.put(c,pl);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } catch (ParseException e) {
            e.printStackTrace();

        }
    }

    /* TODO */
    private void ReadPatternsFromXML() {

    }

    private void HardCodedPatterns() {
        // A
        PixelLetter plA = new PixelLetter();
        plA.setWidth(5);
        plA.setEmoji(":sunny:");
        Integer [][] pixelA = {{0,0,1,0,0},{0,1,0,1,0},{0,1,0,1,0},{1,1,1,1,1},{1,0,0,0,1}};
        plA.setPixel(pixelA);
        patterns.put('A', plA);

        // I
        PixelLetter plI = new PixelLetter();
        plI.setWidth(1);
        plI.setEmoji(":umbrella:");
        Integer [][] pixelI = {{1},{1},{1},{1},{1}};
        plI.setPixel(pixelI);
        patterns.put('I', plI);

        // J
        PixelLetter plJ = new PixelLetter();
        plJ.setWidth(3);
        plJ.setEmoji(":cloud:");
        Integer [][] pixelJ = {{0,0,1},{0,0,1},{0,0,1},{1,0,1},{0,1,1}};
        plJ.setPixel(pixelJ);
        patterns.put('J', plJ);

        // M
        PixelLetter plM = new PixelLetter();
        plM.setWidth(5);
        plM.setEmoji(":snowflake:");
        Integer [][] pixelM = {{1,0,0,0,1},{1,1,0,1,1},{1,0,1,0,1},{1,0,0,0,1},{1,0,0,0,1}};
        plM.setPixel(pixelM);
        patterns.put('M', plM);

        // N
        PixelLetter plN = new PixelLetter();
        plN.setWidth(4);
        plN.setEmoji(":snowman:");
        Integer [][] pixelN = {{1,0,0,1},{1,1,0,1},{1,0,1,1},{1,0,0,1},{1,0,0,1}};
        plN.setPixel(pixelN);
        patterns.put('N', plN);

        // Space
        PixelLetter plSpace = new PixelLetter();
        plSpace.setWidth(4);
        plSpace.setEmoji(" ");
        Integer [][] pixelSpace = {{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1}};
        plSpace.setPixel(pixelSpace);
        patterns.put(' ', plSpace);
    }
}
