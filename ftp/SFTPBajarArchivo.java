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
public class SFTPBajarArchivo {
    private Session session;
    /**
     * Funcion que Permite descargar un archivo 
     * @param src es el path del archivo a descargar
     * @throws JSchException            Excepcion lanzada por algun error en la ecucion del comando sftp.
     * @throws SftpException            Error al utilizar comando SFTP.
     * @throws IllegalAccessException   Excepcion lanzada cuando no hay conexion establecida. 
     * @throws IOException              Excepcion al leer el texto arrojado luego de la ejecucion del comando SFTP.
     */
    public final void getFile(String src) throws JSchException, SftpException, IllegalAccessException, IOException{
        if (this.session != null && this.session.isConnected()) {
            ChannelSftp channelSftp = (ChannelSftp)this.session.openChannel("sftp");
            channelSftp.connect();
            channelSftp.get(src, "C:\\Users\\Ruben Angeles\\Desktop\\Scan\\temp.pdf");
            System.out.println("Archivo descargado Correctamente");
            //return is;
        }else{
            throw new IllegalAccessException("No Existe sesion SFTP iniciada");
        }
    }
}
