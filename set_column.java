package defaultrowadder;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class set_column()
{
  static void readExcel()
    throws IOException
  {
    // Opens up a file selector window which allows the user to select a file with a CSV extension
    JFileChooser fileChooser = new JFileChooser();
    
    FileNameExtensionFilter filter = new FileNameExtensionFilter("csv", new String[] { "csv" });
    
    fileChooser.setFileFilter(filter);
    
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    
    // As long as the user selected a file the program will continue
    int returnVal = fileChooser.showOpenDialog(new JPanel());
    if (returnVal == 0)
    {
      File OGFile = fileChooser.getSelectedFile();
      
      CSVReader reader = new CSVReader(new FileReader(OGFile), ',');
      
      // This next block of code goes through the CSV file, adds two columns together and checks
      // to see if that combined string is in my hash set. If it is, I set the the row of the last
      // column equal to False. If not, I set it equal to True and then add it to my hash set
      List<String[]> csvBody = reader.readAll();
      Set<String> OGSet = new HashSet();
      
      String b = "FALSE";
      String c = "TRUE";
      for (int i = 1; i < csvBody.size(); i++)
      {
        String temp = ((String[])csvBody.get(i))[2];
        String temp2 = ((String[])csvBody.get(i))[3];
        String temp3 = temp + temp2;
        if (OGSet.contains(temp3))
        {
          ((String[])csvBody.get(i))[4] = b;
        }
        else
        {
          ((String[])csvBody.get(i))[4] = c;
          OGSet.add(temp3);
        }
      }
      CSVWriter writer = new CSVWriter(new FileWriter(OGFile));
      writer.writeAll(csvBody);
      writer.flush();
      writer.close();
    }
  }
}