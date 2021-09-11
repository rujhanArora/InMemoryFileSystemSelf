package com.machineCoding.InMemoryFileSystemSelf.models;

import java.util.Iterator;

public interface IDirectory extends IEntry {
    void deleteEntry(String name);
    File addFile(String name);
    Directory addDirectory(String name);
    Iterator<IEntry> getEntries();
}
