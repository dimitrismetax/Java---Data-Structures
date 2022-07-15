
/** ***********************************************************
 * @file   Java.java
 *
 * @author Nikolaos Batsaras <batsaras@csd.uoc.gr> * * @brief Source file for
 * the needs of cs-240a project 2018 *
 * **********************************************************
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    static BinarySearchTree tree1 = new BinarySearchTree();//R gegonos GA soldiers
    static BinarySearchTree tree2 = new BinarySearchTree();//P gegonos GA maxh
    static Node root1 = null;//root of tree1
    static Node temproot1 = null;//root of tree1
    static NodeGA root2 = null;//root of tree2
    static PriorityQueue pq = new PriorityQueue();
    static int N, k, c, hash;
    static int M, counter, bb,ll;
    public final static int TABLE_SIZE = 3391;
    static GA_Battle tempkey;

    public static class Army {

        int id;
        Army rc;
        Army lc;

        Army(int i) {
            this.id = i;
        }

        public int getid() {
            return id;
        }

        public String toString() {
            String s = getid() + " ";
            return s;
        }
    }

    public static class Horse {

        int id;
        int age;

        Horse(int i, int a) {
            this.id = i;
            this.age = a;
        }

        public String toString() {
            return "<" + id + ", " + age + ">, ";
        }
    }

    public static class HorsePQ {

        int size;
        Horse HorsePQ; // array that should be allocated dynamically 
    }

    public static class GA_Battle {

        int soldier_id;
        int horse_id;
        GA_Battle lc;
        GA_Battle rc;

        GA_Battle(int i, int a) {
            this.soldier_id = i;
            this.horse_id = a;
        }

    }

    public static class GA_Τree {

        int size;
        GA_Battle GA_tree;
    }

    public static class AR_Battle {

        int id;
        int type;
        AR_Battle next;

        public int getid() {
            return id;
        }

        public int gettype() {
            return type;
        }

        AR_Battle(int key) {
            this.id = key;
        }

        AR_Battle(int key, int value) {
            this.id = key;
            this.type = value;
        }
    }

    public static AR_Battle[] hash_table = new AR_Battle[TABLE_SIZE];
    int max_soldiers_g;
    int max_soldiers_id_g;

    public static class Node {

        Node() {
        }
        Army key;
        Node leftc, rightc;

        public Node(Army key) {
            this.key = key;
            leftc = rightc = null;
        }
    }

    public static class NodeGA {

        NodeGA() {
        }
        GA_Battle GAkey;
        NodeGA GAleftc, GArightc;

        public NodeGA(GA_Battle key) {
            this.GAkey = key;
            GAleftc = GArightc = null;
        }
    }

    public static class HashMap {

        HashMap() {
            hash_table = new AR_Battle[TABLE_SIZE];
            for (int i = 0; i < TABLE_SIZE; i++) {
                hash_table[i] = null;
            }
        }
    }

    public static int get(int key) {
        int hash = (key % TABLE_SIZE);
        while (hash_table[hash] != null && hash_table[hash].getid() != key) {
            hash = (hash + 1) % TABLE_SIZE;
        }
        if (hash_table[hash] == null) {
            return -1;
        } else {
            return hash_table[hash].getid();
        }
    }

    public static void put(int key) {
        AR_Battle ab = new AR_Battle(key);
        hash = (int) (key % TABLE_SIZE);
        hash_table[hash] = ab;

        //while (hash_table[hash] != null && hash_table[hash].getid() != key) 
        //    hash = (hash + 1) % TABLE_SIZE; 
        //hash_table[hash] = new AR_Battle(key, value); 
    }

    public static int choice(int key) {
        //AR_Battle ab = new AR_Battle(key);
        int ab;
        ab = (int) (key % TABLE_SIZE);
        //hash_table[hash] = ab;

        //while (hash_table[hash] != null && hash_table[hash].getid() != key) 
        //    hash = (hash + 1) % TABLE_SIZE; 
        //hash_table[hash] = new AR_Battle(key, value); 
        return ab;
    }

    public static class BinarySearchTree {

        public Node root;

        public BinarySearchTree() {
            root = null;
        }

    }

    public static Node insert(Node root, Army sol) {
        /* If the tree is empty, return a new node */
        if (root == null) {
            root = new Node(sol);
            return root;
        }
        /* Otherwise, recur down the tree */
        if (sol.id < root.key.id) {
            root.leftc = insert(root.leftc, sol);
        } else if (sol.id > root.key.id) {
            root.rightc = insert(root.rightc, sol);
        }

        /* return the (unchanged) node pointer */
        return root;
    }

    public static NodeGA insertGA(NodeGA root, GA_Battle ga) {
        /* If the tree is empty, return a new node */
        if (root == null) {
            root = new NodeGA(ga);
            return root;
        }
        /* Otherwise, recur down the tree */
        if (ga.soldier_id < root.GAkey.soldier_id) {
            root.GAleftc = insertGA(root.GAleftc, ga);
        } else if (ga.soldier_id > root.GAkey.soldier_id) {
            root.GArightc = insertGA(root.GArightc, ga);
        }

        /* return the (unchanged) node pointer */
        return root;
    }

    public static void split(Node root){
        System.out.print("General 0= ");
        inordera(root1);
        System.out.println("");
        System.out.print("General 1= ");
        inorderb(root1);
        System.out.println("");
        System.out.print("General 2= ");
        inorderc(root1);
        System.out.println("");
        System.out.print("General 3= ");
        inorderd(root1);
        System.out.println("");
        System.out.print("General 4= ");
        inordere(root1);
        System.out.println("");
    }
    public static void inorder(Node root) {
        if (root != null) {
            inorder(root.leftc);
            System.out.print("<" + root.key.id + ">, ");
            inorder(root.rightc);
        }
    }
    public static void inordera(Node root) {
        if (root != null) {
            inordera(root.leftc);
            if(root.key.id < 500){
                System.out.print("<" + root.key.id + ">, ");
            }
            inordera(root.rightc);
        }
    }
    public static void inorderb(Node root) {
        if (root != null) {
            inorderb(root.leftc);
            if((root.key.id >= 500)&&(root.key.id<1000)){
                System.out.print("<" + root.key.id + ">, ");
            }
            inorderb(root.rightc);
        }
    }
    public static void inorderc(Node root) {
        if (root != null) {
            inorderc(root.leftc);
            if((root.key.id >= 1000)&&(root.key.id<1500)){
                System.out.print("<" + root.key.id + ">, ");
            }
            inorderc(root.rightc);
        }
    }
    public static void inorderd(Node root) {
        if (root != null) {
            inorderd(root.leftc);
            if((root.key.id >= 1500)&&(root.key.id<2000)){
                System.out.print("<" + root.key.id + ">, ");
            }
            inorderd(root.rightc);
        }
    }
    public static void inordere(Node root) {
        if (root != null) {
            inordere(root.leftc);
            if(root.key.id >= 2000){
                System.out.print("<" + root.key.id + ">, ");
            }
            inordere(root.rightc);
        }
    }
    
    
    

    public static void inordertrav(Node root) {
        int m, n;

        if (root != null) {
            inordertrav(root.leftc);
            m = root.key.id;
            if (r >= 0) {
                n = arr[r].id;
            } else {
                n = -1;
            }
            GA_Battle GB = new GA_Battle(m, n);
            root2 = insertGA(root2, GB);
            r--;
            //System.out.println(r);
            inordertrav(root.rightc);
        }
    }

    public static void inorderGA(NodeGA root) {
        if (root != null) {
            inorderGA(root.GAleftc);
            if (root.GAkey != null) {
                System.out.print("<" + root.GAkey.soldier_id + "," + root.GAkey.horse_id + ">, ");
            }
            inorderGA(root.GArightc);
        }
    }
    public static void inorderGA2(NodeGA root) {
        if (root != null) {
            inorderGA2(root.GAleftc);
            if (root.GAkey != null) {
                //System.out.print("<" + root.GAkey.soldier_id + "," + root.GAkey.horse_id + ">, ");
            }
            inorderGA2(root.GArightc);
        }
    }

    public static void traverseGA(NodeGA root, int x) { // Each child of a tree is a root of its subtree.
        counter++;
        if (root != null) {
            if (root.GAleftc != null) {
                traverseGA(root.GAleftc, x);
            }
            if (counter == x) {
                root.GAkey = null;
                counter = 0;
            }
            if (root != null) {
                if (root.GArightc != null) {
                    traverseGA(root.GArightc, x);
                }
            }
        }
    }

    public static Horse[] arr;
    public static int nitems;
    public static int r = -1;

    public static class PriorityQueue {

        private int MAX;

        public PriorityQueue() {
            MAX = 2000;
            arr = new Horse[MAX];
            nitems = 0;
        }

        public void insert(Horse ho) {
            int i;
            if (nitems == 0) {
                arr[0] = ho;
                nitems++;
                r++;
                return;
            }
            for (i = nitems - 1; i >= 0; i--) {
                if (ho.age > arr[i].age) {
                    arr[i + 1] = arr[i];
                } else {
                    break;
                }
            }
            arr[i + 1] = ho;
            nitems++;
            r++;
        }

        public void printPriorityQueue() {
            k = 0;
            for (int i = 0; i < nitems; i++) {
                if (i == (Math.pow(2, k) - 1)) {
                    System.out.println("");
                    k++;
                    c = k - 1;
                    System.out.print("Level " + c + ": ");
                }
                System.out.print(arr[i] + " ");
            }
            System.out.println("");
            System.out.println("");
        }
    }

    /*
	 * TODO:
	 *
	 * 1) You need to create the classes that correspond to the C structs
	 * 2) You need to create the global variables as in the C header
     */
    /**
     * @brief Optional function to initialize data structures that need
     * initialization
     *
     * @return true on success false on failure
     */
    public static boolean initialize() {
        return true;
    }

    /**
     * @brief Register Alexander the Great soldier
     *
     * @param sid The soldier's id
     * @return True on success False on failure
     */
    public static boolean register_GA_soldier(int sid) {
        Army soldier = new Army(sid);
        root1 = insert(root1, soldier);
        System.out.print("GA soldiers: ");
        inorder(root1);
        return true;
    }

    /**
     * @brief Register Alexander the Great horse
     *
     * @param sid The horse's id
     * @param age The horese's age
     *
     * @return True on success False on failure
     */
    public static boolean register_GA_horse(int hid, int age) {
        Horse ho = new Horse(hid, age);
        pq.insert(ho);
        pq.printPriorityQueue();
        return true;
    }

    /**
     * @brief Register Satrapy soldier
     * @param aid The soldier's id
     *
     * @return True on success False on failure
     */
    public static boolean register_AR_soldier(int aid) {
        AR_Battle ar = new AR_Battle(aid);
        put(aid);
        int k = 0;
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (hash_table[i] != null) {
                k++;
                System.out.println("Index " + i + ": <" + hash_table[i].id + ">");
            }
        }
        System.out.println(k);
        return true;
    }

    /**
     * @brief Prepare Alexander's soldiers for battle
     *
     * @return True on success False on failure
     */
    public static boolean prepare_for_battle_GA() {
        inordertrav(root1);
        System.out.print("GA battle= ");
        inorderGA(root2);
        while (arr[M] != null) {
            arr[M] = null;
            M++;
        }
        return true;
    }

    /**
     * @brief The ambush against Alexander the Great
     *
     * @param X Kill 1-every-X soldiers
     * @return True on success False on failure
     */
    public static boolean ambush_GA(int X) {
        System.out.print("GA battle= ");
        traverseGA(root2, X);
        inorderGA(root2);
        return true;
    }

    /**
     * @brief Victory of Alexander the Great's army
     *
     * @return True on success False on failure
     */
    public static boolean GA_victory() {
        if (root2 != null) {
            inorderGA2(root2.GAleftc);
            if (root2.GAkey != null) {
                bb = choice(root2.GAkey.soldier_id);
                while(ll<3){
                    if(hash_table[bb]!=null){
                        hash_table[bb]=null;
                        ll++;
                        bb++;
                    }
                    else bb++;
                }
                //System.out.print("<" + root2.GAkey.soldier_id + "," + root2.GAkey.horse_id + ">, ");
            }
            inorderGA2(root2.GArightc);
        }
        int k = 0;
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (hash_table[i] != null) {
                k++;
                System.out.println("Index " + i + ": <" + hash_table[i].id + ">");
            }
        }
        System.out.println(k);
        return true;
    }

    /**
     * @brief The death of Alexander the Great
     *
     * @return True on success False on failure
     */
    public static boolean Alexanders_death() {
        split(root1);
        return true;
    }

    /**
     * @brief Print all soldiers of Alexander the Great
     *
     * @return True on success False on failure
     */
    public static boolean print_GA_soldiers() {
        System.out.print("GA soldiers: ");
        inorder(root1);
        return true;
    }

    /**
     * @brief Print all horses of Alexander the Great
     *
     * @return True on success False on failure
     */
    public static boolean print_GA_horses() {
        pq.printPriorityQueue();
        return true;
    }

    /**
     * @brief Print Alexander the Great army
     *
     * @return True on success False on failure
     */
    public static boolean print_GA_army() {
        System.out.print("GA battle= ");
        inorderGA(root2);
        return true;
    }

    /**
     * @brief Print all soldiers of Alexander the Great
     *
     * @return True on success False on failure
     */
    public static boolean print_AR_army() {
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (hash_table[i] != null) {
                k++;
                System.out.println("Index " + i + ": <" + hash_table[i].id + ">");
            }
        }
        return true;
    }

    /**
     * @brief The main function
     *
     * @param argc Number of arguments
     * @param argv Argument vector
     *
     * @return True on success False on failure
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader inputFile;
        String line;
        String[] params;

        /* Check command buff arguments */
        if (args.length != 1) {
            System.err.println("Usage: <executable-name> <input_file>");
            System.exit(0);
        }

        /* Open input file */
        inputFile = new BufferedReader(new FileReader(args[0]));

        int max_horses = Integer.parseInt(inputFile.readLine());
        System.out.println("Max horses: " + max_horses);

        int max_soldiers_g = Integer.parseInt(inputFile.readLine());
        System.out.println("Max enemy soldiers: " + max_soldiers_g);

        int max_soldiers_id_g = Integer.parseInt(inputFile.readLine());
        System.out.println("Max enemy soldiers' ID: " + max_soldiers_id_g);

        /* Read input file and handle the events */
        while ((line = inputFile.readLine()) != null) {

            if (line.length() == 0) {
                continue;
            }

            System.out.println(">>> Event: " + line);
            params = line.split(" ");
            char eventID = line.charAt(0);

            switch (eventID) {

                /* Comment */
                case '#':
                    break;

                /* Register GA soldier
					 * R <sid> <type> <general> */
                case 'R': {
                    int sid = Integer.parseInt(params[1]);

                    if (register_GA_soldier(sid)) {
                        System.out.println("R " + sid + " succeeded");
                    } else {
                        System.err.println("R " + sid + " failed");
                    }

                    break;
                }

                /* Register GA horse
					 * H <hid> <type> <general> */
                case 'H': {
                    int hid = Integer.parseInt(params[1]);
                    int age = Integer.parseInt(params[2]);

                    if (register_GA_horse(hid, age)) {
                        System.out.println("H " + hid + " " + age + " succeeded");
                    } else {
                        System.err.println("H " + hid + " " + age + " failed");
                    }

                    break;
                }
                /* Register Satrapy soldier
					 * A <sid> <aid> <type> <general> */
                case 'A': {
                    int aid = Integer.parseInt(params[1]);

                    if (register_AR_soldier(aid)) {
                        System.out.println("A " + " " + aid + " succeeded");
                    } else {
                        System.err.println("A " + " " + aid + " failed");
                    }

                    break;
                }

                /* Prepare Alexander's soldiers for battle
					 * P */
                case 'P': {
                    if (prepare_for_battle_GA()) {
                        System.out.println("P succeeded");
                    } else {
                        System.err.println("P failed");
                    }

                    break;
                }

                /* Ambush against Alexander the Great
					 * T <X> */
                case 'T': {
                    int X = Integer.parseInt(params[1]);

                    if (ambush_GA(X)) {
                        System.out.println("T " + X + " succeeded");
                    } else {
                        System.err.println("T " + X + " failed");
                    }

                    break;
                }

                /* Victory of Alexander the Great's army
					 * K */
                case 'K': {
                    if (GA_victory()) {
                        System.out.println("V succeeded");
                    } else {
                        System.err.println("V failed");
                    }

                    break;
                }

                /* The death of Alexander the Great
					 * D */
                case 'D': {
                    if (Alexanders_death()) {
                        System.out.println("D succeeded");
                    } else {
                        System.err.println("D failed");
                    }

                    break;
                }

                /* Print all soldiers of Alexander the Great
					 * X */
                case 'X': {
                    if (print_GA_soldiers()) {
                        System.out.println("X succeeded");
                    } else {
                        System.err.println("X failed");
                    }

                    break;
                }

                /* Print all Satrapy soldiers
					 * Y */
                case 'Y': {
                    if (print_GA_horses()) {
                        System.out.println("Y succeeded");
                    } else {
                        System.err.println("Y failed");
                    }

                    break;
                }
                /* Print of Alexander the Great army
				 * Z */
                case 'Z': {
                    if (print_GA_army()) {
                        System.out.println("Z succeeded");
                    } else {
                        System.err.println("Z failed");
                    }

                    break;
                }
                /* Print of Alexander the Great army
				 * W */
                case 'W': {
                    if (print_AR_army()) {
                        System.out.println("W succeeded");
                    } else {
                        System.err.println("W failed");
                    }

                    break;
                }

                /* Empty line */
                case '\n':
                    break;

                /* Ignore everything else */
                default:
                    System.out.println("Ignoring " + line);
                    break;
            }
        }
    }
}
