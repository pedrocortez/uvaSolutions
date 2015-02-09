import java.io.IOException;
import java.util.StringTokenizer;


public class Main {
	static String ReadLn(int maxLg) // utility function to read from stdin
	{
		byte lin[] = new byte[maxLg];
		int lg = 0, car = -1;
		String line = "";

		try {
			while (lg < maxLg) {
				car = System.in.read();
				if ((car < 0) || (car == '\n'))
					break;
				lin[lg++] += car;
			}
		} catch (IOException e) {
			return (null);
		}

		if ((car < 0) && (lg == 0))
			return (null); // eof
		return (new String(lin, 0, lg));
	}

	public static void main(String args[]) throws IOException { // entry point
		Main myWork = new Main(); // Construct the bootloader
		myWork.run(); // execute
	}
	
	public void run() {
		String linha;
		linha = Main.ReadLn(255);
		int lin, col;
		int qtdField =1;
		boolean isFirstTime = true;
		while (linha != null) {
			StringTokenizer idata = new StringTokenizer(linha);
			lin = Integer.parseInt(idata.nextToken());
			col = Integer.parseInt(idata.nextToken());
			
			if(lin ==0 && col==0) {
				return;
			}
			if(isFirstTime)  {
				isFirstTime = false;
			} else {
				System.out.println();
			}
			
			int[][] field = new int[lin][col];

			for(int i=0; i < lin; i++) {
				linha = Main.ReadLn(255);
				idata = new StringTokenizer(linha);
				String space = idata.nextToken();
				for(int y=0;y <col; y++) {
					if(space.charAt(y) == '*') {
						field[i][y] = -1;
						//preencher todas as adjacentes a partir da mina achada. Se um dos adjacentes for mina, 
						// ele nao preenche.
						
						//up
						if(i-1 >= 0) {
							if(!isMine(field[i-1][y])) {
								field[i-1][y]++;
							}
							//up left
							if(y-1 >=0 && !isMine(field[i-1][y-1]) ) {
								field[i-1][y-1]++;
							}
							//up right
							if(y+1 < col && !isMine(field[i-1][y+1]) ) {
								field[i-1][y+1]++;
							}
						}
						//down
						if( i + 1 < lin) {
							
							if(!isMine(field[i+1][y])) {
								field[i+1][y]++;
							}
							//down left
							if(y-1 >=0 && !isMine(field[i+1][y-1])) {
								field[i+1][y-1]++;
							}
							//down right
							if(y+1 < col && !isMine(field[i+1][y+1])) {
								field[i+1][y+1]++;
							}
							
						}
						//left
						if(y-1 >=0 && !isMine(field[i][y-1])) {
							field[i][y-1]++;
						}
						//right
						if(y+1 < col && !isMine(field[i][y+1])) {
							field[i][y+1]++;
						}
					} 
				}
			}
			printField(field, qtdField, lin, col);
			qtdField++;
			linha = Main.ReadLn(255);
		}
	}
	public void printField(int[][] field, int qtd, int lin, int col) {
		System.out.println("Field #" + qtd+":");
		for (int i = 0; i < lin; i++) {
			for (int j = 0; j < col; j++) {
				int result = field[i][j];
				if(result == -1) {
					System.out.print("*");
				} else {
					System.out.print(field[i][j]);
				}
			}
			System.out.println();
		}
	}
	
	public boolean isMine(int spot) {
		if(spot == -1) {
			return true;
		}
		return false;
	}

}
