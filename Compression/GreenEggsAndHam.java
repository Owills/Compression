import java.io.*;
import java.util.*;
import java.math.BigInteger;  
import java.nio.charset.StandardCharsets; 
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;   
public class GreenEggsAndHam{
    public String fullText;
    public ArrayList<String> dictionary;
    public GreenEggsAndHam(){
        dictionary = new ArrayList<String>();
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
    public long countCharacters(){
        long StrLength = (long)fullText.length();
        long dicLength = (long)0;
        for (int i = 0; i < dictionary.size(); i ++)
            dicLength += dictionary.get(i).length();
        return StrLength + dicLength;
    }    
    public void setDictionary(){
        dictionary.add("0");
        dictionary.add("That ");
        dictionary.add("1");
        dictionary.add("Sam-I-am\n");
        
        dictionary.add("2");
        dictionary.add("green eggs and ham\n");
      
        
        dictionary.add("5");
        dictionary.add("And I will eat them ");
        dictionary.add("6");
        dictionary.add("And I would eat them");
        dictionary.add("8");
        dictionary.add("I will not eat them");
        dictionary.add("B");
        dictionary.add("I would not eat them");
        dictionary.add("#");
        dictionary.add("Would you eat them");
        dictionary.add("<");
        dictionary.add("Eat them ");
        dictionary.add("q");
        dictionary.add("I will eat them ");
        
        
        dictionary.add("9");
        dictionary.add("I do not like them");
        dictionary.add("Q");
        dictionary.add("You may like them");
        dictionary.add("7");
        dictionary.add("I would not like them");
        dictionary.add("4");
        dictionary.add("Would you like them");
        
        dictionary.add("j");
        dictionary.add("I do not like");
        
        dictionary.add(">");
        dictionary.add("Thank you");
        
        dictionary.add("&");
        dictionary.add("here or there\n");
        dictionary.add("`");
        dictionary.add("anywhere\n");
        
        dictionary.add("F");
        dictionary.add("with a mouse");
        dictionary.add("R");
        dictionary.add("with a goat\n");
        dictionary.add(",");
        dictionary.add("with a fox\n");
        
        dictionary.add("J");
        dictionary.add("in a box");
        dictionary.add("P");
        dictionary.add("n a car");
        dictionary.add("G");
        dictionary.add("in a house");
        dictionary.add("!");
        dictionary.add("in a tree");
        
        dictionary.add("U");
        dictionary.add("in the dark");
        dictionary.add("V");
        dictionary.add("in the rain\n");
        
        dictionary.add("/");
        dictionary.add("on a train\n");
        dictionary.add("X");
        dictionary.add(" a boat\n");
        
        dictionary.add("K");
        dictionary.add("Would you could you");
        dictionary.add(";");
        dictionary.add("I would not could not");
        dictionary.add("M");
        dictionary.add("I could not would not");
        dictionary.add("_");
        dictionary.add("Could you would you");
        
        dictionary.add("~");
        dictionary.add(" train");
        dictionary.add("Z");
        dictionary.add("Try them");
        
        dictionary.add("z");
        dictionary.add("Not ");
        
        dictionary.add("x");
        dictionary.add("so good ");
        dictionary.add(":");
        dictionary.add(" will not");
        dictionary.add("ê");
        dictionary.add("And ");
        
        dictionary.add("ë");
        dictionary.add("I am");
        dictionary.add("è");
        dictionary.add("Sam");
        dictionary.add("ï");
        dictionary.add("et me be");
        dictionary.add("î");
        dictionary.add("ould you ");
        dictionary.add("ì");
        dictionary.add("You ");
        dictionary.add("æ");
        dictionary.add("do not like");
        dictionary.add("ô");
        dictionary.add("you may");
        dictionary.add("ö");
        dictionary.add("ere");
        dictionary.add("ò");
        dictionary.add("will ");
        dictionary.add("û");
        dictionary.add("you");
        dictionary.add("ù");
        dictionary.add("like");
        dictionary.add("ÿ");
        dictionary.add(" and ");
        dictionary.add("â");
        dictionary.add("ay ");
        dictionary.add("ä");
        dictionary.add("see");
        dictionary.add("à");
        dictionary.add(" do ");
        dictionary.add("å");
        dictionary.add(" them ");
        dictionary.add("ç");
        dictionary.add(" say");
        dictionary.add("Ç");
        dictionary.add("\n");
        dictionary.add("3");
        dictionary.add("hey are");
    }   
    public void hardEncode(){
        for (int i = 1; i < dictionary.size(); i +=2){
            fullText = fullText.replaceAll(dictionary.get(i),dictionary.get(i-1));
        }    
    }  
    public void hardEncode(String newFile){
        for (int i = 1; i < dictionary.size(); i +=2){
            fullText = fullText.replaceAll(dictionary.get(i),dictionary.get(i-1));
        }    
        try{
            File copyOfFile =  new File(newFile);
            FileWriter writing = new FileWriter(copyOfFile);
            BufferedWriter bw = new BufferedWriter(writing);
            bw.write(fullText);
            bw.close();
        } catch(Exception e){
            System.out.println("error");
        }  
    }    
    public void hardDecode(){
        for (int i = dictionary.size()-1; i >= 0; i -=2){
            fullText = fullText.replaceAll(dictionary.get(i-1),dictionary.get(i));
        }    
    } 
    public void hardDecode(String newFile){
        for (int i = dictionary.size()-1; i >= 0; i -=2){
            fullText = fullText.replaceAll(dictionary.get(i-1),dictionary.get(i));
        }  
        try{
            File copyOfFile =  new File(newFile);
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

