package com.example.user.connections;

/**
 * Created by User on 25/01/2017.
 */
public interface UserManagment {
    public void deleteUser(MyUser user);

    public void createNewUser(MyUser user);

    public MyUser retrieveUser(); //// TODO: 25/01/2017 : what does this method needs?

    public void updateUser(MyUser user);
}
