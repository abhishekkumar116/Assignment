package Assesment;

//Your CRM Software has a module for Contacts and another for Orders/Deals. Both modules have been receiving data from 2021,
// but the orders are not in sync with their respective contacts in the 'Contacts' Module.
//        Write a sample piece of code in whatever language you please to sync all Deals from 2021 to present day (2023) with
//        the Contacts present in the system. (Preferably, use C++)

import java.util.HashMap;
import java.util.Map;

// It contains all contact info
class Contact{
    int id;
    String name;
    public Contact(int id, String name){
        this.id = id;
        this.name = name;
    }
}
// It contains all deals
class Deals{
     int id;
     int contactId;
     String dealName;

     public Deals(int id, int contactId, String dealName){
         this.id = id;
         this.contactId = contactId;
         this.dealName = dealName;
     }
}

public class CRMSoftware {
    public static void main(String[] args) {
        HashMap<Integer, Contact> contacts = new HashMap<>();
        // creating a synchronize contact with id and name

        contacts.put(1, new Contact(1, "Abhishek"));
        contacts.put(2, new Contact(2, "Ankit"));
        contacts.put(3, new Contact(3, "Rahul"));

        HashMap<Integer, Deals> deals = new HashMap<>();
        // creating a synchronize deals with id, contact id and deal name

        deals.put(100, new Deals(100, 1, "Deal 1"));
        deals.put(101, new Deals(101, 2, "Deal 2"));
        deals.put(102, new Deals(102, 3, "Deal 3"));

        // mapping over deal map to check that id is present in contact or not and printing accordingly
        for(Map.Entry<Integer, Deals> entrySet : deals.entrySet()){
            int dealId = entrySet.getKey();
            Deals deal = entrySet.getValue();

            if (contacts.containsKey(deal.contactId)){
                Contact contact = contacts.get(deal.contactId);
                System.out.println("Synchronizing deal "+deal.id + " with contact "+contact.name);
            }else{
                System.out.println("No contact found for this deal "+deal.id);
            }
        }
    }
}
