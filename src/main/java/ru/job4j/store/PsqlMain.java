package ru.job4j.store;
import ru.job4j.model.User;

public class PsqlMain {
    public static void main(String[] args) {
        Store store = PsqlStore.instOf();
        store.saveUser(new User("Admin", "q@mail.ru", "root"));
        for (User user : store.findAllUsers()) {
            System.out.println(user.getId() + " " + user.getName() + " " + user.getEmail() + " " + user.getPassword());
        }
    }
}
