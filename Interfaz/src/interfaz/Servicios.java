package interfaz;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Servicios extends Remote{

   
    
    
    public ArrayList<Empleado> Consul() throws RemoteException;
    
    public ArrayList<Perfil> Con() throws RemoteException;
    
}