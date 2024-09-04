package org.example.service;

public interface UserService {


    public void login(String username, String password);
    public void register ();
    public void deleteUser(int id);
    public void displayUsers();
    public void giveRole(int id);

}
