/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.harding;

import com.google.gdata.client.Query;
import com.google.gdata.client.batch.BatchInterruptedException;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.batch.BatchOperationType;
import com.google.gdata.data.batch.BatchStatus;
import com.google.gdata.data.batch.BatchUtils;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.contacts.*;
import com.google.gdata.data.extensions.*;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.PreconditionFailedException;
import com.google.gdata.util.ServiceException;
import java.awt.Frame;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author T RICH
 */
public class ApiContact
{
    private static ContactsService myService = null;
    private static boolean loggedIn = false;
    private static String groupID = null;
    // Feed that holds all the batch request entries.
    private static ContactFeed requestFeed = new ContactFeed();
    // Map that holds all current and new contacts, along with an EditUrl
    private static Map<String, String> googleContactsMap = new HashMap<>();
    
    private static Map<String, String> googleGroupMap = new HashMap<>();
    
    private static List<String> contactGroupContents = new ArrayList<>();
    
    public static boolean connectApi(String email, String password)
    {
        
        try
        {
            myService = new ContactsService("Google Contacts Importer");
            myService.setUserCredentials(email, password);
            
            loggedIn = true;
        }
        catch (AuthenticationException ex)
        {
            System.err.println(ex);
        }
        return loggedIn;
    }
    
    public static void fetchAllContacts() throws IOException, ServiceException
    {
        // Request the feed
        URL feedUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full?max-results=10000");
        ContactFeed resultFeed = myService.getFeed(feedUrl, ContactFeed.class);
        
        for (ContactEntry entry : resultFeed.getEntries()) 
        {
            if (entry.hasEmailAddresses())
            {
                for (Email email : entry.getEmailAddresses()) 
                {
                    String ID = entry.getEditLink().getHref();
                    String address = email.getAddress();
                    if (googleContactsMap.get(address) == null)
                    {
                        googleContactsMap.put(address, ID);
                        //System.out.println("Address did not exist. Added. ID is " + ID);
                    }
                    else
                    {
                        //System.out.println("Address did exist. Not added.");
                    }
                }
            
            }
        }
    }       
    
    public static void fetchAllGroups() throws ServiceException, IOException 
    {
        // Request the feed
        URL feedUrl = new URL("https://www.google.com/m8/feeds/groups/default/full?max-results=100");
        ContactGroupFeed resultFeed = myService.getFeed(feedUrl, ContactGroupFeed.class);

        String groupName = null;
        String groupId = null;

        for (ContactGroupEntry groupEntry : resultFeed.getEntries()) 
        {
            //System.out.println("Atom Id: " + groupEntry.getId());
            //System.out.println("Group Name: " + groupEntry.getTitle().getPlainText());

            groupName = groupEntry.getTitle().getPlainText();
            groupId = groupEntry.getId();
            
            googleGroupMap.put(groupName, groupId);

        }
    }
    
    public static int updateOrCreateGroup(String groupName, Frame parent)
    {
        int n;
        if(googleGroupMap.get(groupName) != null)
        {
            //System.out.println("Old Group. Updating.");
            groupID = googleGroupMap.get(groupName);
            
            // http://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
            //Custom button text
            Object[] options = {"Overwrite",
                                "Create a new group",
                                "Add new contacts",
                                "Cancel"};

            n = JOptionPane.showOptionDialog( parent,
                "A group with this name already exists.\n"
                    + "What would you like to do?",
                "Group Already Exists",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);
        }
        else
        {
            createContactGroup(groupName);
            n = 5;
        }
        return n;
    }
    
