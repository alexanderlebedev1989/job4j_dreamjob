package ru.job4j.servlet;

import ru.job4j.model.User;
import ru.job4j.store.PsqlStore;
import ru.job4j.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Store store = PsqlStore.instOf();
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (check(name, email, password)) {
            User user = new User(name, email, password);
            store.saveUser(user);
            resp.sendRedirect(req.getContextPath() + "/auth.do");
        } else {
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
        }
    }

    private boolean check(String name, String email, String password) {
        return name != null && email != null && password != null;
    }
}
