/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.controllers;

import com.mycompany.proyectofinal.models.Db;
import com.mycompany.proyectofinal.models.Libro;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Edgarus
 */
@WebServlet(name = "Libros", urlPatterns = {"/Libros"})
public class Libros extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection connection = Db.conexion();

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM libros;");
            ArrayList<Libro> libros = new ArrayList<Libro>();
            while (rs.next()) {
                Libro libro = new Libro();
                libro.setId(rs.getInt("id"));
                libro.setIsbn(rs.getString("isbn"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setAñoDePublicacion(rs.getInt("anio"));
                libro.setGenero(rs.getString("genero"));
                libros.add(libro);
            }
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "    <head>\n"
                        + "        <title>Start Page</title>\n"
                        + "        <meta charset='UTF-8'>\n"
                        + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                        + "        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\n"
                        + "    </head>\n"
                        + "        <nav class=\"navbar navbar-expand-lg navbar-light bg-light\">\n"
                        + "            <div class=\"container-fluid\">\n"
                        + "                <a class=\"navbar-brand\" href=\"#\">Libreria</a>\n"
                        + "                <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n"
                        + "                    <span class=\"navbar-toggler-icon\"></span>\n"
                        + "                </button>\n"
                        + "                <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n"
                        + "                    <ul class=\"navbar-nav me-auto mb-2 mb-lg-0\">\n"
                        + "                        <li class=\"nav-item\">\n"
                        + "                            <a class=\"nav-link\" aria-current=\"page\" href=\"/proyectofinal/Libros\">Ver libros</a>\n"
                        + "                        </li>\n"
                        + "                        <li class=\"nav-item\">\n"
                        + "                            <a class=\"nav-link\" href=\"/proyectofinal/agregar-libro\">Agregar libro</a>\n"
                        + "                        </li>\n"
                        + "                    </ul>\n"
                        + "                    <form action='/proyectofinal/Buscar' method='GET' class=\"d-flex\">\n"
                        + "                        <input name='titulo' class=\"form-control me-2\" type=\"search\" placeholder=\"Harry Potter...\" aria-label=\"Search\">\n"
                        + "                        <button class=\"btn btn-outline-success\" type=\"submit\">Buscar</button>\n"
                        + "                    </form>\n"
                        + "                </div>\n"
                        + "            </div>\n"
                        + "        </nav>"
                        + "    <body>\n"
                        + "        <table class=\"table\">\n"
                        + "            <thead>\n"
                        + "                <tr>\n"
                        + "                    <th>ISBN</th>\n"
                        + "                    <th>Titulo</th>\n"
                        + "                    <th>Autor</th>\n"
                        + "                    <th>Año de publicacion</th>\n"
                        + "                    <th>Genero</th>\n"
                        + "                    <th></th>"
                        + "                </tr>\n"
                        + "            </thead>\n"
                        + "            <tbody>\n");

                for (int i = 0; i < libros.size(); i++) {
                    Libro libro = libros.get(i);
                    out.println("<tr>");
                    out.println("<td>" + libro.getIsbn() + "</td>");
                    out.println("<td>" + libro.getTitulo() + "</td>");
                    out.println("<td>" + libro.getAutor() + "</td>");
                    out.println("<td>" + libro.getAñoDePublicacion() + "</td>");
                    out.println("<td>" + libro.getGenero() + "</td>");
                    out.println("<td><form action='/proyectofinal/EliminarLibro' method='POST'><input name='id' value='" + libro.getId() + "' hidden><input type='submit' value='Eliminar'></form></td>");
                    out.println("</tr>");
                }

                out.println("</tbody>\n"
                        + "        </table>\n"
                        + "        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p\" crossorigin=\"anonymous\"></script>\n"
                        + "    </body>\n"
                        + "</html>\n"
                        + "");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Libros.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Connection connection = Db.conexion();
        String agregarLibroSql = "INSERT INTO libros (isbn, titulo, autor, anio, genero) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement agregarLibroStmt = null;
        try {
            agregarLibroStmt = connection.prepareStatement(agregarLibroSql);
        } catch (SQLException ex) {
            Logger.getLogger(Libros.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            agregarLibroStmt.setString(1, request.getParameter("isbn"));
            agregarLibroStmt.setString(2, request.getParameter("titulo"));
            agregarLibroStmt.setString(3, request.getParameter("autor"));
            agregarLibroStmt.setInt(4, Integer.parseInt(request.getParameter("anio")));
            agregarLibroStmt.setString(5, request.getParameter("genero"));
            agregarLibroStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Libros.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("/proyectofinal/Libros");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
