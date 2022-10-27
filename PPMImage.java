package ce326.hw2;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;


public class PPMImage extends RGBImage {


    public PPMImage(java.io.File file) throws FileNotFoundException, UnsupportedFileFormatException  {

        // Checks if file exists or if it is readable
        if (!file.exists() || !file.canRead()) {
            throw new FileNotFoundException("Error with file.");
        }

        // Checks format of file
        Scanner line = new Scanner(file);
        String p3 = line.next();

        if (!p3.equals("P3")) {
            throw new UnsupportedFileFormatException("Wrong format.");
        }

        // Scans the details for the image and creates it
        int width, height, luminosity;
        short red, green, blue;
        width = line.nextInt();
        height = line.nextInt();
        luminosity = line.nextInt();
        super.width = width;
        super.height = height;
        super.colorDepth = luminosity;
        super.image = new RGBPixel[height][width];
        int i, j;
        for (i = 0; i < height; i++) {
            for (j = 0; j < width; j++) {
                red = line.nextShort();
                green = line.nextShort();
                blue = line.nextShort();
                super.image[i][j] = new RGBPixel(red, green, blue);
            }
        }
    }


    public PPMImage(RGBImage img) {
        // Create a ppm image from an rgb image
        super(img); 

    }

    public PPMImage(YUVImage img) {
        // Create a ppm image from a yuv image
        super(img);

    }

    @Override
    public String toString() {

        StringBuilder info = new StringBuilder();

        // Header of the file
        info.append("P3\n");
        info.append(width);
        info.append("\n");
        info.append(height);
        info.append("\n");
        info.append(colorDepth);
        info.append("\n");

       int i, j;
       for (i = 0; i < height; i++) {
           for (j = 0; j < width; j++) {
               info.append(" ");
               info.append(image[i][j].getRed());
               info.append(" ");
               info.append(image[i][j].getGreen());
               info.append(" ");
               info.append(image[i][j].getBlue());

           }
           info.append("\n");
       }

       return(info.toString());
    }

    public void toFile(java.io.File file) {


        try {
            FileWriter line;
            line = new FileWriter(file);

            // Header of the file
            line.write("P3\n");
            line.write(width + " " + height + "\n");
            line.write(colorDepth + "\n");

            int i, j;

            for (i = 0; i < height; i++) {
                for (j = 0; j < width; j++) {
                    line.write(image[i][j].getRed() + " " + image[i][j].getGreen() + " " + image[i][j].getBlue() + " ");
                }
                line.write("\n");
            }
            line.close();
        } catch (IOException ex) {
            System.out.println("error3.");
        }

    }

}
