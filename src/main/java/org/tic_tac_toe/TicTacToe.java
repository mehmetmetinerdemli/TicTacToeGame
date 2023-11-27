package org.tic_tac_toe;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Step 1: Initialize an empty 3x3 grid
        char[][] grid = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        // Step 2: Display the empty grid
        displayGrid(grid);

        // Step 3: Game loop
        char currentPlayer = 'X'; // X starts the game

        while (true) {
            // Step 4: Ask the current player to make a move
            int[] coordinates = getPlayerMove(scanner, grid, currentPlayer);

            // Step 5: Update the grid and display
            grid[coordinates[0]][coordinates[1]] = currentPlayer;
            displayGrid(grid);

            // Step 6: Check for a winner or draw
            if (isWinner(grid, currentPlayer)) {
                System.out.println(currentPlayer + " wins!");
                break;
            } else if (isGridFull(grid)) {
                System.out.println("It's a draw!");
                break;
            }

            // Step 7: Switch to the next player
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }

        scanner.close();
    }

    // Function to display the Tic-Tac-Toe grid
    private static void displayGrid(char[][] grid) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    // Function to get a valid move from the current player
    private static int[] getPlayerMove(Scanner scanner, char[][] grid, char currentPlayer) {
        int[] coordinates = new int[2];

        while (true) {
            System.out.print("Enter the coordinates (row column) for " + currentPlayer + ": ");
            try {
                coordinates[0] = scanner.nextInt() - 1;
                coordinates[1] = scanner.nextInt() - 1;

                // Check if the coordinates are within the valid range
                if (coordinates[0] < 0 || coordinates[0] >= 3 || coordinates[1] < 0 || coordinates[1] >= 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }

                // Check if the cell is empty
                if (grid[coordinates[0]][coordinates[1]] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }

                // Exit the loop if the move is valid
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter two numbers separated by a space.");
                scanner.nextLine(); // Consume the remaining newline character
            }
        }

        return coordinates;
    }

    // Function to check if the current player has won
    private static boolean isWinner(char[][] grid, char currentPlayer) {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == currentPlayer && grid[i][1] == currentPlayer && grid[i][2] == currentPlayer) {
                return true; // Row win
            }
            if (grid[0][i] == currentPlayer && grid[1][i] == currentPlayer && grid[2][i] == currentPlayer) {
                return true; // Column win
            }
        }
        if (grid[0][0] == currentPlayer && grid[1][1] == currentPlayer && grid[2][2] == currentPlayer) {
            return true; // Diagonal win
        }
        if (grid[0][2] == currentPlayer && grid[1][1] == currentPlayer && grid[2][0] == currentPlayer) {
            return true; // Diagonal win
        }

        return false;
    }

    // Function to check if the grid is full (a draw)
    private static boolean isGridFull(char[][] grid) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == ' ') {
                    return false; // The grid is not full
                }
            }
        }
        return true; // The grid is full
    }
}