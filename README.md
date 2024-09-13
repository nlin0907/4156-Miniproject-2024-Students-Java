# 4156 Mini Project README

### Check Style
Command: 
```
mvn checkstyle:check 
target/site/checkstyle.html
open target/site/checkstyle.html
```

There are no more checkstyle warnings.

### Bug Finder
Bug finder: PMD

Command: inside the IndividualProject directory, do <code> pmd check -d src/main/java/dev/coms4156/project/individualproject -R rulesets/java/quickstart.xml -f text </code>

There are no more PMD bugs.

### Test Coverage
Command: 
```
mvn clean test
mvn jacoco:report
open target/site/jacoco/index.html
```
The JaCoCo report has an instruction coverage of 93% and a branch coverage of 91%.
