import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Reclame {
    public static void main(String[] args) throws IOException {

        Task2 task2 = new Task2();

        try {
            task2.readProblemData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int ok = 0;
        while(ok != 1) {

            try {
                task2.solve();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            task2.formulateOracleQuestion();

            try {
                task2.askOracle();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            FileReader fr1 = new FileReader("sat.sol");
            BufferedReader br1 = new BufferedReader(fr1);

            String line1 = br1.readLine(); // citesc prima linie din sat.sol (cea cu True sau False)

            if(line1.equals("True")) {
                ok = 1;
            }
            
            task2.decipherOracleAnswer();

        }

        task2.writeAnswer();


    }
}