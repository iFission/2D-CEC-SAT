package sat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import immutable.ImList;
import sat.env.Environment;
import sat.formula.Clause;
import sat.formula.Formula;
import sat.formula.Literal;
import sat.formula.PosLiteral;

/**
 * A simple DPLL SAT solver. See http://en.wikipedia.org/wiki/DPLL_algorithm
 */
public class SATSolver {
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

    /**
     * Solve the problem using a simple version of DPLL with backtracking and
     * unit propagation. The returned environment binds literals of class
     * bool.Variable rather than the special literals used in clausification of
     * class clausal.Literal, so that clients can more readily use it.
     * 
     * @return an environment for which the problem evaluates to Bool.TRUE, or
     *         null if no such environment exists.
     */
    /**
     * 	- If there are no clauses, the formula is trivially satisfiable. 
        - If there is an empty clause, the clause list is unsatisfiable -- fail and backtrack. (use empty 
            clause to denote a clause evaluated to FALSE based on the variable binding in the 
            environment) 
        - Otherwise, find the smallest clause (by number of literals). 
            - If the clause has only one literal, bind its variable in the environment so that the clause is satisfied, substitute for the variable in all the other clauses (using the suggested substitute() method), and recursively call solve(). 
            - Otherwise, pick an arbitrary literal from this small clause:
                § First try setting the literal to TRUE, substitute for it in all the clauses, then 
                solve() recursively.
                § If that fails, then try setting the literal to FALSE, substitute, and solve() 
                recursively. 
    
     */

    public static Environment solve(Formula formula) {
        // TODO: implement this.
        throw new RuntimeException("not yet implemented.");
        //     if (formula.getClauses() == null) {
        //         // return environment trival case;

        //     }
        //     else if (formula.getClauses() == empty) {
        //         // fail and check back
        //     }
        //     else find the smallest clause (by # literals)
        //         if cause has 1 literal
        //             assign to environment so that the clause is satisfid
        //             subsitute for the variable in all other clauses, with substitute()
        //             recursively call solve()
        //         else
        //             pick arbitrary literal from the smallest clause
        //                 try:set the literal to True, substitute all, call solve()
        //                 except:
        //                     set literal to false, substitute all, solve()

    }

    /**
     * Takes a partial assignment of variables to values, and recursively
     * searches for a complete satisfying assignment.
     * 
     * @param clauses
     *            formula in conjunctive normal form
     * @param env
     *            assignment of some or all variables in clauses to true or
     *            false values.
     * @return an environment for which all the clauses evaluate to Bool.TRUE,
     *         or null if no such environment exists.
     */
    private static Environment solve(ImList<Clause> clauses, Environment env) {
        // TODO: implement this.
        throw new RuntimeException("not yet implemented.");
    }

    /**
     * given a clause list and literal, produce a new list resulting from
     * setting that literal to true
     * 
     * @param clauses
     *            , a list of clauses
     * @param l
     *            , a literal to set to true
     * @return a new list of clauses resulting from setting l to true
     */
    private static ImList<Clause> substitute(ImList<Clause> clauses, Literal l) {
        // TODO: implement this.
        throw new RuntimeException("not yet implemented.");
    }

}
