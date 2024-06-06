package poly.java5divineshop.Divineshop.Service;

import poly.java5divineshop.Divineshop.Data.Dto.RoleDto;
import poly.java5divineshop.Divineshop.Data.Entity.RoleE;
import poly.java5divineshop.Divineshop.Data.Model.RoleM;

import java.sql.SQLException;

public interface RoleService {
    int updateRoleByIdRole(RoleDto roleDto) throws SQLException;

    RoleM getRoleByUsernameAndRole(String username, String role) throws SQLException;
}
