/*NAME          :SWAPNA MUKKAMALLA
  DATE          :10-28-2019
  COURSE        :IT-516 DATA STRUCTURES AND ALGORITHMS
  HOMEWORK      :HW06
  TITLE         :Testing speed efficiency of symbol table implementations
  DESCRIPTION   :This application takes the input size of test, trail number,
                 minimum,maximum string length from command prompt and generate random
                 strings and tests the put , get and del method efficiency  using stop watch .*/

import edu.princeton.cs.algs4.*;

public class bstHashTester {

    // method to generate random strings
    public static String[] randomString(int n, int min, int max) {
        String[] s = new String[n];   // to store strings
        for (int i = 0; i < n; i++) {
            // generating string
            char[] arr = new char[StdRandom.uniform(min, max + 1)];
            for (int j = 0; j < arr.length; j++) {
                //generating random letters
                arr[j] = ((char) StdRandom.uniform(65, 90));
            }
            s[i] = new String(arr);
        }
        return s;
    }


    public static void main(String[] args) {
        Double[] ptime = {0.0, 0.0, 0.0, 0.0};   // to store put method times
        Double[] gtime = {0.0, 0.0, 0.0, 0.0};   // to store get method times
        Double[] dtime = {0.0, 0.0, 0.0, 0.0};   // to store del method times

        int size = Integer.parseInt(args[0]);      //Number of random numbers generated for each test

        int trialno = Integer.parseInt(args[1]);   //Number of trials

        int min = Integer.parseInt(args[2]);       //minimum word size

        int max = Integer.parseInt(args[3]);       //maximum word size

        String[] symtab = new String[4];   // Array to store different symbol table implementations
        String[] s = new String[size];   // to store strings
        symtab[0] = "BSST";
        symtab[1] = "RBBST";
        symtab[2] = "SCHST";
        symtab[3] = "LPHST";


        StdOut.println("Testing  (" + size + "  X  " + trialno + ") " + min + "-" + max + "  letter words...");


        //for each trail generating random strings
        //testing put, get and del functions for four symbol table implementations
        // calculating the time for each function in each trail
        for (int t = 0; t < trialno; t++) {


            BinarySearchST<String, Integer> test01 = new BinarySearchST<String, Integer>();
            RedBlackBST<String, Integer> test02 = new RedBlackBST<String, Integer>();
            SeparateChainingHashST<String, Integer> test03 = new SeparateChainingHashST<String, Integer>();
            LinearProbingHashST<String, Integer> test04 = new LinearProbingHashST<String, Integer>();

            s = randomString(size, min, max);

            //--------------------------bsst

            Stopwatch tpbsst = new Stopwatch();
            //inserting key and values to BinarySearch ST
            for (int i = 0; i < s.length; i++) {
                String key = s[i];
                test01.put(key, i);
            }
            ptime[0] += tpbsst.elapsedTime();  // put time BSST

            Stopwatch tgbsst = new Stopwatch();
            for (String l : test01.keys()) {
                int o = test01.get(l);
            }
            gtime[0] += tgbsst.elapsedTime(); // get time BSST


            Stopwatch tdbsst = new Stopwatch();
            for (String l : test01.keys()) {
                test01.delete(l);
            }
            dtime[0] += tdbsst.elapsedTime();  // del time BSST


            //--------------------------RBBST

            Stopwatch tprbbsst = new Stopwatch();
            //inserting key and values to Red black B ST
            for (int i = 0; i < s.length; i++) {
                String key = s[i];
                test02.put(key, i);
            }
            ptime[1] += tprbbsst.elapsedTime();  // put time RBBST


            Stopwatch tgrbbsst = new Stopwatch();
            for (String l : test02.keys()) {
                int o = test02.get(l);
            }
            gtime[1] += tgrbbsst.elapsedTime();  // get time RBBST


            Stopwatch tdrbbsst = new Stopwatch();
            for (String l : test02.keys()) {
                test02.delete(l);
            }
            dtime[1] += tdrbbsst.elapsedTime();  // del time RBBST

            //--------------------------schst

            Stopwatch tpschst = new Stopwatch();
            //inserting key and values to separate chaining H ST
            for (int i = 0; i < s.length; i++) {
                String key = s[i];
                test03.put(key, i);
            }
            ptime[2] += tpschst.elapsedTime();  // put time SCHST


            Stopwatch tgschst = new Stopwatch();
            for (String l : test03.keys()) {
                int o = test03.get(l);
            }
            gtime[2] += tgschst.elapsedTime(); // get time SCHST


            Stopwatch tdschst = new Stopwatch();
            for (String l : test03.keys()) {
                test03.delete(l);
            }
            dtime[2] += tdschst.elapsedTime();  // del time SCHST

            //--------------------------lphst

            Stopwatch tplphst = new Stopwatch();
            //inserting key and values to Linear probing hash ST
            for (int i = 0; i < s.length; i++) {
                String key = s[i];
                test04.put(key, i);
            }
            ptime[3] += tplphst.elapsedTime();  // put time for lphst


            Stopwatch tglphst = new Stopwatch();
            for (String l : test04.keys()) {
                int o = test04.get(l);
            }
            gtime[3] += tglphst.elapsedTime(); // get time lphst


            Stopwatch tdlphst = new Stopwatch();
            for (String l : test04.keys()) {
                test04.delete(l);
            }
            dtime[3] += tdlphst.elapsedTime();  // del time lphst

        }


        //Displaying the all symbol table names.
        StdOut.print("        ");
        for (int i = 0; i <= symtab.length - 1; i++) {
            StdOut.print(symtab[i]);
            if (symtab[i].length() == 4) {
                System.out.print("     ");
            } else {
                System.out.print("    ");
            }

        }

        //Displaying put functions time for all symbol table implementations
        StdOut.println();
        StdOut.print("Put");
        StdOut.print("     ");
        for (int i = 0; i < ptime.length; i++) {

            String t = Double.toString(ptime[i]);
            String strDouble = String.format("%.3f", ptime[i]);
            System.out.print(strDouble);
            if (t.length() < 5) {
                for (int j = 0; j < 5 - t.length(); j++) {
                    System.out.print(" ");
                }

            } else {
                StdOut.print("    ");
            }


        }
        //Displaying get functions time for all symbol table implementations
        StdOut.println();
        StdOut.print("get");
        StdOut.print("     ");
        for (int i = 0; i < gtime.length; i++) {
            String t = Double.toString(ptime[i]);
            String strDouble = String.format("%.3f", gtime[i]);
            System.out.print(strDouble);
            if (t.length() < 5) {
                for (int j = 0; j < 5 - t.length(); j++) {
                    System.out.print(" ");
                }

            } else {
                StdOut.print("    ");
            }


        }

//Displaying del functions time for all symbol table implementations
        StdOut.println();
        StdOut.print("del");
        StdOut.print("     ");
        for (int i = 0; i < dtime.length; i++) {
            String t = Double.toString(ptime[i]);

            String strDouble = String.format("%.3f", dtime[i]);
            System.out.print(strDouble);
            if (t.length() < 5) {
                for (int j = 0; j < 5 - t.length(); j++) {
                    System.out.print(" ");
                }

            } else {
                StdOut.print("    ");
            }


        }

        StdOut.println();
    }


}


