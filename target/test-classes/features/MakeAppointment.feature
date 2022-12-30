Feature: Make Appointment
 User should be able to make appointment according to his requirement
 
 Scenario: TC1_Verify Successful Appointment
 Given User has opened the application Url
 And clicks on Make appointment BUtton
 When user enters valid username and valid password
 And clicks on login button
 And clicks on Make Appointment button
 And enter facilty healthcare program and visit date
 And click on Book Appointment button
 Then User should be able to find booking confirmation message
 And verify details in confirmation msg with entered details
 And close the Browser