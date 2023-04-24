import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class Registre {
    public static void main(String[] args) throws IOException {

        Task3 task3 = new Task3();


        try {
            task3.readProblemData();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            task3.solve();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            task3.formulateOracleQuestion();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            task3.askOracle();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        try {
            task3.decipherOracleAnswer();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            task3.writeAnswer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}