package es.elka.koda.app.tree;
import java.util.List;

/**
 * Created by Quetz on 2015-12-06.
 *
 * Klasa drzewa, przygotowana specjalnie dla algorytmu adaptacyjnego Huffmana.
 * Węzły drzewa posiadają maksymalnie dwóch potomków.
 * Każdy węzeł, który nie jest liściem ma indeks 0 i nie ma przypisanego żadnego symbolu.
 *
 */
public class HuffmanTree {

    private Node root;


    /**
     * Metoda zwracająca korzeń drzewa
     * @return korzeń drzewa
     */
    public final Node getRoot()
    {
        return root;
    }

    public void addNode(Node newNode)
    {
        if(root == null)
        {
            root = newNode;
        }
        else
        {
            Node focusNode = root;
            Node parent;


            Node smallestWeightNode = findNode(1);

//            while(true)
//            {
//                parent = focusNode;
//
//                if(newNode.weight < focusNode.weight)
//                {
//                    focusNode = focusNode.leftChild;
//                    if(focusNode == null)
//                    {
//                        parent.leftChild = newNode;
//                        return;
//                    }
//                }
//                else
//                {
//                    focusNode = focusNode.rightChild;
//                    if(focusNode == null)
//                    {
//                        parent.rightChild = newNode;
//                        return;
//                    }
//                }
//            }
        }
    }

    /**
     * Metoda szukająca węzła na podstawie indexu
     *
     * @param index indeks szukanego węzła
     * @return
     */
    public Node findNode(int index)
    {
        return findNode(index, root);
    }

    private Node findNode(int index, Node node)
    {
        if(node != null)
        {
            if(node.index == index)
                return node;
            else
            {
                Node foundNode = findNode(index,node.leftChild);
                if(foundNode == null)
                    foundNode = findNode(index,node.rightChild);
                return foundNode;
            }
        }
        else
            return null;
    }

    /**
     * Metoda szukająca ścieżki do konkretnego węzła w drzewie, ropoczynając od korzenia.
     * Opiera się o przeszukiwanie wgłąb. Złożoność czasowa wynosi O(n).
     *
     * @param beginNode węzeł od którego przeszukujemy wgłąb
     * @param searchedNode szukany węzeł
     * @param path lista ze ścieżką do węzła (zawierająca kolejne korzenie począwszy od korzenia)
     *
     * @return true jeśli ścieżka do węzła została znaleziona (węzeł istnieje), false w przeciwnym wypadku
     */
    public Boolean getPath(Node beginNode, Node searchedNode, List<Node> path)
    {
        if(beginNode == null)
            return false;

        if(beginNode == searchedNode ||
                getPath(beginNode.leftChild, searchedNode, path) ||
                getPath(beginNode.rightChild, searchedNode, path))
            path.add(root);

        return false;
    }

}
