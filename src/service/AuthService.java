package service;

import repository.AuthRepository; // Import Repository

public class AuthService {
    private AuthRepository authRepository;

    public AuthService(AuthRepository repository) {
        this.authRepository = repository;
    }

    public boolean loginUser(String username, String password) {
        return authRepository.validateUser(username, password);
    }

    public int generateOTP() {
        return authRepository.generateOTP();
    }

    public boolean verifyOTP(int enteredOTP) {
        return authRepository.verifyOTP(enteredOTP);
    }
}
