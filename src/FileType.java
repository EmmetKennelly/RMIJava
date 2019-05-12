
import java.util.Objects;
import java.util.Observable;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class FileType extends Observable {
	private static String path; 
	private String name;
	static String filetype;
	    private String tag;
	    private String fileName;
	    private int id;
	    private static int nextId = 0;
	static double size;
	private Lock lock;
	private Semaphore semaphore;
	
	public  FileType() {
        this.name = null;
        this.tag = null;
 
        this.id = 0;
        this.fileName = null;
    }

	
	
	
	
	
	public FileType (String name, String path, double size) {
		this.name = name;
		this.path = path;
		this.size = size;
		this.tag = tag;
        id = nextId;
        nextId++;
        this.fileName = fileName;
		
		;
	}


	
public static String getType() {

	return filetype;
	
	
}
public static double getSize() {
	return size;
}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
		setChanged(); 
		    notifyObservers(); 
		System.out.println("file "+ getPath()+ "Deleted");
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
		setChanged(); 
		    notifyObservers(); 
		System.out.println("file "+ getName()+ "Deleted");
	}
	 public String getTag() {
	        return tag;
	    }

	    public void setTag(String description) {
	        this.tag = description;
	    }

	    public int getId() {
	        return id;
	    }

	    public String getFileName() {
	        return fileName;
	    }

	    public void setFileName(String fileName) {
	        this.fileName = fileName;
	    }


		public boolean equals(FileType file) {
	        if (this == file) return true;
	        if (!(file instanceof FileType)) return false;
	        FileType that = (FileType) file;
	        return Objects.equals(getName(), that.getName()) &&
	                Objects.equals(getTag(), that.getTag());
	    }

	

	public int compareTo(FileType file) {
        return this.name.equalsIgnoreCase(file.name) && this.name.equalsIgnoreCase(file.getName())? 0 :
                this.name.compareToIgnoreCase(file.name) == 1 && this.name.compareToIgnoreCase(file.getName()) == 1 ? 1 : -1;
    }
	public void setSize(double d) {
		// TODO Auto-generated method stub
		size=size;
	}
    public String toString(){
        return String.format("%s.%s", this.name, this.name);
    }
	public void accuirePermit() throws InterruptedException
	{
		
		semaphore.acquire();
	}
	public void releasePermit(){
		semaphore.release();

	}
	public void accuireAll(){
		try {
			semaphore.acquire(4);
			System.out.println("file "+ getName()+ "Deleted");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}



	public void setType(String type) {
		// TODO Auto-generated method stub
		this.filetype=type;
	}

	
}
