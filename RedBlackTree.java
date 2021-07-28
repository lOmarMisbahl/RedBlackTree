public class RedBlackTree {
    private Node root;
    private int size;
    private int height;
    private static final int R=0;
    private static final int B=1;

    public RedBlackTree() {
        this.root = null;
        this.height=-1;
        this.size=0;
    }

    private Node insertBST(Node root,Node node,Node parent){
        if(root==null){
            node.parent=parent;
            root=node;
            this.size++;
        }
        else if(node.word.compareTo(root.word)>0){
            root.rightNode = insertBST(root.rightNode,node,root);
        }
        else if(node.word.compareTo(root.word)<0){
            root.leftNode = insertBST(root.leftNode,node,root);
        }
        return root;
    }


    public void insert(Node node){
        int prevSize = this.size;
        this.root=insertBST(this.root,node,null);
        if(prevSize==this.size){
            System.out.println("Error word already exists!\n");
            return;
        }
        if(this.root.color == R){
            this.root.changeColor();
        }
        checkUncle(node);
        this.height = updateHeight(root);
        //System.out.println("Tree Size: "+this.size+"\n"+
        //"Tree Height: "+this.height +"\n"+ "word: "+node.word +"\n");
    }

    private void checkUncle(Node node){
        if(node==this.root || node.parent.color==B){
            return;
        }
        Node uncle=node.getUncle();
        if(uncle==null || uncle.color==B){
            bCase(node);
        }

        else if(uncle.color==R){
            rCase(node);
        }
    }

    private void rCase(Node node){
        Node grandpa=node.parent.parent;
        Node uncle = node.getUncle();
        uncle.changeColor();
        node.parent.changeColor();
        grandpa.changeColor();
        if(this.root.color==R){
            this.root.changeColor();
        }
        checkUncle(grandpa);
    }

    private void bCase(Node node){
        Node grandpa = node.parent.parent;
        if(grandpa.leftNode == node.parent && grandpa.leftNode.leftNode == node){  //left left
            node.parent.changeColor();
            grandpa.changeColor();
            Node x =rotateRight(grandpa);
            if(x.parent==null){
                root=x;
            }

        }
        else if(grandpa.leftNode == node.parent && grandpa.leftNode.rightNode == node){  //left right
            Node x = rotateLeft(node.parent);
            x.changeColor();
            x.parent.changeColor();
            Node y = rotateRight(x.parent);
            if(y.parent==null){
                root=y;
            }

        }
        else if(grandpa.rightNode == node.parent && grandpa.rightNode.rightNode == node){  //right right
            node.parent.changeColor();
            grandpa.changeColor();
            Node x =rotateLeft(grandpa);
            if(x.parent==null){
                root=x;
            }

        }
        else if(grandpa.rightNode == node.parent && grandpa.rightNode.leftNode == node){  //right left
            Node x = rotateRight(node.parent);
            x.changeColor();
            x.parent.changeColor();
            Node y = rotateLeft(x.parent);
            if(y.parent==null){
                root=y;
            }
        }
    }

    private Node rotateRight(Node g){
        Node temp = g.parent;
        Node p = g.leftNode;
        Node T3 = p.rightNode;
        if(temp!=null){
            if(g==temp.leftNode)
                temp.leftNode=p;
            else
                temp.rightNode=p;
        }

        p.rightNode = g;
        g.parent = p;
        g.leftNode = T3;
        if(T3!=null) {
            T3.parent = g;
        }
        p.parent = temp;

        return p;

    }

    private Node rotateLeft(Node g){
        Node temp = g.parent;
        Node p = g.rightNode;
        Node T3 = p.leftNode;
        if(temp!=null){
            if(g==temp.leftNode)
                temp.leftNode=p;
            else
                temp.rightNode=p;
        }

        p.leftNode = g;
        g.parent = p;
        g.rightNode = T3;
        if(T3!=null) {
            T3.parent = g;
        }
        p.parent = temp;

        return p;
    }

    private int updateHeight(Node root){
        return root==null ? -1 : 1 + Math.max(updateHeight(root.leftNode),updateHeight(root.rightNode));
    }



    public void inOrderTraversal(Node node){
        if (node == null)
            return;
        inOrderTraversal(node.leftNode);
        System.out.print(node.word+/*" "+ node.color+*/"\n");
        inOrderTraversal(node.rightNode);
    }


    public int search(Node root,String word){
        if(root==null){
            return 0;
        }
        else if(word.compareTo(root.word)==0){
            return 1;
        }
        else if(word.compareTo(root.word)<0){
            return search(root.leftNode,word);
        }
        else {
            return search(root.rightNode,word);
        }
    }


    public Node getRoot() {
        return root;
    }


    public int getSize() {
        return size;
    }
}