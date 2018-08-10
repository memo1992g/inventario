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
import persistencia.Acceso;
import persistencia.Configuracion;
import persistencia.Empresa;
import spring.NewHibernateUtil;

/**
 *
 * @author Admin104
 */
public class ConfiguracionMantenimiento {

     
    public int guardarConfiguracion(
           int idConfiguracion,
            Acceso acceso,
            Empresa empresa,
            byte [] logo,
            String simboloMoneda,
            String nombreMoneda,
            double iva,
            String zonaHoraria){
        
        SessionFactory factory= NewHibernateUtil.getSessionFactory();
        Session session=factory.openSession();
        int flag=0;
        
        Configuracion conf=new Configuracion();
        conf.setIdConfiguracion(idConfiguracion);
        conf.setAcceso(acceso);
        conf.setLogo(logo);
        conf.setSimboloMoneda(simboloMoneda);
       conf.setNombreMoneda(nombreMoneda);
       conf.setCodigoMoneda(nombreMoneda);
       conf.setIva(iva);
       conf.setZonaHoraria(zonaHoraria);
        try
        {
           session.beginTransaction();
           session.save(conf);
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
        
    public Configuracion consultarConfiguarionId(int idConfiguracion){
        Configuracion conf=new Configuracion();
        SessionFactory factory=NewHibernateUtil.getSessionFactory();
        Session session =factory.openSession();
        try
        {
           session.beginTransaction();
           conf=(Configuracion) session.get(Configuracion.class, idConfiguracion);
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
        return conf;
    }
    
    public int eliminarConfiguracion(int idConfiguracion){
       Configuracion conf=new Configuracion();
        SessionFactory factory=NewHibernateUtil.getSessionFactory();
        Session session =factory.openSession();
        int flag=0;
        
        session.beginTransaction();
        try
        {
            conf=(Configuracion) session.get(Configuracion.class, idConfiguracion);
            
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
    
    public List consultarTodosConfiguracion(){
        List<Configuracion> listaConf=null;
        SessionFactory factory=NewHibernateUtil.getSessionFactory();
        Session session =factory.openSession();
        
        session.beginTransaction();
        try
        {
           Query q= session.createQuery("from Configuracion");
           listaConf =(List<Configuracion>) q.list();
           
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            
        }
       return listaConf; 
    }
     public int modificarConfiguracion(
           int idConfiguracion,
            Acceso acceso,
            Empresa empresa,
            byte [] logo,
            String simboloMoneda,
            String nombreMoneda,
            double iva,
            String zonaHoraria){
        
        SessionFactory factory= NewHibernateUtil.getSessionFactory();
        Session session=factory.openSession();
        int flag=0;
        
        Configuracion conf=new Configuracion();
        conf.setIdConfiguracion(idConfiguracion);
        conf.setAcceso(acceso);
        conf.setLogo(logo);
        conf.setSimboloMoneda(simboloMoneda);
       conf.setNombreMoneda(nombreMoneda);
       conf.setCodigoMoneda(nombreMoneda);
       conf.setIva(iva);
       conf.setZonaHoraria(zonaHoraria);
        try
        {
           session.beginTransaction();
           session.update(conf);
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
        
    
        }