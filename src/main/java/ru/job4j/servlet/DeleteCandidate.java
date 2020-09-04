package ru.job4j.servlet;

import ru.job4j.model.Candidate;
import ru.job4j.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeleteCandidate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Candidate candidate = PsqlStore.instOf().findByIdCandidate(Integer.valueOf(req.getParameter("id")));
        File file = new File(candidate.getPhoto()).getAbsoluteFile();
        file.delete();
        PsqlStore.instOf().deleteCandidate(Integer.valueOf(req.getParameter("id")));
        req.setAttribute("candidates", PsqlStore.instOf().findAllCandidates());
        req.getRequestDispatcher("candidates.jsp").forward(req, resp);
    }
}
