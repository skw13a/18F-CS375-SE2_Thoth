Feature: Copy folder

Scenario Outline: Copy a folder
	Given "input" exists
	When I open "input"
	Then "output" exists
	And "output" = "input"
  
	Examples:
	| Input = Output |
	