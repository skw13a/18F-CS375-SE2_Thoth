Feature: Copy files
Scenario Outline: Copy files
  When I open the input
  Then the input should be copied to output
  
Examples:
  | 1        | 1       |
  | "name"   | "name"  |
  | "test"   | "test"  |