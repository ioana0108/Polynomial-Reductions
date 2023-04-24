//import check_utils.*;

import javax.management.StringValueExp;
import java.io.*;

class Retele {
    public static void main(String[] args) throws IOException {

        Task1 task1 = new Task1();

        task1.readProblemData();

        try {
            task1.solve();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        task1.formulateOracleQuestion();

        try {
            task1.askOracle();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        task1.decipherOracleAnswer();

        task1.writeAnswer();

    }
}



/*

        // Citirea valorilor pentru N M K din stdin:

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.printf("Da valori pentru N, M and K: ");
        String line1 = reader.readLine();
        String[] split1 = line1.split(" ");
        n = Integer.parseInt(split1[0]);
        m = Integer.parseInt(split1[1]);
        k = Integer.parseInt(split1[2]);

        int NrClauses = 0;
        int NrVariables = n * k;


        // Citirea muchiilor din stdin:

        System.out.printf("Introdu muchiile: ");
        String line = reader.readLine();
        String[] split = line.split(" ");
        for(int i = 0; i < 2*m; i += 2) {
            nod1 = Integer.parseInt(split[i]);
            nod2 = Integer.parseInt(split[i + 1]);

            a[nod1 - 1][nod2 - 1] = 1;
        }



        // Umplerea matricei y[][] pentru a o putea citi Oracolul sau ceva de genul:

        for(int i = 0; i < n; i++)
        {
            //System.out.printf("[");
            for(int j = 0; j < k; j++)
            {
                y[i][j] = nr;
                //System.out.printf("%d ", y[i][j]);
                nr++;
            }
            //System.out.println("]\n");
        }


        FileWriter fw = new FileWriter("sat.cnf");
        PrintWriter pw = new PrintWriter(fw);


        // Umplu sat.cnf

        pw.write("p cnf");
        pw.write(" ");
        pw.write(String.valueOf(NrVariables));
        pw.write(" ");
        pw.write(String.valueOf(NrClauses));
        pw.print("\n");


        // fiecare nod este un posibil element al acoperirii
        pw.write("There must be exactly one vertex <i> in that clique for each <r> between 1 and k:\n");
        for(int r = 0; r < k; r++) {
            for (int i = 0; i < n; i++) {
                pw.write(String.valueOf(y[i][r]));
                pw.write(" ");
            }
            pw.write(String.valueOf(0));
            NrClauses++;
            pw.print("\n");
        }


        // un nod i nu poate fi in acelasi timp si elementul r1 din clica, dar si elementul r2 din clica
        pw.write("A vertex <i> cannot be both the <r1>th vertex in the clique:\n");
        for(int i = 0; i < n; i++) {
            for(int r1 = 0; r1 < k; r1++) {
                for(int r2 = 0; r2 < k; r2++) {
                    if(r1 != r2) {
                        pw.write(String.valueOf(-y[i][r1]));
                        pw.write(" ");
                        pw.write(String.valueOf(-y[i][r2]));
                        pw.write(" ");
                        pw.write(String.valueOf(0));
                        NrClauses++;
                        pw.print("\n");
                    }
                }
            }
        }

        // doua noduri distincte nu pot fi fiecare elementul r din clica
        pw.write("2 different vertices cannot both be the <r>th vertex in the clique:\n");
        for(int r = 0; r < k; r++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(i != j) {
                        {
                            pw.write(String.valueOf(-y[i][r]));
                            pw.write(" ");
                            pw.write(String.valueOf(-y[j][r]));
                            pw.write(" ");
                            pw.write(String.valueOf(0));
                            NrClauses++;
                            pw.print("\n");
                        }
                    }
                }
            }

        }


        // fiecare muchie are cel putin un capat in acoperire
        pw.write("For every non-edge (i, j), <i> and <j> cannot BOTH be in the clique:\n");
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(a[i][j] == 0 && i != j) {
                    for(int r1 = 0; r1 < k; r1++) {
                        for(int r2 = 0; r2 < k; r2++) {
                            if(r1 != r2) {
                                pw.write(String.valueOf(-y[i][r1]));
                                pw.write(" ");
                                pw.write(String.valueOf(-y[j][r2]));
                                pw.write(" ");
                                pw.write(String.valueOf(0));
                                NrClauses++;
                                pw.print("\n");
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Nr clauze: " + NrClauses);

        fw.close();

 */



// Afisare matrice de adiacenta:
/*
        System.out.println("\n");
                System.out.println("Matricea de adiacenta:");
                for(int i = 0; i < n; i++)
        {
        System.out.printf("[ ");
        for(int j = 0; j < n; j++)
        {
        System.out.printf("%d ", a[i][j]);
        }
        System.out.println("]\n");
        }

 */