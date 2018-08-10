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
import persistencia.Empresa;
import spring.NewHibernateUtil;

public class EmpresaMantenimiento {

   
    public int guardarEmpresa(
            int idEmpresa,
            String nombreEmpresa,
            String direccionEmpresa,
            String telefonoEmpresa,
            String encargadoEmpresa) {

        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;

        Empresa emp = new Empresa();
        emp.setIdEmpresa(idEmpresa);
        emp.setNombreEmpresa(nombreEmpresa);
        emp.setDireccionEmpresa(direccionEmpresa);
        emp.setTelefonoEmpresa(telefonoEmpresa);
        emp.setEncargadoEmpresa(encargadoEmpresa);

        try {
            session.beginTransaction();
            session.save(emp);
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

public Empresa consultarEmpresa(int idEmpresa) {
        Empresa emp = new Empresa();
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            emp = (Empresa) session.get(Empresa.class, idEmpresa);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();

            }
        } finally {
            session.close();
        }
        return emp;
    }

    public int eliminarEmpresa(int idEmpresa) {
        Empresa emp = new Empresa();
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;
        session.beginTransaction();
        try {
            emp = (Empresa) session.get(Empresa.class, idEmpresa);
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            flag = 0;
        }
finally{
            session.close();
        }
        return flag;
    }
    
    public List consultarTodosEmpresa(){
        List<Empresa> listaUsuarios=null;
        SessionFactory factory=NewHibernateUtil.getSessionFactory();
        Session session =factory.openSession();
        
        session.beginTransaction();
        try
        {
           Query q= session.createQuery("from Empresa");
           listaUsuarios =(List<Empresa>) q.list();
           
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            
        }
       return listaUsuarios; 
    }
    
}