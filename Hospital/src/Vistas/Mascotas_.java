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
public class Mascotas_ extends javax.swing.JFrame {
    Conexion con = new Conexion();
    Connection cn;
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;
    /** Creates new form Mascotas_ */
    public Mascotas_() {
        initComponents();
        setLocationRelativeTo(null);
        show_mascotas();
    }
    void show_mascotas(){
        String sql = "select * from tb_pet";
        try{
            cn = con.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            //Los datos que devuelve la consulta se muestran en la tabla
            Object[]mascotas = new Object[4];
            modelo = (DefaultTableModel)tbMascotas.getModel();
            while(rs.next()){
                mascotas[0] = rs.getInt("id");
                mascotas[1] = rs.getString("name_");
                mascotas[2] = rs.getString("breed");
                mascotas[3] = rs.getInt("id_owner_pet");
                modelo.addRow(mascotas);
            }
            tbMascotas.setModel(modelo);
        }catch(SQLException e){
        }
    }
    void add_mascota(){
        String name_ = txtNombreM.getText();
        String breed = txtRazaM.getText();
        int id_owner_pet = Integer.parseInt(txtIDdueñoM.getText());
        if (name_.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta ingresar el nombre de la mascota");
        }else{
            String query = "INSERT INTO tb_pet(name_, breed,id_owner_pet) VALUES('" + name_ + "','" + breed + "', "+ id_owner_pet+ ")";
            try{
                cn = con.getConnection();
                st = cn.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "La mascota ha sido creada");
                clear_rows_table();
                show_mascotas();
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(this, "No se pudo crear la mascota");
            }
        }
    }
    void edit_mascota(){
        //Hacemos nuevamente lectura de los valores contenidos en los JTextField
        //Para identificar si el usuario modifico algún valor
        int id = Integer.parseInt(txtIDM.getText());
        String name = txtNombreM.getText();
        String breed = txtRazaM.getText();
        int id_owner_pet = Integer.parseInt(txtIDdueñoM.getText());
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta ingresar el nombre de la mascota");
        }else{
            String query = "UPDATE tb_pet SET name_ = '" + name + "', breed= '"+breed + "',id_owner_pet= "+id_owner_pet+" WHERE id = " + id;
            //UPDATE tb_persons SET dni =dni, nombre= 'name' WHERE id = id
        try{
            cn = con.getConnection();
            st = cn.createStatement();
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "La mascota ha sido modificada con éxito");
            clear_rows_table();
            show_mascotas();
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(this, "No se pudo modificar la mascota");
                System.out.println("error al editar "+e);
            }
        }
    }
    void delete_mascota(){
        int fila = tbMascotas.getSelectedRow();
        int id = Integer.parseInt(txtIDM.getText());
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "No has seleccionado una mascota");
        }else{
            String query = "DELETE FROM tb_pet WHERE id = " + id;
            try{
                cn = con.getConnection();
                st = cn.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "La mascota ha sido eliminada con exito");
                clear_rows_table();
                show_mascotas();
            }catch(HeadlessException | SQLException e){
            }
        }
    }
    void search_owner_pet(){
        Dueños_Mascotas DM = new Dueños_Mascotas(this, true);
        Mascotas_ mas = new Mascotas_();
        int id = Integer.parseInt(txtIDdueñoM.getText());        
        String sql = "select * from tb_pet where id = " + id;
        try{
            cn = con.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            System.out.println(rs);
            //Los datos que devuelve la consulta se muestran en la tabla
            Object[]mascotas = new Object[4];
            modelo = (DefaultTableModel)DM.tbDueños_Mascotas.getModel();
            while(rs.next()){
                mascotas[0] = rs.getInt("id");
                mascotas[1] = rs.getString("name_");
                mascotas[2] = rs.getString("breed");
                mascotas[3] = rs.getInt("id_owner_pet");
                modelo.addRow(mascotas);
            }
            DM.tbDueños_Mascotas.setModel(modelo);
            DM.setVisible(true);
        }catch(SQLException e){
        }
    }
    void clear_rows_table(){
        for (int i = 0; i < tbMascotas.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i-1;
        }
        txtIDM.setText("");
        txtIDdueñoM.setText("");
        txtNombreM.setText("");
        txtRazaM.setText("");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
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
        txtIDM = new javax.swing.JTextField();
        txtNombreM = new javax.swing.JTextField();
        txtRazaM = new javax.swing.JTextField();
        btnAgregarM = new javax.swing.JButton();
        btnEditarM = new javax.swing.JButton();
        btnEliminarM = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtIDdueñoM = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMascotas = new javax.swing.JTable();
        btnDueñosM = new javax.swing.JButton();
        btnHospitalMascota = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 51, 255));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setText("MASCOTAS");

        jLabel2.setText("ID");

        jLabel3.setText("Nombre");

        jLabel4.setText("Raza");

        txtIDM.setEnabled(false);

        btnAgregarM.setBackground(new java.awt.Color(51, 255, 255));
        btnAgregarM.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 11)); // NOI18N
        btnAgregarM.setText("Agregar");
        btnAgregarM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarMActionPerformed(evt);
            }
        });

        btnEditarM.setBackground(new java.awt.Color(51, 255, 255));
        btnEditarM.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 11)); // NOI18N
        btnEditarM.setText("Editar");
        btnEditarM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarMActionPerformed(evt);
            }
        });

        btnEliminarM.setBackground(new java.awt.Color(51, 255, 255));
        btnEliminarM.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 11)); // NOI18N
        btnEliminarM.setText("Eliminar");
        btnEliminarM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarMActionPerformed(evt);
            }
        });

        jLabel5.setText("ID dueño");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(236, 236, 236)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(142, 142, 142)
                            .addComponent(btnAgregarM))))
                .addGap(56, 56, 56)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnEditarM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                        .addComponent(btnEliminarM)
                        .addGap(103, 103, 103))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                            .addComponent(txtIDM)
                            .addComponent(txtNombreM)
                            .addComponent(txtRazaM)
                            .addComponent(txtIDdueñoM))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIDM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombreM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtRazaM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtIDdueñoM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarM)
                    .addComponent(btnEditarM)
                    .addComponent(btnEliminarM))
                .addGap(35, 35, 35))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        tbMascotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "RAZA", "ID DUEÑO"
            }
        ));
        tbMascotas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMascotasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbMascotas);

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

        btnDueñosM.setBackground(new java.awt.Color(51, 255, 255));
        btnDueñosM.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 11)); // NOI18N
        btnDueñosM.setText("Dueños");
        btnDueñosM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDueñosMActionPerformed(evt);
            }
        });

        btnHospitalMascota.setBackground(new java.awt.Color(51, 255, 255));
        btnHospitalMascota.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 11)); // NOI18N
        btnHospitalMascota.setText("Hospital_Mascota");
        btnHospitalMascota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHospitalMascotaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHospitalMascota)
                .addGap(231, 231, 231)
                .addComponent(btnDueñosM)
                .addGap(169, 169, 169))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDueñosM)
                    .addComponent(btnHospitalMascota))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btnAgregarMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarMActionPerformed
        // TODO add your handling code here:
        add_mascota();
    }//GEN-LAST:event_btnAgregarMActionPerformed

    private void btnEditarMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarMActionPerformed
        // TODO add your handling code here:
        edit_mascota();
    }//GEN-LAST:event_btnEditarMActionPerformed

    private void btnEliminarMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarMActionPerformed
        // TODO add your handling code here:
        delete_mascota();
    }//GEN-LAST:event_btnEliminarMActionPerformed

    private void tbMascotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMascotasMouseClicked
        // TODO add your handling code here:
        int row = tbMascotas.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(this, "Debes seleccionar un departamento");
        }else{
            int id = Integer.parseInt((String)tbMascotas.getValueAt(row,0).toString());
            String nombre = (String)tbMascotas.getValueAt(row, 1);
            String raza = (String)tbMascotas.getValueAt(row, 2);
            int id_dueño = Integer.parseInt((String)tbMascotas.getValueAt(row,3).toString());
            txtIDM.setText("" + id);
            txtNombreM.setText(nombre);
            txtRazaM.setText(raza);
            txtIDdueñoM.setText(""+id_dueño);
        }
    }//GEN-LAST:event_tbMascotasMouseClicked

    private void btnDueñosMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDueñosMActionPerformed
        // TODO add your handling code here:
        new Dueños_().setVisible(true);
    }//GEN-LAST:event_btnDueñosMActionPerformed

    private void btnHospitalMascotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHospitalMascotaActionPerformed
        // TODO add your handling code here:
        new Hospital_Mascotas(this, true).setVisible(true);
    }//GEN-LAST:event_btnHospitalMascotaActionPerformed

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
            java.util.logging.Logger.getLogger(Mascotas_.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mascotas_.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mascotas_.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mascotas_.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mascotas_().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarM;
    private javax.swing.JButton btnDueñosM;
    private javax.swing.JButton btnEditarM;
    private javax.swing.JButton btnEliminarM;
    private javax.swing.JButton btnHospitalMascota;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tbMascotas;
    private javax.swing.JTextField txtIDM;
    private javax.swing.JTextField txtIDdueñoM;
    private javax.swing.JTextField txtNombreM;
    private javax.swing.JTextField txtRazaM;
    // End of variables declaration//GEN-END:variables

}
