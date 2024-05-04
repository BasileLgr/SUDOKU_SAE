# Define the Java compiler
JCC = javac

# Define the source files
COMMON_SRC = GridPanel.java Cell.java Solver.java FileHandler.java ButtonPanel.java SudokuFrame.java
CREATOR_SRC = $(COMMON_SRC)
PLAYER_SRC = PlayerGrid.java PlayerCell.java $(COMMON_SRC)

# Define class files
COMMON_CLASS = $(COMMON_SRC:.java=.class)
CREATOR_CLASS = $(CREATOR_SRC:.java=.class)
PLAYER_CLASS = $(PLAYER_SRC:.java=.class)

all:
	@echo "Specify 'createur' or 'joueur' to make specific builds."

%.class: %.java
	$(JCC) $<

createur: $(CREATOR_CLASS)
	java SudokuFrame

joueur: $(PLAYER_CLASS)
	java SudokuFrame player

clean:
	rm -f *.class *$*.class

.PHONY: all createur joueur clean
