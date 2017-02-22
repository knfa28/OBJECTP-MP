/**
 *
 * Authors: Marc Dominic San Pedro, Kurt Neil Aquino
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class MP01{
	public static void main(String[] args){
            boolean yes = true;
            Scanner sc = new Scanner(System.in);
            int i = 0;
            System.out.println("1- PvCPU, 2- PvP");
            do{
                try{
                    yes = true;
                    i = sc.nextInt();
                }
                catch(InputMismatchException e){
                    if( sc.hasNextLine() )
                        sc.nextLine();
                    System.out.print("Invalid, please try again: ");
                    yes = false;
                }
                finally
                {   
                   if( yes && i!=1 && i!=2){
                      System.out.print("Invalid, please try again: "); 
                      yes = false;
                   }
                }
            }while(!yes);
            switch(i){
                case 1: PvCPU kurt = new PvCPU();
                case 2: PvP marc = new PvP();
            }      
        }
}
    

