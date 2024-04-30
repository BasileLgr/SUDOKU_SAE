# Makefile for the Sudoku Editor Java project

# Define the Java compiler
JCC = javac

# Define the Java source files
SRC = GrilleFrame.java GridPanel.java ButtonPanel.java Cell.java FileHandler.java

# Define the Java classes generated from the source files (targets)
CLASS = $(SRC:.java=.class)

# Default target: do nothing
all:
	@echo "Specify 'createur' or 'joueur' to make specific builds."

# Rule for compiling java source files to class files
%.class: %.java
	$(JCC) $<

# Target to compile and run the main class for creator mode
createur: GrilleFrame.class
	java GrilleFrame

# Target to run the main class for player mode (currently does nothing)
joueur:
	@echo "Player mode is not yet implemented."

# Target to clean the workspace: remove all compiled class files including inner classes
clean:
	rm -f *.class *$*.class

# Phony targets to ensure that make treats the targets as commands not files
.PHONY: all createur joueur clean
