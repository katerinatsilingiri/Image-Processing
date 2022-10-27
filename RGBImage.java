package ce326.hw2;

public class RGBImage implements Image {
    public static int MAX_COLORDEPTH = 225;
    int width, height, colorDepth;
    RGBPixel[][] image;

    public RGBImage() {
        // Default constructor
    }

    public RGBImage(int width, int height, int colordepth) {
        // Creates an image with width, height and colordepth
        this.width = width;
        this.height = height;
        image = new RGBPixel[height][width];
        int i,j;
        for (i = 0; i < height; i++) {
            for (j = 0; j < width; j++) {
                image[i][j] = new RGBPixel((short)0,(short)0,(short)0);
            }
        }
        colorDepth = colordepth;
    }


    public RGBImage(RGBImage copyImg) {
        // Creates a copy of the copyImg
        width = copyImg.width;
        height = copyImg.height;
        colorDepth = copyImg.colorDepth;
        image = new RGBPixel[height][width];  // row - col
        int i,j;
        for (i = 0; i < height; i++) {
            for (j = 0; j < width; j++) {
                image[i][j] = new RGBPixel(copyImg.getPixel(i,j));
            }
        }
    }

    public RGBImage(YUVImage YUVImg) {
        // Creates an RGBImage from a YUVImage
        width = YUVImg.width;
        height = YUVImg.height;
        colorDepth = MAX_COLORDEPTH;
        image = new RGBPixel[height][width];  // row - col
        int i,j;
        for (i = 0; i < height; i++) {
            for (j = 0; j < width; j++) {
                image[i][j] = new RGBPixel(YUVImg.getYUVPixel(i,j));
            }
        }

    }

    public int getWidth() {
        return(width);
    }

    public int getHeight() {
        return(height);
    }

    public int getColorDepth() {
        return(colorDepth);
    }

    public RGBPixel getPixel(int row, int col) {
        return(image[row][col]);
    }

    public void setPixel(int row, int col, RGBPixel pixel) {
        image[row][col] = pixel;
    }

    public void grayscale() {
        // Turns the image into black and white by changing its rgb values
        int r, g, b;
        int gray;
        int i, j;
        for (i = 0; i < height; i++) {
            for (j = 0; j < width; j++) {
                r = image[i][j].getRed();
                g = image[i][j].getGreen();
                b = image[i][j].getBlue();
                gray = (int)((r * 0.3) + (g * 0.59) + (b * 0.11));
                image[i][j].setRed((short)gray);
                image[i][j].setGreen((short)gray);
                image[i][j].setBlue((short)gray);
            }
        }
    }

    public void doublesize() {
        // Doubles the size of the image
        int i,j;
        RGBPixel[][] newImage = new RGBPixel[height*2][width*2];
        for (i = 0; i < height; i++) {
            for (j = 0; j < width; j++) {
                newImage[2*i][2*j] = new RGBPixel(image[i][j]);
                newImage[2*i + 1][2*j]= new RGBPixel(image[i][j]);
                newImage[2*i][2*j + 1] = new RGBPixel(image[i][j]);
                newImage[2*i + 1][2*j + 1] = new RGBPixel(image[i][j]);
            }
        }
        height = 2*height;
        width = 2*width;
        image = newImage;

    }

    public void halfsize() {
        // Reduces the size of the image by half
        int i, j;
        RGBPixel[][] newImage = new RGBPixel[height/2][width/2];
        short red, green, blue;

        for (i = 0; i < (height/2); i++) {
            for (j = 0; j < (width/2); j++) {
                red = (short)((image[2*i][2*j].getRed() + image[(2*i) + 1][2*j].getRed() + image[2*i][(2*j) + 1].getRed() + image[(2*i) + 1][(2*j) + 1].getRed()) / 4);
                green = (short)((image[2*i][2*j].getGreen() + image[(2*i) + 1][2*j].getGreen() + image[2*i][(2*j) + 1].getGreen() + image[(2*i) + 1][(2*j) + 1].getGreen()) / 4);
                blue = (short)((image[2*i][2*j].getBlue() + image[(2*i) + 1][2*j].getBlue() + image[2*i][(2*j) + 1].getBlue() + image[(2*i) + 1][(2*j) + 1].getBlue()) / 4);
                newImage[i][j] = new RGBPixel(red, green, blue);
            }
        }

        width = width/2;
        height = height/2;
        image = newImage;
    }

    public void rotateClockwise() {
        // Rotates the images 90degrees clockwise
        int i, j;
        RGBPixel[][] newImage = new RGBPixel[width][height];

        for (i = 0; i < width; i++) {
            for (j = 0; j < height; j++) {
                newImage[i][j] = image[height - 1 - j][i];
            }
        }

        int temp;
        temp = width;
        width = height;
        height = temp;
        image = newImage;
    }

}