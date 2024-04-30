# Makefile for the Sudoku Editor Java project

# Define the Java compiler
JCC = javac

# Define the Java source files
SRC = GrilleFrame.java GridPanel.java ButtonPanel.java Cell.java FileHandler.java

# Define the Java classes generated from the source files (targets)
CLASS = $(SRC:.java=.class)

# Default target: build all classes
all: $(CLASS)

# Rule for compiling java source files to class files
%.class: %.java
	$(JCC) $<

# Target to run the main class
run: all
	java GrilleFrame

# Target to clean the workspace: remove all compiled class files
clean:
	rm -f *.class

# Phony targets to ensure that make treats the targets as commands not files
.PHONY: all run clean
