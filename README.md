# calculator

## To run calculator:
``` bash
javac main/*.java -d out
cd out
java Calculator
```

## To run JUnit tests:
``` bash
cd out
javac -cp .:../lib/junit-4.10.jar ../test/SolverTest.java ../main/Solver.java -d ./
java -cp .:../lib/junit-4.10.jar org.junit.runner.JUnitCore SolverTest
```