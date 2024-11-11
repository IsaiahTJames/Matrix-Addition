/*
Name: Isaiah James
Date: November 9, 2024
Class: Interm Programming
Purpose: part 2 of the Matrix Addition is to implement a multithreading solution, that adds two matrices by divinding
the work into four quadrants, each is a seperate thread. Each thread A and B is assigned to a quadrant and stored in C.
This demonstrates the use of threads for divinng tasks within a matrix operation setting. 
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

			int rows = s.nextInt();			//reads the number of rows from the file
			int cols = s.nextInt();			//reads the number of columns from the file

			//initialize matrices A, B an C
			int[][] A = new int[rows][cols];
			int[][] B = new int[rows][cols];
			int[][] C = new int[rows][cols];

			//Reads matrix A
			for(int i = 0; i < rows; i++)
			{
				for(int j = 0; j < cols; j++)
				{
					A[i][j] = s.nextInt();
				}
			}
			//Reads matrix B
			for(int i = 0; i < rows; i++)
			{
				for(int j = 0; j < cols; j++)
				{
					B[i][j] = s.nextInt();
				}
			}

			s.close();	//scanner close

			//four threads (t1-t4) in a quadrant plane
			ThreadOperation t1 = new ThreadOperation(A, B, C, "upperLeft");
			ThreadOperation t2 = new ThreadOperation(A, B, C, "upperRight");		
			ThreadOperation t3 = new ThreadOperation(A, B, C, "bottomLeft");		
			ThreadOperation t4 = new ThreadOperation(A, B, C, "bottomRight");		

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

		} catch (IOException e) {				//file related exceptions
			System.out.println("Error reading the fil. Please check the file path and format.");
			e.printStackTrace();
		} catch (InterruptedException e){		//exceptions from thread interruptions
			System.out.println("A thread was interrupted.");
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
}