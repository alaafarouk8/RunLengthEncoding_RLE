import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Huffman implements Comparator<Node> {
    public static HashMap<String,Integer> map = new HashMap<String,Integer>();
    public static String code;
    // this function calculate the function of the string . 
    public static void CalculatetheSequences(ArrayList<String> Str){
        for(int i = 0; i < Str.size(); i++){
            String Str1 = Str.get(i);
            // if en howa exist so +1 
            if(map.containsKey(Str1)){
                map.put(Str1,map.get(Str1)+1);
            }
            else{
            	
                map.put(Str1,1);
            }
        }
        System.out.println("the probability :" + map) ; 
    }
    public static void Setcode(Node root){
    	
        if(root == null){
            return;
        }
        if(root.Parent == null){
            root.Left.code = "1";
            if (root.Right!=null){root.Right.code = "0";}
            Setcode(root.Left);
            Setcode(root.Right);
        }
        else{
            if(root.Left!=null){
                root.Left.code = root.code + "1";
                Setcode(root.Left);
            }
            if(root.Right!=null){
                root.Right.code = root.code + "0";
                Setcode(root.Right);
            }
        }
    }



    public static void GetCode(Node root,String str){
        if(root == null){
            return;
        }
        if (root.data.equals(str)){
            code = root.code;
        }
        else{
            GetCode(root.Left, str);
            GetCode(root.Right, str);
        }
    }

    public HashMap<String,String> HuffmanTable (ArrayList<String>Str){
        Node root = new Node();
        ArrayList<Node> array = new ArrayList<>();
        HashMap<String,String> Result = new HashMap<>();
        CalculatetheSequences(Str);
        for (Map.Entry<String,Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            Node node = new Node();
            node.data =  key;
            node.Probability = value;
            System.out.println(" key " + key + ":" + " value " + value );
         //   System.out.println(node) ; 
            array.add(node);
        }
   /*     System.out.println("Array Size " + array.size()) ; 
        for (Node num : array) { 		      
            System.out.println(num); 		
       } */
        while(array.size()>2){
        	Collections.sort(array, new Huffman() ) ; 
       // 	System.out.println("after Sorting" + array ) ; 
            Node node = new Node();
            node.Left = array.get(0); // Returns the element at the specified position in this list
            node.Right = array.get(1);
            node.Probability = node.Left.Probability + node.Right.Probability;
            System.out.println("the probability : " + node.Probability) ; 
            node.Left.Parent = node;
            node.Right.Parent = node;
            array.remove(0);
            array.remove(0);
            array.add(node);
        }
        
        if(array.size()==2){
            root.Left= array.get(0);
            root.Right = array.get(1);
            root.Left.Parent = root;
            root.Right.Parent = root;
            array.remove(0);
            array.remove(0);
        }else{          
            root.Left = array.get(0);
            root.Left.Parent = root;
            array.remove(0);
        }
        
        
        Setcode(root);
        for(int i=0;i<Str.size();i++) {
            String c = Str.get(i);
            GetCode(root, c);
            Result.put(c, code);
        }
        return Result;
    }

	@Override
	public int compare(Node o1, Node o2) {
		// TODO Auto-generated method stub
        return o1.Probability.compareTo(o2.Probability);
	}

}
