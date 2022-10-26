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
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Marcela Alzate
 */
public class HospitalJF extends javax.swing.JFrame {
    Conexion con = new Conexion();
    Connection cn;
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;
    DefaultTableModel modelito;
    /**
     * Creates new form Hospital
     */
    public HospitalJF() {
        initComponents();
        show_hospitales();
        setLocationRelativeTo(null);
        
    }
    void show_hospitales(){
        String sql = "select * from tb_hospital";
        try{
            cn = con.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            //Los datos que devuelve la consulta se muestran en la tabla
            Object[]hospitales = new Object[3];
            modelo = (DefaultTableModel)tbHospitales.getModel();
            while(rs.next()){
                hospitales[0] = rs.getInt("id");
                hospitales[1] = rs.getString("name_");
                hospitales[2] = rs.getString("address");
                modelo.addRow(hospitales);
            }
            tbHospitales.setModel(modelo);
        }catch(SQLException e){
        }
    }
    void add_hospital(){
        String name = txtNombreH.getText();
        String direccion = txtDireccionH.getText();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta ingresar el nombre del hospital");
        }else{
            String query = "INSERT INTO tb_hospital(name_, address) VALUES('" + name + "','" + direccion + "')";
            try{                
                cn = con.getConnection();
                st = cn.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "El hospital ha sido creado");
                clear_rows_table();
                show_hospitales();
            }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(this, "No se pudo crear el hospital");
            }
        }
    }
    void edit_hospital(){
        //Hacemos nuevamente lectura de los valores contenidos en los JTextField
        //Para identificar si el usuario modifico algún valor
        int id = Integer.parseInt(txtIDH.getText());
        String name = txtNombreH.getText();
        String direccion = txtDireccionH.getText();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta ingresar el nombre del hospital");
        }else{
            String query = "UPDATE tb_hospital SET name_ = '" + name + "', address= '"+direccion + "' WHERE id = " + id;
            //UPDATE tb_persons SET dni =dni, nombre= 'name' WHERE id = id
        try{
            cn = con.getConnection();
            st = cn.createStatement();
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "El hospital ha sido modificado con éxito");
            clear_rows_table();
            show_hospitales();
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(this, "No se pudo modificar el hospital");
                System.out.println("error al editar "+e);
            }
        }
    }
    void delete_hospital(){
        int fila = tbHospitales.getSelectedRow();
        int id = Integer.parseInt(txtIDH.getText());
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "No has seleccionado un hospital");
        }else{
            String query = "DELETE FROM tb_hospital WHERE id = " + id;
            try{
                cn = con.getConnection();
                st = cn.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "El hospital ha sido eliminado con exito");
                clear_rows_table();
                show_hospitales();
            }catch(HeadlessException | SQLException e){
            }
        }
    }
    void buscarMascotas(){
        Hospital_Mascotas HM = new Hospital_Mascotas(this, true);
        int fila = tbHospitales.getSelectedRow();
        int id = Integer.parseInt(txtIDH.getText());
        if(fila == -1)
            JOptionPane.showMessageDialog(this, "No has seleccionado un hospital");
        else{
            String query = "select * from tb_pet_hospital where id_hospital = "+id;
            try{
                cn = con.getConnection();
                st = cn.createStatement();
                st.executeUpdate(query);
                Object[]hospitales = new Object[3];
                modelo = (DefaultTableModel)tbHospitales.getModel();
                while(rs.next()){
                    hospitales[0] = rs.getInt("id");
                    hospitales[1] = rs.getString("name_");
                    hospitales[2] = rs.getString("address");
                    modelo.addRow(hospitales);
                }
                tbHospitales.setModel(modelo);
            }catch(HeadlessException | SQLException e){
                System.out.println("error al buscar: "+e);
            }
        }
    }
    void clear_rows_table(){
        for (int i = 0; i < tbHospitales.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i-1;
        }
        txtIDH.setText("");
        txtNombreH.setText("");
        txtDireccionH.setText("");
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
        txtIDH = new javax.swing.JTextField();
        txtNombreH = new javax.swing.JTextField();
        txtDireccionH = new javax.swing.JTextField();
        btnAgregarH = new javax.swing.JButton();
        btnEditarH = new javax.swing.JButton();
        btnEliminarH = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHospitales = new javax.swing.JTable();
        btnHospital_Mascota = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 51, 255));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setText("HOSPITALES");

        jLabel2.setText("ID");

        jLabel3.setText("Nombre");

        jLabel4.setText("Dirección");

        txtIDH.setEnabled(false);

        btnAgregarH.setBackground(new java.awt.Color(51, 255, 255));
        btnAgregarH.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 11)); // NOI18N
        btnAgregarH.setText("Agregar");
        btnAgregarH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarHActionPerformed(evt);
            }
        });

        btnEditarH.setBackground(new java.awt.Color(51, 255, 255));
        btnEditarH.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 11)); // NOI18N
        btnEditarH.setText("Editar");
        btnEditarH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarHActionPerformed(evt);
            }
        });

        btnEliminarH.setBackground(new java.awt.Color(51, 255, 255));
        btnEliminarH.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 11)); // NOI18N
        btnEliminarH.setText("Eliminar");
        btnEliminarH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarHActionPerformed(evt);
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
                        .addComponent(btnAgregarH)))
                .addGap(56, 56, 56)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                            .addComponent(txtIDH)
                            .addComponent(txtNombreH)
                            .addComponent(txtDireccionH))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnEditarH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                        .addComponent(btnEliminarH)
                        .addGap(103, 103, 103))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombreH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDireccionH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarH)
                    .addComponent(btnEditarH)
                    .addComponent(btnEliminarH))
                .addGap(35, 35, 35))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        tbHospitales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "DIRECCIÓN"
            }
        ));
        tbHospitales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHospitalesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbHospitales);

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

        btnHospital_Mascota.setBackground(new java.awt.Color(51, 255, 255));
        btnHospital_Mascota.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 11)); // NOI18N
        btnHospital_Mascota.setText("Mascotas");
        btnHospital_Mascota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHospital_MascotaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(329, 329, 329)
                .addComponent(btnHospital_Mascota)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHospital_Mascota)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbHospitalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHospitalesMouseClicked
        // TODO add your handling code here:
        int row = tbHospitales.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(this, "Debes seleccionar un departamento");
        }else{
            int id = Integer.parseInt((String)tbHospitales.getValueAt(row,0).toString());
            String nombre = (String)tbHospitales.getValueAt(row, 1);
            String direccion = (String)tbHospitales.getValueAt(row, 2);
            txtIDH.setText("" + id);
            txtNombreH.setText("" + nombre);
            txtDireccionH.setText(direccion);
        }
    }//GEN-LAST:event_tbHospitalesMouseClicked

    private void btnAgregarHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarHActionPerformed
        // TODO add your handling code here:
        add_hospital();
    }//GEN-LAST:event_btnAgregarHActionPerformed

    private void btnEditarHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarHActionPerformed
        // TODO add your handling code here:
        edit_hospital();
    }//GEN-LAST:event_btnEditarHActionPerformed

    private void btnEliminarHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarHActionPerformed
        // TODO add your handling code here:
        delete_hospital();
    }//GEN-LAST:event_btnEliminarHActionPerformed

    private void btnHospital_MascotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHospital_MascotaActionPerformed
        // TODO add your handling code here:
        
        new Mascotas_().setVisible(true);      
        
    }//GEN-LAST:event_btnHospital_MascotaActionPerformed

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
            java.util.logging.Logger.getLogger(HospitalJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HospitalJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HospitalJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HospitalJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HospitalJF().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarH;
    private javax.swing.JButton btnEditarH;
    private javax.swing.JButton btnEliminarH;
    private javax.swing.JButton btnHospital_Mascota;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbHospitales;
    private javax.swing.JTextField txtDireccionH;
    private javax.swing.JTextField txtIDH;
    private javax.swing.JTextField txtNombreH;
    // End of variables declaration//GEN-END:variables
}
