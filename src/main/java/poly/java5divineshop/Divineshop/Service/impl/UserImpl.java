package poly.java5divineshop.Divineshop.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.java5divineshop.Divineshop.Data.Model.UserM;
import poly.java5divineshop.Divineshop.Repo.UserRepo;
import poly.java5divineshop.Divineshop.Service.UserService;

import java.util.List;

@Service
public class UserImpl implements UserService {
    @Autowired
    UserRepo userRepo;
    @Override
    public List<UserM> findAll() {
        return UserM.convertListUserEToListUserM(userRepo.findAll());
    }
}
