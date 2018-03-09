/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import BeanSession.AdministrationLocal;
import EntityBean.Employe;
import Structure.Aide;
import Structure.Parametre;
import Structure.Requete;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nawar
 */
@WebServlet(name = "ControleAdministration", urlPatterns = {"/ControleAdministration"})
public class ControleAdministration extends HttpServlet {

    @EJB
    private AdministrationLocal administration;
    public String jspClient = null;
    public String message   = null; 
    public String requete   = null;
    public String DirNAT = "DiRNAT";
    ArrayList <Parametre> mesParam = null;
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
        
        try {
        
            
            String act=request.getParameter("action");
            if ((act == null)||(act.equals("null")))
            {
                 jspClient="/JSP_Pages/test.jsp";
            }else {
            
                  verifierConnexion(request, response);
            }
            
            

            RequestDispatcher Rd;
            Rd = getServletContext().getRequestDispatcher(jspClient);
            Rd.forward(request, response);

        }catch(Exception exepption){System.err.println(exepption.getMessage());}
        /////////////////////////////////////////////////////////////////////////////////////////////////
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControleAdministration</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControleAdministration at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////
   public void verifierConnexion(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
     
        String act=request.getParameter("action");
        if (act.equals("directionNational")){
            DirectionNational(request,response);
            jspClient = "/JSP_Pages/Page_Message.jsp";
            
            
        }

    }
    
            
protected void DirectionNational(HttpServletRequest request,
HttpServletResponse response) throws ServletException, IOException
{
    requete = null;
    mesParam = null;
    String employe = request.getParameter( "employe" );
    try{
    if (!employe.isEmpty()){
        Integer idEmp = Integer.parseInt(employe);
        Parametre p = new Parametre("id","int",idEmp);
        requete = Requete.getEmployes + " AND id =:id"; 
        mesParam.add(p);
        List<Employe> listeEmp = administration.getEmploye(Requete.getEmployes, mesParam);
        if (listeEmp !=null)
        {
             requete = null;
             mesParam = null;
             requete = Requete.getRoles + " AND nom =:nom";
             p = new Parametre("nom", "String", DirNAT);
             mesParam.add(p);
             //administration.employeModifierRole(Aide.getObjectDeListe(listeEmp),null);
        }else message = "Employe n'existe pas";
    }else{

        String nom = request.getParameter( "nom" );
        String prenom = request.getParameter( "prenom" );
        String adresse = request.getParameter( "adresse" );
        String login = request.getParameter( "login" );
        String mdp = request.getParameter( "mdp" );
        String cmdp = request.getParameter( "confirmemdp" );
    }

    request.setAttribute( "message", message );
}catch(Exception exe){}

}  
    
   
    ////////////////////////////////////////////////////////////////////////////////////////////////////

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
        processRequest(request, response);
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
        processRequest(request, response);
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
