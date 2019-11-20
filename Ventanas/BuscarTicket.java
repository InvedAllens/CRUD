/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.mxrck.autocompleter.TextAutoCompleter;
import db.Conexion;
import ftp.SFTPConnector;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import misc.Ticket;
import misc.Usuario;

/**
 *
 * @author Ruben Angeles
 */
public class BuscarTicket extends javax.swing.JFrame {

    public Usuario usuario = Opciones.usuario;
    private Ticket t = new Ticket();
    public Conexion cn = new Conexion();
    ResultSet rs;

    /**
     * Creates new form BuscarTicket
     */
    public BuscarTicket() {
        initComponents();
        this.setLocationRelativeTo(null);
        llenarTicket();
    }

    private void llenarTicket() {
        TextAutoCompleter textAutoCompleter = new TextAutoCompleter(tfTicket);
        try {
            cn.conectar();
            rs = cn.consulta("SELECT ticket FROM Docs");
            while (rs.next()) {
                //cbNS.addItem(resultado.getString("ns"));
                switch (rs.getInt("ticket")) {
                    case 2:
                        textAutoCompleter.addItem("Cambio de Toner");
                        break;
                    case 1:
                        textAutoCompleter.addItem("Falla sin Ticket");
                        break;
                    default:
                        textAutoCompleter.addItem(rs.getObject("ticket"));
                        break;
                }
            }
            cn.desconectar();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private void getValores(String serie) {
        if (!serie.isEmpty()) {
            try {
                cn.conectar();
                rs = cn.consulta("SELECT * FROM Docs WHERE ticket = '" + tfTicket.getText() + "'");
                while (rs.next()) {
                    t.setId(rs.getInt("id"));
                    t.setNs(rs.getString("ns"));
                    t.setFecha(rs.getDate("fecha"));
                    t.setTicket(rs.getInt("ticket"));
                    t.setDetalle(rs.getString("detalle"));
                    t.setObservaciones(rs.getString("observaciones"));
                    t.setPath(rs.getString("path"));
                }
                rs = cn.consulta("SELECT * FROM Equipo WHERE ns = '" + t.getNs() + "'");
                while (rs.next()) {
                    lblSerie_.setText(rs.getString(1));
                    lblMarca_.setText(rs.getString(2));
                    lblModelo_.setText(rs.getString(3) + " " + rs.getString(4));
                    lblUbicacion_.setText(rs.getString(7) + " " + rs.getString(8));
                    lblIP_.setText(rs.getString(5));
                    lblMac_.setText(rs.getString(6));
                    lblTipo_.setText(rs.getString(9));
                    lblToner_.setText(rs.getString(10));
                }
                rs = cn.consulta("SELECT * FROM Toner WHERE modelo = '" + lblToner_.getText() + "'");
                while (rs.next()) {
                    lblTonerCian_.setText(String.valueOf(rs.getInt(2)));
                    lblTonerMagenta_.setText(String.valueOf(rs.getInt(3)));
                    lblTonerYellow_.setText(String.valueOf(rs.getInt(4)));
                    lblTonerBlack_.setText(String.valueOf(rs.getInt(5)));
                }
                cn.desconectar();
                lblTicket_.setText(String.valueOf(t.getTicket()));
                lblDetalles_.setText(t.getDetalle());
                lblObservaciones_.setText(t.getObservaciones());
                lblFecha_.setText(String.valueOf(t.getFecha()));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void bajarArchivoSFTP() {
        String user = "archivo";
        String pass = "soportemx";
        String host = "192.168.40.15";
        int port = 22;
        try {
            SFTPConnector connector = new SFTPConnector();
            System.out.println("conectando.....");
            connector.connect(user, pass, host, port); //conecta al servidor
            System.out.println("Conectado");
            connector.getFile(t.getPath());
            connector.disconnect();

        } catch (JSchException | IllegalAccessException | SftpException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void abrirArchivo() {
        try {
            ProcessBuilder pb = new ProcessBuilder();
            pb.command("cmd.exe", "/c", "C:\\Users\\Ruben Angeles\\Desktop\\Scan\\temp.pdf");
            pb.start();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLogOut = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        pnlBusqueda = new javax.swing.JPanel();
        lblNS = new javax.swing.JLabel();
        tfTicket = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        pnlDatos = new javax.swing.JPanel();
        lblSerie = new javax.swing.JLabel();
        lblSerie_ = new javax.swing.JLabel();
        lblMarca = new javax.swing.JLabel();
        lblMarca_ = new javax.swing.JLabel();
        lblModelo = new javax.swing.JLabel();
        lblModelo_ = new javax.swing.JLabel();
        lblUbicacion = new javax.swing.JLabel();
        lblUbicacion_ = new javax.swing.JLabel();
        lblIP = new javax.swing.JLabel();
        lblIP_ = new javax.swing.JLabel();
        lblMac = new javax.swing.JLabel();
        lblMac_ = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        lblTipo_ = new javax.swing.JLabel();
        pnlInventario = new javax.swing.JPanel();
        lblToner_ = new javax.swing.JLabel();
        lbltTonerCian = new javax.swing.JLabel();
        lblTonerCian_ = new javax.swing.JLabel();
        lbltTonerMagenta = new javax.swing.JLabel();
        lblTonerMagenta_ = new javax.swing.JLabel();
        lbltTonerYellow = new javax.swing.JLabel();
        lblTonerYellow_ = new javax.swing.JLabel();
        lblTonerBlack = new javax.swing.JLabel();
        lblTonerBlack_ = new javax.swing.JLabel();
        pnlDetalles = new javax.swing.JPanel();
        lblTicket = new javax.swing.JLabel();
        lblTicket_ = new javax.swing.JLabel();
        lblDetalles = new javax.swing.JLabel();
        lblDetalles_ = new javax.swing.JLabel();
        lblObservaciones = new javax.swing.JLabel();
        lblObservaciones_ = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblFecha_ = new javax.swing.JLabel();
        btnVerArchivo = new javax.swing.JButton();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLogOut.setBackground(new java.awt.Color(153, 204, 255));
        btnLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit32.png"))); // NOI18N
        btnLogOut.setToolTipText("Regresar a Menú principal");
        btnLogOut.setOpaque(false);
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, -1, -1));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Consultar Ticket");
        getContentPane().add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, -1, -1));

        pnlBusqueda.setOpaque(false);

        lblNS.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblNS.setForeground(new java.awt.Color(255, 255, 255));
        lblNS.setText("Numero de Ticket:");

        tfTicket.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lupa64.png"))); // NOI18N
        btnBuscar.setOpaque(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBusquedaLayout = new javax.swing.GroupLayout(pnlBusqueda);
        pnlBusqueda.setLayout(pnlBusquedaLayout);
        pnlBusquedaLayout.setHorizontalGroup(
            pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBusquedaLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblNS)
                .addGap(10, 10, 10)
                .addComponent(tfTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar)
                .addContainerGap())
        );
        pnlBusquedaLayout.setVerticalGroup(
            pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBusquedaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnBuscar)
                    .addComponent(tfTicket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNS))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pnlBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 390, 90));

        pnlDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Equipo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlDatos.setOpaque(false);

        lblSerie.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblSerie.setForeground(new java.awt.Color(255, 255, 255));
        lblSerie.setText("Numero de Serie:");

        lblSerie_.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblSerie_.setForeground(new java.awt.Color(255, 255, 255));
        lblSerie_.setText("N/A");

        lblMarca.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblMarca.setForeground(new java.awt.Color(255, 255, 255));
        lblMarca.setText("Marca:");

        lblMarca_.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblMarca_.setForeground(new java.awt.Color(255, 255, 255));
        lblMarca_.setText("N/A");

        lblModelo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblModelo.setForeground(new java.awt.Color(255, 255, 255));
        lblModelo.setText("Modelo:");

        lblModelo_.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblModelo_.setForeground(new java.awt.Color(255, 255, 255));
        lblModelo_.setText("N/A");

        lblUbicacion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblUbicacion.setForeground(new java.awt.Color(255, 255, 255));
        lblUbicacion.setText("Ubicación:");

        lblUbicacion_.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblUbicacion_.setForeground(new java.awt.Color(255, 255, 255));
        lblUbicacion_.setText("N/A");

        lblIP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblIP.setForeground(new java.awt.Color(255, 255, 255));
        lblIP.setText("IP:");

        lblIP_.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblIP_.setForeground(new java.awt.Color(255, 255, 255));
        lblIP_.setText("N/A");

        lblMac.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblMac.setForeground(new java.awt.Color(255, 255, 255));
        lblMac.setText("MAC:");

        lblMac_.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblMac_.setForeground(new java.awt.Color(255, 255, 255));
        lblMac_.setText("N/A");

        lblTipo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblTipo.setForeground(new java.awt.Color(255, 255, 255));
        lblTipo.setText("Tipo:");

        lblTipo_.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblTipo_.setForeground(new java.awt.Color(255, 255, 255));
        lblTipo_.setText("N/A");

        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
        pnlDatos.setLayout(pnlDatosLayout);
        pnlDatosLayout.setHorizontalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblModelo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMarca, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSerie, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblModelo_)
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSerie_)
                            .addComponent(lblMarca_))
                        .addGap(50, 50, 50)
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMac, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblIP, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblUbicacion, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMac_)
                            .addGroup(pnlDatosLayout.createSequentialGroup()
                                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblUbicacion_)
                                    .addComponent(lblIP_))
                                .addGap(50, 50, 50)
                                .addComponent(lblTipo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTipo_)))))
                .addContainerGap(417, Short.MAX_VALUE))
        );
        pnlDatosLayout.setVerticalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSerie)
                    .addComponent(lblSerie_)
                    .addComponent(lblUbicacion)
                    .addComponent(lblUbicacion_)
                    .addComponent(lblTipo)
                    .addComponent(lblTipo_))
                .addGap(18, 18, 18)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMarca)
                    .addComponent(lblMarca_)
                    .addComponent(lblIP)
                    .addComponent(lblIP_))
                .addGap(18, 18, 18)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModelo)
                    .addComponent(lblModelo_)
                    .addComponent(lblMac)
                    .addComponent(lblMac_))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(pnlDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 830, 140));

        pnlInventario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Inventario toner", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlInventario.setOpaque(false);

        lblToner_.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblToner_.setForeground(new java.awt.Color(255, 255, 255));
        lblToner_.setText("N/A");

        lbltTonerCian.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbltTonerCian.setForeground(new java.awt.Color(255, 255, 255));
        lbltTonerCian.setText("Toner Cian:");

        lblTonerCian_.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblTonerCian_.setForeground(new java.awt.Color(255, 255, 255));
        lblTonerCian_.setText("N/A");

        lbltTonerMagenta.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbltTonerMagenta.setForeground(new java.awt.Color(255, 255, 255));
        lbltTonerMagenta.setText("Toner Magenta:");

        lblTonerMagenta_.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblTonerMagenta_.setForeground(new java.awt.Color(255, 255, 255));
        lblTonerMagenta_.setText("N/A");

        lbltTonerYellow.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbltTonerYellow.setForeground(new java.awt.Color(255, 255, 255));
        lbltTonerYellow.setText("Toner Yellow:");

        lblTonerYellow_.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblTonerYellow_.setForeground(new java.awt.Color(255, 255, 255));
        lblTonerYellow_.setText("N/A");

        lblTonerBlack.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblTonerBlack.setForeground(new java.awt.Color(255, 255, 255));
        lblTonerBlack.setText("Toner Black:");

        lblTonerBlack_.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblTonerBlack_.setForeground(new java.awt.Color(255, 255, 255));
        lblTonerBlack_.setText("N/A");

        javax.swing.GroupLayout pnlInventarioLayout = new javax.swing.GroupLayout(pnlInventario);
        pnlInventario.setLayout(pnlInventarioLayout);
        pnlInventarioLayout.setHorizontalGroup(
            pnlInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInventarioLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTonerBlack, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbltTonerYellow, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbltTonerMagenta, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbltTonerCian, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(10, 10, 10)
                .addGroup(pnlInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTonerYellow_)
                    .addComponent(lblTonerMagenta_)
                    .addComponent(lblTonerCian_)
                    .addComponent(lblTonerBlack_))
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInventarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblToner_)
                .addGap(82, 82, 82))
        );
        pnlInventarioLayout.setVerticalGroup(
            pnlInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInventarioLayout.createSequentialGroup()
                .addComponent(lblToner_)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTonerCian_)
                    .addComponent(lbltTonerCian))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTonerMagenta_)
                    .addComponent(lbltTonerMagenta, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTonerYellow_)
                    .addComponent(lbltTonerYellow))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTonerBlack_)
                    .addComponent(lblTonerBlack))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        getContentPane().add(pnlInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 200, 140));

        pnlDetalles.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalles del Ticket", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlDetalles.setOpaque(false);

        lblTicket.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblTicket.setForeground(new java.awt.Color(255, 255, 255));
        lblTicket.setText("Ticket:");

        lblTicket_.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblTicket_.setForeground(new java.awt.Color(255, 255, 255));
        lblTicket_.setText("N/A");

        lblDetalles.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblDetalles.setForeground(new java.awt.Color(255, 255, 255));
        lblDetalles.setText("Detalles:");

        lblDetalles_.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblDetalles_.setForeground(new java.awt.Color(255, 255, 255));
        lblDetalles_.setText("N/A");

        lblObservaciones.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblObservaciones.setForeground(new java.awt.Color(255, 255, 255));
        lblObservaciones.setText("Observaciones:");

        lblObservaciones_.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblObservaciones_.setForeground(new java.awt.Color(255, 255, 255));
        lblObservaciones_.setText("N/A");

        lblFecha.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(255, 255, 255));
        lblFecha.setText("Fecha:");

        lblFecha_.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblFecha_.setForeground(new java.awt.Color(255, 255, 255));
        lblFecha_.setText("N/A");

        btnVerArchivo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnVerArchivo.setText("Ver Archivo");
        btnVerArchivo.setOpaque(false);
        btnVerArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerArchivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDetallesLayout = new javax.swing.GroupLayout(pnlDetalles);
        pnlDetalles.setLayout(pnlDetallesLayout);
        pnlDetallesLayout.setHorizontalGroup(
            pnlDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetallesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTicket, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDetalles, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblObservaciones, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDetallesLayout.createSequentialGroup()
                        .addComponent(lblTicket_)
                        .addGap(323, 323, 323)
                        .addComponent(lblFecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFecha_))
                    .addComponent(lblDetalles_)
                    .addComponent(lblObservaciones_))
                .addContainerGap(286, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetallesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVerArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(337, 337, 337))
        );
        pnlDetallesLayout.setVerticalGroup(
            pnlDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetallesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTicket)
                    .addComponent(lblTicket_)
                    .addComponent(lblFecha)
                    .addComponent(lblFecha_))
                .addGap(18, 18, 18)
                .addGroup(pnlDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDetalles_)
                    .addComponent(lblDetalles))
                .addGap(18, 18, 18)
                .addGroup(pnlDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblObservaciones)
                    .addComponent(lblObservaciones_))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(btnVerArchivo)
                .addContainerGap())
        );

        getContentPane().add(pnlDetalles, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 830, 200));

        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/FondoBuscarNS.png"))); // NOI18N
        getContentPane().add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        Opciones o = new Opciones();
        o.setUsuario(usuario);
        o.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        getValores(tfTicket.getText());

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnVerArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerArchivoActionPerformed
        
        bajarArchivoSFTP();
        abrirArchivo();
    }//GEN-LAST:event_btnVerArchivoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscarTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new BuscarTicket().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnVerArchivo;
    private javax.swing.JLabel lblDetalles;
    private javax.swing.JLabel lblDetalles_;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFecha_;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblIP;
    private javax.swing.JLabel lblIP_;
    private javax.swing.JLabel lblMac;
    private javax.swing.JLabel lblMac_;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblMarca_;
    private javax.swing.JLabel lblModelo;
    private javax.swing.JLabel lblModelo_;
    private javax.swing.JLabel lblNS;
    private javax.swing.JLabel lblObservaciones;
    private javax.swing.JLabel lblObservaciones_;
    private javax.swing.JLabel lblSerie;
    private javax.swing.JLabel lblSerie_;
    private javax.swing.JLabel lblTicket;
    private javax.swing.JLabel lblTicket_;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTipo_;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTonerBlack;
    private javax.swing.JLabel lblTonerBlack_;
    private javax.swing.JLabel lblTonerCian_;
    private javax.swing.JLabel lblTonerMagenta_;
    private javax.swing.JLabel lblTonerYellow_;
    private javax.swing.JLabel lblToner_;
    private javax.swing.JLabel lblUbicacion;
    private javax.swing.JLabel lblUbicacion_;
    private javax.swing.JLabel lbltTonerCian;
    private javax.swing.JLabel lbltTonerMagenta;
    private javax.swing.JLabel lbltTonerYellow;
    private javax.swing.JPanel pnlBusqueda;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlDetalles;
    private javax.swing.JPanel pnlInventario;
    private javax.swing.JTextField tfTicket;
    // End of variables declaration//GEN-END:variables
}
