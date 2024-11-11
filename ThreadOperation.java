

public class ThreadOperation extends Thread
{
	//Matrices A and B are the initial input matrices, C is the result of both.
	private int[][] A;
	private int[][] B;
	private int[][] C;
	private String quadrant;		//represents the quadrant that the thread will process.

	//constructors initializes matrices and the quadrant for the thread
	public ThreadOperation(int[][] A, int[][] B, int[][] C, String quadrant)
	{
		this.A = A;		
		this.B = B;
		this.C = C;
		this.quadrant = quadrant;

	}
	public int[] getQuadrantIndexes()
	{
		int rows = A.length;				//total number of rows in matrix
		int columns = A[0].length;			//total number of columns in the matrix
		int midRow = rows / 2;				//midpoint of rows, divide the top and bottom
		int midCol = columns / 2;			//midpoint of the columns, divide the left and right

		//initialize vairables for the row and columns
		int rowStart = 0, rowEnd = 0, colStart = 0, colEnd = 0;
		//determins the row and column indexes based on the quarants
		if(quadrant.equals("upperLeft"))
		{
			rowStart = 0;
			rowEnd = midRow;
			colStart = 0;
			colEnd = midCol;
		} else if(quadrant.equals("upperRight"))
		{
			rowStart = 0;
			rowEnd = midRow;
			colStart = midCol;
			colEnd = columns;
		} else if(quadrant.equals("bottomLeft"))
		{
			rowStart = midRow;
			rowEnd = rows;
			colStart = 0;
			colEnd = midCol;
		} else{
			rowStart = midRow;
			rowEnd = rows;
			colStart = midCol;
			colEnd = columns;
		}
		return new int[]{rowStart, rowEnd, colStart, colEnd};		//return the calculated row and column boundares as an array
	}
	@Override
	//runs the threads task of addition, with the matrices A and B = C matrix for the quadrants
	public void run()
	{
		int[] indexes = getQuadrantIndexes();
		int rowStart = indexes[0], rowEnd = indexes[1];		//the boundaries for the threads in the designated quadrants.
		int colStart = indexes[2], colEnd = indexes[3];
		{
			for(int i = rowStart; i < rowEnd; i++)			//matrix addition will take place in the specfic row and column within the boundries
			{
				for(int j = colStart; j < colEnd; j++)
				{
					C[i][j] = A[i][j] + B[i][j];		//the elements A and B are stored in C
				}
			}
		}
	}
}