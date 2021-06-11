import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;



public class RLE {
	 public static HashMap<String,String> huffmanTable = new HashMap<>();     //Huffman Table 
	    public static ArrayList<String> nextelement = new ArrayList<>();  // for non zeros values , next element
	    
	    public static String Encode(String input){
	        String Result = "";
	        ArrayList<String> Descriptors = toDescriptors(input);    
	        Huffman H= new Huffman();
	        huffmanTable = H.HuffmanTable(Descriptors);
	        System.out.println("huffman table" + huffmanTable) ; 
	        System.out.println("the Descriptors :" + Descriptors); 
	        for(int i = 0 ; i<Descriptors.size()-1;++i){
	            Result+=( huffmanTable.get(Descriptors.get(i)) + FromDecimaltoBinary(nextelement.get(i)) );
	        }
	        Result += huffmanTable.get(Descriptors.get(Descriptors.size()-1));
	        System.out.println("Encode : " + Result ) ; 
	        return Result;
	    }
	    
	    public static String Decode(String Str){
	        ArrayList<String> des = new ArrayList<>();
	        String Result = "" ;
	        for(int i=0 ; i<Str.length();++i ){
	            String current = "";
	            while(!huffmanTable.containsValue(current)){
	                current+=(Str.charAt(i)+"");
	                i++;
	            }
	            String descriptor = "";
	            for(String S: huffmanTable.keySet()){
	                if(huffmanTable.get(S).equals(current)){
	                    descriptor = S;
	                    des.add(S);
	                    break;
	                }
	            }
	            if(i == Str.length()){
	                return Result+"EOB";
	            }
	            String binary = "";
	            for(int j=0 ; j< Integer.parseInt(descriptor.charAt(descriptor.length()-1) +"");++j ){
	                binary+= (Str.charAt(i)+"");
	                i++;
	            }
	            i--;
	            for(int j=0 ; j< Integer.parseInt(descriptor.charAt(0) +"");++j ){
	                Result+= "0,";
	            }
	            Result+=(frombinarytoDecimal(binary)+",");
	        }
	        return Result;
	    }
	    
	    // this fuctions takes input and divide into descriptor . 
	    public static ArrayList<String> toDescriptors(String input){
	        String[] arr = input.split(",");
	        ArrayList<String> Des = new ArrayList<>();
	        int zeros = 0;
	        for (int i = 0 ; i< arr.length ;++i){
	            zeros = 0;
	            if(arr[i].equals("EOB")){
	                nextelement.add(arr[i]);
	                Des.add("EOB");
	            }
	            else{
	                while (arr[i].equals("0")){
	                    zeros++;
	                    i++;
	                }
	                nextelement.add(arr[i]);
	                Des.add(zeros + "/" + FromDecimaltoBinary(arr[i]).length());
	            }
	        }
	        return Des;
	    }
	    
	    public static String FromDecimaltoBinary(String number){
	        if(number.charAt(0) == '-'){  // 3shan law negative 
	            int decimal = ~Integer.parseInt(FromDecimaltoBinary(number.substring(1)),2);
	            String outputdecimal = Integer.toBinaryString(decimal);
	            return outputdecimal.substring(outputdecimal.indexOf("0", 0));
	        }
	        else{
	        	// law howa mosh negative ha- convert 3ady 
	            return Integer.toBinaryString(Integer.parseInt(number));
	        }
	    }
	    
	    public static String frombinarytoDecimal(String binary){
	        if(binary.charAt(0) == '0'){
	            String outputbianry ="";
	            for (int i = 0 ; i< binary.length() ;++i){
	                if(binary.charAt(i) == '0') 
	                	outputbianry+="1";
	                else   
	                	outputbianry+="0";
	            }
	            return "-"+Integer.parseInt(outputbianry, 2);
	        }
	        else{
	            return Integer.parseInt(binary,2)+"";
	        }
	    }
	    
	    public static void SaveToFiles(String code,HashMap<String,String> arr) throws IOException{
	        FileWriter Writer = new FileWriter("Compression.txt");
	        Writer.write(code);
	        Writer.close();
	        FileWriter Writer2 = new FileWriter("HuffmanTable.txt");
	        String line = "";
	        for(String i : arr.keySet()){
	            Writer2.write(line +i +" "+ arr.get(i));
	            line = "\n";
	        }
	        Writer2.close();
	    }
	    
	    public static HashMap<String,String>  getHuffmantable(){
	        return huffmanTable; 
	    }
	    
	    public static String ReadFromFiles() throws FileNotFoundException{
	        File Obj = new File("Compression.txt");
	        Scanner Reader = new Scanner(Obj);
	        String data = Reader.nextLine();
	        return data;
	    }

}
