package mp02;


/**
 *
 * Authors: Marc Dominic San Pedro, Kurt Neil Aquino
 */
	
public class Move{
	private String name;
	private int power;
	private String type;

	public Move(String name, int power, int type){
		this.name = name;
		this.power = power;
		
		if(type == 1)
			this.type = "grass";
		else if(type == 2)
			this.type = "poison";
		else if(type == 3)
			this.type = "fire";
		else if(type == 4)
			this.type = "water";
		else this.type = "normal";
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getPower(){
		return this.power;
	}
	
	public String getType(){
		return this.type;
	}
}
