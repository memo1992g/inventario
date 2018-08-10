
package usam.mantenimientos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import usam.persistencia.Usuarios;
import usam.spring.HibernateUtil;

public class UsuariosMantenimiento {
    public static void main(String[] args) {
        String usuario="orivas";
        String primerApellido="";
        String segundoApellido="";
        String nombres="";
        String tipoDocumento="orivas";
        String numeroDocumento="";
        String noNit="";
        String codigoPais="";
        String codigoDepartamento="orivas";
        String codigoMunicipio="";
        String direccionParticular="";
        String telefonoParticular="";
        String direccionTrabajoUsuario="orivas";
        String telefonoTrabajoUsuario="";
        String faxUsuario="";
        String correoElectronico="";
        String estadoUsuario="orivas";
        String tipoUsuario="";
       
        UsuariosMantenimiento mantenimiento= new UsuariosMantenimiento();
        
        
        System.out.println();
    }
    
    public int guardarUsuario(String usuario,
        String primerApellido,
        String segundoApellido,
        String nombres,
        String tipoDocumento,
        String numeroDocumento,
        String noNit,
        String codigoPais,
        String codigoDepartamento,
        String codigoMunicipio,
        String direccionParticular,
        String telefonoParticular,
        String direccionTrabajoUsuario,
        String telefonoTrabajoUsuario,
        String faxUsuario,
        String correoElectronico,
        String estadoUsuario,
        String tipoUsuario){
        
        SessionFactory factory= HibernateUtil.getSessionFactory();
        Session session=factory.openSession();
        int flag=0;
        
        Usuarios usu=new Usuarios();
        usu.setUsuario(usuario);
        usu.setPrimerApellido(primerApellido);
        usu.setSegundoApellido(segundoApellido);
        usu.setNombres(nombres);
        usu.setTipoDocumento(tipoDocumento);
        usu.setNoNit(noNit);
        usu.setCodigoPais(codigoPais);
        usu.setCodigoDepartamento(codigoDepartamento);
        usu.setCodigoMunicipio(codigoMunicipio);
        usu.setDireccionParticular(direccionParticular);
        usu.setTelefonoParticular(telefonoParticular);
        usu.setDireccionTrabajoUsuario(direccionTrabajoUsuario);
        usu.setTelefonoTrabajoUsuario(telefonoTrabajoUsuario);
        usu.setFaxUsuario(faxUsuario);
        usu.setCorreoElectronico(correoElectronico);
        usu.setEstadoUsuario(estadoUsuario);
        java.util.Date hoy=new java.util.Date();
        usu.setFechaCreacion(hoy);
        try
        {
           session.beginTransaction();
           session.save(usu);
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

