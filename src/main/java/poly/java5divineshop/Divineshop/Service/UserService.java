package poly.java5divineshop.Divineshop.Service;

import org.springframework.stereotype.Service;
import poly.java5divineshop.Divineshop.Data.Model.UserM;

import java.util.List;

@Service
public interface UserService {
    List<UserM> findAll();
}
