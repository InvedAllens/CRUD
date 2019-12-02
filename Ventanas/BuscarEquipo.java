/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import ConexionFTPconThreads.SFTPBajarArchivo;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.mxrck.autocompleter.TextAutoCompleter;
import db.Conexion;
import ftp.SFTPConnector;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import misc.Render;
import misc.Usuario;

/**
 *
 * @author Ruben Angeles
 */
public class BuscarEquipo extends javax.swing.JFrame implements Observer{
    public Usuario usuario = Opciones.usuario;
    public Conexion cn = new Conexion();
    private Thread th;
    ResultSet rs,rs1;
    DefaultTableModel modelo = new DefaultTableModel(){
    @Override
    public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
    };
    JButton btnAbrir = new JButton("Abrir");
    String pathDescarga;

    /**
     * Creates new form BuscarEquipo
     */
    public BuscarEquipo() {
        initComponents();
        this.setLocationRelativeTo(null);
        llenarNS();
        busyLabel.setVisible(false);
    }
    /**
     *  Metodo para Obtener NS de la BD y agregarlos al TextAutoCompleter
     */
    private void llenarNS(){
        TextAutoCompleter textAutoCompleter = new TextAutoCompleter(tfNS);
        try {
            cn.conectar();
            rs =cn.consulta("SELECT ns FROM Equipo");
            while(rs.next()){
                //cbNS.addItem(resultado.getString("ns"));
                textAutoCompleter.addItem(rs.getObject("ns"));
            }
            cn.desconectar();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
   
    private void getValores(String serie){
        if (!serie.isEmpty()) {
            try{
                cn.conectar();
                rs = cn.consulta("SELECT * FROM Equipo WHERE ns = '"+tfNS.getText()+"'");
                while(rs.next()){
                    lblSerie_.setText(rs.getString(1));
                    lblMarca_.setText(rs.getString(2));
                    lblModelo_.setText(rs.getString(3)+" "+rs.getString(4));
                    lblUbicacion_.setText(rs.getString(7)+" "+rs.getString(8));
                    lblIP_.setText(rs.getString(5));
                    lblMac_.setText(rs.getString(6));
                    lblTipo_.setText(rs.getString(9));
                    lblToner_.setText(rs.getString(10));
                }
                rs = cn.consulta("SELECT * FROM Toner WHERE modelo = '"+lblToner_.getText()+"'");
                while(rs.next()){
                    lblTonerCian_.setText(String.valueOf(rs.getInt(2)));
                    lblTonerMagenta_.setText(String.valueOf(rs.getInt(3)));
                    lblTonerYellow_.setText(String.valueOf(rs.getInt(4)));
                    lblTonerBlack_.setText(String.valueOf(rs.getInt(5)));
                }
                llenarTabla();
                cn.desconectar();
                if (lblTipo_.getText().equals("Blanco y negro")) {
                    lbltTonerCian.setVisible(false);
                    lblTonerCian_.setVisible(false);
                    lbltTonerMagenta.setVisible(false);
                    lblTonerMagenta_.setVisible(false);
                    lbltTonerYellow.setVisible(false);
                    lblTonerYellow_.setVisible(false);
                } else{
                    lbltTonerCian.setVisible(true);
                    lblTonerCian_.setVisible(true);
                    lbltTonerMagenta.setVisible(true);
                    lblTonerMagenta_.setVisible(true);
                    lbltTonerYellow.setVisible(true);
                    lblTonerYellow_.setVisible(true);
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    private void llenarTabla(){
        modelo.setRowCount(0);
        modelo.setColumnIdentifiers(new Object[]{"ID","TICKET","FECHA","DETALLE","USUARIO","PATH","VER"});
        tabla.setDefaultRenderer(Object.class, new Render());
        try {
            rs = cn.consulta("SELECT * FROM Docs WHERE ns ='"+tfNS.getText()+"' ORDER BY fecha DESC");
            while(rs.next()){
                String ticketOpc;
                if (null==rs.getString("ticket")) {
                    ticketOpc = rs.getString("ticket");
                }else switch (rs.getString("ticket")) {
                    case "1":
                        ticketOpc = "Falla Sin Ticket";
                        break;
                    case "2":
                        ticketOpc = "Cambio de Toner";
                        break;
                    default:
                        ticketOpc = rs.getString("ticket");
                        break;
                }
                modelo.addRow(new Object[]{rs.getInt("id"),ticketOpc,rs.getString("fecha"),rs.getString("detalle"),rs.getString("username"),rs.getString("path"),btnAbrir});
            }
            tabla.setModel(modelo);
            //BusyLabel.setVisible(false);
        } catch (SQLException ex) {
            System.out.println(ex);
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

        lblTitulo = new javax.swing.JLabel();
        btnLogOut = new javax.swing.JButton();
        pnlBusqueda = new javax.swing.JPanel();
        lblNS = new javax.swing.JLabel();
        tfNS = new javax.swing.JTextField();
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
        pnlTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        busyLabel = new org.jdesktop.swingx.JXBusyLabel();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Consultar NS");
        getContentPane().add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

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

        pnlBusqueda.setOpaque(false);

        lblNS.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblNS.setForeground(new java.awt.Color(255, 255, 255));
        lblNS.setText("Numero de Serie:");

        tfNS.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

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
                .addComponent(tfNS, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(tfNS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(0, 5, Short.MAX_VALUE))
        );

        getContentPane().add(pnlInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 200, 140));

        pnlTabla.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Historial", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlTabla.setOpaque(false);

        tabla.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TICKET", "FECHA", "DETALLE", "USUARIO", "PATH", "VER"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.setEnabled(false);
        tabla.setOpaque(false);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout pnlTablaLayout = new javax.swing.GroupLayout(pnlTabla);
        pnlTabla.setLayout(pnlTablaLayout);
        pnlTablaLayout.setHorizontalGroup(
            pnlTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE)
        );
        pnlTablaLayout.setVerticalGroup(
            pnlTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTablaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(pnlTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 830, 210));

        busyLabel.setForeground(new java.awt.Color(255, 255, 255));
        busyLabel.setText("Cargando...");
        busyLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        getContentPane().add(busyLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 110, -1, -1));

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
       
        getValores(tfNS.getText());
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        int columna = tabla.getColumnModel().getColumnIndexAtX(evt.getX());
        int fila = evt.getY()/tabla.getRowHeight();
        if (fila < tabla.getRowCount() && fila >= 0 && columna < tabla.getColumnCount() && columna >= 0) {
            Object o = tabla.getValueAt(fila, columna);
            if (o instanceof JButton) {
                ((JButton)o).doClick();
                JButton btn = (JButton)o;
                System.out.println("PATH: "+tabla.getValueAt(fila,5));
                pathDescarga =  String.valueOf(tabla.getValueAt(fila, 5));
                busyLabel.setVisible(true);
                busyLabel.setBusy(true);
                SFTPBajarArchivo bajarArchivo = new SFTPBajarArchivo(pathDescarga);
                bajarArchivo.addObserver(this);
                th = new Thread(bajarArchivo);
                th.start();
            }
        }
    }//GEN-LAST:event_tablaMouseClicked

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
            java.util.logging.Logger.getLogger(BuscarEquipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new BuscarEquipo().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnLogOut;
    private org.jdesktop.swingx.JXBusyLabel busyLabel;
    private javax.swing.JScrollPane jScrollPane1;
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
    private javax.swing.JLabel lblSerie;
    private javax.swing.JLabel lblSerie_;
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
    private javax.swing.JPanel pnlInventario;
    private javax.swing.JPanel pnlTabla;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField tfNS;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        boolean flag = (boolean)arg;
        if (flag == false) {
            busyLabel.setVisible(false);
            busyLabel.setBusy(false);
        }
    }
}
