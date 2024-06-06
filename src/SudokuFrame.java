import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuFrame extends MyFrame {

    private static final int GRID_SIZE = 9; // size of the grid (9x9)
    private JTextField[][] cells = new JTextField[GRID_SIZE][GRID_SIZE];

    public SudokuFrame() {
        super();
        initializeGrid();
        initializeButtons();
    }

    private void initializeGrid() {
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(JTextField.CENTER); // center text in the cell
                cell.setFont(new Font("Arial", Font.BOLD, 20)); // set font for better visibility
                cells[row][col] = cell; // store the cell reference
                gridPanel.add(cell); // add the cell to the grid
            }
        }

        add(gridPanel, BorderLayout.CENTER);
    }

    private void initializeButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solveSudoku();
            }
        });

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearGrid();
            }
        });

        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void solveSudoku() {
        int[][] board = new int[GRID_SIZE][GRID_SIZE];

        // Retrieve the numbers from the grid
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                String text = cells[row][col].getText();
                if (!text.isEmpty()) {
                    board[row][col] = Integer.parseInt(text);
                }
            }
        }

        // Solve the Sudoku
        if (solve(board)) {
            // Display the solution on the grid
            for (int row = 0; row < GRID_SIZE; row++) {
                for (int col = 0; col < GRID_SIZE; col++) {
                    cells[row][col].setText(Integer.toString(board[row][col]));
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No solution exists!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearGrid() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cells[row][col].setText("");
            }
        }
    }

    private boolean solve(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= GRID_SIZE; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;

                            if (solve(board)) {
                                return true;
                            }

                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int[][] board, int row, int col, int num) {
        // Check the row
        for (int x = 0; x < GRID_SIZE; x++) {
            if (board[row][x] == num) {
                return false;
            }
        }

        // Check the column
        for (int x = 0; x < GRID_SIZE; x++) {
            if (board[x][col] == num) {
                return false;
            }
        }

        // Check the 3x3 box
        int startRow = row - row % 3, startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SudokuFrame();
        });
    }
}
