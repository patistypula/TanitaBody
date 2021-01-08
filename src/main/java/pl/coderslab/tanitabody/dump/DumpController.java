package pl.coderslab.tanitabody.dump;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


@Controller
@RequestMapping("/dump")
public class DumpController {
    private final static String dbUserName = "root";
    private final static String dbUserPassword = "coderslab";
    private final static String dbName = "tanita_body";

    @RequestMapping("/export")
    public String exportDB(Model model) {
        String data [] = data();
        String mysqldump = data[0];
        String dumpFolder = data[2];
        String fileName = data[3];
        boolean backup = createDatabaseFile(mysqldump, dumpFolder, fileName);
        if(backup==true){
            model.addAttribute("message", "Kopia zapasowa zapisana poprawnie.");
            model.addAttribute("image", "/images/hapy_face.jpg");
        } else{
            model.addAttribute("message", "Kopia nie zostałą zapisana.");
            model.addAttribute("image", "/images/sad_face.jpg");
        }
        return "dump/export";
    }

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String importDB(Model model){
        String data [] = data();
        String mysql = data[1];
        String dumpFolder = data[2];
        String fileName = data[3];
        List<String> files = loadFilesDB(dumpFolder);
        model.addAttribute("files", files);
        model.addAttribute("path", dumpFolder);
        model.addAttribute("fileName", fileName);
        return "dump/selectDB";
    }
    //@ResponseBody
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String loadDBFile(@RequestParam String path,
                           @RequestParam String fileName,
                           @RequestParam String data,
                           Model model){
        String name = path+"\\"+fileName;
        name += "_" + data + ".sql";
        String tab [] = data();
        String mysql = tab[1];
        boolean restore = restoreDB(name, mysql);
        if(restore==true) {
            model.addAttribute("message", "Załadowanie bazy danych z pliku: "+name+" zakończone sukcesem.");
            model.addAttribute("image", "/images/hapy_face.jpg");
        } else{
            model.addAttribute("message", "Załadowanie bazy danych z pliku: "+name+" zakończone niepowodzeniem.");
            model.addAttribute("image", "/images/sad_face.jpg");
        }
        return "dump/import";
    }





    private static boolean createDatabaseFile(String mysqldump, String folderPath, String fileName){
        Date backupDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String backupDateStr = format.format(backupDate);
        //String dbNameList = "tanita_body";

        //String fileName = "Daily_DB_Backup"; // default file name
        //String folderPath = "d:\\coderslab";
        File f1 = new File(folderPath);
        f1.mkdir(); // create folder if not exist

        String saveFileName = fileName + "_" + backupDateStr + ".sql";
        String savePath = folderPath + File.separator + saveFileName;

        String executeCmd = mysqldump+" -u " + dbUserName + " -p" + dbUserPassword + "  --databases " + dbName
                + " -r " + savePath;

        Process runtimeProcess = null;
        try {
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int processComplete = 0;
        try {
            processComplete = runtimeProcess.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (processComplete == 0) {
            //System.out.println("Backup Complete at " + new Date());
            return true;
        } else {
            return false;
        }
    }

    public static boolean restoreDB(String source, String mysql) {
        //String[] executeCmd = new String[]{mysql, "--user=" + dbUserName, "--password=" + dbUserPassword, dbName,"-e", " source "+source};
        String[] executeCmd = new String[]{mysql ,dbName, "--user=" + dbUserName, "--password=" + dbUserPassword, "-e", " source " + source};
        Process runtimeProcess;
        try {
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                System.out.println("Backup restored successfully");
                return true;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
}

    private static String [] data(){
        File file = new File("D:\\coderslab\\tanitabody\\TanitaBody\\src\\main\\resources\\static\\data\\tanitabody.dat");
        StringBuilder builder = new StringBuilder();
        String [] data = new String [4];
        try{
            Scanner scan = new Scanner(file);
            int count = 0;
            while(scan.hasNextLine()){
                String [] line = scan.nextLine().split("___");
                data[count] = line[1].trim();
                count++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku.");
        }
        return data;
    }

    private static List<String> loadFilesDB(String folderPath){
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        List<String> files = new ArrayList<>();
        for(int i=0;   i<listOfFiles.length;   i++){
            if(listOfFiles[i].isFile()){
                String[] file = listOfFiles[i].getName().split("_");
                String aaa = file[1].replace(".sql", "");
                files.add(aaa);
            }
        }
        return files;
    }
}
