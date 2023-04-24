import java.io.*;
import java.util.ArrayList;


public class Task2 extends Task{

    Integer n, m, NrClauses = 0;
    int[][] a = new int[50][50]; // matricea de adiacenta
    int[][] y = new int[50][50]; // matricea de variabile
    ArrayList<Integer> VertexCover = new ArrayList<>();
    ArrayList<Integer> IsolatedNodes = new ArrayList<>();

    int k = 1;
    int VCoverSize = 0;

    @Override
    public void readProblemData() throws IOException {

        int nod1 = 0, nod2 = 0;

        // Citirea valorilor pentru N M din stdin:

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String line1 = reader.readLine();
        String[] split1 = line1.split(" ");
        n = Integer.parseInt(split1[0]);
        m = Integer.parseInt(split1[1]);


        // Citirea muchiilor din stdin:

        for(int i = 0; i < m; i++) {
            String line = reader.readLine();

            String[] split = line.split(" ");

            nod1 = Integer.parseInt(split[0]);
            nod2 = Integer.parseInt(split[1]);

            a[nod1 - 1][nod2 - 1] = 1;
            a[nod2 - 1][nod1 - 1] = 1;
        }

    }


    @Override
    public void solve() throws IOException, InterruptedException {

        int nr = 1;
        NrClauses = 0;

        // Umplerea matricei y[][] pentru a o putea citi Oracolul sau ceva de genul:

        for(int i = 0; i < n; i++)
        {
            for(int r = 0; r < k; r++)
            {
                y[i][r] = nr;
                nr++;
            }
        }


        // Calulez numarul de clauze:

        // aceasta clauza forteaza ca exista macar un nod pentru fiecare element al acoperirii
        for(int r = 0; r < k; r++) {
            NrClauses++;
        }


        // aceasta clauza forteaza ca un nod se afla cel mult o data in acoperire (pe o singura pozitie, nu poate fi pe mai multe)
        for(int i = 0; i < n; i++) {
            for(int r1 = 0; r1 < k; r1++) {
                for(int r2 = 0; r2 < k; r2++) {
                    if(r1 != r2) {
                        NrClauses++;
                        //pw.write(String.valueOf(0));
                    }
                }
            }
        }


        // aceasta clauza forteaza ca oricare muchie sa aiba cel putin un capat în acoperire
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if(a[i][j] == 1) {
                    NrClauses++;
                    //pw.write(String.valueOf(0));
                }
            }
        }


        // aceasta clauza forteaza ca o pozitie sa nu poata fi ocupata de 2 noduri diferite
        for(int r = 0; r < k; r++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(i != j) {
                        {
                            NrClauses++;
                            //pw.write(String.valueOf(0));
                        }
                    }
                }
            }
        }

    }


    @Override
    public void formulateOracleQuestion() throws IOException {

        int NrVariables = n * k;

        FileWriter fw = new FileWriter("sat.cnf");
        PrintWriter pw = new PrintWriter(fw);

        // Scrierea in sat.cnf:

        pw.write("p cnf");
        pw.write(" ");
        pw.write(String.valueOf(NrVariables));
        pw.write(" ");
        pw.write(String.valueOf(NrClauses));


        //aceasta clauza forteaza ca exista macar un nod pentru fiecare element al acoperirii
        for(int r = 0; r < k; r++) {
            pw.print("\n");
            for (int i = 0; i < n; i++) {
                pw.write(String.valueOf(y[i][r]));
                pw.write(" ");
            }
            pw.write(String.valueOf(0));
        }


        // aceasta clauza forteaza ca un nod se afla cel mult o data in acoperire (pe o singura pozitie, nu poate fi pe mai multe)
        for(int i = 0; i < n; i++) {
            for(int r1 = 0; r1 < k; r1++) {
                for(int r2 = 0; r2 < k; r2++) {
                    if(r1 != r2) {
                        pw.print("\n");
                        pw.write(String.valueOf(-y[i][r1]));
                        pw.write(" ");
                        pw.write(String.valueOf(-y[i][r2]));
                        pw.write(" ");
                        pw.write(String.valueOf(0));
                    }
                }
            }
        }


        // aceasta clauza forteaza ca oricare muchie sa aiba cel putin un capat în acoperire
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if(a[i][j] == 1) {
                    pw.print("\n");
                    for(int r = 0; r < k; r++) {
                        pw.write(String.valueOf(y[i][r]));
                        pw.write(" ");
                    }
                    for(int r = 0; r < k; r++) {
                        pw.write(String.valueOf(y[j][r]));
                        pw.write(" ");
                    }
                    pw.write(String.valueOf(0));
                }
            }
        }


        // aceasta clauza forteaza ca o pozitie sa nu poata fi ocupata de 2 noduri diferite
        for(int r = 0; r < k; r++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(i != j) {
                        {
                            pw.print("\n");
                            pw.write(String.valueOf(-y[i][r]));
                            pw.write(" ");
                            pw.write(String.valueOf(-y[j][r]));
                            pw.write(" ");
                            pw.write(String.valueOf(0));
                        }
                    }
                }
            }
        }

        fw.close();
    }

    @Override
    public void decipherOracleAnswer() throws IOException {

        VertexCover.clear();

        FileReader fr = new FileReader("sat.sol");
        BufferedReader br = new BufferedReader(fr);

        String line = br.readLine(); // citesc prima linie din sat.sol (cea cu True sau False)

        if(!line.equals("False")) {
            line = br.readLine(); // acum line contine numarul de variabile (a doua linie din sat.sol)
            line = br.readLine(); // iar acum line contine variabilele de pe al treilea rand din sat.sol

            String[] split = line.split(" ");

            int variable;
            ArrayList<Integer> PositiveVariables = new ArrayList<>();

            for(int i = 0; i < n * k; i++)
            {
                variable = Integer.parseInt(split[i]);
                if(variable > 0) {
                    PositiveVariables.add(variable);
                }
            }

            for(int index = 0; index < PositiveVariables.size(); index++) { // parcurg numerele din sol ca sa le gasesc codificarea in matricea y[][]
                for(int i = 0; i < n; i++) {
                    for(int r = 0; r < k; r++) {
                        if(y[i][r] == PositiveVariables.get(index)) {
                            VertexCover.add(i + 1);
                        }
                    }
                }
            }

            for(int i = 1; i <= n; i++) {
                if(!VertexCover.contains(i)) {
                    IsolatedNodes.add(i);
                }
            }

            // cand e True, atunci am gasit acoperirea
            VCoverSize = k;
        }
        else
        {
            k++;
        }

    }

    @Override
    public void writeAnswer() throws IOException {
        
        System.out.printf("%d", VertexCover.get(0));
        for(int index = 1; index < VertexCover.size(); index++) {
            System.out.printf(" %d", VertexCover.get(index));
        }
        System.out.printf("\n");
        
    }
}
