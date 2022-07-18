import java.net.*;
import java.io.*;
import static java.time.Clock.system;
import java.util.Scanner;
import javax.swing.JOptionPane;



/**
 *
 * @author Julian Campos
 */
public class Class_Client
{
 
    
    public static void main(String[] args)
    {
        
        DataOutputStream ValorSalida; 
        BufferedInputStream BuffInput;
        BufferedOutputStream BuffOutput; 
    
        byte[] ArrayBytes; 
        int ValorNum; 
        String Directorio;
        
        Scanner sc = new Scanner(System.in);
        String ip = JOptionPane.showInputDialog("Ingresa la ip");
        
        try
        {               
            Socket Sck_Client = new Socket(ip, 5000);
            
            ValorSalida = new DataOutputStream(Sck_Client.getOutputStream());
        
            ArrayBytes = new byte[1024];
            BuffInput = new BufferedInputStream(Sck_Client.getInputStream());
                            
            DataInputStream DInputS = new DataInputStream(Sck_Client.getInputStream());
            
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del archivo");
           //String nombre = sc.nextLine();
            
            ValorSalida.writeUTF(nombre);
            
            Directorio = DInputS.readUTF();
           
            
         //String rut = "/home/hhhh/";
         //rut+=Directorio;
           
            BuffOutput = new BufferedOutputStream(new FileOutputStream(Directorio));
            ValorNum = BuffInput.read(ArrayBytes);
            BuffOutput.write(ArrayBytes, 0, ValorNum);
        
           while((ValorNum = BuffInput.read(ArrayBytes)) != 1)
            {
                if(ValorNum==-1)break;
                BuffOutput.write(ArrayBytes, 0, ValorNum);
                System.out.println(ValorNum);
            
            }
            
             BuffOutput.close();
             DInputS.close();
             
             JOptionPane.showMessageDialog(null, "se enviado el archivo con exito");

        }
        catch (Exception ValorExcecion)
        {
            System.err.println(ValorExcecion.getCause());
        
        }
    
    }
    
}//Cierre
