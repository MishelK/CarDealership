package Command;

import java.util.List;

public interface Command {
	
	public boolean execute(String[] args);
	
	public void help();
	

}
