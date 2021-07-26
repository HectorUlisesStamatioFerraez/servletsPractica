package mx.edu.utez.controller;

import mx.edu.utez.model.person.BeanPerson;
import mx.edu.utez.model.role.BeanRole;
import mx.edu.utez.model.user.BeanUser;
import mx.edu.utez.model.user.DaoUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletUser", value = "/ServletUser")
public class ServletUser extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(ServletUser.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listUsers", new DaoUser().findAll());
        request.getRequestDispatcher("/views/user/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        switch(action){
            case "create":
                // do something
                String name = request.getParameter("name") != null ? request.getParameter("name") : "";
                String lastname = request.getParameter("lastname");
                int age = Integer.parseInt(request.getParameter("age"));

                String email = request.getParameter("email");
                String password = request.getParameter("password");
                int role = Integer.parseInt(request.getParameter("role"));

                BeanRole beanRole = new BeanRole(role, "");
                BeanPerson beanPerson = new BeanPerson(0, name, lastname, age);
                BeanUser beanUser = new BeanUser(0, email, password, 0, beanPerson, beanRole);

                if(new DaoUser().create(beanUser)){
                    request.setAttribute("message", "Usuario registrado correctamente");
                } else {
                    request.setAttribute("message", "Usuario no registrado");
                }

                doGet(request, response);
                break;

            case "getUserById":
                // do something
                long id = Long.parseLong(request.getParameter("id"));
                request.setAttribute("user", new DaoUser().findById(id));
                request.getRequestDispatcher("/views/user/update.jsp").forward(request, response);
                break;
            case "update":
                // do something
                String name1 = request.getParameter("name") != null ? request.getParameter("name") : "";
                String lastname1 = request.getParameter("lastname");
                int age1 = Integer.parseInt(request.getParameter("age"));

                long id1 = Long.parseLong(request.getParameter("id"));
                String email1 = request.getParameter("email");
                String password1 = request.getParameter("password");
                int role1 = Integer.parseInt(request.getParameter("role"));

                BeanRole beanRole1 = new BeanRole(role1, "");
                BeanPerson beanPerson1 = new BeanPerson(0, name1, lastname1, age1);
                BeanUser beanUser1 = new BeanUser(id1, email1, password1, 0, beanPerson1, beanRole1);

                if(new DaoUser().update(beanUser1)){
                    request.setAttribute("message", "Usuario modificado correctamente");
                } else {
                    request.setAttribute("message", "Usuario no modificado");
                }

                doGet(request, response);
                break;
            case "delete":
                //Para solo cambiar el status a desactivado//
                String name2 = request.getParameter("name") != null ? request.getParameter("name") : "";
                String lastname2 = request.getParameter("lastname");
                int age2 = Integer.parseInt(request.getParameter("age"));

                long id2 = Long.parseLong(request.getParameter("id"));
                String email2 = request.getParameter("email");
                String password2 = request.getParameter("password");
                int role2 = Integer.parseInt(request.getParameter("role"));

                BeanRole beanRole2 = new BeanRole(role2, "");
                BeanPerson beanPerson2 = new BeanPerson(0, name2, lastname2, age2);
                BeanUser beanUser2 = new BeanUser(id2, email2, password2, 1, beanPerson2, beanRole2);

                if(new DaoUser().update(beanUser2)){
                    request.setAttribute("message", "Usuario desactivado correctamente");
                } else {
                    request.setAttribute("message", "Usuario no desactivado");
                }

                /*O tambien podria ser.... (creo) para eliminarlo tal cual
                long id2 = Long.parseLong(request.getParameter("id"));
                request.setAttribute("user", new DaoUser().findById(id2));
                if(new DaoUser().delete(id2)){
                    request.setAttribute("message", "Usuario eliminado correctamente");
                } else {
                    request.setAttribute("message", "Usuario no modificado");
                }
                */
                doGet(request,response);
                break;
            default:

                break;
        }
    }
}
