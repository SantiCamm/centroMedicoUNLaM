package producto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class ListadoEnfermedad extends javax.swing.JFrame {
  
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(ListadoEnfermedad.class.getName());
	
	/*
	 * Constructor
	 */
    public ListadoEnfermedad() {
        initComponents();
        MenuPrincipalForm.CenteredFrame(this);
    }
    
    /*
     * Limpia el formulario
     */
    public void cleanForm() {
        codMed.setText(null);
        labelMed.setText(null);
        DefaultListModel<String> vacio = new DefaultListModel<>();
        listEnf.setModel(vacio);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        codMed = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        labelMed = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listEnf = new javax.swing.JList<String>();
        
        this.setTitle("Medicus - Informes");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Enfermedades que atiende cada m?dico");

        jLabel2.setText("Ingrese el c?digo del m?dico");

        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(listEnf);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(codMed, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton1))
                    .addComponent(labelMed)
                    .addComponent(jScrollPane1))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(codMed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(labelMed)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*
     * Muestra las enfermedades que atiende un medico
     * Valida que exista el medico
     */
    @SuppressWarnings("unused")
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String codtem, codm, nomm, espm, codp, codme, enfp, readMed, readPac;
        boolean encontrado = false;
        BufferedReader brMed = null;
        BufferedReader brPac = null;
        DefaultListModel<String> listado = new DefaultListModel<>();
        listEnf.setModel(listado);
        
        listado.clear();
        labelMed.setText(null);

        try {
            brMed = new BufferedReader(new FileReader(MenuPrincipalForm.getRutaArchivoMedicos()));
            brPac = new BufferedReader(new FileReader(MenuPrincipalForm.getRutaArchivoSituacionPacientes()));
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Error: archivo no encontrado","Error",JOptionPane.ERROR_MESSAGE);
        }

        codtem = codMed.getText();
        try {
            while ((readMed = brMed.readLine()) != null) {
                
                // separo los campos y obtengo los valores
                String[] campos_med = readMed.split("\\|");
                codm = campos_med[0];
                nomm = campos_med[1];
                espm = campos_med[2];
                
                if (codm.equals(codtem)) { // compara el codigo digitado con el codigo del medico de la tabla "datomed"
                    encontrado = true;
                    logger.info("Generando informe sobre enfermedades que atiene un m?dico."); 
                    labelMed.setText("El m?dico " + nomm + " trata las siguientes enfermedades:");
                    try {
                        while ((readPac = brPac.readLine()) != null) {
                            
                            // separo los campos y obtengo los valores
                            String[] campos_pac = readPac.split("\\|");
                            codp = campos_pac[0];
                            codme = campos_pac[1];
                            enfp = campos_pac[2];
                            
                            if (codtem.equals(codme)) { // compara el codigo del medico de la tabla "datomed" con el codigo del medico en la tabla "situpac"
                                listado.addElement(">>>> " + enfp);
                            }
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,"Hubo un error al leer los datos");
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Hubo un error al leer los datos");
        }		
        try {
                brMed.close();
                brPac.close();
        } catch (IOException e) {}
        
        if(!encontrado) {
            JOptionPane.showMessageDialog(null,"No se encontraron datos");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ListadoEnfermedad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListadoEnfermedad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListadoEnfermedad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListadoEnfermedad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListadoEnfermedad().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField codMed;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelMed;
    private javax.swing.JList<String> listEnf;
    // End of variables declaration//GEN-END:variables
}
