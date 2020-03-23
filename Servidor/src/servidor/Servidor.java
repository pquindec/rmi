package servidor;

import interfaz.Servicios;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import conexion.Conexion;
import interfaz.Empleado;
import interfaz.Perfil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Servidor extends UnicastRemoteObject implements Servicios {
    public Servidor() throws RemoteException{
        super();
        try{
            Registry registro = LocateRegistry.createRegistry(1201);
            registro.rebind("rmi://localhost:1211/Servicios", new Servidor());
            System.out.println("Servidor Activo");
        }catch (RemoteException ex){}
    }
    
 
   @Override
    public ArrayList<Perfil> Con() throws RemoteException {
        ArrayList<Perfil> ListadoPerfil = new ArrayList<Perfil>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            String sql = "SELECT * FROM perfil";
            Conexion objConexion = new Conexion();
            conn = objConexion.getConnection();
            pst = conn.prepareCall(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                Perfil objPerfil = new Perfil();
              
                objPerfil.setDescrip(rs.getString("descrip"));
                objPerfil.setId(rs.getString("id"));
                objPerfil.setSueldo(rs.getString("sueldo"));
              
                ListadoPerfil.add(objPerfil);
               
            }
        } catch (Exception e) {
        } finally{
            try {
                if(rs != null) rs.close();
                if(pst != null) pst.close();
                if(conn != null) conn.close();
            } catch (Exception e) {
            }
        }   
        return ListadoPerfil;
    }
    
    @Override
    public ArrayList<Empleado> Consul() throws RemoteException {
        ArrayList<Empleado> ListadoEmpleado = new ArrayList<Empleado>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            String sql = "SELECT * FROM empleado";
            Conexion objConexion = new Conexion();
            conn = objConexion.getConnection();
            pst = conn.prepareCall(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                Empleado objEmpleado = new Empleado();
              
                objEmpleado.setApellido(rs.getString("apellido"));
                objEmpleado.setIdemp(rs.getString("idemp"));
                objEmpleado.setIdperfil(rs.getString("idperfil"));
                objEmpleado.setNombre(rs.getString("nombre"));
              
                ListadoEmpleado.add(objEmpleado);
               
            }
        } catch (Exception e) {
        } finally{
            try {
                if(rs != null) rs.close();
                if(pst != null) pst.close();
                if(conn != null) conn.close();
            } catch (Exception e) {
            }
        }   
        return ListadoEmpleado;
    }
}