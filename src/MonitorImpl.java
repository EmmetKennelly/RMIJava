

import java.io.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.Vector;

public class MonitorImpl extends UnicastRemoteObject implements Moniterinterface {



	private Vector clientList;
	final public static int BUF_SIZE = 1024 * 64;

	public MonitorImpl() throws RemoteException {
		super();
		clientList = new Vector();

	}
	public String sayHello() throws java.rmi.RemoteException {
		return ("hello");
	}

	public synchronized void registerForCallback(Moniterinterface callbackClientObject)
			throws java.rmi.RemoteException {
	
		if (!(clientList.contains(callbackClientObject))) {
			clientList.addElement(callbackClientObject);
			System.out.println("Registered new client ");
			doCallbacks();
		} 
	}

	
	public synchronized void unregisterForCallback(Moniterinterface callbackClientObject)
			throws java.rmi.RemoteException {
		if (clientList.removeElement(callbackClientObject)) {
			System.out.println("Unregistered client ");
		} else {
			System.out.println("unregister: clientwasn't registered.");
		}
	}

	private synchronized void doCallbacks() throws java.rmi.RemoteException {
		
		System.out.println("\n" + "Callbacks initiated ---");
		for (int i = 0; i < clientList.size(); i++) {
			System.out.println("doing " + i + "-th callback\n");
			Moniterinterface nextClient = (Monitorinteface) clientList.elementAt(i);
	
			nextClient.notifyMe("Number of registered clients=" + clientList.size());
		} 
		System.out.println("\n" + "Server completed callbacks ---");
	} 

	public File getFileToDownload(String fileName) {

		File file = new File("C:\\Users\\emmet\\Desktop\\SharedFolder\\" + fileName);
		if (!file.exists()) {
			return null;
		}
		return file;
	}

	
	public byte[] fileToBytes(File file) {
		
		byte[] bytes = new byte[BUF_SIZE];

		try {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[BUF_SIZE];
			try {
				for (int readNum; (readNum = fis.read(buf)) != -1;) {
					bos.write(buf, 0, readNum);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			bytes = bos.toByteArray();
			bos.close();

		} catch (IOException e) {
			// handle IOException
			e.printStackTrace();
		}

		return bytes;
	}

	public String upload(byte[] bytes, File fileDest, String name, String tag) {

		try {

		//todo: fix the path
		File file = new File("C:\\Users\\emmet\\Desktop\\SharedFoldere"+'.'+tag);

		writeBytesToFile(file, bytes);

		System.out.println("File: " + fileDest.getName() + " uploaded correctly.");
		return "File: " + name + "." + tag + " uploaded correctly.";
		} catch (Exception e) {
		e.printStackTrace();
		return "Upload error!!!!";
		}
		}
		public static void writeBytesToFile(File theFile, byte[] bytes) throws IOException {
			BufferedOutputStream bos = null;

			try {
			FileOutputStream fos = new FileOutputStream(theFile);
			bos = new BufferedOutputStream(fos);
			bos.write(bytes);
			}finally {
			if(bos != null) {
			try  {
			//flush and close the BufferedOutputStream
			bos.flush();
			bos.close();
			} catch(Exception e){}
			}
			}
			

	}
	public byte[] download(String name) {
	// byte[] bytes = new byte[BUF_SIZE];
		File fileSource = getFileToDownload(name);

		if (fileSource == null) {
			return null;
		}
		byte[] fileBytes = fileToBytes(fileSource);

		System.out.println("File: " + fileSource.getName() + " downloaded correctly.");

		return fileBytes;

	}
}