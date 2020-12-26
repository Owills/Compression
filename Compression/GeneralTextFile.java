
import java.io.*;
import java.util.*;
import java.math.BigInteger;  
import java.nio.charset.StandardCharsets; 
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;
public class GeneralTextFile{
    public String fullText;
    public String rText;
    public HuffmanTree ht;
    public GeneralTextFile(){
    }
    public String getText(String f) throws Exception{
        String s = "";
        File newFile = new File(f);
        FileReader reading = new FileReader(newFile);
        BufferedReader br = new BufferedReader(reading);
        String str; 
        while ((str = br.readLine()) != null) {
           s += str + "\n";
        }
        br.close(); 
        return s.trim();
    } 
    public Node getTree(String s){
        String tempText = s;
        ArrayList<Node> chars = new ArrayList<Node>();
        while (tempText.length() != 0){
           chars.add(this.getMostCommonChar(tempText));
           tempText = tempText.replaceAll(chars.get(chars.size()-1).ch, "");
           
        }    
        while (chars.size() > 1){
            Node left = chars.get(chars.size()-1);
            chars.remove(chars.size()-1);
            Node right = chars.get(chars.size()-1);
            chars.remove(chars.size()-1);
            Node n = new Node();
            n.ch = "-";
            n.data = left.data+right.data;
            n.left = left;
            n.right = right;
            for(int i = chars.size()-1; i >= 0; i --){
                if(n.data < chars.get(i).data){
                    chars.add(i+1,n);
                    i = -1;
                } else if (i == 0){
                    chars.add(i,n);
                }    
            }   
            if(chars.size() == 0){
                chars.add(n);
            }    
        } 
        return chars.get(0);
    } 
    public Node getMostCommonChar(String s){
        int num = 0;
        int highestNum = 0;
        String c= "" + (char)32;
        String highestC = "" + (char)32;
        for (int i = 0; i <=127; i ++){
            c = "" + (char)i;
            for(int j = 0; j < s.length(); j ++){
                if (s.substring(j,j+1).equals(c)){
                    num ++ ;  
                }    
            }  
            if (num > highestNum){
                highestNum = num;
                highestC = c;
                
            }
            num = 0;
        }  
        c = "\n";
        for(int j = 0; j < s.length(); j ++){
             if (s.substring(j,j+1).equals(c))
                 num ++ ;  
        }  
        if (num > highestNum){
            highestNum = num;
            highestC = c;
        }  
        Node n = new Node();
        n.data = highestNum;
        n.ch = highestC;
        return n;
    }    
    public void treeEncode(){
        rText = ht.encode(ht.root,fullText);
    } 
    public void treeEncode(String s){
        rText = ht.encode(ht.root,fullText);
        Hashtable<String,String> code = new Hashtable<String,String>();
        ht.getCode(ht.root, "", code);
        String str = "";
        Enumeration<String> keys = code.keys();
        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            str += key + code.get(key) + "~";
        }  
        str += "DICTIONARY\n";
        try{
            File copyOfFile =  new File(s);
            FileWriter writing = new FileWriter(copyOfFile);
            BufferedWriter bw = new BufferedWriter(writing);
            bw.write(str + rText);
            bw.close();
        } catch(Exception e){
            System.out.println("error");
        }  
    } 
    public String treeDecode(){
        String temp = ht.decode(rText);
        return temp;
    } 
    public String treeDecode(String s){
        String temp = ht.decode(rText);
        try{
            File copyOfFile =  new File(s);
            FileWriter writing = new FileWriter(copyOfFile);
            BufferedWriter bw = new BufferedWriter(writing);
            bw.write(temp);
            bw.close();
        } catch(Exception e){
            System.out.println("error");
        }  
        return temp;
    }
    public void treeFileDecode(String s){
        String[] strA = fullText.split("DICTIONARY\n"); 
        String[] dictionary = strA[0].split("~");
        String str = "";
        int k = 1;
        for (int i =0; i <strA[1].length(); i += k-1){
              Boolean found = false;
              k = 1;
              while(!found){
                 String temp = strA[1].substring(i,i+k);
                  for(int j = 0; j <dictionary.length; j++){
                      int num = dictionary[j].indexOf(temp);
                      if(num == 1 && num+k == dictionary[j].length()){
                          found = true;
                          str += dictionary[j].substring(0,1);
                          j = dictionary.length;  
                      }  else if(num == 2 && num+k == dictionary[j].length() && dictionary[j].indexOf("\n") != -1){
                          found = true;
                          str += dictionary[j].substring(0,2);
                          j = dictionary.length; 
                     }
                 }    
                  k++;
              }    
        }  
        fullText = str;
        try{
            File copyOfFile =  new File(s);
            FileWriter writing = new FileWriter(copyOfFile);
            BufferedWriter bw = new BufferedWriter(writing);
            bw.write(fullText);
            bw.close();
        } catch(Exception e){
            System.out.println("error");
        }  
    }
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {  
        MessageDigest md = MessageDigest.getInstance("SHA-256"); 
        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    } 
    public static String toHexString(byte[] hash) { 
        BigInteger number = new BigInteger(1, hash);  
        StringBuilder hexString = new StringBuilder(number.toString(16));  
        while (hexString.length() < 32)  {  
            hexString.insert(0, '0');  
        }  
        return hexString.toString();  
    } 
}
