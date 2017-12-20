package mx.infotec.dads.kukulkan.shell.util;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.shell.CompletionProposal;

import mx.infotec.dads.kukulkan.shell.domain.ShellCompletionProposal;

public class FilesCommons {

    private FilesCommons() {
    }

    public static List<CompletionProposal> filterFiles(Path currentPath) {
        List<CompletionProposal> completionProposal = new ArrayList<>();
        try (DirectoryStream<Path> directories = Files.newDirectoryStream(currentPath);) {
            for (Path path : directories) {
                if (!path.toFile().isDirectory()) {
                    completionProposal.add(new ShellCompletionProposal(path.getFileName().toString(), "file"));
                } else {
                    completionProposal.add(new ShellCompletionProposal(path.getFileName().toString(), "directory"));
                }
            }
        } catch (IOException e) {
        }
        return completionProposal;
    }

    public static List<CharSequence> showFiles(Path currentPath) {
        List<CharSequence> fileList = new ArrayList<>();
        fileList.add("");
        try (DirectoryStream<Path> directories = Files.newDirectoryStream(currentPath);) {
            for (Path path : directories) {
                if (!path.toFile().isDirectory()) {
                    fileList.add(path.getFileName().toString());
                }
            }
        } catch (IOException e) {
        }
        fileList.add("\n");
        return fileList;
    }

    public static List<CompletionProposal> filterDirs(Path currentPath) {
        List<CompletionProposal> completionProposal = new ArrayList<>();
        try (DirectoryStream<Path> directories = Files.newDirectoryStream(currentPath);) {
            for (Path path : directories) {
                if (path.toFile().isDirectory()) {
                    completionProposal.add(new ShellCompletionProposal(path.getFileName().toString()));
                }
            }
        } catch (IOException e) {
        }
        return completionProposal;
    }

    public static boolean hasGitFiles(Path currentPath) {
        try (DirectoryStream<Path> directories = Files.newDirectoryStream(currentPath)) {
            for (Path path : directories) {
                if (path.toFile().isDirectory() && path.toFile().getName().equals(".git")) {
                    return true;
                }
            }
        } catch (IOException e) {
        }
        return false;
    }
}
