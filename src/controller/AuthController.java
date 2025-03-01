package controller;

import java.util.Scanner;
import java.util.InputMismatchException;
import service.AuthService; // Import Service
import repository.AuthRepository; // Import Repository

public class AuthController {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthRepository repository = new AuthRepository();
        AuthService authService = new AuthService(repository);

        int loginAttempts = 0;
        while (loginAttempts < 3) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (authService.loginUser(username, password)) {
                System.out.println("Login Successful. Proceeding to 2FA...");
                int otp = authService.generateOTP();
                System.out.println("Your OTP is: " + otp); // Simulating OTP sent to user

                int otpAttempts = 0;
                while (otpAttempts < 3) {
                    try {
                        System.out.print("Enter OTP: ");
                        int enteredOTP = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (authService.verifyOTP(enteredOTP)) {
                            System.out.println("Access Granted! Welcome, " + username + ".");
                            return;
                        } else {
                            otpAttempts++;
                            System.out.println("Incorrect OTP. Attempts left: " + (3 - otpAttempts));
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a 6-digit number.");
                        scanner.nextLine(); // Clear buffer
                    }
                }
                System.out.println("OTP verification failed. Please login again.");
                break;
            } else {
                loginAttempts++;
                System.out.println("Invalid credentials. Attempts left: " + (3 - loginAttempts));
            }
        }
        System.out.println("Too many failed login attempts. Exiting.");
    }
}
