/**
 * Convert any number of images to a gray scale version by setting all color components of each pixel to the same value.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {
    
    public ImageResource makeGray(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
    
        // Copy filename from inImage
        outImage.setFileName(inImage.getFileName()); 
    
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen()) / 3;
            
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        return outImage;
    }

    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeGray(inImage);
            gray.draw();
        }
    }

    public void testGray() {
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        gray.draw();
    }
    
    public void saveImage(ImageResource image, String folderPath, String prefix){
        String fileName = image.getFileName();
        
        // Ensure the folder path ends with a slash (for compatibility)
        if (!folderPath.endsWith(File.separator)) {
            folderPath += File.separator;
        }
    
        // Save the image in the specified folder
        String newName = prefix + fileName;
        image.setFileName(folderPath + newName);
        image.save();
    }
    
    public void convertAndSave () {
        String folderPath = "grayscale-images"; // Folder where images will be saved
        File folder = new File(folderPath);

        // Create the folder if it doesn’t exist
        if (!folder.exists()) {
            folder.mkdir();
        }
    
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            ImageResource gray = makeGray(image);
            saveImage(gray, folderPath, "gray-");
        }
        System.out.println("Done! Images saved in " + folderPath);
    }
}
