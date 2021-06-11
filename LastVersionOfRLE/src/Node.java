public class Node {
	
	public Node Parent ; 
	public Node Left ; 
	public Node Right ; 
	
	public String data , code ;
	public Integer Probability ; 
	public Node()
	{
		Parent = null ;
		Left = Right = null ; 
		data = "" ; 
		code = "" ;
	}
	public Node(String c, int p, Node l, Node r) {
        this.data = c;
        this.Probability = p;
        Left = l;
        Right = r;
    }

    public boolean isLeaf() {
        return Left == null && Right == null;
    }
}