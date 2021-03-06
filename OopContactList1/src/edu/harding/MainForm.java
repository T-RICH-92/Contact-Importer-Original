/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.harding;

import com.google.gdata.util.ServiceException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author T RICH
 */
public class MainForm extends javax.swing.JFrame
{
    Frame frame = this;
    
    // FFR: Email address is primary key within List. Treat it as such.
    static ArrayList<Contact> contactList = new ArrayList<>();
    
    JFileChooser fc = new JFileChooser();
    File file;
    
    /**
     * Creates new form MainForm
     */
    public MainForm()
    {
        initComponents();
        file = fc.getCurrentDirectory();
        LoginDialog login = new LoginDialog(this, true);
        
        Image img = null;
        try {
            // http://stackoverflow.com/questions/5485034/add-images-to-jar
            img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("hu64.png"));
            this.setIconImage(img);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        
        this.getContentPane().setBackground(Color.darkGray);
        
        // http://www.coderanch.com/t/333523/GUI/java/JFrame-position-size
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int sWIDTH = screenSize.width;
        int sHEIGHT = screenSize.height;
        setLocation(new Point(sWIDTH/2 - getWidth()/2, sHEIGHT/2 - getHeight()/2));
        
        

        do
        {
            login.setVisible(true);
            if(!login.getLoginAttempted())
            {
                System.exit(1);
            }
        }
        while(ApiContact.getLoggedInStatus() == false);
        
        
        try
        {
            //ApiContact.connectApi();
            notificationLabel.setVisible(false);
            ApiContact.fetchAllContacts();
            ApiContact.fetchAllGroups();
            jProgressBar1.setVisible(false);
            importErrorLabel.setVisible(false);
            //ApiContact.createContactGroup();
        }
        catch (Exception ex)
        {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jDialog1 = new javax.swing.JDialog();
        jButton2 = new javax.swing.JButton();
        path = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        modifyButton = new javax.swing.JButton();
        ApplyButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        groupNameTextBox = new javax.swing.JTextField();
        jProgressBar1 = new javax.swing.JProgressBar();
        pathTextBox = new javax.swing.JTextField();
        importErrorLabel = new javax.swing.JLabel();
        notificationLabel = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Google Contacts Importer");

        jButton2.setText("Browse...");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Browse_ButtonPressed(evt);
            }
        });

        path.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        path.setForeground(new java.awt.Color(255, 255, 255));
        path.setText("File Location:");

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Last Name", "Preferred Name", "Email Address"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable1);

        jButton3.setText("Import");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Import_ButtonPressed(evt);
            }
        });

        jButton4.setText("Add Row");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddRow_ButtonPressed(evt);
            }
        });

        jButton5.setText("Remove Selected");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveSelected_ButtonPressed(evt);
            }
        });

        modifyButton.setText("Modify Selected");
        modifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyButtonActionPerformed(evt);
            }
        });

        ApplyButton.setText("Send to Google");
        ApplyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApplyButtonActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("New Group Name:");

        importErrorLabel.setForeground(new java.awt.Color(255, 255, 255));
        importErrorLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        importErrorLabel.setText(" ");
        importErrorLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        notificationLabel.setFont(notificationLabel.getFont().deriveFont(notificationLabel.getFont().getStyle() | java.awt.Font.BOLD, notificationLabel.getFont().getSize()+5));
        notificationLabel.setForeground(new java.awt.Color(255, 255, 255));
        notificationLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        notificationLabel.setText(" ");
        notificationLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modifyButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(path)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pathTextBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(groupNameTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(notificationLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ApplyButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(importErrorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(pathTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(path)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(importErrorLabel)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(modifyButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(groupNameTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ApplyButton)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(notificationLabel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void Browse_ButtonPressed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_Browse_ButtonPressed
    {//GEN-HEADEREND:event_Browse_ButtonPressed
        // http://docs.oracle.com/javase/7/docs/api/javax/swing/JFileChooser.html#showOpenDialog(java.awt.Component)
        fc = new JFileChooser(file);
        fc.setMultiSelectionEnabled(true);
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        //Handle open button action.
        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            pathTextBox.setText("");
            for (File f : fc.getSelectedFiles())
                {
                file = fc.getSelectedFile().getParentFile();
                if (pathTextBox.getText().equals(""))
                {
                    pathTextBox.setText(f.getPath());
                }
                else
                {
                    pathTextBox.setText(pathTextBox.getText() + "," + f.getPath());
                }
            }
        }
   
    }//GEN-LAST:event_Browse_ButtonPressed

    private void Import_ButtonPressed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_Import_ButtonPressed
    {//GEN-HEADEREND:event_Import_ButtonPressed
        ArrayList<String> filesToImport = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(pathTextBox.getText(), ",");
        String token;
        while(st.hasMoreTokens())
        {
            token = st.nextToken().trim();
            filesToImport.add(token);
            //System.out.println("adding to filestoimport" + token);
        }

        for (String s : filesToImport)
        {
            file = new File(s);
            findFiles(file);
        }
        
        //System.out.println(pathTextBox.getText());
        
        printContacts();
        
    }//GEN-LAST:event_Import_ButtonPressed

    private void AddRow_ButtonPressed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_AddRow_ButtonPressed
    {//GEN-HEADEREND:event_AddRow_ButtonPressed
        // TODO add your handling code here:
        //String[] s = {"", "", "", ""};
        //((DefaultTableModel)jTable1.getModel()).addRow(s);
        
        AddModifyContactForm add = new AddModifyContactForm(this);
        add.setVisible(true);
    }//GEN-LAST:event_AddRow_ButtonPressed

    public boolean AddRowFromDialog(String firstName, String lastName, String preferredName, String email)
    {
        boolean allowInsert = true;
        for (int i = 0; i < getModel().getRowCount(); i++)
        {
            if (((String)getModel().getValueAt(i, 3)).equalsIgnoreCase(email))
            {
                allowInsert = false;
            }
        }
        if (allowInsert)
        {
            String[] s = {firstName, lastName, preferredName, email};
            getModel().addRow(s);
            contactList.add(new Contact(s[0], s[1], s[2], s[3]));
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean ModifyRowFromDialog(String firstName, String lastName, String preferredName, String email)
    {
        boolean allowModify = true;
        for (int i = 0; i < getModel().getRowCount(); i++)
        {
            if (((String)getModel().getValueAt(i, 3)).equalsIgnoreCase(email) && jTable1.convertRowIndexToModel(jTable1.getSelectedRow()) != i)
            {
                allowModify = false;
            }
        }
        if (allowModify)
        {
            for (int i = 0; i < contactList.size(); i++)
            {
                if(contactList.get(i).getEmailAddress() == email)
                {
                    contactList.remove(i);
                }
            }
            
            getModel().setValueAt(firstName, jTable1.convertRowIndexToModel(jTable1.getSelectedRow()), 0);
            getModel().setValueAt(lastName, jTable1.convertRowIndexToModel(jTable1.getSelectedRow()), 1);
            getModel().setValueAt(preferredName, jTable1.convertRowIndexToModel(jTable1.getSelectedRow()), 2);
            getModel().setValueAt(email, jTable1.convertRowIndexToModel(jTable1.getSelectedRow()), 3);
            
            
            contactList.add(new Contact(firstName, lastName, preferredName, email));

            return true;
        }
        else
        {
            return false;
        }
    }
   
    private void RemoveSelected_ButtonPressed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_RemoveSelected_ButtonPressed
    {//GEN-HEADEREND:event_RemoveSelected_ButtonPressed
        if (jTable1.isEditing()) 
        {
            jTable1.getCellEditor().stopCellEditing();
        }
        
        int row[] = jTable1.getSelectedRows();
        Arrays.sort(row);
        
        contactList.clear();
                
        
        for (int i = row.length; i > 0; i--)
        {
            int convertedRowNumber = jTable1.convertRowIndexToModel(row[i-1]);
            
            /*String first = getModel().getValueAt(convertedRowNumber, 0).toString();
            String last = getModel().getValueAt(convertedRowNumber, 1).toString();
            String preferred = getModel().getValueAt(convertedRowNumber, 2).toString();
            String email = getModel().getValueAt(convertedRowNumber, 3).toString();
            
            Contact c = new Contact(first, last, preferred, email);
            contactList.remove(c);*/
//            for (int j = 0; j < contactList.size(); j++)
//            {
//                if(contactList.get(j).getEmailAddress().equals(getModel().getValueAt(convertedRowNumber, 3).toString()))
//                {
//                    contactList.remove(j);
//                }
//            }
            getModel().removeRow(convertedRowNumber);
            
        }
        for (int i = 0; i < getModel().getRowCount(); i++)
        {
            contactList.add( new Contact(getModel().getValueAt(i, 0).toString(), getModel().getValueAt(i, 1).toString(), getModel().getValueAt(i, 2).toString(), getModel().getValueAt(i, 3).toString()));
        }
    }//GEN-LAST:event_RemoveSelected_ButtonPressed

    private void modifyButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_modifyButtonActionPerformed
    {//GEN-HEADEREND:event_modifyButtonActionPerformed
        // TODO add your handling code here:
        int rowNumber = jTable1.convertRowIndexToModel(jTable1.getSelectedRow());
        DefaultTableModel tm = getModel();
        
        String first = tm.getValueAt(rowNumber, 0).toString();
        String last = tm.getValueAt(rowNumber, 1).toString();
        String preferred = tm.getValueAt(rowNumber, 2).toString();
        String email = tm.getValueAt(rowNumber, 3).toString();
        AddModifyContactForm mod = new AddModifyContactForm(this, first, last, preferred, email);
        mod.setVisible(true);
    }//GEN-LAST:event_modifyButtonActionPerformed

    private void ApplyButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ApplyButtonActionPerformed
    {//GEN-HEADEREND:event_ApplyButtonActionPerformed
       //System.out.println(notificationLabel.getText());
        if(!groupNameTextBox.getText().isEmpty())
        {
            notificationLabel.setVisible(false);
            
            notificationLabel.setForeground(Color.white);
            Thread t = new Thread(new ContactLoop());
            t.start();
        }
        else
        {
            notificationLabel.setText("Please enter a group name.");
            notificationLabel.setForeground(Color.red);
            notificationLabel.setVisible(true);
        }
    }//GEN-LAST:event_ApplyButtonActionPerformed

    
    // http://docs.oracle.com/javase/tutorial/essential/concurrency/simple.html
    private class ContactLoop implements Runnable
    {
        @Override
        public void run()
        {

                //ApiContact.createContactGroup(groupNameTextBox.getText());
            int n = ApiContact.updateOrCreateGroup(groupNameTextBox.getText(), frame);

            if (n == 0)
            {
                ApiContact.overwriteGroup(groupNameTextBox.getText());
                addContacts();
            }
            else if (n == 1)
            {
                ApiContact.createContactGroup(groupNameTextBox.getText());
                addContacts();
            }
            else if (n == 2 || n == 5)
            {
                addContacts();
            }
            
        }
        
        private void addContacts()
        {
            try
            {
                DefaultTableModel tm = getModel();
                jProgressBar1.setMaximum(tm.getRowCount());
                jProgressBar1.setValue(0);
                jProgressBar1.setVisible(true);
                for (int i = 0; i < tm.getRowCount(); i++)
                {
                    System.out.println(tm.getValueAt(i, 3).toString());
                    String email = tm.getValueAt(i, 3).toString();
                    if (!ApiContact.getContactExistence(email))
                    {
                        String first = tm.getValueAt(i, 0).toString();
                        String last = tm.getValueAt(i, 1).toString();
                        String preferred = tm.getValueAt(i, 2).toString();
                        ApiContact.createContact(first, last, preferred, email);
                    }

                    //ApiContact.addContactToGroup(email);
                    ApiContact.buildBatchRequest(email);
                    jProgressBar1.setValue(i+1);
                }
                ApiContact.executeBatchRequest();
                notificationLabel.setText("Success!");
                //System.out.println(notificationLabel.getText());
            }
            catch (Exception ex)
            {
                notificationLabel.setText("Uh oh! Something went wrong. Please try again.");
            }
            
            jProgressBar1.setVisible(false);
            notificationLabel.setVisible(true);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        
        
        
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable()
        {

            public void run()
            {
                new MainForm().setVisible(true);
            }
        });
        
    }
    
    //static DefaultTableModel tm = new DefaultTableModel();
    
    public  void findFiles(final File file) 
    {
        importErrorLabel.setVisible(false);
        
        //System.out.println("FindingFiles" + file.getPath());
        if (file.isDirectory())
        {
            for (File fileEntry : file.listFiles()) 
            {
                //System.out.println(fileEntry.getAbsolutePath());

                //if (fileEntry.isDirectory()) {
                    findFiles(fileEntry);
                //} 
            }
        }
        else if(file.isFile())
        {
            parseContacts(file.getAbsolutePath());
        }
        else
        {
            importErrorLabel.setText(file.toString() + " is not a valid file or folder.");
            importErrorLabel.setVisible(true);
            
        }
    }

    public  void parseContacts(final String filePath)
    {
        // http://docs.oracle.com/javase/tutorial/essential/io/file.html
        Path path = Paths.get(filePath);
        Charset charset = Charset.forName("US-ASCII");
        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
            String line;
            while ((line = reader.readLine()) != null) {
                
                // http://www.informit.com/articles/article.aspx?p=680829&seqNum=8
                StringTokenizer st = new StringTokenizer(line, "\t");
                String token;
                String last = null;
                String first = null;
                String preferred = null;
                String email = null;
                for (int i = 0; i < 10; i++)
                {
                    token = st.nextToken();
                    if (i == 0)
                    {
                        last = token.trim();
                    }
                    else if (i == 1)
                    {
                        first = token.trim();
                    }
                    else if (i == 2)
                    {
                        preferred = token.trim();
                    }
                    else if (i == 9)
                    {
                        email = token.trim();
                    }
                }
                Contact contact = new Contact(first, preferred, last, email);
                boolean newContact = true;
                if(!first.equalsIgnoreCase("First Name"))
                {
                    for (Contact c : contactList)
                    {
                        //System.out.println(c.getEmailAddress());
                        if (c.getEmailAddress().equalsIgnoreCase(email))
                        {
                            newContact = false;
                        }
                    }
                    if(newContact)
                    {
                        contactList.add(contact);
                    }
                }
                
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        catch(Exception e)
        {
            System.err.format("Exception: %s%n", e);
        }
    }
    
//    public void sortList()
//    {
//        Comparator comp = new Comparator(){
//            public int compare(Object o1, Object o2) {
//                Contact c1 = (Contact) o1;
//                Contact c2 = (Contact) o2;
//               return c1.getLastName().compareToIgnoreCase(c2.getLastName());
//            }};
//    
//    
//        Collections.sort(contactList, comp);
//    }
    
    
    public  void printContacts()
    {
        //sortList();
        boolean allowAdd;
        for (Contact c : contactList)
        {
            allowAdd = true;
            for (int i = 0; i < jTable1.getModel().getRowCount(); i++)
            {
                if (jTable1.getModel().getValueAt(i, 3) == c.getEmailAddress())
                {
                    allowAdd = false;
                }
            }
            if (allowAdd)
            {
            String[] s = {c.getFirstName(), c.getLastName(), c.getPreferredName(), c.getEmailAddress()};
            getModel().addRow(s);
            }
        }
    }
    
    private DefaultTableModel getModel()
    {
        return (DefaultTableModel)jTable1.getModel();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ApplyButton;
    private javax.swing.JTextField groupNameTextBox;
    private javax.swing.JLabel importErrorLabel;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton modifyButton;
    private javax.swing.JLabel notificationLabel;
    private javax.swing.JLabel path;
    private javax.swing.JTextField pathTextBox;
    // End of variables declaration//GEN-END:variables
}
