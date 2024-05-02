public class Solver {
    private GridPanel gridPanel;

    public Solver(GridPanel gridPanel) {
        this.gridPanel = gridPanel;
    }

    public boolean solve() {
        for (int row = 0; row < GridPanel.TAILLE_GRILLE; row++) {
            for (int col = 0; col < GridPanel.TAILLE_GRILLE; col++) {
                Cell cell = gridPanel.getCellules()[row][col];
                if (cell.getText().isEmpty()) {
                    for (char num = '1'; num <= '9'; num++) {
                        if (gridPanel.isValidInput(row, col, num)) {
                            cell.setText(String.valueOf(num));
                            if (solve()) {
                                return true;
                            } else {
                                cell.setText(""); // backtrack
                            }
                        }
                    }
                    return false; // trigger backtrack
                }
            }
        }
        return true; // Solution trouvée
    }
}
