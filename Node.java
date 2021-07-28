public class Node {
    private static final int B=1;
    private static final int R=0;
    public String word;
    public int color=R;
    public Node rightNode;
    public Node leftNode;
    public Node parent;

    public Node(Node parent,String word) {
        this.word=word;
        rightNode=null;
        leftNode=null;
        this.parent=parent;
    }

    public void changeColor(){
        if(this.color==R){
            this.color=B;
        }
        else{
            this.color=R;
        }
    }

    public Node getUncle(){
        Node grandpa = this.parent.parent;
        Node uncle =null;
        if(this.parent ==grandpa.leftNode){
            uncle=grandpa.rightNode;
        }
        else if(this.parent ==grandpa.rightNode){
            uncle=grandpa.leftNode;
        }
        return uncle;
    }

}
