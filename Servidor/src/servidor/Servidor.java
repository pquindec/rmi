package servidor;

import interfaz.Servicios;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import conexion.Conexion;
import interfaz.Empleado;
import interfaz.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Servidor extends UnicastRemoteObject implements Servicios {
    public Servidor() throws RemoteException{
        super();
        try{
            Registry registro = LocateRegistry.createRegistry(1234);
            registro.rebind("rmi://localhost:1234/Servicios", new Servidor());
            UIServer.list.addElement("Servidor Activo");
            UIServer.lista.setModel(UIServer.list);
        }catch (RemoteException ex){}
    }
    
 
    @Override
    public boolean Guardar(String Nombre, String Categoria,String Candest, String Tipoedu) throws RemoteException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        boolean resp = false;
        
        try {
            String sql = "insert into institucion ( nombre, categoria, candest, tipoedu) values(?, ?, ?, ?)";

            Conexion objConexion = new Conexion();
            conn = objConexion.getConnection();
            PreparedStatement ps= conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, Nombre);
            ps.setString(2, Categoria);
            ps.setString(3, Candest);
            ps.setString(4, Tipoedu);
            ps.executeUpdate();
            resp = true;

            UIServer.list.addElement("Metodo Ingreso: Persona Registrada (" + Nombre + " " + Categoria + Candest + Tipoedu+")");
            UIServer.lista.setModel(UIServer.list);
        } catch (Exception e) {
        } finally{
            try {
                if(rs != null) rs.close();
                if(pst != null) pst.close();
                if(conn != null) conn.close();
            } catch (Exception e) {
            }
        }
        return resp;
    }

    @Override
    public ArrayList<Persona> Consultar() throws RemoteException {
        ArrayList<Persona> ListadoPersona = new ArrayList<Persona>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            String sql = "select * from institucion";
            Conexion objConexion = new Conexion();
            conn = objConexion.getConnection();
            pst = conn.prepareCall(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                Persona objPersona = new Persona();
              
                objPersona.setNombre(rs.getString("nombre"));
                objPersona.setCategoria(rs.getString("categoria"));
                objPersona.setCandest(rs.getString("candest"));
                objPersona.setTipoedu(rs.getString("tipoedu"));
              
                ListadoPersona.add(objPersona);
            }
            
            UIServer.list.addElement("Metodo Consulta: Consulta Realizada");
            UIServer.lista.setModel(UIServer.list);
        } catch (Exception e) {
        } finally{
            try {
                if(rs != null) rs.close();
                if(pst != null) pst.close();
                if(conn != null) conn.close();
            } catch (Exception e) {
            }
        }   
        return ListadoPersona;
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
                System.out.println(objEmpleado.getApellido());
                System.out.println(objEmpleado.getIdemp());
            }
            
            UIServer.list.addElement("Metodo Consulta: Consulta Realizada");
            UIServer.lista.setModel(UIServer.list);
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