package mx.edu.utez.model.user;

import mx.edu.utez.model.person.BeanPerson;
import mx.edu.utez.model.role.BeanRole;
import mx.edu.utez.service.ConnectionMySQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoUser {
    Connection con;
    CallableStatement cstm;
    ResultSet rs;
    Logger logger = LoggerFactory.getLogger(DaoUser.class);

    public List<BeanUser> findAll(){
        List<BeanUser> listUsers = new ArrayList<>();
        try {
            // SELECT * FROM users AS U INNER JOIN persons AS P ON U.idPerson = P.id INNER JOIN roles AS R ON U.idRole = R.id;
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_findAll}");
            rs = cstm.executeQuery();

            while(rs.next()){
                BeanRole role = new BeanRole();
                BeanPerson person = new BeanPerson();
                BeanUser user = new BeanUser();

                role.setId(rs.getInt("idRole"));
                role.setDescription(rs.getString("nameRole"));

                person.setId(rs.getLong("idPerson"));
                person.setName(rs.getString("name"));
                person.setLastname(rs.getString("lastname"));
                person.setEdad(rs.getInt("age"));

                user.setId(rs.getLong("idUser"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setStatus(rs.getInt("status"));

                user.setIdPerson(person);
                user.setIdRole(role);

                listUsers.add(user);
            }
        }catch (SQLException e){
            logger.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return listUsers;
    }

    public BeanUser findById(long id){
        BeanUser user = null;
        try {
            // SELECT * FROM users AS U INNER JOIN persons AS P ON U.idPerson = P.id INNER JOIN roles AS R ON U.idRole = R.id;
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("SELECT * FROM user AS U INNER JOIN person AS P ON U.idPersons = P.idPerson INNER JOIN roles AS R ON U.idRoles = R.idRole WHERE U.idUser = ?");
            cstm.setLong(1, id);
            rs = cstm.executeQuery();

            if(rs.next()){
                BeanRole role = new BeanRole();
                BeanPerson person = new BeanPerson();
                user = new BeanUser();

                role.setId(rs.getInt("idRole"));
                role.setDescription(rs.getString("nameRole"));

                person.setId(rs.getLong("idPerson"));
                person.setName(rs.getString("name"));
                person.setLastname(rs.getString("lastname"));
                person.setEdad(rs.getInt("age"));

                user.setId(rs.getLong("idUser"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setStatus(rs.getInt("status"));

                user.setIdPerson(person);
                user.setIdRole(role);
            }
        }catch (SQLException e){
            logger.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return user;
    }

    public boolean create(BeanUser user){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_create(?,?,?,?,?,?)}");
            cstm.setString(1, user.getIdPerson().getName());
            cstm.setString(2, user.getIdPerson().getLastname());
            cstm.setInt(3, user.getIdPerson().getEdad());
            cstm.setString(4, user.getEmail());
            cstm.setString(5, user.getPassword());
            cstm.setInt(6, user.getIdRole().getId());
            cstm.execute();
            flag = true;
        }catch(SQLException e){
            logger.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }

    public boolean update(BeanUser user){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_update(?,?,?,?,?,?,?)}");
            cstm.setString(1, user.getIdPerson().getName());
            cstm.setString(2, user.getIdPerson().getLastname());
            cstm.setInt(3, user.getIdPerson().getEdad());
            cstm.setString(4, user.getEmail());
            cstm.setString(5, user.getPassword());
            cstm.setInt(6, user.getIdRole().getId());
            cstm.setLong(7, user.getId());

            flag = cstm.execute();
        }catch(SQLException e){
            logger.error("Ha ocurrido un error: " + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }

    public boolean delete(long idUser){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_delete2(?)}");
            cstm.setLong(1, idUser);

            flag = cstm.execute();
        }catch(SQLException e){
            logger.error("Ha ocurrido un error: " + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }
/*
    public static void main(String[] args) {
        BeanUser beanUser = new BeanUser();
        BeanPerson beanPerson = new BeanPerson();
        BeanRole beanRole = new BeanRole();
        DaoUser daoUser = new DaoUser();

        // Listando usuarios
        List<BeanUser> listUsers = new ArrayList<>();
        listUsers = daoUser.findAll();

        for (int i = 0; i < listUsers.size(); i++){
            System.out.println(listUsers.get(i).getEmail());
        }

        /*
        // Registrando usuarios
        boolean registed = false;

        beanRole.setId(1);

        beanPerson.setName("Paty");
        beanPerson.setLastname("Morales");
        beanPerson.setEdad(29);

        beanUser.setEmail("patymorales@utez.edu.mx");
        beanUser.setPassword("admin1234");

        beanUser.setIdPerson(beanPerson);
        beanUser.setIdRole(beanRole);

        registed = daoUser.create(beanUser);

        System.out.println("Se ha registrado correctamente");
        */

        // Eliminar de manera "baja lógica"
        /*
        boolean flag = false;
        flag = daoUser.delete(4);
        System.out.println("Se realizó correctamente");

    }*/
}
