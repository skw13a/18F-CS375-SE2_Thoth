Feature: Copy folder
Scenario Outline: Copy folder
	Given the input folder "input" exists
	When I open "input"
	Then "output" exists
	And "output" = "input"
  
	Examples:
	| Input = Output |