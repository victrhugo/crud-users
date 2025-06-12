import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class userRepo {
    private List<User> users = new ArrayList<>();
    private int nextId = 1;

    public void addUser(String name, String email) {
        users.add(new User(nextId++, name, email));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public boolean updateUser(int id, String newName, String newEmail) {
        for (User u : users) {
            if (u.getId() == id) {
                u.setName(newName);
                u.setEmail(newEmail);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(int id) {
        return users.removeIf(u -> u.getId() == id);
    }
}
