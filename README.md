Image Processing Program in Java 

- This program supports the processing of an image, like turning the 
image into black and white (greyscale()), duplicate the size of the image
(doublesize()), reducing the image size into half (halfsize()) and rotating 
the image clock wise (rotateClockwise()) using a GUI. The input image has the format of PPM.
Another supported image format is YUV.
- Another feauture of the program is the balancing of the given image that has
as main goal to only change the brightness of the picture and retain the same colors. 
- Lastly, the method of photo stacking is implemented where many similar pictures are combined 
into a new one, in which every RGB pixel value is equal to the average RGB pixel value of all the 
given pictures. 
- The implemented program has the following menu for the user to choose the actions to perform:
    - File
        - Open
            - PPM File
            - YUV File
            - Other Formats (like PNG, JPG)
        - Save 
            - PPM File
            - YUV File
    - Actions
        - Grayscale
        - Increase size
        - Decrease size
        - Rotate Clockwise
        - Equalize Histogram 
        - Stacking Algorithm 
            - Select Directory 
