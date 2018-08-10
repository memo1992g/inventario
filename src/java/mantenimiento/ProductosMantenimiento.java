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
import persistencia.Productos;
import spring.NewHibernateUtil;
/**
 *
 * @author Admin104
 */
public class ProductosMantenimiento {
     public int guardarProductos(
            int idProducto,
            Fabricantes fabricantes,
            String nombreProducto,
            Double precioUnitario,
            String descripcionProducto,
            String modelo) {
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;

        Productos pro = new Productos();
        pro.setIdProducto(idProducto);
        pro.setFabricantes(fabricantes);
        pro.setNombreProducto(nombreProducto);
        pro.setPrecioUnitario(precioUnitario);
        pro.setDescripcionProducto(descripcionProducto);
        pro.setModelo(modelo);

        try {
            session.beginTransaction();
            session.save(pro);
            session.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                flag = 1;
            }
        } finally {
            session.close();
        }
        return flag;
    }

    public Productos consultarProductos(int idProducto) {
        Productos pro = new Productos();
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            pro = (Productos) session.get(Productos.class, idProducto);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
        return pro;
    }
    public int eliminarProductos (int idProducto){
        Productos pro = new Productos();
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
                Session session = factory.openSession();
        int flag = 0;
        
        session.beginTransaction();
        try {
            session.beginTransaction();
            session.delete(pro);
            session.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            if(session.getTransaction().isActive()){
                session.getTransaction().rollback();
                flag = 1;
            }
        } finally {
            session.close();
        }
        return flag;
    }
    
    public List consultarTodoProductos(){
        List<Productos> listaProductos = null;
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        
        session.beginTransaction();
        try {
            Query q = session.createQuery("fromProductos");
            listaProductos = (List<Productos>) q.list() ;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
        }
        return listaProductos;
    }
    
}


