package ce326.hw2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class PPMImageStacker {
    List<PPMImage> images = new ArrayList<>(); // List of images
    PPMImage avgImage;


    public PPMImageStacker(java.io.File dir) throws FileNotFoundException, UnsupportedFileFormatException {

        // Checks if the the directory exists
        if (!dir.exists()) {
            throw new FileNotFoundException("[ERROR]Directory " + dir + " does not exist!");
        }

        // Checks if the "dir" is a directory
        if (dir.exists() && !dir.isDirectory()) {
            throw new FileNotFoundException("ERROR.Directory " + dir + " is not a directory!");
        }


        File[] listOfFiles = dir.listFiles(); // List of the files (that are in the "dir")
        int i;


        try {
            // Add all files in the list
            for (i = 0; i < listOfFiles.length; i++) {
                images.add(new PPMImage(listOfFiles[i]));
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("error.");
        }
        catch (UnsupportedFileFormatException e) {
            System.out.println("error2.");
        }

    }

    public void stack() {
        int i, j, k;
        int red, green, blue;

        // Gets info for the image from the first one in the list
        int width = images.get(0).getWidth();
        int height = images.get(0).getHeight();
        int colordepth = images.get(0).getColorDepth();
        RGBImage tempRGBImage = new RGBImage(width, height, colordepth);
        avgImage = new PPMImage(tempRGBImage);


        for (i = 0; i < height; i++) {
            for (j = 0;  j < width; j++) {
                red = 0;
                green = 0;
                blue = 0;
                // Scans all the images and add the rgb values
                for (k = 0; k < images.size(); k++) {
                    red = (red + images.get(k).image[i][j].getRed());
                    green = (green + images.get(k).image[i][j].getGreen());
                    blue = (blue + images.get(k).image[i][j].getBlue());
                }
                // Find the average of rgb values
                red = red/(images.size());
                avgImage.image[i][j].setRed((short)red);
                green = green/(images.size());
                avgImage.image[i][j].setGreen((short)green);
                blue = blue/(images.size());
                avgImage.image[i][j].setBlue((short)blue);
            }
        }
    }

    public PPMImage getStackedImage() {
        // Returns the stacked image
        return(avgImage);
    }
}
