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
import persistencia.Compras;
import persistencia.Contactos;
import persistencia.Inventario;
import persistencia.Iva;
import persistencia.Productos;
import spring.NewHibernateUtil;

/**
 *
 * @author Admin104
 */
public class ComprarMantenimiento {
     public int guardarcompras(
           int idCompra,
            Contactos contactos,
            Inventario inventario,
            Iva iva,
            Productos productos,
            String fechaCompra,
            double totalCompra
            ){
        
        SessionFactory factory= NewHibernateUtil.getSessionFactory();
        Session session=factory.openSession();
        int flag=0;
        
        Compras com=new Compras();
        com.setIdCompra(idCompra);
        com.setContactos(contactos);
        com.setInventario(inventario);
        com.setIva(iva);
        com.setProductos(productos);
        com.setFechaCompra(fechaCompra);
        com.setTotalCompra(totalCompra);
        try
        {
           session.beginTransaction();
           session.save(com);
           session.getTransaction().commit();
           flag=1;
        }
        catch(Exception e){
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
                flag=1;
            }
        }finally
            {
                    session.close();
                    }
            return flag;
        }
        
    public Compras consultarComprasId(int idCompra){
       Compras com =new Compras();
        SessionFactory factory=NewHibernateUtil.getSessionFactory();
        Session session =factory.openSession();
        try
        {
           session.beginTransaction();
          com=(Compras) session.get(Compras.class, idCompra);
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
        return com;
    }
    
    public int eliminarCompras(int idCompra){
       Compras com=new Compras();
        SessionFactory factory=NewHibernateUtil.getSessionFactory();
        Session session =factory.openSession();
        int flag=0;
        
        session.beginTransaction();
        try
        {
            com=(Compras) session.get(Compras.class, idCompra);
            
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
    
    public List consultarCompras(){
        List<Compras> listaCom=null;
        SessionFactory factory=NewHibernateUtil.getSessionFactory();
        Session session =factory.openSession();
        
        session.beginTransaction();
        try
        {
           Query q= session.createQuery("from Compras");
           listaCom =(List<Compras>) q.list();
           
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            
        }
       return listaCom; 
    }
        }

