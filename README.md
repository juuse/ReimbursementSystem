# Reimbursement System

## Project Description

This is the first project from Revature training.

This entails a basic reimbursement system with login validation and the ability for users to create and submit reimbursements. Finance managers can also approve or deny open reimbursement tickets. 

## Technologies Used

* JDBC
* Java Servlets
* AWS PostGreSQL RDS
* HTML/CSS

## Features

* Database connected login page with proper logout feature
* Ability to create and edit reimbursements persisted in the database
* Bootstrap based components and formatting

## Getting Started

Use the command below in git bash to download all of the files
git clone https://github.com/juuse/ReimbursementSystem.git

### Importing the project
Download spring tool suite(sts)
Open sts on your local machine
Create or select a folder to use as your workspace
In the top navbar go to File -> Import -> "Existing Maven Projects" 
Select the cloned repo directory by clicking the browse button
Check the pom.xml and click finish

### Setting up tomcat in sts 
Download Apache Tomcat from the internet and unzip it(NOTE: Do not get Tomcat NATIVE, this project ran on a regular tomcat server)
Put tomcat in your sts workspace through the top navbar go to Window -> Preferences
In the popup window go to Server -> Runtime Environments -> Add
Type in tomcat in the search bar of the window and click on the matching tomcat version downloaded
In the following window click Browse and select the folder for tomcat that was downloaded
Hit finish then apply and close

Next go to the Servers folder in your workspace in the left window
Open the tomcat folder and right click the server.xml and click open with
Type maven in the search bar and use the "Maven POM Editor"
Go to line 63 and change the port number to a higher one, since 8080 is frequently used and lower ports also have set purposes (i.e. use 9001 or similar)
Right click the Tomcat server in the servers window in the bottom left of sts
Click on Add and Remove and add the project to the server and click finish
Then click on the project in the left window again.
In the top navbar go to Project -> Properties
In the popup window go to Targeted Runtimes -> Check Apache Tomcat -> Apply and Close

### Setting up the AWS Database
Create an AWS free tier account
Search Security Group and click on the one labeled VPC feature
Click on create security group, name it whatever you want, and click on Add Rule under Inbound Rules
For type, just do All Traffic and for Source use Anywhere or My Ip and then click create security group
Go to the RDS module in AWS and click on Create Database
Engine Type -> PostGreSQL, Templates -> Free Tier, Public Access -> Yes, VPC security group -> The one just created that allows either only your IP or everyone
Set the master username and master password (NOTE: Make sure to remember what these are because these are how you will access the database)
Once the status becomes available click on the database and copy the endpoint and port

Now navigate to the dao package in src/main/java inside of sts
Near the top of each class in the DaoImpl files where there are the System.getenv calls replace them with your credentials
username will be your database username, password will be your master password, and in the url only replace the system.get env section with your endpoint

Now the project is ready to run

## Usage

After running the project, open your desired browser and type localhost:<port number>/project1 (i.e. http://localhost:9001/project1/)
The login screen should appear at this point. Simply log in to either an employee or finance manager and it will redirect you to their respective home pages.
From there you will be shown tables of information for the user and their reimbursements as well as cards to create reimbursement tickets and a card to approve or deny reimbursements if you are logged into a finance manager.
To logout you can simply click the dropdown menu in the top right and click logout

