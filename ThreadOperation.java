public class ThreadOperation extends Thread
{
	//matrices A, B is inputed. Then C is the result
	private int[][] A;
	private int[][] B;
	private int[][] C;
	private String quadrant;			//quadrant is specified where the threads will be processed in.

	//constructors initializes matrices and the quadrant for the thread
	public ThreadOperation(int[][] A, int[][] B, int[][] C, String quadrant)
	{
		this.A = A;
		this.B = B;
		this.C = C;
		this.quadrant = quadrant;
	}
	//run method defines the work done by the thread when it starts
	@Override
	public void run()
	{
		int rowStart = 0, rowEnd = 0, colStart = 0, colEnd = 0;

		//calculates the midpoints of the rows and colums to split the matrix into quadrants.
		int midRow = A.length / 2;		//middle row, dividing the top and bottom halves
		int midCol = A[0].length / 2;		//middle column, dividing the left and right halves

		//the boundaries (start and the end) of the indices bassed on the quadrant
		switch(quadrant)
		{
		case "00":			//top-left
			rowStart = 0;					//first row
			rowEnd = midRow;				//ends the row at the midpoint
			colStart = 0;					//first column
			colEnd = midCol;				//ends the column at the midpoint
			break;
		case "01":			//top-right
			rowStart = 0;					//first row
			rowEnd = midRow;				//end at the midpoint row
			colStart = midCol;				//starts from the midpoint column
			colEnd = A[0].length;			//ends at the last column
			break;
		case "10":				//bottom-left
			rowStart = midRow;				//starts from the midpoint
			rowEnd = A.length;				//end at the last row
			colStart = 0;					//starts from the first column
			colEnd = midCol;				//ends at the midpoint of the column
			break;
		case "11":				//bottom-right
			rowStart = midRow;				//starts at the midpoint row
			rowEnd = A.length;				//end at the last row
			colStart = midCol;				//starts from the midpoint column
			colEnd = A[0].length;			//ends at the last column
			break;
		}
		for(int i = rowStart; i < rowEnd; i++)			//loops through each row in the particular quadrant
		{
			for(int j = colStart; j < colEnd; j++)		//loops through each column in the particul quadrant
			{
				C[i][j] = A[i][j] + B[i][j];			//result C = A and B (row and col)
			}	
		}
	}
}