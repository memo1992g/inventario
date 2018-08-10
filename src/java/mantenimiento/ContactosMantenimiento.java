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
import persistencia.Contactos;
import spring.NewHibernateUtil;

public class ContactosMantenimiento {
   
    public int guardarContacto(
    int idContacto,
    String nombreContacto,
    String direccionContacto,
    String tipoContacto,
    String telefonoContacto,
    String emailContacto,
    String encargadoContacto,
    String telefonoEncargadoContacto,
    String fechaRegistroContacto){
        
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag=0;
        
        Contactos con = new Contactos();
        con.setIdContacto(idContacto);
        con.setNombreContacto(nombreContacto);
        con.setDireccionContacto(direccionContacto);
        con.setTipoContacto(tipoContacto);
        con.setTelefonoContacto(telefonoContacto);
        con.setEmailContacto(emailContacto);
        con.setEncargadoContacto(encargadoContacto);
        con.setTelefonoEncargadoContacto(telefonoEncargadoContacto);
        con.setFechaRegistroContacto(fechaRegistroContacto);
        try{
            session.beginTransaction();
            session.save(con);
            session.getTransaction().commit();
            flag=1;
            
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
public Contactos consultarContactos(int idContacto){
        Contactos con=new Contactos();
        SessionFactory factory=NewHibernateUtil.getSessionFactory();
        Session session =factory.openSession();
        try
        {
           session.beginTransaction();
           con=(Contactos) session.get(Contactos.class, idContacto);
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
        return con;
    }
    
    public int eliminarContactos(int idContacto){
        Contactos con=new Contactos();
        SessionFactory factory=NewHibernateUtil.getSessionFactory();
        Session session =factory.openSession();
        int flag=0;
        
        session.beginTransaction();
        try
        {
            con=(Contactos) session.get(Contactos.class, idContacto);
            
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
    
    public List consultarTodosContactos(){
        List<Contactos> listaContactos=null;
        SessionFactory factory=NewHibernateUtil.getSessionFactory();
        Session session =factory.openSession();
        
        session.beginTransaction();
        try
        {
           Query q= session.createQuery("from Contactos");
           listaContactos =(List<Contactos>) q.list();
           
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            
        }
       return listaContactos; 
    } 
}
