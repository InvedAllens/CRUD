package ftp;

import ConexionFTP.SFTPConnector;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

/**
 *
 * @author Ruben Angeles
 */
public class SFTPSubirArchivo extends Observable implements Runnable {

    private Session session;
    private boolean flag;
    private final File fichero;

    
    public SFTPSubirArchivo() {
        this.fichero = null;
    }

    public SFTPSubirArchivo(File fichero) {
        this.fichero = fichero;
    }

    

    public boolean isFlag() {
        return flag;
    }

    /**
     * Añade un archivo al directorio FTP usando SFTP.
     *
     * @param fichero es el archivo a subir
     * @throws IllegalAccessException Excepcion lanzada cuando no hay conexion
     * establecida.
     * @throws JSchException Excepcion lanzada por algun error en la ecucion del
     * comando sftp.
     * @throws SftpException Error al utilizar comando SFTP.
     * @throws IOException Excepcion al leer el texto arrojado luego de la
     * ejecucion del comando SFTP.
     */
    public final void addFile(File fichero) throws IllegalAccessException, JSchException, SftpException, IOException {
        if (this.session != null && this.session.isConnected()) {
            ChannelSftp channelSftp = (ChannelSftp) this.session.openChannel("sftp"); //Abre el canal SFTP
            //channelSftp.cd(ftpPath); //Cambiamos a la ubicacion de Destino
            channelSftp.connect();
            System.out.println(String.format("Creando archivo %s en el directorio %s", fichero.getName(), "~/"));
            channelSftp.put(fichero.getAbsolutePath(), fichero.getName()); //Subimos el archivo con el path de destino y con el nombre
            System.out.println("Archivo subido Exitosamente");
            channelSftp.exit();
            channelSftp.disconnect();
        } else {
            throw new IllegalAccessException("No Existe sesion SFTP iniciada");
        }
    }

    @Override
    public void run() {
        this.flag = true;
        while (flag) {
            try {
                SFTPConnector connector = new SFTPConnector("archivo", "soportemx", "192.168.40.15");
                System.out.println("conectando.....");
                this.session = connector.connect();
                System.out.println("Conectado");
                addFile(this.fichero);
                connector.disconnect();
            } catch (JSchException | SftpException | IllegalAccessException | IOException  e) {
                System.out.println(e.getMessage());
            } 
            this.flag = false;
        }
        this.setChanged();
        this.notifyObservers(flag);
        this.clearChanged();
        System.out.println("Proceso Terminado");
    }

}
