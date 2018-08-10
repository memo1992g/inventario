
package mantenimiento;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import persistencia.Contactos;
import persistencia.Empresa;
import persistencia.FacturaEncabezado;
import spring.NewHibernateUtil;

public class FacturaEncabezadoMantenimiento {

    public int guardarFacturaEncabezado(
            int idFacturaEncabezado,
            Contactos contactos,
            Empresa empresa,
            String fechaFactura,
            String vendedor) {

        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;

        FacturaEncabezado fae = new FacturaEncabezado();
        fae.setIdFacturaEncabezado(idFacturaEncabezado);
        fae.setFechaFactura(fechaFactura);
        fae.setEmpresa(empresa);
        fae.setVendedor(vendedor);
        fae.setContactos(contactos);

        try {
            session.beginTransaction();
            session.save(fae);
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

    public FacturaEncabezado consultarFacturaEncabezado(int idFacturaEncabezado) {
        FacturaEncabezado fae = new FacturaEncabezado();
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            fae = (FacturaEncabezado) session.get(FacturaEncabezado.class, idFacturaEncabezado);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
        return fae;
    }

    public int eliminarFacturaEncabezado(int idFacturaEncabezado) {
        FacturaEncabezado fae = new FacturaEncabezado();
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;

        session.beginTransaction();
        try {
            session.beginTransaction();
            session.delete(fae);
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
}