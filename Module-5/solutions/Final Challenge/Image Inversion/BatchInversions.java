/**
 * Convert any number of images to an inverted version.
 * 
 */
import edu.duke.*;
import java.io.*;

public class BatchInversions {
    
    public ImageResource makeInversion(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
    
        // Copy filename from inImage
        outImage.setFileName(inImage.getFileName()); 
    
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            
            pixel.setRed(255 - inPixel.getRed());
            pixel.setGreen(255 - inPixel.getGreen());
            pixel.setBlue(255 - inPixel.getBlue());
        }
        return outImage;
    }

    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource inverted = makeInversion(inImage);
            inverted.draw();
        }
    }

    public void testInversion() {
        ImageResource ir = new ImageResource();
        ImageResource gray = makeInversion(ir);
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
        String folderPath = "inverted-images"; // Folder where images will be saved
        File folder = new File(folderPath);

        // Create the folder if it doesn’t exist
        if (!folder.exists()) {
            folder.mkdir();
        }
    
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            ImageResource gray = makeInversion(image);
            saveImage(gray, folderPath, "inverted-");
        }
        System.out.println("Done! Images saved in " + folderPath);
    }
}
