# Define the Java compiler
JCC = javac

# Define the source files for different modes
COMMON_SRC = GridPanel.java Cell.java Solver.java FileHandler.java
CREATOR_SRC = GrilleFrame.java ButtonPanel.java
PLAYER_SRC = PlayerGridFrame.java PlayerGrid.java PlayerCell.java

# Define class files
COMMON_CLASS = $(COMMON_SRC:.java=.class)
CREATOR_CLASS = $(CREATOR_SRC:.java=.class)
PLAYER_CLASS = $(PLAYER_SRC:.java=.class)

all:
	@echo "Specify 'createur' or 'joueur' to make specific builds."

%.class: %.java
	$(JCC) $<

createur: $(COMMON_CLASS) $(CREATOR_CLASS)
	java GrilleFrame

joueur: $(COMMON_CLASS) $(PLAYER_CLASS)
	java PlayerGridFrame

clean:
	rm -f *.class *$*.class

.PHONY: all createur joueur clean
