package ce326.hw2;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Histogram {
    int[] frequencyArray; // Histogram
    int total; // Total numbers of pixels in the image
    double[] pmfArray; // PMF (used in equalize)
    double[] cdfArray; // CDF (used in equalize)
    int[] changeOfLuminosity; // Used in equalize
    StringBuilder str; // Used in toString

    public Histogram(YUVImage img) {
        int i, j, pos;
        frequencyArray = new int[236];
        total = img.height * img.width;

        // Init the frequencyArray with zeros
        for (i = 0; i < frequencyArray.length; i++) {
            frequencyArray[i] = 0;
        }

        // Make histogram
        for (i = 0; i < img.height; i++) {
            for (j = 0; j < img.width; j++) {
                pos = img.getYUVPixel(i,j).getY();
                frequencyArray[pos] = frequencyArray[pos] + 1;
            }
        }

    }

    @Override
    public String toString() {
        str = new StringBuilder();
        
        int i;
        for (i = 0; i < frequencyArray.length; i++) {
            str.append("\n");
            if (i/10 == 0) {
                str.append("  ");
                str.append(i);
                str.append(".");
            }
            else if ((i/10 < 10) && (i/10 > 0)) {
                str.append(" ");
                str.append(i);
                str.append(".");
            }
            else {
                str.append(i);
                str.append(".");
            }

            str.append("(");
            if (frequencyArray[i]/10 == 0) {
                str.append("   ");
                str.append(frequencyArray[i]);
            }
            else if (frequencyArray[i]/100 == 0) {
                str.append("  ");
                str.append(frequencyArray[i]);
            }
            else if (frequencyArray[i]/1000 == 0){
                str.append(" ");
                str.append(frequencyArray[i]);
            }
            else {
                 str.append(frequencyArray[i]);
            }

            str.append(")");
            str.append("\t");

            int res, times;
            int number;
            number = frequencyArray[i];

            res = frequencyArray[i]/1000;
            number = number - res*1000;
            for (times = 0; times < res; times++) {
                str.append("#");
            }

            res =number/100;
            number = number - res*100;

            for (times = 0; times < res; times++) {
                str.append("$");
            }

            res = number/10;
            number = number - res*10;
            for (times = 0; times < res; times++) {
                str.append("@");
            }

            res = number;
            for (times = 0; times < res; times++) {
                str.append("*");
            }
            
        }
        str.append("\n");
        return(str.toString());
    }

    public void toFile(File file) {

        try {
            FileWriter line;
            line = new FileWriter(file);
            line.write(str.toString()); // Copy the string from toString into the file
            line.close();
        } catch (IOException ex) {
            System.out.println("error.");
        }
    }

    public void equalize() {

        pmfArray = new double[236];
        cdfArray = new double[236];
        changeOfLuminosity = new int[236];
        int i;

        // Probability mass function (PMF)
        for (i = 0; i < pmfArray.length; i++) {
            pmfArray[i] = (double)(frequencyArray[i]) / (double)(total);
        }

        // Cumulative distributive function (CDF)
        // Copy the pmf array into the cdf array
        for (i = 0; i < pmfArray.length; i++) {
            cdfArray[i] = pmfArray[i];
        }

        // Keep the fist cell the same
        for (i = 1; i < cdfArray.length; i++) {
            cdfArray[i] = cdfArray[i] + cdfArray[i-1];
        }

        // Find the suggested change of luminosity (int)
        for (i = 0; i < changeOfLuminosity.length; i++) {
            changeOfLuminosity[i] = (int)(cdfArray[i] * 235);
        }
    }

    public short getEqualizedLuminocity(int luminocity) {
        short newLuminosity;
        newLuminosity = (short)changeOfLuminosity[luminocity];
        return(newLuminosity);
    }
}

