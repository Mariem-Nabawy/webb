document.addEventListener('DOMContentLoaded', function () {
    console.log("Document ready");

    // Manage Login and Register Section Toggles
    const loginSection = document.getElementById('login');
    const registerSection = document.getElementById('register');
    const registerLink = document.getElementById('register-link');
    const loginLink = document.getElementById('login-link');

    // Ensure only one section is visible at load
    if (loginSection && registerSection) {
        loginSection.style.display = 'block';
        registerSection.style.display = 'none'; // Hide register by default
    }

    // Handle Register Link Click
    if (registerLink) {
        registerLink.addEventListener('click', function (e) {
            e.preventDefault();
            if (loginSection && registerSection) {
                loginSection.style.display = 'none';
                registerSection.style.display = 'block';
            }
        });
    }

    // Handle Login Link Click
    if (loginLink) {
        loginLink.addEventListener('click', function (e) {
            e.preventDefault();
            if (loginSection && registerSection) {
                registerSection.style.display = 'none';
                loginSection.style.display = 'block';
            }
        });
    }

    function handleRegisterForm(event) {
        event.preventDefault();
        console.log('Form submission triggered');
    }
    

    // Register Form Submission
    function handleRegisterForm(event) {
        event.preventDefault(); // Prevent the form from submitting the traditional way
        
        const registerForm = document.getElementById('register-form');
        const formData = new FormData(registerForm);
        const data = {};
    
        formData.forEach((value, key) => {
            data[key] = value; // Convert form data to a JSON object
        });
    
        fetch('http://localhost:8080/api/users/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json', // Ensure the server expects JSON
            },
            body: JSON.stringify(data), // Send the data as JSON
        })
        .then(response => {
            if (response.ok) {
                return response.text(); // Handle success response
            } else {
                return response.text().then(err => Promise.reject(err)); // Handle errors
            }
        })
        .then(successMessage => {
            alert('Registration successful: ' + successMessage);
            registerForm.reset(); // Clear the form
        })
        .catch(errorMessage => {
            alert('Registration failed: ' + errorMessage);
        });
    }

    document.getElementById("login-form").addEventListener("submit", async function (e) {
        e.preventDefault(); // Prevent default form submission behavior
        const formData = new FormData(e.target);
    
        // Construct the login payload
        const credentials = {
            username: formData.get("username"),
            password: formData.get("password"),
        };
    
        try {
            const response = await fetch("http://localhost:8080/api/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(credentials),
            });
    
            if (response.ok) {
                const data = await response.json();
                alert("Login successful!");
                localStorage.setItem("token", data.token); // Save token for future requests
                window.location.href = "index.html"; // Redirect to homepage
            } else {
                const errorMessage = await response.text();
                alert("Login failed: " + errorMessage);
            }
        } catch (error) {
            console.error("Error:", error);
            alert("An error occurred during login.");
        }
    });

    document.getElementById("register-form").addEventListener("submit", async function (e) {
        e.preventDefault(); // Prevent default form submission behavior
        const formData = new FormData(e.target);
    
        // Construct the registration object
        const user = {
            fullName: formData.get("fullName"),
            nationalId: formData.get("nationalId"),
            email: formData.get("email"),
            phone: formData.get("phone"),
            subscription: formData.get("subscription"),
            password: formData.get("password"),
        };
    
        try {
            const response = await fetch("http://localhost:8080/api/users/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(user),
            });
    
            if (response.ok) {
                alert("User registered successfully!");
                window.location.href = "login.html"; // Redirect to login page
            } else {
                const errorMessage = await response.text();
                alert("Failed to register user: " + errorMessage);
            }
        } catch (error) {
            console.error("Error:", error);
            alert("An error occurred while registering.");
        }
    });

    console.log("Script loaded successfully");
});
