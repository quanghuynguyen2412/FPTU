//======= DO NOT EDIT THIS FILE ============
import java.io.*;
public class Main {
    
   public static void main(String args[]) throws Exception {
       
       BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
       System.out.print("Enter user role: ");
       String role = in.readLine();
       System.out.print("Enter user password: ");
       String password = in.readLine();
        System.out.printf("1. TC = 1 - test toString()\n2. TC = 2 - test getRole()"
               + "\n3. TC = 3 - test getCode()\n");       
       System.out.print("Enter TC: ");
       int tc = Integer.parseInt(in.readLine());
       System.out.println("OUTPUT:");
       Password s = new Password(password, role);
       if(tc == 1) 
           System.out.println(s);
       else if(tc == 2)
           System.out.println(s.getRole()); 
       else if(tc == 3)
           System.out.println(s.getCode());
   }
 }