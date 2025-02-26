<%-- 
    Document   : signup
    Created on : Feb 24, 2025, 9:55:45 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Signup</title>
    </head>
    <body>
        <h2>Signup Page</h2>
        <form id="signupForm" action="RegisterServlet" method="post" onsubmit="return validatePassword()">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required maxlength="30">
            <br>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required maxlength="30">
            <br>

            <label for="confirmPassword">Confirm Password:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required maxlength="30">
            <br>

            <span id="error-message" style="color: red;"></span>
            <br>

            <button type="submit">Signup</button>
            <br>
        </form>
        <form action="login.jsp">
            <button type="submit">Return</button>
        </form> 



    </body>

</html>

<script>
    function validatePassword() {
        var password = document.getElementById("password").value;
        var confirmPassword = document.getElementById("confirmPassword").value;
        var errorMessage = document.getElementById("error-message");

        if (password !== confirmPassword) {
            errorMessage.textContent = "Passwords do not match!";
            return false; // Prevent form submission
        } else {
            errorMessage.textContent = ""; // Clear error message
            return true; // Allow form submission
        }
    }
</script>