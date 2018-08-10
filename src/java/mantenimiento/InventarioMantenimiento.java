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
import persistencia.Inventario;
import persistencia.Productos;
import spring.NewHibernateUtil;

public class InventarioMantenimiento {

    public int guardarInventario(
            int idInventario,
            Productos productos,
            String existencia,
            String estadoExistencia,
            int stockMinimo,
            String estadoFisico) {

        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;

        Inventario inv = new Inventario();
        inv.setIdInventario(idInventario);
        inv.setProductos(productos);
        inv.setExistencia(existencia);
        inv.setEstadoExistencia(estadoExistencia);
        inv.setStockMinimo(stockMinimo);
        inv.setEstadoFisico(estadoFisico);

        try {
            session.beginTransaction();
            session.save(inv);
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

    public Inventario consultarInventario(int idInventario) {
        Inventario inv = new Inventario();
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            inv = (Inventario) session.get(Inventario.class, idInventario);
            session.getTransaction().rollback();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }finally {
            session.close();
        }
    return inv;
    }
    
    public int eliminarInventario(int idInventario){
        Inventario inv = new Inventario();
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;
        
        session.beginTransaction();
        try {
            session.beginTransaction();
            session.delete(inv);
            session.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            if(session.getTransaction().isActive()){
            session.getTransaction().rollback();
            flag=1;
            }
        } finally{
            session.close();
        }
        return flag;
    }
    public List consultarTodoInventario(){
        List<Inventario> listaInventario = null;
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        
        session.beginTransaction();
        try {
            Query q = session.createQuery("from Inventario");
            listaInventario = (List<Inventario>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{}
        return listaInventario;
    }
}

