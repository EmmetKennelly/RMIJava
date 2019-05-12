import java.io.File;
import java.io.FileWriter;
import java.rmi.*;



public interface Moniterinterface extends Remote {

    public String sayHello( )
            throws java.rmi.RemoteException;


    public void registerForCallback(
            Moniterinterface callbackClientObject
    ) throws java.rmi.RemoteException;

    public void unregisterForCallback(
            Moniterinterface callbackClientObject)
            throws java.rmi.RemoteException;

    public byte[] fileToBytes(File file) throws java.rmi.RemoteException;
    public File getFileToDownload(String fileName) throws java.rmi.RemoteException;

    public String upload(byte[] bytes, File fileDest, String name, String tag)throws java.rmi.RemoteException;

    public byte[] download(String name)throws java.rmi.RemoteException;
}
