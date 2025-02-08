# Challenge 3: Finding a Gene and Web Links

## Problem Description

In this challenge, you will write a Java program to search for a gene sequence within a DNA string and find corresponding web links to further study that gene. The challenge will help you understand string manipulation and regular expression techniques in Java.

You are given a DNA string consisting of the characters A, C, G, and T. A gene is defined as any substring of this string that starts with "ATG" and ends with "TAA", "TAG", or "TGA". Your task is to find all such genes in the DNA string and return them.

## Table of Contents

- [Learning Objectives](#learning-objectives)
- [Input/Output Specification](#inputoutput-specification)
- [Hints & Considerations](#hints--considerations)
- [Approach](#approach)
- [Example](#example)
- [Final Notes](#final-notes)

## Learning Objectives

By completing this challenge, you will:

- Learn how to identify patterns in DNA sequences using strings in Java.
- Gain experience in looping through strings and handling conditional logic to detect start and stop codons.
- Practice implementing custom helper methods to improve code modularity and readability.
- Develop skills in constructing formatted output (e.g., generating URLs dynamically based on extracted genes).
- Strengthen your ability to handle edge cases, such as missing start/stop codons or overlapping gene sequences.

## Input/Output Specification

### Input Specification

- A string containing the DNA sequence. This string consists only of the characters A, C, G, and T and can vary in length from a minimum of 1 character to a large size (thousands of characters).

### Output Specification

- A list of all the gene sequences found within the input DNA string. Each sequence starts with "ATG" and ends with one of the stop codons: "TAA", "TAG", or "TGA".

## Hints & Considerations

- Use regular expressions to match the pattern for genes: Start with "ATG" and end with one of the stop codons ("TAA", "TAG", "TGA").
- Use the String methods like substring() to extract gene sequences once you find a match.
- Make sure to handle cases where no gene is found.

## Approach

1. Loop through the DNA string to find "ATG".
2. Once "ATG" is found, continue scanning until a stop codon ("TAA", "TAG", "TGA") is encountered.
3. Store the gene sequence.
4. Output the list of genes.

## Example

### Input

```java
DNA = "ATGCATGTAAAGGTGATGACTGAGTAA"
```

### Output

```yaml
Gene found: ATGCATGTAA
Gene found: ATGACTGAGTAA
```

## Final Notes

- Ensure your program handles edge cases, such as when no genes are found or the sequence doesn't have the correct starting or ending codons.
- Test your program with different DNA sequences to ensure that the gene detection is accurate.
