public class Driver{
    public static void main(){
        GreenEggsAndHam e = new GreenEggsAndHam();
        System.out.println("GreenEggsAndHam.txt Compression By GreenEggsAndHam");
        try{
          e.fullText = e.getText("GreenEggsAndHam.txt");
          System.out.println("Hash Of File: " + e.toHexString(e.getSHA(e.fullText)));
          System.out.println("Original Character Count: " + e.countCharacters());
        }catch(Exception ex){
           System.out.println("ERROR: FILE NOT FOUND");
        }    
        e.setDictionary();
        e.hardEncode();
        System.out.println("Reduced Character Count: " + e.countCharacters());
        System.out.println("Compression Rate Percentage: " + ((double)e.countCharacters()/3254)*100 + "%");
        e.hardDecode();
        try{
            System.out.println("Hash Of Decompressed File: " + e.toHexString(e.getSHA(e.fullText)));
        }catch(Exception ex){
            System.out.println("Hash Error");
        }  
        System.out.println();
        
        GeneralTextFile t = new GeneralTextFile();
        System.out.println("GreenEggsAndHam.txt Compression By HuffmanTree");

        try{
           t.fullText = t.getText("GreenEggsAndHam.txt");
                System.out.println("Hash Of File: " + t.toHexString(t.getSHA(t.fullText)));
                System.out.println("Original Bit Count: " + t.fullText.length()*8);
            }catch(Exception ex){
                System.out.println("ERROR: FILE NOT FOUND");
            }  
            t.ht = new HuffmanTree(t.getTree(t.fullText));
            t.treeEncode();
            System.out.println("Reduced Bit Count: " + t.rText.length());
            int i = t.rText.length() + t.ht.getDictionaryLength(t.ht.root);
            System.out.println("Compression Rate Percentage:  " +  (double)i/(t.fullText.length()*8)*100 + "%");
            
            try{
                System.out.println("Decoded Hash: " + t.toHexString(t.getSHA(t.treeDecode())));
            }catch(Exception ex){
                System.out.println("Hash Error");
            } 
           
        System.out.println(); 
        
        GeneralTextFile tt = new GeneralTextFile();
        System.out.println("generaltext.txt Compression By HuffmanTree");

        try{
           tt.fullText = tt.getText("generaltext.txt");
                System.out.println("Hash Of File: " + tt.toHexString(tt.getSHA(tt.fullText)));
                System.out.println("Original Bit Count: " + tt.fullText.length()*8);
            }catch(Exception ex){
                System.out.println("ERROR: FILE NOT FOUND");
            }  
            tt.ht = new HuffmanTree(tt.getTree(tt.fullText));
            tt.treeEncode();
            System.out.println("Reduced Bit Count: " + tt.rText.length());
            long l = tt.rText.length() + tt.ht.getDictionaryLength(tt.ht.root);
            System.out.println("Compression Rate Percentage:  " +  (double)l/(tt.fullText.length()*8)*100 + "%");
            try{
                System.out.println("Decoded Hash: " + tt.toHexString(tt.getSHA(tt.treeDecode())));
            }catch(Exception ex){
                System.out.println("Hash Error");
            } 
           
        
    }    
}
