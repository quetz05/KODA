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
            root = newNode;
        else
        {
            Node focusNode = root;
            Node parent;

            while(true)
            {
                parent = focusNode;

                if(weight < focusNode.weight)
                {
                    

                }

            }


        }


    }

    public BinaryTree()
    {

    }


}
