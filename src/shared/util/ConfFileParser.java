package shared.util;

// Java Imports
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * The ConfFileParser class will parse any given formatted text file for
 * configuration variables.
 */
public class ConfFileParser {

    private String fileName;
    private FileInputStream fis;
    private BufferedReader bur;

    /**
     * Initialize the parser with the filename for the formatted file to be
     * parsed.
     *
     * @param fileName a variable that holds the filename
     */
    public ConfFileParser(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Open the file using the given filename.
     */
    public void openFile() {
        try {
            InputStream in = this.getClass().getClassLoader()
                            .getResourceAsStream(fileName);
            bur = new BufferedReader(new InputStreamReader(in));
        } catch (Exception ex) {
            System.err.printf("[In ConfFileParser.java]---No such file %s, please check.", fileName);
        }
    }

    /**
     * Parse each line of the formatted file for configuration variables.
     *
     * @return the hash map containing the parsed variables
     */
    public Map<String, String> parse() {
        Map<String, String> records = new HashMap<String, String>();
        String str; // Store one line of string, like "Port 9090"
        String key = null; // Store key, like "Port"
        String value = null; // Store value, like "9090"
        StringTokenizer st;

        openFile();

        try {
            while ((str = bur.readLine()) != null) {
                // Delete spaces at the beginning and end
                str = str.trim();
                // If the line has no content or starts with "#", then go to the next line
                if (str.length() == 0 || str.charAt(0) == '#') {
                    continue;
                }
                // Tokenize the string and parse for variables
                st = new StringTokenizer(str);
                int i = 0;
                while (st.hasMoreTokens()) {
                    i++;
                    if (i == 1) {
                        key = st.nextToken();
                    } else if (i == 2) {
                        value = st.nextToken();
                    } else {
                        System.out.println("[In ConfFileParser.java]---There are more than 2 parts in a line in file: " + fileName + ", please check.");
                    }
                }
                records.put(key, value);
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        return records;
    }
}