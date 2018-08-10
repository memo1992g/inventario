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
import persistencia.Configuracion;
import persistencia.FacturaDetalle;
import persistencia.FacturaEncabezado;
import persistencia.Iva;
import persistencia.Productos;

import spring.NewHibernateUtil;

/**
 *
 * @author Admin104
 */
public class FacturaDetalleMantenimiento {
    public int guardarFactD(
           int idFacturaDetalle,
            Configuracion configuracion,
            FacturaEncabezado facturaEncabezado,
            Iva iva,
            Productos productos,
            int cantidad,
            double totalFila,
            double totalColumna,
            double subtotalColumna){
        
        SessionFactory factory= NewHibernateUtil.getSessionFactory();
        Session session=factory.openSession();
        int flag=0;
        
        FacturaDetalle facdet=new FacturaDetalle();
         facdet.setIdFacturaDetalle(idFacturaDetalle);
         facdet.setConfiguracion(configuracion);
         facdet.setFacturaEncabezado(facturaEncabezado);
         facdet.setIva(iva);
         facdet.setProductos(productos);
         facdet.setCantidad(cantidad);
         facdet.setTotalFila(totalFila);
         facdet.setTotalColumna(totalColumna);
         facdet.setSubtotalColumna(subtotalColumna);
        try
        {
           session.beginTransaction();
           session.save(facdet);
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
        
    public FacturaDetalle consultarFacturaDetalle(int idFacturaDetalle){
        FacturaDetalle factdet=new FacturaDetalle();
        SessionFactory factory=NewHibernateUtil.getSessionFactory();
        Session session =factory.openSession();
        try
        {
           session.beginTransaction();
           factdet=(FacturaDetalle) session.get(Configuracion.class, idFacturaDetalle);
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
        return factdet;
    }
    
    public int eliminarFacturaDetalle(int idFacturaDetalle){
       FacturaDetalle factdet=new FacturaDetalle();
        SessionFactory factory=NewHibernateUtil.getSessionFactory();
        Session session =factory.openSession();
        int flag=0;
        
        session.beginTransaction();
        try
        {
            factdet=(FacturaDetalle) session.get(FacturaDetalle.class, idFacturaDetalle);
            
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
    
    public List consultarTodosUsuarios(){
        List<FacturaDetalle> listafactd=null;
        SessionFactory factory=NewHibernateUtil.getSessionFactory();
        Session session =factory.openSession();
        
        session.beginTransaction();
        try
        {
           Query q= session.createQuery("from Factura_Detalle");
           listafactd =(List<FacturaDetalle>) q.list();
           
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            
        }
       return listafactd; 
    }
        }


