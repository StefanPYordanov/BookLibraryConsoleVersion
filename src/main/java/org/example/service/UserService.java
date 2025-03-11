package org.example.service;

public interface UserService {
    public boolean login(String username, String password);

    public String register();

    public void deleteUser(int id);

    public void displayUsers();

    public void giveRole(int id);

    public int nextUserId();

    public String findRole(String username);

    public int findUserId(String currentUser);
}
