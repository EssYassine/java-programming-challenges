package perimeter_quiz;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    
    /**
     * Calculates the perimeter of a given shape.
     */
    public double getPerimeter (Shape s) {
        double totalPerimeter = 0.0;
        Point prevPt = s.getLastPoint(); // Get the last point to start the loop
        
        for (Point currPt : s.getPoints()) {
            totalPerimeter += prevPt.distance(currPt); // Add distance between points
            prevPt = currPt; // Update previous point
        }
        return totalPerimeter;
    }

    /**
     * Returns the number of points in the given shape.
     */
    public int getNumPoints(Shape s) {
        int count = 0;
        for (Point p : s.getPoints()) {
            count++;
        }
        return count;
    }

    /**
     * Computes the average side length of the shape.
     */
    public double getAverageLength(Shape s) {
        return getPerimeter(s) / getNumPoints(s);
    }

    /**
     * Finds the largest side length in the shape.
     */
    public double getLargestSide(Shape s) {
        double largestSide = 0.0;
        Point prevPt = s.getLastPoint();
        
        for (Point currPt : s.getPoints()) {
            largestSide = Math.max(largestSide, prevPt.distance(currPt));
            prevPt = currPt;
        }
        return largestSide;
    }

    /**
     * Finds the largest X coordinate in the shape.
     */
    public double getLargestX(Shape s) {
        double largestX = Double.NEGATIVE_INFINITY;
        
        for (Point currPt : s.getPoints()) {
            largestX = Math.max(largestX, currPt.getX());
        }
        return largestX;
    }


    /**
     * Finds the largest perimeter among multiple shape files.
     */
    public double getLargestPerimeterMultipleFiles() {
        double largestPerimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            Shape s = new Shape(new FileResource(f));
            largestPerimeter = Math.max(largestPerimeter, getPerimeter(s));
        }
        return largestPerimeter;
    }

    /**
     * Finds the file with the largest perimeter.
     */
    public String getFileWithLargestPerimeter() {
        File largestFile = null;
        double largestPerimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            Shape s = new Shape(new FileResource(f));
            double perimeter = getPerimeter(s);
            if (largestFile == null || perimeter > largestPerimeter) {
                largestFile = f;
                largestPerimeter = perimeter;
            }
        }
        return (largestFile != null) ? largestFile.getName() : "No files selected";
    }

    /**
     * Tests perimeter calculations on a single shape.
     */
    public void testPerimeter() {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        
        System.out.println("Number of points = " + getNumPoints(s));
        System.out.println("Average Length = " + getAverageLength(s));
        System.out.println("Largest Side = " + getLargestSide(s));
        System.out.println("Largest X = " + getLargestX(s));
        System.out.println("Perimeter = " + getPerimeter(s));
    }
    
    /**
     * Tests perimeter calculations on multiple shape files.
     */
    public void testPerimeterMultipleFiles() {
        System.out.println("Largest Perimeter = " + getLargestPerimeterMultipleFiles());
    }

    /**
     * Tests which file has the largest perimeter.
     */
    public void testFileWithLargestPerimeter() {
        System.out.println("File with Largest Perimeter = " + getFileWithLargestPerimeter());
    }

    /**
     * Creates a sample triangle and prints its details.
     */
    public void triangle() {
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0, 0));
        triangle.addPoint(new Point(6, 0));
        triangle.addPoint(new Point(3, 6));
        
        for (Point p : triangle.getPoints()) {
            System.out.println(p);
        }
        System.out.println("Perimeter = " + getPerimeter(triangle));
    }

    /**
     * Prints names of selected files.
     */
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f.getName());
        }
    }
}
