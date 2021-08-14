package gr.uop;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Structure {

    Map<String, String> treeMap = new TreeMap<>();

    public Structure() {
        //Filling the treeMap
        set("-1eksw","Πλύσιμο εξωτερικό",7);
        set("-1mesa","Πλύσιμο εσωτερικό",6);
        set("-1ekswMesa","Πλύσιμο εξωτ.+εσωτ.",12);
        set("-1ekswSpecial","Πλύσιμο εξωτ. σπέσιαλ",9);
        set("-1mesaSpecial","Πλύσιμο εσωτ. σπέσιαλ",8);
        set("-1ekswMesaSpecial","Πλύσιμο εξωτ. + εσωτ. σπέσιαλ",15);
        set("-1biologikos","Βιολογικός καθαρισμός εσωτ.",80);
        set("-1kerwmaGualisma","Κέρωμα‐Γυάλισμα",80);
        set("-1kinhthras","Καθαρισμός κινητήρα",20);
        set("-1sasi","Πλύσιμο σασί",3);

        set("0eksw","Πλύσιμο εξωτερικό",8);
        set("0mesa","Πλύσιμο εσωτερικό",7);
        set("0ekswMesa","Πλύσιμο εξωτ.+εσωτ.",14);
        set("0ekswSpecial","Πλύσιμο εξωτ. σπέσιαλ",10);
        set("0mesaSpecial","Πλύσιμο εσωτ. σπέσιαλ",9);
        set("0ekswMesaSpecial","Πλύσιμο εξωτ. + εσωτ. σπέσιαλ",17);
        //set("0biologikos","Βιολογικός καθαρισμός εσωτ.",80);
        set("0kerwmaGualisma","Κέρωμα‐Γυάλισμα",90);
        //set("0kinhthras","Καθαρισμός κινητήρα",20);
        //set("0sasi","Πλύσιμο σασί",3);

        set("1eksw","Πλύσιμο εξωτερικό",6);
        set("1ekswSpecial","Πλύσιμο εξωτ. σπέσιαλ",8);
        set("1kerwmaGualisma","Κέρωμα‐Γυάλισμα",40);
        set("1kinhthras","Καθαρισμός κινητήρα",10);
    }

    //Return value
    public String getValue(String code) {
        Set<Map.Entry<String, String>> entries = treeMap.entrySet();
        for (var e : entries) {
            if(code.equals(e.getKey())) {
                return  e.getValue();
            }
        }
        return "";
    }

    //Return code
    public String getCode(String value) {
        Set<Map.Entry<String, String>> entries = treeMap.entrySet();
        for (var e : entries) {
            if(value.equals(e.getValue())) {
                return  e.getKey();
            }
        }
        return "";
    }

    //Put input into TreeMap
    public void set(String code, String service, int cost) {
        if(treeMap.containsKey(code) == false) {
            treeMap.put(code, service + " " + cost);
        }
        else {
            System.out.println("Given code already exists.");
        }
    }
    
    //Print TreeMap
    public void print() {
        Set<Map.Entry<String, String>> entries = treeMap.entrySet();
        for (var e : entries) {
            System.out.println(e.getKey()  + " -> " + e.getValue());
        }  
    }
}