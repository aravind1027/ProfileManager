# ProfileManager

1.	Create Android Project, use MVP
2.	Backend with database, use room database
3.	3 screens
    1. 1st screen is a form that collects bio info (first name, last name, photo, age, d.o.b., brief description, education level (options like uneducated, hs, some uni, associates, bachelors, masters, phd))
        1. edu level: dropdown
        2. gender: radio buttons
        3. hobbies: checkbox
    2. should be able to save data to database
    3. 	view all profiles link (all bios)
        1.	click takes user to 2nd screen
    4.	2nd screen is list of all the bios
        1.	similar to create goal tile
        2.	circular profile picture (placeholder if none), mix both cases
        3.	first name, last name, age, gender
        4.	bio (2 line max. Ellipses after that)
        5.	education level
        6.	padding in all directions should be 8dp
        7.	card should be clickable to nav to detail screen
        8.	bonus: should be able to swipe (left or right) to dismiss and delete from database
        9.	bonus: pagination, only show first n number of tiles, swipe up reveals more
    5.	3rd screen is detail screen
        1.	first item is image (square) on left, followed by name, age, gender
        2.	underneath is the full bio
        3.	page title can be bio/profile
4.	use xml not compose
6.	2 weeks deadline
7.	don’t worry much about testing, mainly focus on architecture 


![create_profile_screen_1](https://user-images.githubusercontent.com/29805179/227346506-424ae835-7c36-45cd-a23f-4a97fcdbcf88.png)

![create_profile_screen_2](https://user-images.githubusercontent.com/29805179/227346547-55832a60-f2e1-4999-905a-585bb3e67fa6.png)

![all_profiles_screen_2](https://user-images.githubusercontent.com/29805179/227346658-a8e7a7cc-86d4-407f-bd7f-d2f6be120b2f.png)

![profile_details_screen_1](https://user-images.githubusercontent.com/29805179/227346754-8669f83f-5a4c-48ea-93ab-34efb69ffdf5.png)
