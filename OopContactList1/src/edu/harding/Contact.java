/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.harding;

/**
 *
 * @author T RICH
 */
public class Contact
{
    public Contact(String firstName, String preferredName, String lastName, String emailAddress)
    {
        this.firstName = firstName;
        this.preferredName = preferredName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }
    
    private String firstName;
    private String preferredName;
    private String lastName;
    private String emailAddress;
    
    public String getFirstName()
    {
        return firstName;
    }
    
    public String getPreferredName()
    {
        return preferredName;
    }
    
    public String getLastName()
    {
        return lastName;
    }
    
    
    public String getEmailAddress()
    {
        return emailAddress;
    }
}
