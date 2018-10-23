Feature: check empty folder
Scenario Outline: check for an empty folder
	Given "output" exists
	When I open output
	Then output is empty
  
	Examples:
	| Input is empty. |