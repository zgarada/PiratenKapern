# A1 - Piraten Karpen

  * Author: < Zain-Alabedeen Garada >
  * Email: < garadz1@mcmaster.ca >

## Build and Execution

  * To clean your working directory:
    * `mvn clean`
  * To compile the project:
    * `mvn compile`
  * To run the project in development mode:
    * `mvn -q exec:java` (here, `-q` tells maven to be _quiet_)
  * To package the project as a turn-key artefact:
    * `mvn package`
  * To run the packaged delivery:
    * `java -jar target/piraten-karpen-jar-with-dependencies.jar` 

Remark: **We are assuming here you are using a _real_ shell (e.g., anything but PowerShell on Windows)**

## Feature Backlog

 * Status: 
   * Pending (P), Started (S), Blocked (B), Done (D)
 * Definition of Done (DoD):
   * When a fucntion runs with little delay and accounts for possible errors.

### Backlog 

| MVP? | Id  | Feature  | Status  |  Started  | Delivered |
| :-:  |:-:  |---       | :-:     | :-:       | :-:       |
| x   | F01 | Roll the die |  S | 01/01/23 |  |
| x   | F02 | Compute Score |  S | 01/01/23 |  |
| x   | F03 | Sim 42 games  |  P  |   |
| x   | F04 | Player keeping random dice at their turn|  | | 
| x   | F05 | End game when player reaches 6000 points |  | | 
| x   | F06 | OR, end game when both run out of die |  | |

