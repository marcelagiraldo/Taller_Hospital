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
public class Dueños_ extends javax.swing.JFrame {
    Conexion con = new Conexion();
    Connection cn;
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;

    /**
     * Creates new form Dueños_
     */
    public Dueños_() {
        initComponents();
        show_dueños_mascotas();
    }
    void show_dueños_mascotas(){
        String sql = "select * from tb_pet_owners";
        try{
            cn = con.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            //Los datos que devuelve la consulta se muestran en la tabla
            Object[]dueños = new Object[7];
            modelo = (DefaultTableModel)tbDueñosMascotas.getModel();
            while(rs.next()){
                dueños[0] = rs.getInt("id");
                dueños[1] = rs.getString("owner_");
                dueños[2] = rs.getString("document_type");
                dueños[3] = rs.getString("document");
                dueños[4] = rs.getString("contact");
                dueños[5] = rs.getString("gender");
                modelo.addRow(dueños);
            }
            tbDueñosMascotas.setModel(modelo);
        }catch(SQLException e){
        }
    }
    void add_dueño_mascota(){
        String name_ = txtNombreDM.getText();
        String document_type = txtDocumentoDM.getText();
        String document = txtDocumentoDM.getText();
        String contact = txtContactoDM.getText();
        String gender = txtGeneroDM.getText();
        if (name_.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta ingresar el nombre del dueño");
        }else{
            String query = "INSERT INTO tb_pet_owners(owner_, document_type, document,contact,"
                    + " gender) VALUES('" + name_ + "', '"+
                    document_type+ "','" + document + "','" + contact + "','" + gender + "')";
            try{
                cn = con.getConnection();
                st = cn.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "El dueño ha sido cread0");
                clear_rows_table();
                show_dueños_mascotas();
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(this, "No se pudo crear el dueño");
            }
        }
    }
    void edit_dueño_mascota(){
        //Hacemos nuevamente lectura de los valores contenidos en los JTextField
        //Para identificar si el usuario modifico algún valor
        int id = Integer.parseInt(txtIDDM.getText());
        String name_ = txtNombreDM.getText();
        String document_type = txtDocumentoDM.getText();
        String document = txtDocumentoDM.getText();
        String contact = txtContactoDM.getText();
        String gender = txtGeneroDM.getText();
        if (name_.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta ingresar el nombre del dueño");
        }else{
            String query = "UPDATE tb_pet_owners SET owner_ = '" + name_+ "', document_type = '"+
                    document_type+ "', document = '" + document + "', contact = '" + contact + "', gender= '" + gender + "' WHERE id = " + id;
            //UPDATE tb_persons SET dni =dni, nombre= 'name' WHERE id = id
        try{
            cn = con.getConnection();
            st = cn.createStatement();
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "El dueño ha sido modificado con éxito");
            clear_rows_table();
            show_dueños_mascotas();
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(this, "No se pudo modificar el dueño");
            }
        }
    }
    void delete_dueño_mascota(){
        int fila = tbDueñosMascotas.getSelectedRow();
        int id = Integer.parseInt(txtIDDM.getText());
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "No has seleccionado un dueño");
        }else{
            String query = "DELETE FROM tb_pet_owners WHERE id = " + id;
            try{
                cn = con.getConnection();
                st = cn.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "El dueño ha sido eliminada con exito");
                clear_rows_table();
                show_dueños_mascotas();
            }catch(HeadlessException | SQLException e){
            }
        }
    }
    void search_owner_pet(){
        Dueños_Mascotas DM = new Dueños_Mascotas(this, true);
        Mascotas_ mas = new Mascotas_();
        int id = Integer.parseInt(txtIDDM.getText());        
        String sql = "select * from tb_pet where tb_pet.id_owner_pet = " + id;
        try{
            cn = con.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            System.out.println(rs);
            //Los datos que devuelve la consulta se muestran en la tabla
            Object[]dueños = new Object[4];
            modelo = (DefaultTableModel)DM.tbDueños_Mascotas.getModel();
            while(rs.next()){
                dueños[0] = rs.getInt("id");
                dueños[1] = rs.getString("name_");
                dueños[2] = rs.getString("breed");
                dueños[3] = rs.getInt("id_owner_pet");
                modelo.addRow(dueños);
            }
            DM.tbDueños_Mascotas.setModel(modelo);
            DM.setVisible(true);
        }catch(SQLException e){
        }
    }
    void clear_rows_table(){
        for (int i = 0; i < tbDueñosMascotas.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i-1;
        }
        txtContactoDM.setText("");
        txtDocumentoDM.setText("");
        txtGeneroDM.setText("");
        txtIDDM.setText("");
        txtTipoDocumentoDM.setText("");
        txtNombreDM.setText("");
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
        txtIDDM = new javax.swing.JTextField();
        txtNombreDM = new javax.swing.JTextField();
        btnAgregarDM = new javax.swing.JButton();
        btnEditarDM = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtTipoDocumentoDM = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDocumentoDM = new javax.swing.JTextField();
        txtContactoDM = new javax.swing.JTextField();
        txtGeneroDM = new javax.swing.JTextField();
        btnEliminarDM = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDueñosMascotas = new javax.swing.JTable();
        btnVerMascotas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 51, 255));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setText("DUEÑOS MASCOTAS");

        jLabel2.setText("ID");

        jLabel3.setText("Nombre");

        txtIDDM.setEnabled(false);

        btnAgregarDM.setBackground(new java.awt.Color(51, 255, 255));
        btnAgregarDM.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 11)); // NOI18N
        btnAgregarDM.setText("Agregar");
        btnAgregarDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDMActionPerformed(evt);
            }
        });

        btnEditarDM.setBackground(new java.awt.Color(51, 255, 255));
        btnEditarDM.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 11)); // NOI18N
        btnEditarDM.setText("Editar");
        btnEditarDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarDMActionPerformed(evt);
            }
        });

        jLabel5.setText("Tipo documento");

        jLabel6.setText("Documento");

        jLabel7.setText("Contacto");

        jLabel8.setText("Género");

        btnEliminarDM.setBackground(new java.awt.Color(51, 255, 255));
        btnEliminarDM.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 11)); // NOI18N
        btnEliminarDM.setText("Eliminar");
        btnEliminarDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarDMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(btnAgregarDM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 198, Short.MAX_VALUE)
                .addComponent(btnEditarDM)
                .addGap(173, 173, 173)
                .addComponent(btnEliminarDM)
                .addGap(48, 48, 48))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(220, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(245, 245, 245))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(56, 56, 56)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTipoDocumentoDM, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDocumentoDM, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtContactoDM, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGeneroDM, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(56, 56, 56)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtIDDM, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                    .addComponent(txtNombreDM))))
                        .addGap(187, 187, 187))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIDDM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombreDM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTipoDocumentoDM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDocumentoDM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtContactoDM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtGeneroDM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarDM)
                    .addComponent(btnEditarDM)
                    .addComponent(btnEliminarDM))
                .addGap(22, 22, 22))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        tbDueñosMascotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "Tipo documento", "Documento", "Contacto", "Género"
            }
        ));
        tbDueñosMascotas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDueñosMascotasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbDueñosMascotas);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnVerMascotas.setBackground(new java.awt.Color(51, 255, 255));
        btnVerMascotas.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 11)); // NOI18N
        btnVerMascotas.setText("Ver mascotas");
        btnVerMascotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerMascotasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(327, 327, 327)
                .addComponent(btnVerMascotas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVerMascotas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDMActionPerformed
        // TODO add your handling code here:
        add_dueño_mascota();
    }//GEN-LAST:event_btnAgregarDMActionPerformed

    private void btnEditarDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarDMActionPerformed
        // TODO add your handling code here:
        edit_dueño_mascota();
    }//GEN-LAST:event_btnEditarDMActionPerformed

    private void btnEliminarDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarDMActionPerformed
        // TODO add your handling code here:
        delete_dueño_mascota();
    }//GEN-LAST:event_btnEliminarDMActionPerformed

    private void tbDueñosMascotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDueñosMascotasMouseClicked
        // TODO add your handling code here:
        int row = tbDueñosMascotas.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(this, "Debes seleccionar un dueño");
        }else{
            int id = Integer.parseInt((String)tbDueñosMascotas.getValueAt(row,0).toString());
            String owner_ = (String)tbDueñosMascotas.getValueAt(row, 1);
            String document_type = (String)tbDueñosMascotas.getValueAt(row, 2);
            String document = (String)tbDueñosMascotas.getValueAt(row, 3);
            String contact = (String)tbDueñosMascotas.getValueAt(row, 4);
            String gender = (String)tbDueñosMascotas.getValueAt(row, 5);
            txtIDDM.setText("" + id);
            txtNombreDM.setText(owner_);
            txtTipoDocumentoDM.setText(document_type);
            txtDocumentoDM.setText(document);
            txtContactoDM.setText(contact);
            txtGeneroDM.setText(gender);
        }
    }//GEN-LAST:event_tbDueñosMascotasMouseClicked

    private void btnVerMascotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerMascotasActionPerformed
        // TODO add your handling code here:
        search_owner_pet();
    }//GEN-LAST:event_btnVerMascotasActionPerformed

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
            java.util.logging.Logger.getLogger(Dueños_.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dueños_.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dueños_.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dueños_.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dueños_().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarDM;
    private javax.swing.JButton btnEditarDM;
    private javax.swing.JButton btnEliminarDM;
    private javax.swing.JButton btnVerMascotas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tbDueñosMascotas;
    private javax.swing.JTextField txtContactoDM;
    private javax.swing.JTextField txtDocumentoDM;
    private javax.swing.JTextField txtGeneroDM;
    private javax.swing.JTextField txtIDDM;
    private javax.swing.JTextField txtNombreDM;
    private javax.swing.JTextField txtTipoDocumentoDM;
    // End of variables declaration//GEN-END:variables
}
