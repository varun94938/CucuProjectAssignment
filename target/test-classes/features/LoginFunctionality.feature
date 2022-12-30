@login
Feature: Login functionality
  User should be able to login with valid credentials
	Background:
		Given User has opened the application url
		And click on Make Appointment button
  @validcredentials
  Scenario: TC1_Verify Successful Login with Valid credentials
    When User enters valid username and valid password
    And click on login button
    Then find page appointment within ten seconds
    And close the browser
    
  @invalidcredentials
  Scenario: TC1_Verify Login with Invalid Credentials
  	When User enters invalid username and invalid password
  	And click on login button
  	Then find error message showing invalid credentials
  	And close the browser

  

