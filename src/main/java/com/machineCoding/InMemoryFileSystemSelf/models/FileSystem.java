package com.machineCoding.InMemoryFileSystemSelf.models;

import com.machineCoding.InMemoryFileSystemSelf.utils.PathUtils;
import com.machineCoding.InMemoryFileSystemSelf.utils.ValidationUtils;

import java.util.Optional;

public class FileSystem {
    private RootDirectory rootDirectory;
    public FileSystem() {
        rootDirectory = new RootDirectory();
    }

    public void ls() {
        rootDirectory.ls();
    }

    private Directory getDirectoryFromPath(String path, int beforeIndex) {
        String [] nodes = PathUtils.getNodesArray(path);
        if (beforeIndex > nodes.length) {
            beforeIndex = nodes.length - 1;
        }
        if (beforeIndex <= 0) {
            beforeIndex = 0;
        }
        Directory directory = rootDirectory;
        for (int i = 1; i < nodes.length; i++) {
            if (beforeIndex == i) {
                return directory;
            }
            String dirName = nodes[i];
            Optional<IEntry> iEntryOptional = directory.getEntryWithName(dirName);
            ValidationUtils.ensureTrue(iEntryOptional.isPresent(), "Dir name: " + dirName + " not present!");
            IEntry iEntry = iEntryOptional.get();
            ValidationUtils.ensureTrue(iEntry.getEntryType().equals(EntryType.DIRECTORY), "Dir name: " + dirName + " not present!");
            directory = (Directory) iEntry;
        }
        return directory;
    }

    public Directory mkdir(String path) {
        String [] nodes = PathUtils.getNodesArray(path);
        Directory directory = getDirectoryFromPath(path, nodes.length - 1);
        String dirName = nodes[nodes.length - 1];
        return directory.addDirectory(dirName);
    }

    public void addContentToFile(String path, String content) {
        String [] nodes = PathUtils.getNodesArray(path);
        Directory directory = getDirectoryFromPath(path, nodes.length - 1);
        String fileName = nodes[nodes.length - 1];
        Optional<IEntry> iEntryOptional = directory.getEntryWithName(fileName);
        ValidationUtils.ensureTrue(iEntryOptional.isPresent(), "File name: " + fileName + " not present!");
        ValidationUtils.ensureTrue(iEntryOptional.get().getEntryType().equals(EntryType.FILE), "File name: " + fileName + " not present!");
        File file = (File) iEntryOptional.get();
        file.addContent(content);
    }

    public File cat(String path) {
        String [] nodes = PathUtils.getNodesArray(path);
        Directory directory = getDirectoryFromPath(path, nodes.length - 1);
        String fileName = nodes[nodes.length - 1];
        return directory.addFile(fileName);
    }
}
