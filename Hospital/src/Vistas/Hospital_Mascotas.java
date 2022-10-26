/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;
import Controladores.Conexion;
import java.sql.Statement;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marcela Alzate
 */
public class Hospital_Mascotas extends javax.swing.JDialog {
    Conexion con = new Conexion();
    Connection cn;
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;
    DefaultTableModel modelo1;
    DefaultTableModel modelo2;
    /**
     * Creates new form Hospital_Mascotas
     */
    public Hospital_Mascotas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        show_hospital_mascota();
        show_hospitales();
        show_mascotas();
    }
    void show_hospital_mascota(){
        String sql = "select * from tb_pet_hospital";
        try{
            cn = con.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            //Los datos que devuelve la consulta se muestran en la tabla
            Object[]hospital_mascota = new Object[3];
            modelo = (DefaultTableModel)tbHospital_Mascota.getModel();
            while(rs.next()){
                hospital_mascota[0] = rs.getInt("id");
                hospital_mascota[1] = rs.getString("id_pet");
                hospital_mascota[2] = rs.getString("id_hospital");
                modelo.addRow(hospital_mascota);
            }
            tbHospital_Mascota.setModel(modelo);
        }catch(SQLException e){
        }
    }
    void show_hospitales(){
        String sql = "select * from tb_hospital";
        try{
            cn = con.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            //Los datos que devuelve la consulta se muestran en la tabla
            Object[]hospitales = new Object[3];
            modelo1 = (DefaultTableModel)tbHospitales_.getModel();
            while(rs.next()){
                hospitales[0] = rs.getInt("id");
                hospitales[1] = rs.getString("name_");
                hospitales[2] = rs.getString("address");
                modelo1.addRow(hospitales);
            }
            tbHospitales_.setModel(modelo1);
        }catch(SQLException e){
        }
    }
    void show_mascotas(){
        String sql = "select * from tb_pet";
        try{
            cn = con.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            //Los datos que devuelve la consulta se muestran en la tabla
            Object[]mascotas = new Object[4];
            modelo2 = (DefaultTableModel)tbMascotas_.getModel();
            while(rs.next()){
                mascotas[0] = rs.getInt("id");
                mascotas[1] = rs.getString("name_");
                mascotas[2] = rs.getString("breed");
                mascotas[3] = rs.getInt("id_owner_pet");
                modelo2.addRow(mascotas);
            }
            tbMascotas_.setModel(modelo2);
        }catch(SQLException e){
        }
    }
    void add_mascota_hospital(){
        int IDh = Integer.parseInt(txtH.getText());
        int IDm = Integer.parseInt(txtIDM.getText());
        String query = "INSERT INTO tb_pet_hospital(id_pet,id_hospital) VALUES(" + IDm + "," + IDh + ")";
        try{
            cn = con.getConnection();
            st = cn.createStatement();
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "La relacion hospital-mascota ha sido creada");
            clear_rows_tableHM();
            show_hospital_mascota();
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, "No se pudo crear La relacion hospital-mascota");
            System.out.println("error "+e);
        }        
    }
    void edit_hospital_mascotas(){
        //Hacemos nuevamente lectura de los valores contenidos en los JTextField
        //Para identificar si el usuario modifico algún valor
        int id = Integer.parseInt(txtIDHM.getText());
        int idH = Integer.parseInt(txtH.getText());
        int idM = Integer.parseInt(txtIDM.getText());
        String query = "UPDATE tb_pet_hospital SET id_pet = " + idM + ", id_hospital= "+idH+ " WHERE id = " + id;
        try{
            cn = con.getConnection();
            st = cn.createStatement();
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "La relacion hospital-mascota ha sido modificado con éxito");
            clear_rows_tableHM();
            show_hospital_mascota();
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, "No se pudo modificar La relacion hospital-mascota");
            System.out.println("error al editar "+e);
        }        
    }
    void delete_hospital_mascotas(){
        int fila = tbHospital_Mascota.getSelectedRow();
        int id = Integer.parseInt(txtIDHM.getText());
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "No has seleccionado La relacion hospital-mascota");
        }else{
            String query = "DELETE FROM tb_pet_hospital WHERE id = " + id;
            try{
                cn = con.getConnection();
                st = cn.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "La relacion hospital-mascota ha sido eliminado con exito");
                clear_rows_tableHM();
                show_hospital_mascota();
                show_mascotas();
            }catch(HeadlessException | SQLException e){
            }
        }
        
    }
    void clear_rows_tableHM(){
        for (int i = 0; i < tbHospital_Mascota.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i-1;
        }
        txtH.setText("");
        txtIDHM.setText("");
        txtIDM.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtIDHM = new javax.swing.JTextField();
        txtIDM = new javax.swing.JTextField();
        txtH = new javax.swing.JTextField();
        btnAgregarHM = new javax.swing.JButton();
        btnEditarHM = new javax.swing.JButton();
        btnEliminarHM = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHospital_Mascota = new javax.swing.JTable();
        btnMascotas = new javax.swing.JButton();
        btnDueños = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbHospitales_ = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbMascotas_ = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 51, 255));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setText("HOSPITAL_MASCOTAS");

        jLabel2.setText("ID");

        jLabel3.setText("ID mascota");

        jLabel4.setText("ID hospital");

        txtIDHM.setEnabled(false);

        btnAgregarHM.setBackground(new java.awt.Color(51, 255, 255));
        btnAgregarHM.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 11)); // NOI18N
        btnAgregarHM.setText("Agregar");
        btnAgregarHM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarHMActionPerformed(evt);
            }
        });

        btnEditarHM.setBackground(new java.awt.Color(51, 255, 255));
        btnEditarHM.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 11)); // NOI18N
        btnEditarHM.setText("Editar");
        btnEditarHM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarHMActionPerformed(evt);
            }
        });

        btnEliminarHM.setBackground(new java.awt.Color(51, 255, 255));
        btnEliminarHM.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 11)); // NOI18N
        btnEliminarHM.setText("Eliminar");
        btnEliminarHM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarHMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(btnAgregarHM)
                        .addGap(61, 61, 61)
                        .addComponent(btnEditarHM)))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIDHM, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(txtIDM)
                            .addComponent(txtH))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 54, Short.MAX_VALUE)
                        .addComponent(btnEliminarHM)
                        .addGap(103, 103, 103))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(236, 236, 236)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIDHM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtIDM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarHM)
                    .addComponent(btnEditarHM)
                    .addComponent(btnEliminarHM))
                .addGap(35, 35, 35))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        tbHospital_Mascota.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ID mascota", "ID hospital"
            }
        ));
        tbHospital_Mascota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHospital_MascotaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbHospital_Mascota);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnMascotas.setBackground(new java.awt.Color(51, 255, 255));
        btnMascotas.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 11)); // NOI18N
        btnMascotas.setText("Mascotas");
        btnMascotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMascotasActionPerformed(evt);
            }
        });

        btnDueños.setBackground(new java.awt.Color(51, 255, 255));
        btnDueños.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 11)); // NOI18N
        btnDueños.setText("Dueños");
        btnDueños.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDueñosActionPerformed(evt);
            }
        });

        jLabel5.setText("HOSPITALES");

        tbHospitales_.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Dirección"
            }
        ));
        jScrollPane2.setViewportView(tbHospitales_);

        tbMascotas_.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Raza", "Id dueño"
            }
        ));
        jScrollPane3.setViewportView(tbMascotas_);

        jLabel6.setText("MASCOTAS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(btnMascotas)
                        .addGap(292, 292, 292)
                        .addComponent(btnDueños))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(209, 209, 209)
                                .addComponent(jLabel5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(229, 229, 229)
                                .addComponent(jLabel6)))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGap(8, 8, 8)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMascotas)
                    .addComponent(btnDueños))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarHMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarHMActionPerformed
        // TODO add your handling code here:
        add_mascota_hospital();
    }//GEN-LAST:event_btnAgregarHMActionPerformed

    private void btnEditarHMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarHMActionPerformed
        // TODO add your handling code here:
        edit_hospital_mascotas();
    }//GEN-LAST:event_btnEditarHMActionPerformed

    private void btnEliminarHMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarHMActionPerformed
        // TODO add your handling code here:
        delete_hospital_mascotas();
    }//GEN-LAST:event_btnEliminarHMActionPerformed

    private void tbHospital_MascotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHospital_MascotaMouseClicked
        // TODO add your handling code here:
        int row = tbHospital_Mascota.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(this, "Debes seleccionar un hospital_mascota");
        }else{
            int id = Integer.parseInt((String)tbHospital_Mascota.getValueAt(row,0).toString());
            int idM = Integer.parseInt((String)tbHospital_Mascota.getValueAt(row,1).toString());
            int idH= Integer.parseInt((String)tbHospital_Mascota.getValueAt(row,2).toString());
            txtIDM.setText("" + idM);
            txtH.setText("" + idH);
            txtIDHM.setText(""+id);
            
        }
    }//GEN-LAST:event_tbHospital_MascotaMouseClicked

    private void btnMascotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMascotasActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Mascotas_().setVisible(true);
    }//GEN-LAST:event_btnMascotasActionPerformed

    private void btnDueñosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDueñosActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Dueños_().setVisible(true);
    }//GEN-LAST:event_btnDueñosActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Hospital_Mascotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Hospital_Mascotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Hospital_Mascotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Hospital_Mascotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Hospital_Mascotas dialog = new Hospital_Mascotas(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarHM;
    private javax.swing.JButton btnDueños;
    private javax.swing.JButton btnEditarHM;
    private javax.swing.JButton btnEliminarHM;
    private javax.swing.JButton btnMascotas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JTable tbHospital_Mascota;
    private javax.swing.JTable tbHospitales_;
    private javax.swing.JTable tbMascotas_;
    private javax.swing.JTextField txtH;
    private javax.swing.JTextField txtIDHM;
    private javax.swing.JTextField txtIDM;
    // End of variables declaration//GEN-END:variables
}
