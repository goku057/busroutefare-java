/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sourcepackage;

/**
 *
 * @author User
 */
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.io.*;
import javax.swing.*;
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
    */
    
    
    String route1="Select...",route2="Select...",folderNamesForFile="jinishpati/routelist";
    
    String cmp1="",cmp2="";
    
     DefaultComboBoxModel dmFrom=new DefaultComboBoxModel(); 
     DefaultComboBoxModel  dmTo=new DefaultComboBoxModel();
     DefaultComboBoxModel  dmUpdate=new DefaultComboBoxModel();
    
    public Home() {
        initComponents();
        setTitle("HOME");
         
        try{
            String s,filepath="jinishpati/routelist/routelist.txt";
            File fp= new File(filepath);
            FileReader fr=new FileReader(filepath);
            Scanner scan=new Scanner(fr);
           
            while(scan.hasNextLine())
            {
                s=scan.nextLine();
                dmFrom.addElement(s);
                dmTo.addElement(s);
                if(s.equals("Select..."))
                {
                    continue;
                }
                dmUpdate.addElement(s);
            }
            
            
            jComboBoxFrom.setModel(dmFrom);
            jComboBoxTo.setModel(dmTo);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        
        
    }
    
    
  
    
    /*void compare(String str)
    {
        
        try{
        File folder=new File(folderNamesForFile);
        String flist[]=folder.list();
      
         for(String s:flist)
            {
                 for(int i=0;i<dmFrom.getSize();i++){
              
                    String filename1=(jComboBoxFrom.getItemAt(i).toString())+str+".txt";
              
                    String filename2=str+(jComboBoxFrom.getItemAt(i).toString())+".txt";
              
                    System.out.println(filename1+"\n"+filename2);
                    if(s.equals(filename1))
                        {
                        File f=new File(folderNamesForFile+"/"+filename1);
                        f.delete();
                         //f.deleteOnExit();
                  
                        }
                    if(s.equals(filename2))
                       {
                      
                        File f=new File(folderNamesForFile+"/"+filename2);
                        f.delete();
                         //f.deleteOnExit();
                      }
                    System.out.println(i);
            }
          
          System.out.println("ASI");
      } 
      
      
      
      
     }
        
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Home er delete method e pera ");
        }
        
        
    }*/
    
    
    
    
    
    
    void checkAndPrintValidRoute()
    {
        if((route1.equals("Select..."))||(route2.equals("Select...")))
       {
           jTextFieldRDetails.setText("");
       }
       else
       {
           boolean flag=true;
           try{
        File folder=new File(folderNamesForFile);
        String flist[]=folder.list();
      
         for(String s:flist)
            {
                 if(s.equals(route1+route2+".txt")||s.equals(route2+route1+".txt"))
                 {
                     jTextFieldRDetails.setText("Valid route exists" );
                     flag=false;
                     break;
                 }
                 
            } 
      
        if(flag)
        {
            
            jTextFieldRDetails.setText("NO VALID route is available");
        }
      
      
     }
        
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Home er delete method e pera ");
        }
           
           
       }
    }
    
    
    
     void updateRouteName(String oldRoute,String newRoute)
    {
        //System.out.println("ASI");
        
        String filePath="jinishpati/routelist";
        File f= new File(filePath);
        String[] flists=f.list();
        for(String s:flists)
        {
            for(int i=1;i<dmFrom.getSize();i++)
            {
                //System.out.println("ASi2");
                String nameOld1=oldRoute+jComboBoxFrom.getItemAt(i)+".txt";
                String nameOld2=jComboBoxFrom.getItemAt(i)+oldRoute+".txt";
                
                String nameNew1=newRoute+jComboBoxFrom.getItemAt(i)+".txt";
                String nameNew2=jComboBoxFrom.getItemAt(i)+newRoute+".txt";
                
                
                if(s.equalsIgnoreCase(nameOld1))
                {
                    
                    
                    
                    try{
                        //System.out.println("ASI3");
                        
                        File fp=new File(filePath+"/"+nameOld1);
                        //FileReader fw=new FileReader(fp);
                        Scanner scan=new Scanner(fp);
                        FileWriter fw= new FileWriter(filePath+"/"+"temp.txt");
                        PrintWriter pw= new PrintWriter(fw);
                        //System.out.println("ASI k er age");
                        //int k=1;
                        while(scan.hasNextLine())
                        {
                            
                            pw.println(scan.nextLine());
                            pw.flush();
                            //System.out.println("k = "+i);
                            //k++;
                        }
                       // System.out.println("ASI k er pore loop er pore");
                        scan.close();
                        fw.close();
                        pw.close();
                        fp.delete();
                        
                        
                        
                        
                        
                        File fpi=new File(filePath+"/"+"temp.txt");
                        //FileReader fw=new FileReader(fp);
                        Scanner scani=new Scanner(fpi);
                        FileWriter fwi= new FileWriter(filePath+"/"+nameNew1);
                        PrintWriter pwi= new PrintWriter(fwi);
                        //System.out.println("ASI k er age");
                        
                        while(scani.hasNextLine())
                        {
                            
                            pwi.println(scani.nextLine());
                            pwi.flush();
                            
                        }
                        //System.out.println("ASI k er pore loop er pore");
                        scani.close();
                        fwi.close();
                        pwi.close();
                        fpi.delete();
                        
                        
                        
                        
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null, "HomeAdmin er updateRouteName method e pera 1");
                    }
                    
                    
                    
                    
                }
                
                
                else if(s.equalsIgnoreCase(nameOld2))
                {
                    
                    
                    
                    try{
                       // System.out.println("ASI3");
                        
                        File fp=new File(filePath+"/"+nameOld2);
                        //FileReader fw=new FileReader(fp);
                        Scanner scan=new Scanner(fp);
                        FileWriter fw= new FileWriter(filePath+"/"+"temp.txt");
                        PrintWriter pw= new PrintWriter(fw);
                        //System.out.println("ASI k er age");
                        //int k=1;
                        while(scan.hasNextLine())
                        {
                            
                            pw.println(scan.nextLine());
                            pw.flush();
                            //System.out.println("k = "+i);
                            //k++;
                        }
                        //System.out.println("ASI k er pore loop er pore");
                        scan.close();
                        fw.close();
                        pw.close();
                        fp.delete();
                        
                        
                        
                        
                        
                        File fpi=new File(filePath+"/"+"temp.txt");
                        //FileReader fw=new FileReader(fp);
                        Scanner scani=new Scanner(fpi);
                        FileWriter fwi= new FileWriter(filePath+"/"+nameNew2);
                        PrintWriter pwi= new PrintWriter(fwi);
                        //System.out.println("ASI k er age");
                        
                        while(scani.hasNextLine())
                        {
                            
                            pwi.println(scani.nextLine());
                            pwi.flush();
                            
                        }
                        //System.out.println("ASI k er pore loop er pore");
                        scani.close();
                        fwi.close();
                        pwi.close();
                        fpi.delete();
                        
                        
                        
                        
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null, "HomeAdmin er updateRouteName method e pera 2");
                    }
                    
                    
                    
                    
                    
                }
                
                else if(s.equals(nameOld1))
                {
                    
                    try{
                       // System.out.println("ASI3");
                        
                        File fp=new File(filePath+"/"+nameOld1);
                        //FileReader fw=new FileReader(fp);
                        Scanner scan=new Scanner(fp);
                        FileWriter fw= new FileWriter(filePath+"/"+nameNew1);
                        PrintWriter pw= new PrintWriter(fw);
                        System.out.println("ASI k er age");
                        //int k=1;
                        while(scan.hasNextLine())
                        {
                            
                            pw.println(scan.nextLine());
                            pw.flush();
                           // System.out.println("k = "+i);
                           // k++;
                        }
                       // System.out.println("ASI k er pore loop er pore");
                        scan.close();
                        fw.close();
                        pw.close();
                        fp.delete();
                        
                        
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null, "HomeAdmin er updateRouteName method e pera 3");
                    }
                    
                }
                
                else if(s.equals(nameOld2))
                {
                    
                    
                    try{
                        
                        System.out.println("ASI4");
                        File fp=new File(filePath+"/"+nameOld2);
                        //FileReader fw=new FileReader(fp);
                        Scanner scan=new Scanner(fp);
                        FileWriter fw= new FileWriter(filePath+"/"+nameNew2);
                        PrintWriter pw= new PrintWriter(fw);
                        
                        //int j=1;
                        while(scan.hasNextLine())
                        {
                            
                            pw.println(scan.nextLine());
                            pw.flush();
                            
                            //System.out.println("j= "+j);
                           // j++;
                        }
                        
                        scan.close();
                        fw.close();
                        pw.close();
                        fp.delete();
                       
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null, "HomeAdmin er updateRouteName method e pera 4");
                    }
                    
                    
                }
                
            }
        }
    }
    
    
    
    

    void deletefiles(String str)
    {
        try{
        File folder=new File(folderNamesForFile);
        String flist[]=folder.list();
      
      for(String s:flist)
      {
          for(int i=0;i<dmFrom.getSize();i++){
              
              String filename1=(jComboBoxFrom.getItemAt(i).toString())+str+".txt";
              
              String filename2=str+(jComboBoxFrom.getItemAt(i).toString())+".txt";
              
              //System.out.println(filename1+"\n"+filename2);
              if(s.equals(filename1))
              {
                  File f=new File(folderNamesForFile+"/"+filename1);
                  f.delete();
                  //f.deleteOnExit();
                  
              }
               if(s.equals(filename2))
                      {
                      
                      File f=new File(folderNamesForFile+"/"+filename2);
                       f.delete();
                      //f.deleteOnExit();
                      }
             // System.out.println(i);
          }
          
          //System.out.println("ASI");
      } 
      
      
      
      
     }
        
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Home er delete method e pera ");
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

        jComboBoxFrom = new javax.swing.JComboBox<>();
        jComboBoxTo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldRDetails = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBoxFrom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select..." }));
        jComboBoxFrom.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxFromItemStateChanged(evt);
            }
        });

        jComboBoxTo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select..." }));
        jComboBoxTo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxToItemStateChanged(evt);
            }
        });

        jLabel1.setText("From :");

        jLabel2.setText("To");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Select your route");

        jTextFieldRDetails.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxTo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117))))
            .addGroup(layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jTextFieldRDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jTextFieldRDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxFromItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxFromItemStateChanged
       route1=jComboBoxFrom.getSelectedItem().toString();
       
       //cmp1=jComboBoxFrom.getSelectedItem().toString();
       checkAndPrintValidRoute();
       
       
       
    }//GEN-LAST:event_jComboBoxFromItemStateChanged

    private void jComboBoxToItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxToItemStateChanged
        route2=jComboBoxTo.getSelectedItem().toString();
        
        checkAndPrintValidRoute();
        //cmp2=jComboBoxTo.getSelectedItem().toString();
        
        
        
        
    }//GEN-LAST:event_jComboBoxToItemStateChanged

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBoxFrom;
    private javax.swing.JComboBox<String> jComboBoxTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextFieldRDetails;
    // End of variables declaration//GEN-END:variables
}
