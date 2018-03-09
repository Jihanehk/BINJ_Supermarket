/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structure;

import EntityBean.Employe;
import EntityBean.Role;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Nawar
 */
public abstract class Aide {
    
     public static void Casting(String type,Object o) throws ExecutionException{
      try {
       switch(type){
           case "int":
               o = (int)o;
               break;
           case "String":
                o = (String)o;
               break;
           case "Role":
                o = (Role)o;
               break;
               
           case "Employe":
                o = (Employe)o;
               break;
       }
      }catch(Exception exe){ throw  exe;}
   }
     
     public Object getObjectDeListe(List<Object> liste){
         Object o = null;
         for ( Object element : liste){
             o = element;
         }
         return o;
     }
    
}
