package ce326.hw2;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class YUVImage {
    int width, height;
    YUVPixel[][] image;

    public YUVImage(int width, int height) {
        // Creates a YUVImage with width and height
        this.width = width;
        this.height = height;
        image = new YUVPixel[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                image[i][j].setY((short) 16);
                image[i][j].setU((short) 128);
                image[i][j].setV((short) 128);
            }
        }
    }

    public YUVImage(YUVImage copyImg) {
        // Creates a copy of copyImg
        width = copyImg.width;
        height = copyImg.height;
        image = new YUVPixel[height][width];
        int i,j;
        for (i = 0; i < height; i++) {
            for (j = 0; j < width; j++) {
                image[i][j] = new YUVPixel(copyImg.getYUVPixel(i,j).getY(),copyImg.getYUVPixel(i,j).getU(), copyImg.getYUVPixel(i,j).getV());
            }
        }

    }


    public YUVImage(RGBImage RGBImg) {
        // Creates a YUVImage of the RGBImg
        width = RGBImg.width;
        height = RGBImg.height;
        image = new YUVPixel[height][width];
        int i,j;

        for (i = 0; i < height; i++) {
            for (j = 0; j < width; j++) {
                image[i][j] = new YUVPixel(RGBImg.getPixel(i,j));
            }
        }

    }

    public YUVImage(java.io.File file) throws FileNotFoundException, UnsupportedFileFormatException {
        // Creates a YUVImage from a file

        // Checks if the file exists or if the file is readable
        if (!file.exists() || !file.canRead()) {
            throw new FileNotFoundException("[ERROR]File " + file + " does not exist or is unreadable!");
        }
        Scanner line = new Scanner(file);
        String yuv3 = line.next();

        // Checks if the header of the file is correct
        if (!yuv3.equals("YUV3")) {
            throw new UnsupportedFileFormatException("[ERROR]File " + file + " does not have the correct header!");
        }
        width = line.nextInt();
        height = line.nextInt();
        image = new YUVPixel[height][width];

        int i, j;
        short y, u ,v;

        for (i = 0; i < height; i++) {
            for (j = 0; j < width; j++) {
                y = line.nextShort();
                u = line.nextShort();
                v = line.nextShort();
                image[i][j] = new YUVPixel(y,u,v);
            }
        }
    }

    @Override
    public String toString() {
        String info;

        // Header of the file
        info = "YUV3" + "\n" + width + " " + height + "\n";

        int i, j;
        for (i = 0; i < height; i++) {
            for (j = 0; j < width; j++) {
                info = info + " " + image[i][j].getY() + " " + image[i][j].getU() + " " + image[i][j].getV();
            }
            info = info + "\n";
        }

        return(info);
    }

    public void toFile(java.io.File file) {

        try {
            FileWriter line;
            line = new FileWriter(file);
            
            // Header of the file
            line.write("YUV3\n");
            line.write(width + " " + height + "\n");

            int i, j;

            for (i = 0; i < height; i++) {
                for (j = 0; j < width; j++) {
                    line.write(image[i][j].getY() + " " + image[i][j].getU() + " " + image[i][j].getV() + " ");
                }
                line.write("\n");
            }
            line.close();
        } catch (IOException ex) {
            System.out.println("error.");
        }

    }

    public void equalize() {
        YUVPixel[][] newImage;
        newImage = new YUVPixel[height][width];
        Histogram histrogramma;
        histrogramma = new Histogram(this);
        short newY, newU, newV;
        histrogramma.equalize();

        int i, j;
        for (i = 0; i < height; i++) {
            for (j = 0; j < width; j++) {
                // Calculates the new values of Y(with equalized Luminosity), U and V (remain the same)
                newY = histrogramma.getEqualizedLuminocity(image[i][j].getY());
                newU = image[i][j].getU();
                newV = image[i][j].getV();
                newImage[i][j] = new YUVPixel(newY, newU, newV);
            }
        }
        image = newImage;
    }


    public YUVPixel getYUVPixel(int row, int col) {
        // Returns the YUVPixel in the position [row][col]
        return(image[row][col]);
    }
}
