package ftp;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.IOException;

public class SFTPConnector {
    private Session session;
    /**
     * Establece una  conexion a un servidor SFTP
     * @param username Nombre de Usuario
     * @param password Contraseña
     * @param host Host a conectar
     * @param port Puerto del host
     * @throws JSchException Cualquier Error al establecer conexion SFTP
     * @throws IllegalAccessException Indcica que ya existe una conexion SFTP establecida
     */
    public void connect(String username,String password,String host,int port) throws JSchException, IllegalAccessException{
        if (this.session == null || !this.session.isConnected()) {
            JSch jSch = new JSch();
            this.session = jSch.getSession(username, host, port);
            this.session.setPassword(password);
            this.session.setConfig("StrictHostKeyChecking", "no");
            this.session.connect();
        }else{
            throw new IllegalAccessException("Sesion SFTP ya iniciada");
        }
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
    /**
     * Cierra la sesion SFTP
     */
    public final void disconnect(){
        this.session.disconnect();
    }
}