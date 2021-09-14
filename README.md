# Appointment Management System MySQL
 An appointment Management system built in SQL and Java

This system is built to manage appointments for a business, it can be a barbershop, a carshop, a doctor's clinic, etc... It shows how using inheritance can facilitate the update of the state of appointments. Four states of appointments were created: Booked, Canceled, Running, Done. when an action is executed on an appointment, it changes its state automatically, or keeps the same state and informs the user that this change is not allowed, ex: an appointment cannot be canceled on the same day it was booked on. The information of the appointments are stored in an SQL database, using mySQL. And the database is directly updated when the state of an appointment changes, the status of the previous state is updated to be false, so that the true status is only assigned to the current state of the appointment. And that way, the owner and the users can keep track of the all the appointments in an organized way.

Here is a video showing the application working!

https://user-images.githubusercontent.com/76274266/133290941-d6423d37-b6dc-4730-ab22-f6a238d47fe1.mp4
