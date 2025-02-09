# Challenge 4: Parsing Weather Data

## Problem Description

In this challenge, you will write a Java program to analyze and extract insights from weather data stored in CSV files. The program will read temperature and humidity data, find specific weather conditions (such as the coldest temperature), and display the results in a meaningful way.

## Table of Contents

- [Learning Objectives](#learning-objectives)
- [Input/Output Specification](#inputoutput-specification)
- [Hints & Considerations](#hints--considerations)
- [Approach](#approach)
- [Example](#example)
- [Final Notes](#final-notes)

## Learning Objectives

By completing this challenge, you will:

- Learn how to read and parse CSV files in Java.
- Gain experience in extracting numerical data from structured files.
- Implement looping and conditional logic to find specific weather values.
- Understand how to handle missing or malformed data.
- Improve code modularity by implementing helper methods for data processing.

## Input/Output Specification

### Input

- A CSV file containing weather data, where each row represents a weather record.
- Example CSV format:

```yaml
DateUTC,TemperatureF,Humidity
2023-09-01 00:00:00,75.2,80
2023-09-01 01:00:00,73.5,85
2023-09-01 02:00:00,72.1,90
```

### Output

- The program will compute and print
  - The coldest temperature and the corresponding date.
  - The day with the lowest humidity.
  - Other optional statistics, such as the average temperature.

## Hints & Considerations

- Use CSVParser from the Apache Commons CSV library (provided in Duke’s Java environment).
- Convert temperatures to numerical values before comparing them.
- Be mindful of missing data values (e.g., missing temperature or humidity).
- Consider handling multiple files to analyze data from different days.

## Approach

1. Read the CSV file using CSVParser.
2. Loop through each row, extract the temperature and humidity values.
3. Keep track of the lowest temperature and the corresponding date.
4. Find the lowest humidity and the associated day.
5. Format and display results, ensuring clear and meaningful output.

## Example

### Input

```java
findColdestDay();
```

### Output

```yaml
Coldest temperature: 72.1°F on 2023-09-01 02:00:00
Day with the lowest humidity: 2023-09-01 (Humidity: 80%)
```

## Final Notes

- Extend functionality to analyze multiple weather files for trends.
- Add features to compute highest temperature, average humidity, etc.
- Ensure proper error handling for missing or malformed data.
