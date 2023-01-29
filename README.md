# Introduction
This document serves as a guide to understand the flow of the application. It explains the different interfaces of the application through screenshots and descriptions.
## Splash Screen
 After starting the application, the user will be presented with a splash screen. Once it finishes, the user will be given a choice between a login or sign up option.<br /><br />
![alt text](https://github.com/TheSaucese/DiniM3ak/blob/master/README-DiniM3ak/Splash.png)
![alt text](https://github.com/TheSaucese/DiniM3ak/blob/master/README-DiniM3ak/Welcome.png)
## Login
If the user chooses to login, he will be taken to a login interface where he will be prompted to enter his email and password. If the login credentials are incorrect, an error message will appear.<br /><br />
![alt text](https://github.com/TheSaucese/DiniM3ak/blob/master/README-DiniM3ak/Login.png)
![alt text](https://github.com/TheSaucese/DiniM3ak/blob/master/README-DiniM3ak/Login_Wrong_password.png)<br />
## Home Page
If the login is successful, the user will be taken to a home page corresponding to his job (driver or rider), this is the home interface of the app, where users can access all the different features . Such as Requesting a ride or looking for some available rides in the map , let's take example of a Rider.<br /><br />
![alt text](https://github.com/TheSaucese/DiniM3ak/blob/master/README-DiniM3ak/Home_Rider.png)
![alt text](https://github.com/TheSaucese/DiniM3ak/blob/master/README-DiniM3ak/Show_More.png)
![alt text](https://github.com/TheSaucese/DiniM3ak/blob/master/README-DiniM3ak/Notes.png)
## Request a Ride
Riders can request a ride to be shown in the Driver side , they have to enter the ride info and clicking in the __apply__ button.<br /><br />
![alt text](https://github.com/TheSaucese/DiniM3ak/blob/master/README-DiniM3ak/Request_Ride.png)
## Profile
By clicking on the "Profile" button, the user can access their profile details, such as their name, email,payment and job, he has also the ability to switch to the "Driver" mode.<br /><br />
![alt text](https://github.com/TheSaucese/DiniM3ak/blob/master/README-DiniM3ak/Profile_Driver.png)
![alt text](https://github.com/TheSaucese/DiniM3ak/blob/master/README-DiniM3ak/Switch_To_Rider.png)
## Notification 
One of the key features of the app is the ability for riders and drivers to receive notifications about rides that they have requested. These notifications can include messages about sent rides, as well as the ability to accept or decline a ride.<br /><br />
![alt text](https://github.com/TheSaucese/DiniM3ak/blob/master/README-DiniM3ak/Notification%20.png)
## Destination Reached
 When the ride is successfully completed and the destination is reached, the driver will receive a notification saying "Destination Reached". The notification will include a payment interface ,the user can pay with cash, in this case a confirmation notification will be sent to the driver , or he can use a credit card .<br /><br />
![alt text](https://github.com/TheSaucese/DiniM3ak/blob/master/README-DiniM3ak/DestinationReached.png)

## Profile (Driver)

![image](https://user-images.githubusercontent.com/88286462/215357932-7f72dad7-db97-4f66-871d-50fd54691280.png)

As a Driver your profile contains the option to 
  * Change your name (by clicking on your name)
  * See how many stars you have (it is sorta functional but because of technical limitations a rider cannot rate a driver)
  * Switch to Rider 
  * Payment (which has the same functionality as the payment button at the top bar)
  * See your Latest Clients (although we did not have enough time to complete the history function)

Any change will result in this bottom bar popping up 

![image](https://user-images.githubusercontent.com/88286462/215358338-2e092236-aaaf-4f0c-8318-caef0d179795.png)

 
 ## Vehicle
 
 ![image](https://user-images.githubusercontent.com/88286462/215358267-76cc8fa6-31d8-4269-9fa9-dd21e2a27a97.png)
 
 As a Driver you can upload a photo of your vehicle, your vehicle name and the number.
 
 
 Clicking on apply will clear the fields and send your data to the database.
 
## Sign up

 ![image](https://user-images.githubusercontent.com/88286462/215358637-af83020c-bdac-4a09-9a63-7e94ad98cec2.png)
 
 In the Sign up screen, You must enter a valid email, a valid password or the following errors will show up :

 ![image](https://user-images.githubusercontent.com/88286462/215359349-5524b1ab-2570-42d7-a340-252aa67a7ef5.png)
 
 ![image](https://user-images.githubusercontent.com/88286462/215359449-c4faedda-b75b-4c50-b654-6b59bb20edbb.png)

 You can toggle hide/show password : 
 
 ![image](https://user-images.githubusercontent.com/88286462/215359624-6d243cd8-f741-45bf-aef4-77b280669b6e.png)

 You can switch to the login screen by clicking the login text.
 
 ## Payment 
 
 ![image](https://user-images.githubusercontent.com/88286462/215359703-4db119a1-0c5b-4c8f-aa90-e6c0a99a543b.png)

 A user can only have 4 cards max.
 
 You can drag to see all your cards :
 
 ![image](https://user-images.githubusercontent.com/88286462/215359766-32888d96-6a02-451f-a7a0-30aa4ad4a80b.png)
 
 (Notice how the small circles change in color ?)
 
 You can add a new card by clicking the "Add New Card" button.
 
 You can delete a card (by selecting the card that you wish to delete)
 
 
 ## Add new Card
 
 ![image](https://user-images.githubusercontent.com/88286462/215359943-cfff3dd1-9977-404a-b402-9ace854f5dc9.png)

 You can fill in the form, regular expression are used here so everything has to be valid.
 
 ![image](https://user-images.githubusercontent.com/88286462/215359978-ce92d07f-e6ae-42e4-b309-bbdc779bb8b1.png)
 
 The card number is not correct.
 
 ![image](https://user-images.githubusercontent.com/88286462/215360009-d4361502-e83f-4a51-860d-b2bfae04aaa4.png)

 Now it is correct.
 
 (Notice how the numbers are being written on the card ?)
 
 You also have the option to simply go back.
 
 
 ## Verification : 
 
 ![image](https://user-images.githubusercontent.com/88286462/215360273-3aa3ec15-97c3-430f-8197-3366b2a80674.png)
 
 You have to type in the correct code.
 
 You will recieve the following email which contains the verification code :
 
 ![image](https://user-images.githubusercontent.com/88286462/215360252-f4a1d0ff-b13d-4fd8-829f-4532e81c62c4.png)

 ## Driving or Riding ? : 
 
 ![image](https://user-images.githubusercontent.com/88286462/215360335-768af11b-3541-4b03-8088-0eaffd1c92e0.png)
 
 You can change your choice in your profile if you wish to later on.

 ## Final Registration :
 
 ![image](https://user-images.githubusercontent.com/88286462/215360546-274a502b-d457-4504-ad0e-d6ab9f939962.png)

 Here where you finalize your registration, if you exit this screen you have to start all over again.
 
 
 
 




