# Fetch Challenge

### Tech

Automation is based on:

* Java17
* Maven
* TestNG
* Selenium
* Cucumber

### Execution

To execute the scenario for the challenge:

1. Open the project in IntelliJ
2. Open Java runner class ```fetch/challenge/runner/MainRunnerTest``` 
3. Press Ctrl-Shift-F10 to execute the scenario

Also it can be executed from Cucumber file ```src/test/resources/features/solution.feature```
or running Test step of Maven Livecycle (```mvn clean test```)

### Configuration

Challenge URL and browser can be configured in ```src/test/resources/challenge.properties```

Supported browsers: Chrome (default), FireFox, Edge

### Solution approach

To identify the fake bar, we need to perform 2 iterations. In each iteration, we divide the bars into 3 equal groups. We
then weigh the first 2 groups against each other, and the group with the lesser weight is selected for the next
iteration. If the weights of the two groups are equal, the third group is chosen instead. This process is repeated in
the next iteration with the selected group. The bar with the lower weight at the end is identified as the fake bar.

### Sample output

Implemented output to console and log-file (```target/logs/solution.log```).

Sample output:
```
17:37:32 Open challenge page: http://sdetchallenge.fetch.com/
17:37:32 Enter indexes to the LEFT bowl:
17:37:32 [Bar-0, Bar-1, Bar-2]
17:37:32 Enter indexes to the RIGHT bowl:
17:37:32 [Bar-3, Bar-4, Bar-5]
17:37:32 Click Weigh button
17:37:32 Wait until the result is ready
17:37:34 Weighing result: >
17:37:34 Bars kept: [Bar-3, Bar-4, Bar-5]
17:37:34 Click Reset button
17:37:35 Wait until reset done
17:37:35 Enter indexes to the LEFT bowl:
17:37:35 [Bar-3]
17:37:35 Enter indexes to the RIGHT bowl:
17:37:35 [Bar-4]
17:37:35 Click Weigh button
17:37:35 Wait until the result is ready
17:37:37 Weighing result: =
17:37:37 Bars kept: [Bar-5]
17:37:37 Weighings:
17:37:37 [0,1,2] > [3,4,5]
17:37:37 [3] = [4]
17:37:37 Click on the fake bar button: 5
17:37:37 Result: Yay! You find it!
17:37:37 FAKE BAR INDEX: 5
```

### Some observations

Result button (located between bowls) has id="reset" (same as Reset button) what is a bit inconsistent.
