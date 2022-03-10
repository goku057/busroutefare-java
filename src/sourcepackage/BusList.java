/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sourcepackage;

import javax.swing.JOptionPane;
import java.util.*;
import java.io.*;
import javax.swing.DefaultListModel;
/**
 *
 * @author User
 */
public class BusList extends javax.swing.JFrame {

    /**
     * Creates new form BusList
     */
    
    
    boolean enableFlag=false;
    private String currentFileName;
    private String selectedBus="";
    String busListPath="jinishpati/bus list";
    String filePath="jinishpati/routelist";
    
    public BusList() {
        initComponents();
       
        setTitle("Bus list");
    }
    
    
    DefaultListModel list= new DefaultListModel();    

    public BusList(String fileName,String routeName) {
        
        
        
        initComponents();
       
        setTitle("Bus list");
        jTextFieldHead.setText("Following buses are available on "+routeName);
        currentFileName=fileName;
        
        
        try{
            
            
            FileReader fr= new FileReader(fileName);
            Scanner scan= new Scanner(fr);
            int i=0;
            while(scan.hasNextLine())
            {
                String s=scan.nextLine();
                list.addElement(s);
                i++;
            }
            jList1.setModel(list);
            fr.close();
            scan.close();
            
            if(i==0)
                selectedBus="There are no bus in the route";
            else{
                selectedBus=list.getElementAt(0).toString();
                
                jList1.setSelectedIndex(0);
                
            }    
            
            jTextField2.setText(selectedBus);
          }
        
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"EKHANE GENJAM ASEH BUS LIST EH");
        }
        
    
          
    }
    
    
    
    String getCurrentFileName()
    {
        return currentFileName;
    }
    
    //this constructor is for opening BusList.txt 
    
    
    public BusList(String fileName) {
        
        
        
        initComponents();
       
        setTitle("Bus list");
        jTextFieldHead.setText("List of all BUS");
        currentFileName=fileName;
        
        
        try{
            
            
            FileReader fr= new FileReader(fileName);
            Scanner scan= new Scanner(fr);
            int i=0;
            while(scan.hasNextLine())
            {
                String s=scan.nextLine();
                list.addElement(s);
                i++;
            }
            jList1.setModel(list);
            fr.close();
            scan.close();
            
            if(i==0)
                selectedBus="There are no bus in the route";
            else{
                selectedBus=list.getElementAt(0).toString();
                
                jList1.setSelectedIndex(0);
                
            }    
            
            jTextField2.setText(selectedBus);
          }
        
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"EKHANE GENJAM ASEH BUS LIST EH");
        }
        
    
          
    }
    
    
    
    
    
    
    
    
    
    String getSelectedBusName(){
    
           return selectedBus;
          
          }
    
    int getSelectedBusIndex()
    {
        return jList1.getSelectedIndex();
    }
     
    
    void createFile(String name)
    {
        try{
            
            File f=new File(busListPath+"/buses");
            String flist[]=f.list();
            
            for(String s:flist)
            {
                if(s.equals(name+".txt"))
                    return;
            }
            
            FileWriter fw=new FileWriter(busListPath+"/buses/"+name+".txt");
            fw.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "BusList er Create file metthod e pera");
        }
    }
    
    
    void deleteFile(String name)
    {
        File f=new File(busListPath+"/buses/"+name+".txt");
        f.delete();
    }
    
    
    void updateBusList()
    {
        try {
          
            
          FileWriter fw=new FileWriter(busListPath+"/buslist.txt");
          PrintWriter pw =new PrintWriter(fw);
          
          for(int i=0;i<list.getSize();i++)
          {
              pw.println(list.getElementAt(i).toString());
          }
          pw.close();
          fw.close();
          
           
          
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,"BusList er updateBusList method e pera");
        }
    }
    
     
     void addtolist(String s)
     {
         s=s.toUpperCase();
         
         for(int i=0;i<list.getSize();i++)
         {
             if(s.equals(list.getElementAt(i).toString()))
             {
                 JOptionPane.showMessageDialog(this,"This bus has already been added");
                 return;
             }
             
         }
         
         
         list.addElement(s);
         jList1.setSelectedIndex((list.getSize()-1));
         updateFile();
         addToBusList(s);
         createFile(s);
         
         JOptionPane.showMessageDialog(this,"This bus has been SUCCESSFULLY  added");
     }
     
     
    void addToBusList(String name)
    {
        
        try{
            
            FileReader fr=new FileReader(busListPath+"/"+"buslist.txt");
            Scanner scan= new Scanner (fr);
            while(scan.hasNext())
            {
                if(name.equals(scan.nextLine()))
                    return;
            }
            
            
            FileWriter fw= new FileWriter(busListPath+"/"+"buslist.txt",true);
            PrintWriter pw= new PrintWriter(fw,true);
            pw.println(name);
            pw.flush();
            fw.close();
            pw.close();
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "BusList er addTobusList method e pera");
        }
        
    }
     
    boolean  check=true;//For avoid deleting bus details in BusListAdmin class delete button
    
    void deletetolist(int index)
    {
       int n= JOptionPane.showConfirmDialog(this, "Are you sure you want to delete "+list.getElementAt(index).toString(),"Confirmation",JOptionPane.YES_NO_OPTION);
       if(n==1){
           check=false;
           return;
           
       }
       
       //deleteFile(list.getElementAt(index).toString());
       
       list.remove(index);
       if(!list.isEmpty())
       {
           jList1.setSelectedIndex(list.size()-1);
       }
       updateFile();
       
    }
    
    void deletetoAlllist(int index)
    {
       
       
       deleteFile(list.getElementAt(index).toString());
       
       list.remove(index);
       if(!list.isEmpty())
       {
           jList1.setSelectedIndex(0);
       }
       updateFile();
       
    }
    
    
    
    void update(String s,int index)
    {
        list.remove(index);
        list.add(index, s);
    }
    
    
    void updateFile()
    {
        try{
            
            FileWriter fr= new FileWriter(currentFileName);
            PrintWriter pr=new PrintWriter(fr);
            
            for(int i=0;i<list.getSize();i++)
            {
                pr.println(list.getElementAt(i).toString());
                pr.flush();
            }
            fr.close();
            pr.close();
            
            
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null,"BusList er updateFile method e pera");
            
        }
    }
    
    String getJtextFName(){
        return jTextField2.getText();
    }
    
    void setBackButtonVisibility(boolean n)
    {
        if(n)
        {
            jButtonBack.setVisible(true);
        }
        else
            jButtonBack.setVisible(false);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jTextFieldHead = new javax.swing.JTextField();
        jButtonShowDetails = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButtonBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jTextFieldHead.setEditable(false);

        jButtonShowDetails.setText("Show Details");
        jButtonShowDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShowDetailsActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButtonBack.setText("Back");
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Selected Bus");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldHead)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(56, 56, 56))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonShowDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(60, 60, 60)
                                        .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jTextFieldHead, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(30, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonShowDetails)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonBack)
                        .addGap(18, 18, 18))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        selectedBus=jList1.getSelectedValue();
        jTextField2.setText(selectedBus);
        
    }//GEN-LAST:event_jList1ValueChanged

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        this.setVisible(false);
        this.dispose();
        new HomeUser().setVisible(true);
    }//GEN-LAST:event_jButtonBackActionPerformed

    private void jButtonShowDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShowDetailsActionPerformed
       
        if(list.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "There are no buses to show");
            return;
        }
        
        String filePathWithName =busListPath+"/buses/"+jTextField2.getText()+".txt";
        
        new RouteDisplay(filePathWithName).setVisible(true);
        
        
        
    }//GEN-LAST:event_jButtonShowDetailsActionPerformed

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
            java.util.logging.Logger.getLogger(BusList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BusList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BusList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BusList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BusList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBack;
    protected javax.swing.JButton jButtonShowDetails;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextFieldHead;
    // End of variables declaration//GEN-END:variables
}
