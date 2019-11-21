package ftp;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.IOException;

/**
 *
 * @author Ruben Angeles
 */
public class SFTPSubirArchivo implements Runnable {
    private Session session;
    private final SFTPConnector connector = new SFTPConnector();
    private String ftpPath,filePath,fileName;

    public SFTPSubirArchivo(String ftpPath, String filePath, String fileName) {
        this.ftpPath = ftpPath;
        this.filePath = filePath;
        this.fileName = fileName;
    }
    
    
    
    /**
     * Añade un archivo al directorio FTP usando SFTP.
     * @param ftpPath Path del directorio FTP o destino.
     * @param filePath Path original del Archivo a subir.
     * @param fileName Nombre que tendra el archivo en el destino.
     * @throws IllegalAccessException   Excepcion lanzada cuando no hay conexion establecida.
     * @throws JSchException            Excepcion lanzada por algun error en la ecucion del comando sftp.
     * @throws SftpException            Error al utilizar comando SFTP.
     * @throws IOException              Excepcion al leer el texto arrojado luego de la ejecucion del comando SFTP.
     */
    public final void addFile(String ftpPath,String filePath,String fileName) throws IllegalAccessException, JSchException, SftpException, IOException{
        if (this.session != null && this.session.isConnected()) {
            ChannelSftp channelSftp = (ChannelSftp) this.session.openChannel("sftp"); //Abre el canal SFTP
            //channelSftp.cd(ftpPath); //Cambiamos a la ubicacion de Destino
            channelSftp.connect();
            System.out.println(String.format("Creando archivo %s en el directorio %s", fileName,ftpPath));
            channelSftp.put(filePath, fileName); //Subimos el archivo con el path de destino y con el nombre
            System.out.println("Archivo subido Exitosamente");
            channelSftp.exit();
            channelSftp.disconnect();
        }else{
            throw new IllegalAccessException("No Existe sesion SFTP iniciada");
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("conectando.....");
            connector.connect("archivo", "soportemx", "192.168.40.15", 22);
            System.out.println("Conectado");
            addFile(ftpPath, filePath, fileName);
            
        } catch (JSchException | SftpException | IllegalAccessException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
}
