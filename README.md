# PersonManagement

Using Spring Boot (http://projects.spring.io/spring-boot/) create a Java web application project with an embedded web server (pick any) and an embedded database (pick any).
The goal is to store user information in the database. For now we only store firstname and lastname.
Create a web page that allows entering of new user data and stores the information in the database. The page should display an input field for firstname, an input field for lastname and a save button.
On submit, the page should either clear the form, and display the message ‘User data saved’, or it should indicate an error if any or both of the input fields are empty.

Create a page to search for a user. It should contain one input field and a ‘search’ button. On submitting the form, it should display a list of all users were either the firstname or the lastname or both together contain the search word(s).