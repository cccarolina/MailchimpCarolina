Feature: Register a new user
-email is not registered before 
-the username is not registred before
-all field are registred
-the username is less then 100 letters 
@mytag
Scenario Outline: Signup new user at mailchimp 

Given I have navigate to MailChimp signup site
Given I have entered  email "<email>" in the emailfield
And I have entered  username "<username>" in the usernamefield
And  I have entered   password "<password>" in the passwordfield
When I press Signup
Then depending if valid or invalid inputs login status "<status>" appears 

Examples:
|status        |email                |username               |password                    |
|validinput    |validemail           |validusername           |Minfakemail1!               |
|UsernameTaken |carolinac@hotmail.se |carolinaciray          |Hejhopp1!                   |
|NoEmailEntered|                     |Hejhopphej             |Hejhopp1!                   |
|TomanyLetters |hejhopp202019@hotmail.com|aaaaaaaaaaaaaaaaaabbbbbbbbbccccccccccccccccccccdddddddddddddddddeeeeeeeeeeeeeeeeeeeeefffffffffffffffffffjjjjjjjjjj |Hejhopp1!|