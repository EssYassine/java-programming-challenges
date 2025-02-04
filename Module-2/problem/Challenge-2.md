# Challenge 2: Calculating the Perimeter of a Shape

## Problem Description

In this challenge, you will write a Java program to calculate the perimeter of a shape based on a set of points. The shape is defined as a sequence of points forming a closed polygon. Your program will read these points from a file, compute the perimeter, and display the result.

## Table of Contents

- [Learning Objectives](#learning-objectives)
- [Input/Output Specification](#inputoutput-specification)
- [Output Specification](#output-specification)
- [Example](#example)
- [Implementation Steps](#implementation-steps)
- [Hints & Considerations](#hints--considerations)
- [Bonus Challenge](#bonus-challenge)

## Learning Objectives

By completing this challenge, you will:

- Learn how to store and manipulate geometric data in Java
- Work with the `Point` class to represent coordinates
- Implement methods to process and analyze shape-related data
- Understand how to read input from a file and perform calculations

## Input/Output Specification

### Input Specification

- The program will read a series of **(x, y) coordinates** representing points of a shape.
- Each point is connected to the next, forming a closed polygon.

### Output Specification

- The program should output the **total perimeter** of the shape.

### Example

#### **Input (points forming a triangle)**

(0, 0) (4, 0) (4, 3)

#### **Expected Output**

Total perimeter: 12.0

## Implementation Steps

1. **Reading Points from a File**

   - Store the coordinates in a list of `Point` objects.
   - Ensure that the last point connects back to the first.

2. **Computing Distances**

   - Calculate the distance between consecutive points using the distance formula:
     \[
     \text{distance} = \sqrt{(x_2 - x_1)^2 + (y_2 - y_1)^2}
     \]
   - Sum all the distances to compute the perimeter.

3. **Displaying the Result**
   - Print the computed perimeter.

## Hints & Considerations

- Make sure to **close the loop** by adding the distance from the last point back to the first.
- Use **double precision** for accurate calculations.
- Handle edge cases, such as:
  - A shape with **only one point** (perimeter should be `0`).
  - A shape with **two points** (perimeter should be `2 * distance`).

## Bonus Challenge

- Extend the program to calculate the **largest side length** and **average side length**.
- Experiment with **different data sets** to test your implementation.

Happy coding! 🚀
