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
   * When a function runs with little delay and accounts for possible errors.

### Backlog 

| MVP? | Id  | Feature  | Status  |  Started  | Delivered |
| :-:  |:-:  |---       | :-:     | :-:       | :-:       |
| x   | F01 | Roll the die |  D | 01/01/23 | 01/20/23 |
| x   | F02 | Compute Score |  D | 01/01/23 | 01/20/23 |
| x   | F03 | Sim 42 games  |  D  |  01/18/23 | 01/20/23 |
| x   | F04 | Player rolling random dice until turn ends |  D  | 01/18/23 | 01/20/23 |
| x   | F05 | Player scores 0 POINTS when 3 skulls rolled |  D  | 01/24/23 | 01/24/23 |
| x   | F06 | End game when player reaches 6000 points |  D  | 01/18/23 | 01/20/23 |
| x   | F07 | Print the % of wins for each player | D | 01/22/23 | 01/22/23 |
| x   | F08 | Keep dice at random and roll other non-skull dice | D | 01/24/23 | 01/24/23 |
| x   | F09 | Store value of dice that are kept | P | 01/25/23 |      |
| x   | F10 | The computer can randomly stop its turn | D | 01/24/23 | 01/24/23 |