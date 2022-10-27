package ce326.hw2;

public class YUVPixel {
    private int yuvValues;


    public YUVPixel(short Y, short U, short V) {
        // Creates a YUV pixel with Y, U and V
        yuvValues = Y;
        yuvValues = (yuvValues << 8) + U;
        yuvValues = (yuvValues << 8) + V;
    }

    public YUVPixel(YUVPixel pixel) {
        // Creates a copy of pixel
        yuvValues = pixel.getYUV();
    }

    public YUVPixel(RGBPixel pixel) {
        // Creates a YUVPixel from a RGBPixel
        short red, green, blue;
        red = pixel.getRed();
        green = pixel.getGreen();
        blue = pixel.getBlue();
        short y, u, v;

        y = (short) ((((66 * red) + (129 * green) + (25 * blue) + 128) >> 8) + 16);
        u = (short) ((((-38 * red) - (74 * green) + (112 * blue) + 128) >> 8) + 128);
        v = (short) ((((112 * red) - (94 * green) - (18 * blue) + 128) >> 8) + 128);

        setY(y);
        setU(u);
        setV(v);
    }

    public short getY() {
        short y;
        y = (short)((yuvValues >> 16) & 0xFF);
        return(y);
    }

    public short getU() {
        short u;
        u = (short)((yuvValues >> 8) & 0xFF);
        return(u);
    }

    public short getV() {
        short v;
        v = (short)(yuvValues & 0xFF);
        return(v);
    }

    public void setY(short Y) {
        short oldU, oldV;
        oldU = getU();
        oldV = getV();
        yuvValues = Y;
        yuvValues = (yuvValues << 8) + oldU;
        yuvValues = (yuvValues << 8) + oldV;
    }

    public void setU(short U) {
        short oldY, oldV;
        oldY = getY();
        oldV = getV();
        yuvValues = oldY;
        yuvValues = (yuvValues << 8) + U;
        yuvValues = (yuvValues << 8) + oldV;
    }
    public void setV(short V) {
        short oldU, oldY;
        oldU = getU();
        oldY = getY();
        yuvValues = oldY;
        yuvValues = (yuvValues << 8) + oldU;
        yuvValues = (yuvValues << 8) + V;
    }

    public int getYUV() {
        // Returns the yuvValues of the pixel
        return(yuvValues);
    }
}
