import java.util.*;
public class HuffmanTree{
    Node root;
    public HuffmanTree(Node n){
       root = n;
    }   
    public void printCode(Node root, String s) { 
        if (root.left == null && root.right == null && root.ch != "-") { 
            System.out.println(root.ch + ":" + s); 
            return; 
        } 
        printCode(root.left, s + "0"); 
        printCode(root.right, s + "1"); 
    } 
    public void getCode(Node root, String s, Hashtable code) { 
        if (root.left == null && root.right == null && root.ch != "-") { 
            code.put(root.ch,s);
            return; 
        } 
        getCode(root.left, s + "0", code); 
        getCode(root.right, s + "1", code); 
    } 
    public String encode(Node root, String s){
        Hashtable<String,String> code = new Hashtable<String,String>();
        this.getCode(root, "", code);
        String str = "";
        Enumeration<String> keys = code.keys();
        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            s = s.replaceAll(key,code.get(key));
        }  
        return s;
    }  
    public String decode(String s){
        String str = "";
        while(s.length() != 0){
            String q = decode(root, s, 0);
            if(q.length() == 0){
                System.out.println(s);
                System.out.println(q);
                return str;
            }    
            String[] strA = q.split(":");
            str += strA[0];              
            s = s.substring(Integer.parseInt(strA[1]));
        }    
        return str;
    }    
    public String decode(Node temp, String s, int i){
        String str = "";
        if (temp.left == null && temp.right == null && temp.ch != "-") { 
            return temp.ch + ":" + (i);
        } 
        if(s.substring(0,1).equals("0")){
            str += decode(temp.left,s.substring(1), i+1);
        }else if(s.substring(0,1).equals("1")){
            str += decode(temp.right,s.substring(1), i+1);
        } 
        return str ;
    } 
    public int getDictionaryLength(Node root){
        int i =0;
        Hashtable<String,String> code = new Hashtable<String,String>();
        this.getCode(root, "", code);
        Enumeration<String> keys = code.keys();
        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            i += key.length()*8;
            i += code.get(key).length();
        } 
        return i;
    }    
}
