package com.tutrit.storestapiservice.client;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.core.persistence.UserPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserClient {

    @Autowired(required = false)
    private UserPersistence userPersistence;

    /**
     * Saves the specified user.
     *
     * <p>This method saves the user with the provided
     * name and delegates the save operation to the
     * underlying user persistence.
     * If you extend this class and override this method,
     * make sure to document any specific behavior or considerations.</p>
     *
     * @param user The user to be saved.
     * @return The saved user.
     */
    public User save(final User user) {
        userPersistence.save(user);
        return user;
    }

    /**
     * Finds a user by its ID.
     *
     * <p>This method retrieves the user with the
     * specified ID from the underlying user persistence.
     * If you extend this class and override this
     * method, make sure to document any specific
     * behavior or considerations.</p>
     *
     * @param id The ID of the user to find.
     * @return The found user.
     */
    public User findById(final String id) {
        return userPersistence.findById(id);
    }
}
