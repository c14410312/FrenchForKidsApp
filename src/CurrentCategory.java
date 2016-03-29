public class CurrentCategory {
	String fr;
	String eng;
	  
	CurrentCategory(String line)
	{
	    String[] parts = line.split(",");
	    fr = parts[0];
	    eng = parts[1];
	  }
}
