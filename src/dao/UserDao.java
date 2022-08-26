package dao;

import entities.UserEntity;
import entityControllers.UserDaoCtrl;

import javax.persistence.*;

public class UserDao
{
    private final UserDaoCtrl userDaoCtrl;
    private EntityManagerFactory emf;

    public UserDao()
    {
        assert false;
        emf.createEntityManager();
        userDaoCtrl = new UserDaoCtrl(emf);
    }
    public void addUserEntity (UserEntity userEntity) { userDaoCtrl.create(userEntity); }

    public void editUserEntity (UserEntity userEntity) { userDaoCtrl.update(userEntity); }

    public void deleteUserEntity (UserEntity userEntity, int id) { userDaoCtrl.remove(userEntity, id);}

    public List<UserEntity> getAllUserEntities () { userDaoCtrl.findAll(); }

    public UserEntity getUserById (int id) {userDaoCtrl.find(id);}
}
