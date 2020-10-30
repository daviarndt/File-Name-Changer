import java.io.File;

public class ChangeFileName {

    public static void main(String[] args) throws Exception {

        File folderSource = new File("");

        if (!folderSource.exists()) {
            throw new Exception("O diretório de origem não existe.");
        }

        File[] listOfFiles = folderSource.listFiles();
        int qtdFiles = listOfFiles.length;

        File folderTarget = new File("");
        boolean directoryCreated;
        if (!folderTarget.exists()) {
            directoryCreated = folderTarget.mkdir();
            if (!directoryCreated)
                throw new Exception("Não foi possível criar o diretório");
        }

        String pieceToRemove = "";
        String fileName;
        String newFilename;

        for (int i=0; i<qtdFiles; i++) {

            if (listOfFiles[i].isFile()) {
                File currentFile = listOfFiles[i];
                fileName = listOfFiles[i].getName();

                if (fileName.contains(pieceToRemove)) {
                    newFilename = fileName.replace(pieceToRemove, "");
                    File completeFile = new File(folderTarget + "\\" + newFilename);
                    try {
                        currentFile.renameTo(completeFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    System.out.println("O arquivo " + fileName + " não contém o texto fornecido para remover.");
                }
            }
        }
    }
}
