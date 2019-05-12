import java.io.*;
import java.rmi.*;
import java.rmi.server.*;

public class AbstractClient extends UnicastRemoteObject implements 


	final public static int BUF_SIZE = 1024 * 64;

	public ClientImpl() throws RemoteException {
		super();
	}

	public String notifyMe(String message) {
		String returnMessage = "Call back received: " + message;
		System.out.println(returnMessage);
		return returnMessage;
	}

	public File getFile(String fileName) {

		File file = new File("C:\\Users\\emmet\\Desktop\\SharedFolder\\" + fileName);
		if (file.exists()) {
			return file;
		}
		return null;
	}

	@Override
	public byte[] fileToBytes(File file) {

		// FileInputStream fileInputStream = new FileInputStream(file);
		// ByteArrayOutputStream bOutputStream = new ByteArrayOutputStream();
		// bOutputStream.toByteArray(fileInputStream);

		byte[] bytes = new byte[BUF_SIZE];

		try {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Error no file ");
				// e.printStackTrace();
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
}
