package sat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
import static org.junit.Assert.*;

import org.junit.Test;
*/
import sat.env.Environment;
import sat.formula.Clause;
import sat.formula.Formula;
import sat.formula.Literal;
import sat.formula.PosLiteral;

public class SATSolverTest {
    Literal a = PosLiteral.make("a");
    Literal b = PosLiteral.make("b");
    Literal c = PosLiteral.make("c");
    Literal na = a.getNegation();
    Literal nb = b.getNegation();
    Literal nc = c.getNegation();

    /**
    * 
    In main(), read in and parse a .cnf file (see cnf file handout for file format), and construct the corresponding Formula instance as input to your SAT solver. Print out “satisfiable” or “not satisfiable” depending on the result. If the Formula is satisfiable, output your variable assignments to a result file “BoolAssignment.txt” with format of one variable per line: <variable>:<assignment>, e.g.,
    1:TRUE
    2:FALSE
    Report the execution time of your SAT solver by including the following code surrounding your SATSolver.solve():
    Formula f2 = ...
    System.out.println("SAT solver starts!!!"); long started = System.nanoTime(); Environment e = SATSolver.solve(f2);
    long time = System.nanoTime();
    long timeTaken= time - started;
    System.out.println("Time:" + timeTaken/1000000.0 + "ms");
    
    */
    public static void main(String[] args) {
        String path = "/Users/ALEX/Documents/2D-CEC-SAT/2D/src/main/java/sampleCNF/s8Sat.cnf";

        String line = null;
        Formula currentFormula = new Formula();

        try {
            FileReader fileReader = new FileReader(path);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() > 0) { // check the line is non-empty
                    String firstChar = line.substring(0, 1);
                    // System.out.println("first char is " + firstChar);
                    // System.out.println(line.length());

                    if (firstChar.equals("c")) {
                        System.out.println("It is a comment " + line);
                    } else if (firstChar.equals("p")) {
                        System.out.println("It is a problem " + line);
                    } else {
                        System.out.println("It is a clause " + line);
                        String clause[] = line.split(" ");
                        Clause currentClause = new Clause();
                        for (String variable : clause) {

                            System.out.println(variable);

                            if (variable.equals("0")) {
                                break;
                            }
                            Literal currentLiteral;
                            if (variable.startsWith("-")) {
                                currentLiteral = PosLiteral.make(variable.replaceAll("-", ""));
                                currentLiteral = currentLiteral.getNegation();

                            } else {
                                currentLiteral = PosLiteral.make(variable);
                            }

                            System.out.println(currentLiteral.toString());
                            currentClause = currentClause.add(currentLiteral);
                        }
                        System.out.println(currentClause.toString());
                        currentFormula = currentFormula.addClause(currentClause);
                    }

                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + path + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + path + "'");
            // Or we could just do this: 
            // ex.printStackTrace();
        }

        System.out.println(currentFormula.toString());
    }

    public void testSATSolver1() {
        // (a v b)
        Environment e = SATSolver.solve(makeFm(makeCl(a, b)));
        /*
            	assertTrue( "one of the literals should be set to true",
            			Bool.TRUE == e.get(a.getVariable())  
            			|| Bool.TRUE == e.get(b.getVariable())	);
            	
        */
    }

    public void testSATSolver2() {
        // (~a)
        Environment e = SATSolver.solve(makeFm(makeCl(na)));
        /*
            	assertEquals( Bool.FALSE, e.get(na.getVariable()));
        */
    }

    private static Formula makeFm(Clause... e) {
        Formula f = new Formula();
        for (Clause c : e) {
            f = f.addClause(c);
        }
        return f;
    }

    private static Clause makeCl(Literal... e) {
        Clause c = new Clause();
        for (Literal l : e) {
            c = c.add(l);
        }
        return c;
    }

}