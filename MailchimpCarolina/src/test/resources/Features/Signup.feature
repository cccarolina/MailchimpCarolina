Feature: Register a new user
- the username is not registred before
- no field is empty
- the username is less then 100 letters
- new valid username, email and password should create user

Scenario Outline: Signup new user at mailchimp 

Given I have navigate to MailChimp signup site
Given I have entered email "<email>" in the emailfield
And I have entered username "<username>" in the usernamefield
And  I have entered password "<password>" in the passwordfield
When I press signup
Then depending if valid or invalid inputs login status "<status>" appears 

Examples:
|status        |email                     |username               |password                    |
|validinput    |validemail                |validusername          |Mailemail13!               |
|UsernameTaken |validemail								|Jack                   |Hejhopp1!                   |
|NoEmailEntered|                          |Hejhopphej             |Hejhopp163!                   |
|LongUser |hejhopp202019@hotmail.com |aaaaaaaaaaaaaaaaaabbbbbbbbb733002ccccccccccccccccccdddddddddddddddddeeeeeeeeeeeeeeeeeeeeeffffff4894894939ffffffffffjjjjjjjjjj |Hejhopp4341!|