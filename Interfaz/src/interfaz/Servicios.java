package interfaz;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Servicios extends Remote{

    public boolean Guardar( String Nombre, String Categoria,String Candest, String Tipoedu) throws RemoteException;
    public ArrayList<Persona> Consultar() throws RemoteException;
    
    public ArrayList<Empleado> Consul() throws RemoteException;
    
}