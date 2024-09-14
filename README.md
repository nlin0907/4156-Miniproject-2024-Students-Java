# 4156 Mini Project README

### Check Style
In the IndividualProject directory, run this command: 
```
mvn checkstyle:checkstyle 
target/site/checkstyle.html
open target/site/checkstyle.html
```

There are no more checkstyle warnings.

### Bug Finder
Bug finder: PMD

Command: inside the IndividualProject directory, do <code> pmd check -d src/main/java/dev/coms4156/project/individualproject -R rulesets/java/quickstart.xml -f text </code>

There are no more PMD bugs.

### Test Coverage
In the IndividualProject directory, run this command: 
```
mvn clean test
mvn jacoco:report
open target/site/jacoco/index.html
```
The JaCoCo report has an instruction coverage of 94% and a branch coverage of 94%.
