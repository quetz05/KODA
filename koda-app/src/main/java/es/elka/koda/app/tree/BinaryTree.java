package es.elka.koda.app.tree;

/**
 * Created by Quetz on 2015-12-06.
 */
public class BinaryTree {

    Node root;

    public void addNode(char symbol, int weight)
    {
        Node newNode = new Node(symbol, weight);
        newNode.weight = weight;

        if(root == null)
        {
            root = newNode;
        }
        else
        {
            Node focusNode = root;
            Node parent;

            while(true)
            {
                parent = focusNode;

                if(weight < focusNode.weight)
                {
                    focusNode = focusNode.leftChild;
                    if(focusNode == null)
                    {
                        parent.leftChild = newNode;
                        return;
                    }
                }
                else
                {
                    focusNode = focusNode.rightChild;
                    if(focusNode == null)
                    {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }


    }

    public Node findNode(char symbol)
    {
        return findNode(symbol, root);
    }

    private Node findNode(char symbol, Node node)
    {
        if(node != null)
        {
            if(node.symbol == symbol)
                return node;
            else
            {
                Node foundNode = findNode(symbol,node.leftChild);
                if(foundNode == null)
                    foundNode = findNode(symbol,node.rightChild);
                return foundNode;
            }
        }
        else
            return null;
    }

}
