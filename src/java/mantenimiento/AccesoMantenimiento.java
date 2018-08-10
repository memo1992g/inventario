package mantenimiento;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import persistencia.Acceso;
import persistencia.Empresa;
import spring.NewHibernateUtil;

public class AccesoMantenimiento {

    public int guardarAcceso(int idAcceso,
            Empresa empresa,
            String nombreAcceso,
            String apellidoAcceso,
            String usuario,
            String fechaRegistroAcceso,
            String contrasena,
            String email,
            String tipoAcceso) {

        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;

        Acceso ace = new Acceso();
        ace.setIdAcceso(idAcceso);
        ace.setEmpresa(empresa);
        ace.setNombreAcceso(nombreAcceso);
        ace.setApellidoAcceso(apellidoAcceso);
        ace.setUsuario(usuario);
        ace.setEmail(email);
        ace.setFechaRegistroAcceso(fechaRegistroAcceso);
        ace.setTipoAcceso(tipoAcceso);

        try {
            session.beginTransaction();
            session.save(ace);
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

    public Acceso consultarAcceso(int idAcceso) {
        Acceso ace = new Acceso();
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            ace = (Acceso) session.get(Acceso.class, idAcceso);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
        return ace;
    }

    public int eliminarAcceso(int idAcceso) {
        Acceso ace = new Acceso();
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;

        session.beginTransaction();
        try {
            session.beginTransaction();
            session.delete(ace);
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

    public List consultarTodoUsuarios() {
        List<Acceso> listaAcceso = null;
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        session.beginTransaction();
        try {
            Query q = session.createQuery("from Acceso");
            listaAcceso = (List<Acceso>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return listaAcceso;
    }

}