package schedeass.app.services;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.annotations.common.util.impl.Log;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import schedeass.app.entities.User;
import schedeass.app.repositories.UserRepository;
import schedeass.app.utils.AES;
import schedeass.app.utils.Utilities;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final String key = "SchedeAssPT";



    @Transactional
    public int registerUser(String username, String email, String password) {

        int flag = 0;

        if (!(Utilities.isNotNull(username) && Utilities.isNotNull(email) && Utilities.isNotNull(password))) {
            //"ERRORE";
            flag = 0;
            return flag;
        }

        if (isUserAlreadyRegistered(email)) {
            //"Sei già registrato!";
            flag = 1;
            return flag;
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        userRepository.save(user);

        return 2;
    }


    public Boolean isUserAlreadyRegistered(String email) {

        User user = new User();
        user = userRepository.findByEmail(email).orElse(null);

        return user != null;

    }

    public User login(String email, String password) {

        if (isUserAlreadyRegistered(email)) {

            User user = new User();
            user = userRepository.findByEmail(email).orElse(null);

            String decPassword = AES.decrypt(user.getPassword(), key);
            if (decPassword.equals(password)) {
                return user;
            }

        }

        return null;

    }

}
