# MiniProject Exercise Guide

## Problem Description

In this mini-project, you will build a Java program to analyze real-world datasets, apply complex filtering criteria, and extract meaningful insights. You will work with CSV files containing structured data, perform calculations, and display well-formatted results. This project will combine multiple programming concepts you have learned so far, including file handling, conditionals, loops, and modular programming.

## Table of Contents

- [Learning Objectives](#learning-objectives)
- [Input/Output Specification](#inputoutput-specification)
- [Hints & Considerations](#hints--considerations)
- [Approach](#approach)
- [Example](#example)
- [Final Notes](#final-notes)

## Learning Objectives

By completing this mini-project, you will:

- Develop a real-world data processing application using Java.
- Learn how to handle and filter large datasets efficiently.
- Apply modular programming principles to structure your code effectively.
- Gain experience in using CSV data parsing libraries for structured data analysis.

## Input/Output Specification

### Input

- A CSV file containing export data, where each row represents a country and its exports.
- Example CSV format:

```bash
Country,Exports,Value (dollars)
Argentina,Gold; Silver; Copper,$1,234,567,890
Brazil,Oil; Gold,$987,654,321
Canada,Diamonds; Silver,$500,000,000
```

### Output

- A list of countries based on filtering criteria (e.g., countries that export a specific product).
- The total number of countries meeting a given condition.
- Formatted information for selected countries.

## Hints & Considerations

- Use CSVParser from the Apache Commons CSV library (available in Duke’s Java environment).
- Be mindful of cases where countries export multiple products, separated by semicolons (;).
- Handle edge cases, such as :
  - Countries with no listed exports.
  - Countries with exports containing spaces or mixed casing.
  - Large numbers formatted with commas.

## Approach

1. Read the CSV file using CSVParser.
2. Loop through each row and extract the country name, export items, and value.
3. Filter data based on conditions (e.g., countries exporting a specific product).
4. Format and display results, handling any special cases in output formatting.

## Example

### Input

```java
String exportItem = "Gold";
printCountriesWithExport(exportItem);
```

### Output

```less
Countries that export Gold:
- Argentina (Exports: Gold; Silver; Copper, Value: $1,234,567,890)
- Brazil (Exports: Oil; Gold, Value: $987,654,321)
```

## Final Notes

- Test the program with different export items to verify filtering works correctly.
- Extend functionality to count and print total matching countries.
- Consider adding multiple filtering criteria, such as minimum export value.
