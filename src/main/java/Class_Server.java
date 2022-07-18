
import java.net.*;
import java.io.*;

/**
 * @author Julian Campos
 */
public class Class_Server 
{
    
    public static void main(String[] args)
    {
    
        ServerSocket Sck_Server; 
        Socket Sck; 
        
        DataInputStream ValorEntrada;
        DataOutputStream ValorSalida;
        
        BufferedInputStream BuffInput; 
        BufferedOutputStream BuffOutput; 
        
        int ValorNum; 
        byte[] ArrayBytesServer; 
        
        //Fichero a transferir                 '
        final String LlamadaArchivo = "//../home/ubuntu/"; 
        
      
        try
        {
            try
            {
              
                Sck_Server = new ServerSocket(5000);
                Sck = Sck_Server.accept();
                           
              ValorEntrada = new DataInputStream(Sck.getInputStream());
              
              String nombre = ValorEntrada.readUTF();
                           
              System.out.println(nombre);
              
                           
                final File DirectorioDeArchi = new File(LlamadaArchivo+nombre);          
                       
                //Socket Sck_Client = new Socket("localhost", 5000);
                
                BuffInput = new BufferedInputStream(new FileInputStream(DirectorioDeArchi));
                BuffOutput = new BufferedOutputStream(Sck.getOutputStream());
                
                //El nombre del fichero se envia  
                DataOutputStream DatOutStm = new DataOutputStream(Sck.getOutputStream());
                DatOutStm.writeUTF(DirectorioDeArchi.getName());
                
                //El fichero se envia
                ArrayBytesServer = new byte[8192];
            
                  while ((ValorNum = BuffInput.read(ArrayBytesServer)) != -1)
                  {
                    BuffOutput.write(ArrayBytesServer, 0, ValorNum);
                  }

                
                  BuffInput.close();
                  BuffOutput.close();

            }//Cierre del try interno
            catch(Exception ValorExcepcion)
            {
                System.err.println(ValorExcepcion);
            
            }//Cierre del catch interno
        
        }//Cierre del try principal
        catch(Exception ValorExcepcion)
        {
            System.err.println(ValorExcepcion);
            
        }//Cierre del catch principal
        
        
    }
 
}
