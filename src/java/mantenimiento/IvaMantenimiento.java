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
import persistencia.Iva;
import spring.NewHibernateUtil;
public class IvaMantenimiento {
  


   
    
   public int guardarIva( int idIva,
   double ivaRetenido,
   double ivaPagado,
   double ivaTotal,
   Double ivaTasa){
       
       SessionFactory factory = NewHibernateUtil.getSessionFactory();
       Session session = factory.openSession();
       int flag=0;
       
       Iva iva = new Iva ();
       iva.setIdIva(idIva);
       iva.setIvaRetenido(ivaRetenido);
       iva.setIvaPagado(ivaPagado);
       iva.setIvaTotal(ivaTotal);
       iva.setIvaTasa(ivaTasa);
       try{
         session.beginTransaction();
         session.save(iva);
         session.getTransaction().commit();
         flag = 1;
       }catch(Exception e){
           if (session.getTransaction().isActive()) {
               session.getTransaction().rollback();
               flag=1;
           }
       }finally
       {
       session.close();
       }
       return flag;
   }

public Iva consultarIva (int idIva){
       Iva iva = new Iva ();
       
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
       Session session = factory.openSession();
       try{
        session.beginTransaction();  
        iva= (Iva) session.get(Iva.class, idIva);
        session.getTransaction().commit();
       }catch(Exception e){
           if (session.getTransaction().isActive()) {
               session.getTransaction().rollback();
           }
       }
       finally{
           session.close();
       }
       return iva;
   }
   
   public int eliminarIva(int idIva){
          Iva iva = new Iva ();
          SessionFactory factory = NewHibernateUtil.getSessionFactory();
       Session session = factory.openSession();
       int flag=0;
       
       session.beginTransaction();
       try{
           
           iva = (Iva) session.get(Iva.class, idIva);
       }catch(Exception e){
           if (session.getTransaction().isActive()){
            session.getTransaction().rollback(); 
               
           }
           flag=0;
       }
       finally{
           session.close();
       }
       return flag;
   }
   
   public List consultarTodosIva(){
       List<Iva> listaIva=null;
       SessionFactory factory = NewHibernateUtil.getSessionFactory();
       Session session = factory.openSession();
       
       session.beginTransaction();
       try{
           Query q = session.createQuery("from Iva");
           listaIva = (List<Iva>) q.list();
           
       }catch(Exception e){
           e.printStackTrace();
       }
       finally{
           
       }
       return listaIva;
   }
   
}
 

