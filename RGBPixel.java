package ce326.hw2;

public class RGBPixel {
    private int rgbValues;

    public RGBPixel (short red, short green, short blue) {
        // Creates a RGBPixel with red, green and blue
        rgbValues = red;
        rgbValues = (rgbValues << 8) + green;
        rgbValues = (rgbValues << 8) + blue;

    }
    public RGBPixel (RGBPixel pixel) {
        // Creates a copy of pixel
        rgbValues = pixel.getRGB();

    }

    public RGBPixel (YUVPixel pixel) {
        // Creates an RGBPixel from a YUVPixel
        short c, d, e;
        c = (short) (pixel.getY() - 16);
        d = (short) (pixel.getU() - 128);
        e = (short) (pixel.getV() - 128);

        short red, green, blue;
        red = (short) clip((298 * c + 409 * e + 128) >> 8);
        rgbValues = red;
        green = (short) clip(( 298 * c - 100 * d - 208 * e + 128) >> 8);
        rgbValues = (rgbValues << 8) + green;
        blue = (short) clip(( 298 * c + 516 * d + 128) >> 8);
        rgbValues = (rgbValues << 8) + blue;
    }

    int clip(int insert) {
        
        // If the "insert" is a negative number, it becomes 0
        if (insert < 0) {
            return(0);
        }
        // If the "insert"  is grater that 255, it becomes 255
        else if (insert > 255) {
            return(255);
        }
        return((short)insert);
    }

    public short getRed() {
        short red_value;
        red_value = (short)(rgbValues >> 16);
        return(red_value);
    }

    public short getGreen() {
        short green_value;
        green_value = (short)((rgbValues >> 8) & 0xFF);
        return(green_value);
    }

    public short getBlue() {
       short blue_value;
        blue_value = (short) (rgbValues & 0xFF);
        return(blue_value);
    }

    public void setRed(short red) {
        int oldValues;
        oldValues = rgbValues & 0xFFFF;
        rgbValues = oldValues + ((int)red << 16);
    }

    public void setGreen(short green) {
        int oldRed, oldBlue;
        oldRed = getRed();
        oldBlue = getBlue();
        rgbValues = oldRed;
        rgbValues = (rgbValues << 8) + green;
        rgbValues = (rgbValues << 8) + oldBlue;
    }

    public void setBlue(short blue) {
        int oldValues;
        oldValues = rgbValues & 0xFFFF00;
        rgbValues = oldValues + (int)blue;
    }

    public int getRGB() {
        return(rgbValues);
    }

    public void setRGB(int value) {
        short red, blue, green;
        red = (short)(value >> 16);
        green = (short)((rgbValues >> 8) & 0xFF);
        blue =  (short) (rgbValues & 0xFF);

        setRed(red);
        setGreen(green);
        setBlue(blue);

    }

    public final void setRGB(short red, short green, short blue) {
        setRed(red);
        setGreen(green);
        setBlue(blue);
    }

    @Override
    public String toString() {
        String RGB;
        short redVal = getRed();
        short greenVal = getGreen();
        short blueVal = getBlue();
        RGB = redVal + " " + greenVal + " " + blueVal + " ";
        return(RGB);
    }

}