    public static void overwriteGroup(String groupName)
    {
        String groupID = googleGroupMap.get(groupName);
        
        try {
            // Retrieving the contact group is required in order to get the Etag.
            ContactGroupEntry group = myService.getEntry(new URL(groupID), ContactGroupEntry.class);

            try {
                group.delete();
            } catch (PreconditionFailedException e) {
                // Etags mismatch: handle the exception.
            }
        }
        catch (IOException | ServiceException ex)
        {
            Logger.getLogger(ApiContact.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //System.out.println("Old ID" + groupID);
        googleGroupMap.remove(groupName);
        
        createContactGroup(groupName);
    }
    
    //public static ContactGroupEntry createContactGroup()
    public static void createContactGroup(String name)
    {
        try
        {
            // Create the entry to insert
            ContactGroupEntry newGroup = new ContactGroupEntry();
            newGroup.setTitle(new PlainTextConstruct(name));


            // Ask the service to insert the new entry
            URL postUrl = new URL("https://www.google.com/m8/feeds/groups/default/full");
            ContactGroupEntry createdGroup = myService.insert(postUrl, newGroup);
            groupID = createdGroup.getId();
            googleGroupMap.put(name, groupID);
            
            //System.out.println("New ID" + groupID);
        }
        catch (IOException | ServiceException ex)
        {
            Logger.getLogger(ApiContact.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean getContactExistence(String email)
    {
        if(googleContactsMap.get(email) == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public static void createContact(String first, String last, String preferred, String email)
    {
        try
        {
            // Create the entry to insert.
            ContactEntry contact = new ContactEntry();
            // Set the contact's name.
            Name name = new Name();
            final String NO_YOMI = null;
            name.setFullName(new FullName((preferred + " " + last), NO_YOMI));
            name.setGivenName(new GivenName(first, NO_YOMI));
            name.setFamilyName(new FamilyName(last, NO_YOMI));
            contact.setName(name);
            
            // Set contact's e-mail addresses.
            Email primaryMail = new Email();
            primaryMail.setAddress(email);
            primaryMail.setDisplayName((preferred + " " + last));
            primaryMail.setRel("http://schemas.google.com/g/2005#home");
            primaryMail.setPrimary(true);
            contact.addEmailAddress(primaryMail);
            // Ask the service to insert the new entry
            URL postUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full");
            ContactEntry createdContact = myService.insert(postUrl, contact);
            
            //ContactEntry contact = myService.getEntry(contactURL, ContactEntry.class);
            //System.out.println(groupID);
            
            // Adds the new contact to the map
            googleContactsMap.put(email, createdContact.getEditLink().getHref());
        }
        catch (MalformedURLException ex)
        {
            Logger.getLogger(ApiContact.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }
    
//    public static void addContactToGroup(String email)
//    {
//        try
//        {
//            String ID = googleContactsMap.get(email);
//            ContactEntry contact = myService.getEntry( new URL(ID), ContactEntry.class);
//            System.out.println("Adding" + email + " " + ID);
//            System.out.println("Edit Link: " + contact.getEditLink().getHref());
//            //GroupMembershipInfo info = new GroupMembershipInfo(false, groupID);
//            //info.setHref(groupID);
//            contact.addGroupMembershipInfo(new GroupMembershipInfo(false, groupID));
//            URL editUrl = new URL(contact.getEditLink().getHref());
//            myService.update(editUrl, contact);
//        }
//        catch (IOException | ServiceException ex)
//        {
//            Logger.getLogger(ApiContact.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    
    public static boolean getLoggedInStatus()
    {
        return loggedIn;
    }

    
    public static void buildBatchRequest(String email) throws ServiceException, IOException
    {

        String ID = googleContactsMap.get(email);
        // Retrieve the ContactEntry to update.

        // This call happens too many times. Causes quota to be exceded. 
        ContactEntry updateContact = myService.getEntry(new URL(ID), ContactEntry.class);
        updateContact.addGroupMembershipInfo(new GroupMembershipInfo(false, groupID));
        BatchUtils.setBatchId(updateContact, "update");
        BatchUtils.setBatchOperationType(updateContact, BatchOperationType.UPDATE);
        try
        {
            Thread.sleep((long)50);
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(ApiContact.class.getName()).log(Level.SEVERE, null, ex);
        }

        //System.out.println(email);

        if(requestFeed.getEntries().size()==99)
        {
            executeBatchRequest();
            requestFeed.getEntries().clear();
        }

        // Insert the entries to the batch feed.          
        requestFeed.getEntries().add(updateContact);

        //System.out.println("Adding " + email + " to group");

        //return responseFeed;

    }
    
    public static void executeBatchRequest() throws ServiceException, MalformedURLException, IOException
    {

        // Submit the batch request to the server.
        ContactFeed responseFeed =
            myService.batch(new URL("https://www.google.com/m8/feeds/contacts/default/full/batch"),
                requestFeed);

//            // Check the status of each operation.
//            for (ContactEntry entry : responseFeed.getEntries()) {
//                String batchId = BatchUtils.getBatchId(entry);
//                BatchStatus status = BatchUtils.getBatchStatus(entry);
//                System.out.println(batchId + ": " + status.getCode() + " (" + status.getReason() + ")");
//            }

    }
}
