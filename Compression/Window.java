import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 
import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import java.awt.dnd.*;  
class Window extends JFrame implements ItemListener, ActionListener, KeyListener{ 
    private static Choice c; 
    private static JFrame f; 
    private static GridBagConstraints gc;
    private static JPanel p;
    private static Label l; 
    private static Label l2; 
    private static Label lname; 
    private static JTextField t; 
    private static JTextField t2; 
    private static Label output1;
    private static Label output2;
    private static Label output3;
    private static Label output4;
    private static Label output5;
    private static JButton b; 
    Window() { 
    } 
    public static void main() { 
        //set up interface
        f = new JFrame("Compression"); 
        //main pannel
        Window pl = new Window(); 
        p = new JPanel(); 
        // create wordlist object
        //text box
        t = new JTextField(16);
        t.addKeyListener(pl);
        t2 = new JTextField(16);
        t2.addKeyListener(pl);
        
        //layout manager
        p.setLayout(new GridBagLayout()); 
        gc = new GridBagConstraints(); 
        
        //submit button
        b = new JButton("ENTER"); 
        b.addActionListener(pl);
        
        //top choice box
        c = new Choice(); 
        c.add("Huffman Compression"); 
        c.add("Green Eggs And Ham Compression"); 
        c.add("Huffman Decompression"); 
        c.add("Green Eggs And Ham Decompression"); 
        c.addItemListener(pl); 
        //instruction labels
        l = new Label(); 
        l.setText(" Enter Original File Name: ");
        l2 = new Label(); 
        l2.setText(" Enter New File Name: ");
        //output display
        output1 = new Label("");
        output2 = new Label("");
        output3 = new Label("");
        output4 = new Label("");
        output5 = new Label("");
        
        //horizontal alignment
        gc.anchor = GridBagConstraints.WEST;
        gc.fill = GridBagConstraints.VERTICAL;
        
        gc.gridwidth = 1;
        gc.gridheight = 1;
        
        signupSetup();
        f.add(p); 

        f.show(); 
        f.setSize(1000, 500); 
        f.setResizable(false);
    } 
    // change selected item
    public void itemStateChanged(ItemEvent e) { 
        t.setText("");
        t2.setText("");
        output1.setText("");
        output2.setText("");
        output3.setText("");
        output4.setText("");
        output5.setText("");
        f.add(p); 
        f.show(); 
    } 
    //submit button on click
    public void actionPerformed(ActionEvent e) { 
        enter();
    } 
    //submit button with enter key
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
             enter();
        }  
    }
    //statisfying abstract class, but do nothing
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    //enter logic
    public static void enter(){
       output1.setText("");
       output1.setSize(1000,10);
       output2.setText("");
       output2.setSize(1000,10);
       output3.setText("");
       output3.setSize(1000,10);
       output4.setText("");
       output4.setSize(1000,10);
       output5.setText("");
       output5.setSize(1000,10);
       if(t.getText().length() == 0 || t2.getText().length() == 0){
           output1.setText("Enter Text File Names");
       }else if(c.getSelectedItem().equals("Huffman Compression")){
           GeneralTextFile e = new GeneralTextFile();
           try{
                e.fullText = e.getText(t.getText());
                output1.setText("Hash Of File: " + e.toHexString(e.getSHA(e.fullText)));
                output2.setText("Original Bit Count: " + e.fullText.length()*8);
            }catch(Exception ex){
                output1.setText("ERROR: FILE NOT FOUND");
            }  
            e.ht = new HuffmanTree(e.getTree(e.fullText));
            e.treeEncode(t2.getText());
            output3.setText("Reduced Bit Count: " + e.rText.length());
            int i = e.rText.length() + e.ht.getDictionaryLength(e.ht.root);
            output4.setText("Compression Rate Percentage:  " +  (double)i/(e.fullText.length()*8)*100 + "%");
            
            try{
                output5.setText("Decoded Hash: " + e.toHexString(e.getSHA(e.treeDecode())));
            }catch(Exception ex){
                output1.setText("Hash Error");
            }  
       }else if(c.getSelectedItem().equals("Green Eggs And Ham Compression")){
           GreenEggsAndHam e = new GreenEggsAndHam();
           
           try{
                e.fullText = e.getText(t.getText());
                output1.setText("Hash Of File: " + e.toHexString(e.getSHA(e.fullText)));
                output2.setText("Original Character Count: " + e.countCharacters());
            }catch(Exception ex){
                output1.setText("ERROR: FILE NOT FOUND");
            }    
            long i = e.countCharacters();
            e.setDictionary();
            e.hardEncode(t2.getText());
            output3.setText("Reduced Character Count: " + e.countCharacters());
            output4.setText("Compression Rate Percentage: " + ((double)e.countCharacters()/i)*100 + "%");
            e.hardDecode();
            try{
                output5.setText("Hash Of Decompressed File: " + e.toHexString(e.getSHA(e.fullText)));
            }catch(Exception ex){
                output1.setText("Hash Error");
            }  
       }else if(c.getSelectedItem().equals("Green Eggs And Ham Decompression")){
           GreenEggsAndHam e = new GreenEggsAndHam();
           try{
                e.fullText = e.getText(t.getText());
           }catch(Exception ex){
                output1.setText("ERROR: FILE NOT FOUND");
           } 
           e.setDictionary();
           e.hardDecode(t2.getText());
           try{
                output1.setText("Hash Of Decompressed File: " + e.toHexString(e.getSHA(e.fullText)));
           }catch(Exception ex){
                output1.setText("Hash Error");
           }  
       }else{
           GeneralTextFile e = new GeneralTextFile();
           try{
                e.fullText = e.getText(t.getText());
           }catch(Exception ex){
                output1.setText("ERROR: FILE NOT FOUND");
           } 
           e.treeFileDecode(t2.getText());
           try{
                output1.setText("Hash Of Decompressed File: " + e.toHexString(e.getSHA(e.fullText)));
           }catch(Exception ex){
                output1.setText("Hash Error");
           } 
       }    
       
       t.setText("");
       t2.setText("");
    }    
    public static void signupSetup(){
        p.removeAll();
        gc.gridx = 0; 
        gc.gridy = 0; 
        gc.ipadx = 0; 
        gc.ipady = 1; 
        p.add(c,gc); 
        
        gc.gridx = 0; 
        gc.gridy = 1; 
        p.add(l,gc); 
        
        gc.gridx = 0; 
        gc.gridy = 2; 
        p.add(t,gc);
        
        gc.gridx = 0; 
        gc.gridy = 5; 
        p.add(l2,gc); 
        
        gc.gridx = 0; 
        gc.gridy = 6; 
        p.add(t2,gc);
        
        gc.gridx = 1; 
        gc.gridy = 9; 
        p.add(b,gc);
        
        gc.gridx = 0; 
        gc.gridy = 10; 
        p.add(output1,gc);
        
        gc.gridx = 0; 
        gc.gridy = 11; 
        p.add(output2,gc);
        
        gc.gridx = 0; 
        gc.gridy = 12; 
        p.add(output3,gc);
        
        gc.gridx = 0; 
        gc.gridy = 13; 
        p.add(output4,gc);
        
        gc.gridx = 0; 
        gc.gridy = 14; 
        p.add(output5,gc);
    } 
} 