/**
 * Created by ziyanzha on 8/22/17.
 */
public class PixelLetter {
    private Integer width;
    private String emoji;
    public static final Integer LETTER_HEIGHT = 5;
    private Integer [][] pixel;

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public Integer [][] getPixel() {
        return pixel;
    }

    public Integer[] getPixelByRow(Integer rowIndex) {
        return pixel[rowIndex];
    }

    public void setPixel(Integer[][] pixel) {
        this.pixel = pixel;
    }
}
