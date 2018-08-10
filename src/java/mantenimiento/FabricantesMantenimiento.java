/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimiento;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import persistencia.Fabricantes;
import spring.NewHibernateUtil;

public class FabricantesMantenimiento {


    public int guardarFabricantes(
            int idFabricante,
            String nombreFabricante,
            String descripcionFabricante,
            int numeroProductos,
            String fechaRegistroFabricante) {

        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;

        Fabricantes fab = new Fabricantes();
        fab.setIdFabricante(idFabricante);
        fab.setNombreFabricante(nombreFabricante);
        fab.setDescripcionFabricante(descripcionFabricante);
        fab.setNumeroProductos(numeroProductos);
        fab.setFechaRegistroFabricante(fechaRegistroFabricante);
        try{
           session.beginTransaction();
            session.save(fab);
            session.getTransaction().commit();
            
        }catch(Exception e){
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                flag=1;
            }
        }finally{
            session.close();
        }
        return flag;
    }

public Fabricantes consultarFabricantes(int idFabricante){
       Fabricantes fab = new Fabricantes();
        SessionFactory factory=NewHibernateUtil.getSessionFactory();
        Session session =factory.openSession();
        try
        {
           session.beginTransaction();
           fab=(Fabricantes) session.get(Fabricantes.class, idFabricante);
           session.getTransaction().commit();;
        }
        catch(Exception e)
        {
            if(session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            
        }
        finally{
            session.close();
        }
        return fab;
    } 
    
    public int eliminarFabricante(int idFabricante){
       Fabricantes fab = new Fabricantes();
        SessionFactory factory=NewHibernateUtil.getSessionFactory();
        Session session =factory.openSession();
        int flag=0;
        
        session.beginTransaction();
        try
        {
            fab=(Fabricantes) session.get(Fabricantes.class, idFabricante);
            
        }
        catch(Exception e){
            if(session.getTransaction().isActive()){
            session.getTransaction().rollback();
        }
        flag=0;
        }
        finally{
               session.close();
                }
       
  
    return flag;
    } 
    
    public List consultarTodosFabricantes(){
        List<Fabricantes> listaFabricantes=null;
        SessionFactory factory=NewHibernateUtil.getSessionFactory();
        Session session =factory.openSession();
        
        session.beginTransaction();
        try
        {
           Query q= session.createQuery("from Fabricantes");
           listaFabricantes =(List<Fabricantes>) q.list();
           
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            
        }
       return listaFabricantes; 
    }
}
