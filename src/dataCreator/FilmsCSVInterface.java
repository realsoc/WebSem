public class FilmsCSVInterface{


	public void run() {		
		try{

				this.bw.write(this.getCity()+";"+tournage[titlesIndex.get(ROW_TITLE)]+";"+tournage[titlesIndex.get(ROW_PLACE)]
					+";"+tournage[titlesIndex.get(ROW_DATE_BEGIN)]+";"+tournage[titlesIndex.get(ROW_DATE_END)]);
				this.bw.newLine();
			}
			catch (FileNotFoundException e) {
			e.printStackTrace();
			} catch (IOException e) {
			e.printStackTrace();
			} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

  	} 
}