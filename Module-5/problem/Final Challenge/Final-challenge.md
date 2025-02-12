# Challenge: Batch Grayscale and Image Inversion

## Problem Description

In this challenge, you will write a Java program to manipulate images in bulk by converting them to grayscale and inverting their colors. You will use the Duke Image Library to read, modify, and save images. Your program will process multiple images and apply transformations efficiently.

## Table of Contents

- [Learning Objectives](#learning-objectives)
- [Input/Output Specification](#inputoutput-specification)
- [Hints & Considerations](#hints--considerations)
- [Approach](#approach)
- [Example](#example)
- [Final Notes](#final-notes)

## Learning Objectives

By completing this challenge, you will:

- Learn how to manipulate pixel values in an image using Java.
- Understand RGB color models and how they affect image transformations.
- Gain experience in batch processing multiple image files.
- Work with Duke's ImageResource class for reading and saving images.

## Input/Output Specification

### Input

- One or more image files (e.g., .jpg, .png).
- Images will be read from a directory and processed in batch mode.

### Output

- Two versions of each input image:
  - **Grayscale version**: Converts each pixel to its grayscale equivalent.
  - **Inverted version**: Inverts the colors of each pixel.
- The modified images will be saved with appropriate filenames.

## Hints & Considerations

- Use ImageResource from the Duke Image Library.
- Remember that grayscale images have equal red, green, and blue values.
- Color inversion requires subtracting each RGB value from 255.
- Ensure the output images retain the original file names with modifications.

## Approach

1. Read an image file using ImageResource.
2. Convert the image to grayscale:
   - Compute the average of the red, green, and blue values for each pixel.
   - Set all three color values to the computed average.
3. Invert the colors of the image:
   - Subtract each color channel value from 255.
4. Save the modified images with appropriate filenames.

## Example

### Input

📷 beach.jpg

### Output

📷 beach-gray.jpg (Grayscale)
📷 beach-inverted.jpg (Inverted Colors)

## Final Notes

- This code processes multiple images at once.
- It ensures that the new images are saved with clear file names.
- Experiment by adding other filters like sepia tone or contrast enhancement.
