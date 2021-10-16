package by.derovi.solid.dependency_inversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Comparator;

@Component
@Qualifier("myControllerNamedOrCalledSimplyA")
class Controller {
    @Autowired
    private Model model;

    public String getTopByAge() {
        return model.getAllUsers()
                .stream()
                .sorted(Comparator.comparingInt(User::getAge).reversed())
                .limit(10)
                .map(user -> user.getName() + "-" + user.getAge())
                .reduce("[", (str, name) -> str + name + ";") + "]";
    }

    public void register(String name, int age) {
        model.saveUser(new User(name, age));
    }

    public void growOld(String name) {
        User user = model.getUser(name);
        user.setAge(user.getAge() + 1);
        model.saveUser(user);
    }
}

