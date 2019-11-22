package ftp;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import db.Conexion;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Observable;
import misc.Ticket;

/**
 *
 * @author Ruben Angeles
 */
public class SFTPSubirArchivo extends Observable implements Runnable {

    private Session session;
    private final SFTPConnector connector = new SFTPConnector();
    Conexion cn = new Conexion();
    private String pathDestino;
    private boolean flag;
    private final File fichero;
    private final Ticket ticket;

    public SFTPSubirArchivo(File fichero,Ticket ticket) {
        this.fichero = fichero;
        this.ticket = ticket;
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
            this.pathDestino = "/home/archivo/"+fichero.getName();
        } else {
            throw new IllegalAccessException("No Existe sesion SFTP iniciada");
        }
    }

    @Override
    public void run() {
        this.flag = true;
        while (flag) {
            try {
                System.out.println("conectando.....");
                connector.connect("archivo", "soportemx", "192.168.40.15", 22);
                System.out.println("Conectado");
                addFile(this.fichero);
                connector.disconnect();
                cn.conectar();
                cn.insertar("INSERT INTO Docs(ns,fecha,ticket,detalle,observaciones,path) VALUES ('"+ticket.getNs()+"','"+ticket.getFecha()+"','"+ticket.getTicket()+"','"+ticket.getDetalle()+"','"+ticket.getObservaciones()+"','"+pathDestino+"')");
                cn.desconectar();
            } catch (JSchException | SftpException | IllegalAccessException | IOException | SQLException e) {
                System.out.println(e.getMessage());
            } 
            this.flag = false;
        }
        javax.swing.JOptionPane.showMessageDialog(null, "Registro Creado Exitosamente");
        this.setChanged();
        this.notifyObservers(flag);
        this.clearChanged();
        System.out.println("Proceso Terminado");
    }

}
