/*
Name: Isaiah James
Date: November 2, 2024
Class: Interm Programming
Purpose: implement multi-threaded matrix addition, demonstrates how to divide a larger
problem into a smaller quadrants. Each seperate thread is processed simultaneously, results
in more efficient and optimized. Reducing the execution time, memory management, and dividing tasks. 

This code is provided to give you a
starting place. It should be modified.
No further imports are needed.
To earn full credit, you must also
answer the following question:

Q1: One of the goals of multi-threading
is to minimize the resource usage, such
as memory and processor cycles. In three
sentences, explain how multi-threaded
code accomplishes this goal. Consider
writing about blocking on I/O, multicore 
machines, how sluggish humans are,
threads compared to processes, etcetera,
and connect these issues to 
multi-threading.

Answer: 
mult-threading minimiezes resources by having the tasks run concurrently on
a multi-core processor, this maximizes CPU efficiency. Threads share memory space, which is 
different from processes. Threads elimates the act of copying memory and is faster, this saves
memory and the processor cylces. By using threads, we can continue to execute multiple tasks while 
waiting on I/O expections, reduces the delays and handles tasks more efficiently.

*/
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args) 
	{
		if(args.length < 1)				//checks if the file name is provided in the terminal/command line
		{
			System.out.println("Please provide the input file name.");			//if there isnt a file name provided
			return;																//this message pops up.
		}
		String filename = args[0];		//gets the file name from the terminal/command line

		try
		{		
			Scanner s = new Scanner(new File(filename));		//opens the file name using the scanner

			int row = s.nextInt();			//reads the number of rows from the file
			int col = s.nextInt();			//reads the number of columns from the file

			//reads the matrices of A and B from the file, then creates the Matrix C for the results.
			int[][] A = matrixFromFile(row, col, s);		//Matrix A
			int[][] B = matrixFromFile(row, col, s);		//Matrix B
			int[][] C = new int[row][col];					//Matrix C (results)

			//four threads (t1-t4) in a quadrant plane
			ThreadOperation t1 = new ThreadOperation(A, B, C, "00");		//top-left quadrant
			ThreadOperation t2 = new ThreadOperation(A, B, C, "01");		//top-right quadrant
			ThreadOperation t3 = new ThreadOperation(A, B, C, "10");		//bottom-left quadrant
			ThreadOperation t4 = new ThreadOperation(A, B, C, "11");		//bottom-right quadrant

			//starts each of the threads
			t1.start();
			t2.start();
			t3.start();
			t4.start();
			//waits for all of the threads to complete before continuing
			t1.join();
			t2.join();
			t3.join();
			t4.join();

			print2dArray(C);		//prints the final result in matrix C

			s.close();			//scanner close

		} catch (IOException e) {				//file related exceptions
			e.printStackTrace();
		} catch (InterruptedException e){		//exceptions from thread interruptions
			e.printStackTrace();
		}
	}
	//method to print a 2D matrix
	public static void print2dArray(int[][] matrix)
	{
		for(int[] row : matrix)		//loops through each row in the matrix
		{
			for(int value : row)		//loops through each value within the row
			{
				System.out.printf("%3d", value);		//prints the value, width of 3 for readablity. 
			}
			System.out.println();				//new line after each row for the matrix
		}
	}
	//method to read a matrix from the file, using the columns and rows.
	public static int[][] matrixFromFile(int row, int col, Scanner s)
	{
		int[][] matrix = new int[row][col];		//initializes the matrix as a 2D array
		for(int i = 0; i < row; i++)			//loops through each row as i
		{
			for(int j = 0; j < col; j++)		//loops though each column, within each row
			{
				matrix[i][j] = s.nextInt();		//reads and stores the next integer from the file
			}
		}
		return matrix;			//returns the given matrix
	}
}